package academy.digitallab.store.delivery.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoiceItem {

    private Long id;

    private Double quantity;

    private Double price;

    private Long productId;

    private Double subTotal;

}
