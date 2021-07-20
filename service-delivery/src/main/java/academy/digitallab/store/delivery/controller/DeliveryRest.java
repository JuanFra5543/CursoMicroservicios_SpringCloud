package academy.digitallab.store.delivery.controller;

import academy.digitallab.store.delivery.service.DeliveryService;
import academy.digitallab.store.delivery.entity.Delivery;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/delivery")
public class DeliveryRest {

    @Autowired
    DeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<List<Delivery>> listAllDeliveries() {
        List<Delivery> delivery = deliveryService.findDeliveryAll();
        if (delivery.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(delivery);
    }

    // -------------------Retrieve Single
    @GetMapping(value = "/{id}")
    public ResponseEntity<Delivery> getDelivery(@PathVariable("id") long id) {
        log.info("Fetching Delivery with id {}", id);
        Delivery delivery = deliveryService.getDelivery(id);
        if (null == delivery) {
            log.error("Deliverywith id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(delivery);
    }

    // -------------------Create a
    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@Valid @RequestBody Delivery delivery, BindingResult result) {
        log.info("Creating Delivery : {}", delivery);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Delivery deliveryDB = deliveryService.createDelivery(delivery);

        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryDB);
    }

    // ------------------- Update a Delivery
    // ------------------------------------------------
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateDelivery(@PathVariable("id") long id, @RequestBody Delivery delivery) {
        log.info("Updating Delivery with id {}", id);

        delivery.setId(id);
        Delivery currentDelivery = deliveryService.updateDelivery(delivery);

        if (currentDelivery == null) {
            log.error("Unable to update. Delivery with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(currentDelivery);
    }

    // ------------------- Delete a
    // Delivery-----------------------------------------
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Delivery> deleteDelivery(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Delivery with id {}", id);

        Delivery delivery = deliveryService.getDelivery(id);
        if (delivery == null) {
            log.error("Unable to delete. Delivery with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        delivery = deliveryService.deleteDelivery(delivery);
        return ResponseEntity.ok(delivery);
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream().map(err -> {
            Map<String, String> error = new HashMap<>();
            error.put(err.getField(), err.getDefaultMessage());
            return error;

        }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder().code("01").messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
