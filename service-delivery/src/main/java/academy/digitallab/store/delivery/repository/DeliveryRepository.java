package academy.digitallab.store.delivery.repository;

import academy.digitallab.store.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    public List<Delivery> findByInvoiceId(Long invoiceId);

    public Delivery findByNumberDelivery(String numberDelivery);
}
