package dev.arena.order.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.arena.order.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name= "customer_order")
public class Order extends BaseEntity {

    private String customerId;
    private String reference;
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<OrderLine> orderLines;
}
