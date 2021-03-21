package br.dev.jfr.pdv.checkout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes= @Index(name="checkoutitem_n1", columnList = "checkout_id", unique=false) )
public class CheckoutItem {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "checkout_id")
    private Checkout checkout;

}
