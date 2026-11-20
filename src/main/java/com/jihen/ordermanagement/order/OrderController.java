package com.jihen.ordermanagement.order;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
/**
 * REST controller exposing order creation and retrieval endpoints under
 * {@code /api/v1/orders}.
 */
public class OrderController {
    private final OrderService service;

    /**
     * Constructs the controller with its application service.
     *
     * @param service service handling order operations
     */
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    /**
     * Creates an order and returns HTTP 201 with a Location header.
     *
     * @param request order creation payload
     * @return the created order and URI identifying the new resource
     */
    public ResponseEntity<Order> create(@RequestBody CreateOrderRequest request) {
        Order order = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/orders/" + order.id())).body(order);
    }

    @GetMapping("/{id}")
    /**
     * Retrieves an order, returning HTTP 404 when no order matches.
     *
     * @param id identifier of the requested order
     * @return HTTP response containing the order or a not-found status
     */
    public ResponseEntity<Order> findById(@PathVariable UUID id) {
        return ResponseEntity.of(service.findById(id));
    }

    @GetMapping
    /**
     * Lists all orders currently stored by the application.
     *
     * @return all available orders
     */
    public List<Order> findAll() {
        return service.findAll();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    /**
     * Converts order validation failures into HTTP 400 responses.
     *
     * @param ex validation exception raised by the service
     * @return an error response describing the invalid request
     */
    public ResponseEntity<Map<String, String>> invalidOrder(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
    }
}
