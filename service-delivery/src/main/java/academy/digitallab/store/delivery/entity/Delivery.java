package academy.digitallab.store.delivery.entity;

import academy.digitallab.store.delivery.model.Invoice;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tlb_delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_delivery")
    private String numberDelivery;

    @Column(name = "direction_delivery")
    private String directionDelivery;

    @Column(name = "invoice_id")
    private Long invoiceId;

    @Transient
    private Invoice invoice;

}
