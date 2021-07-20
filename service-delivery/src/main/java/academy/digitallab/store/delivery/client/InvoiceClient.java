package academy.digitallab.store.delivery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import academy.digitallab.store.delivery.model.Invoice;

@FeignClient(name = "service-shopping", fallback = CustomerHystrixFallbackFactory.class)
public interface InvoiceClient {

    @GetMapping(value = "/invoices/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable("id") Long id);

}
