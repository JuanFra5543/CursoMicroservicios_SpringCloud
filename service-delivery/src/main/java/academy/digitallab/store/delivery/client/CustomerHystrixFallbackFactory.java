package academy.digitallab.store.delivery.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import academy.digitallab.store.delivery.model.Invoice;

@Component
public class CustomerHystrixFallbackFactory implements InvoiceClient {

    @Override
    public ResponseEntity<Invoice> getInvoice(Long id) {
        Invoice invoice = Invoice.builder().numberInvoice("none").description("none").customerId((long) 0).build();
        return ResponseEntity.ok(invoice);
    }

}
