package academy.digitallab.store.delivery.service;

import academy.digitallab.store.delivery.entity.Delivery;

import java.util.List;

public interface DeliveryService {
    public List<Delivery> findDeliveryAll();

    public Delivery createDelivery(Delivery delivery);

    public Delivery updateDelivery(Delivery delivery);

    public Delivery deleteDelivery(Delivery delivery);

    public Delivery getDelivery(Long id);
}
