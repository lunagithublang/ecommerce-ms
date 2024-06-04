package dev.arena.order.services.order;

import dev.arena.common.constants.CommonConstants;
import dev.arena.common.dto.PageResponseDTO;
import dev.arena.common.exceptions.NotFoundEntityException;
import dev.arena.common.utils.PageableUtils;
import dev.arena.common.utils.PaginationUtils;
import dev.arena.order.apis.CustomerClient;
import dev.arena.order.apis.PaymentClient;
import dev.arena.order.apis.ProductClient;
import dev.arena.order.apis.ProductClientRestTemplate;
import dev.arena.order.constants.Constants;
import dev.arena.order.dto.customer.CustomerResponseDTO;
import dev.arena.order.dto.order.OrderRequestDTO;
import dev.arena.order.dto.order.OrderResponseDTO;
import dev.arena.order.dto.orderline.OrderLineRequestDTO;
import dev.arena.order.dto.payment.PaymentRequestDTO;
import dev.arena.order.dto.product.PurchaseProductRequestDTO;
import dev.arena.order.dto.product.PurchaseProductResponseDTO;
import dev.arena.order.entities.Order;
import dev.arena.order.entities.OrderLine;
import dev.arena.order.producer.OrderConfirmation;
import dev.arena.order.producer.OrderProducer;
import dev.arena.order.mappers.CustomerMapper;
import dev.arena.order.mappers.OrderMapper;
import dev.arena.order.respositories.OrderRepository;
import dev.arena.order.services.orderline.OrderLineService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;
    private final CustomerClient customerClient;
    private final PaymentClient paymentClient;
    private final ProductClient productClient;
    private final ProductClientRestTemplate productClientRestTemplate;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;


    @Override
    @Transactional
    public OrderResponseDTO saveOrder(@Valid OrderRequestDTO orderRequestDTO) {

        // Check customer
        CustomerResponseDTO customerResponseDTO = customerClient
                .findCustomerById(orderRequestDTO.customerId())
                .orElseThrow(() -> new NotFoundEntityException("Costumer not found with ID " + orderRequestDTO.customerId()));

        // Purchase the product using product-service -> /purchase
        List<PurchaseProductResponseDTO> purchasedProducts = productClientRestTemplate.purchaseProducts(orderRequestDTO.products());

        // Persist order
        Order order = orderRepository.save(orderMapper.requestDTOToOrder(orderRequestDTO));

        List<OrderLine> orderLines = new ArrayList<>();

        // Persist order lines
        for (PurchaseProductRequestDTO purchaseProduct : orderRequestDTO.products()) {

            productClient.findProductById(purchaseProduct.productId());

            OrderLine orderLine = orderLineService.saveOrderLine(
                    new OrderLineRequestDTO(
                        order,
                        purchaseProduct.quantity(),
                        purchaseProduct.productId()
                    )
            );
            orderLines.add(orderLine);
        }

        order.setOrderLines(orderLines);

        // Start payment process
        paymentClient.requestOrderPayment(
                new PaymentRequestDTO(
                        orderRequestDTO.totalAmount(),
                        orderRequestDTO.paymentMethod(),
                        order.getId(),
                        orderRequestDTO.reference(),
                        customerResponseDTO
                )
        );

        // Send order confirmation - notification service(producer)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequestDTO.reference(),
                        orderRequestDTO.totalAmount(),
                        orderRequestDTO.paymentMethod(),
                        customerResponseDTO,
                        purchasedProducts
                )
        );

        return orderMapper.orderResponseDTO(order);
    }

    @Override
    @Transactional
    public Boolean deletedById(UUID resourceId) {
        System.out.println(resourceId);
        if (!orderRepository.existsById(resourceId)) {
            return false;
        }

        orderRepository.deleteById(resourceId);

        return true;
    }

    @Override
    public PageResponseDTO<OrderResponseDTO> findAll(int page, int size, UriComponentsBuilder uriComponentsBuilder) {

        uriComponentsBuilder.path(Constants.ORDERS_URL);

        Pageable pageable = PageableUtils.setPageable(page, size, CommonConstants.CREATED_AT);

        Page<Order> orderPage = orderRepository.findAll(pageable);

        return PaginationUtils.createPageResponse(orderPage, orderMapper::orderResponseDTO, uriComponentsBuilder);
    }
}
