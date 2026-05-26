package pe.edu.upeu.api_orders.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import pe.edu.upeu.api_orders.model.Order;
import pe.edu.upeu.api_orders.service.OrderService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService service;

    @InjectMocks
    private OrderController controller;

    @Test
    void save_ShouldReturnCreatedOrder() {

        Order order = new Order(null,"Hector",1200.0);
        Order saved = new Order(1L,"Hector",1080.0);

        when(service.createOrder(order)).thenReturn(saved);

        ResponseEntity<Order> response = controller.save(order);

        assertEquals(201,response.getStatusCode().value());
        assertEquals("Hector",response.getBody().getCustomer());

        verify(service,times(1)).createOrder(order);
    }

    @Test
    void list_ShouldReturnOrders() {

        List<Order> orders=List.of(
                new Order(1L,"Hector",500.0),
                new Order(2L,"Brian",1500.0)
        );

        when(service.getAllOrders()).thenReturn(orders);

        List<Order> result=controller.list();

        assertEquals(2,result.size());

        verify(service,times(1)).getAllOrders();
    }
}