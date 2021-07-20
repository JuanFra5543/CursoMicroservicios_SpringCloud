package academy.digitallab.store.delivery.model;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Invoice {

    private Long id;

    private String numberInvoice;

    private String description;

    private Long customerId;

    private Date createAt;

    private List<InvoiceItem> items;

    private String state;
}
