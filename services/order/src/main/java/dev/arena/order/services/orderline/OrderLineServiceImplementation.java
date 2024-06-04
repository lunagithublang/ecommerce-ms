package dev.arena.order.services.orderline;

import dev.arena.common.constants.CommonConstants;
import dev.arena.common.dto.PageResponseDTO;
import dev.arena.common.utils.PageableUtils;
import dev.arena.common.utils.PaginationUtils;
import dev.arena.order.constants.Constants;
import dev.arena.order.dto.orderline.OrderLineRequestDTO;
import dev.arena.order.dto.orderline.OrderLineResponseDTO;
import dev.arena.order.entities.OrderLine;
import dev.arena.order.mappers.OrderLineMapper;
import dev.arena.order.respositories.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.data.domain.Pageable;


@Service
@RequiredArgsConstructor
public class OrderLineServiceImplementation implements OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;


    @Override
    public PageResponseDTO<OrderLineResponseDTO> findAll(int page, int size, UriComponentsBuilder uriComponentsBuilder) {
        uriComponentsBuilder.path(Constants.ORDER_LINE_URL);

        Pageable pageable = PageableUtils.setPageable(page, size, CommonConstants.CREATED_AT);

        Page<OrderLine> orderLinePage = orderLineRepository.findAll(pageable);

        return PaginationUtils.createPageResponse(orderLinePage, orderLineMapper::orderLineResponseDTO, uriComponentsBuilder);
    }

    @Override
    public OrderLine saveOrderLine(OrderLineRequestDTO orderLineRequestDTO) {

        OrderLine orderLine = OrderLine.builder()
                .order(orderLineRequestDTO.order())
                .productId(orderLineRequestDTO.productId())
                .quantity(orderLineRequestDTO.quality())
                .build();

        return orderLineRepository.save(orderLine);
    }
}
