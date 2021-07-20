package academy.digitallab.store.delivery.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.digitallab.store.delivery.client.InvoiceClient;
import academy.digitallab.store.delivery.entity.Delivery;
import academy.digitallab.store.delivery.model.Invoice;
import academy.digitallab.store.delivery.repository.DeliveryRepository;

import java.util.List;

@Slf4j
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    InvoiceClient invoiceClient;

    @Override
    public List<Delivery> findDeliveryAll() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery createDelivery(Delivery delivery) {
        Delivery deliveryDB = deliveryRepository.findByNumberDelivery(delivery.getNumberDelivery());
        if (deliveryDB != null) {
            return deliveryDB;
        }
        deliveryDB = deliveryRepository.save(delivery);

        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery updateDelivery(Delivery delivery) {
        Delivery deliveryDB = getDelivery(delivery.getId());
        if (deliveryDB == null) {
            return null;
        }
        deliveryDB.setNumberDelivery(delivery.getNumberDelivery());
        deliveryDB.setDirectionDelivery(delivery.getDirectionDelivery());
        deliveryDB.setInvoiceId(delivery.getInvoiceId());
        return deliveryRepository.save(deliveryDB);
    }

    @Override
    public Delivery deleteDelivery(Delivery delivery) {
        Delivery deliveryDB = getDelivery(delivery.getId());
        if (deliveryDB == null) {
            return null;
        }
        return deliveryRepository.save(deliveryDB);
    }

    @Override
    public Delivery getDelivery(Long id) {

        Delivery delivery = deliveryRepository.findById(id).orElse(null);

        if (null != delivery) {
            Invoice invoice = invoiceClient.getInvoice(delivery.getInvoiceId()).getBody();
            delivery.setInvoice(invoice);
        }

        return delivery;
    }
}
