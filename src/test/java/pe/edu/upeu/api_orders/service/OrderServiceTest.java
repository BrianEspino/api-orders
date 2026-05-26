package pe.edu.upeu.api_orders.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upeu.api_orders.model.Order;
import pe.edu.upeu.api_orders.repository.OrderRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrder_WithAmountGreaterThan1000_ShouldApplyDiscount() {
        Order order = new Order();
        order.setAmount(1200.0);

        Order savedOrder = new Order();
        savedOrder.setAmount(1080.0);

        when(repository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order result = orderService.createOrder(order);

        assertEquals(1080.0, result.getAmount());
        verify(repository, times(1)).save(order);
    }

    @Test
    void createOrder_WithAmountLessOrEqualThan1000_ShouldNotApplyDiscount() {
        Order order = new Order();
        order.setAmount(800.0);

        when(repository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order result = orderService.createOrder(order);

        assertEquals(800.0, result.getAmount());
        verify(repository, times(1)).save(order);
    }

    @Test
    void getAllOrders_ShouldReturnOrderList() {
        Order order1 = new Order();
        order1.setAmount(500.0);

        Order order2 = new Order();
        order2.setAmount(1500.0);

        when(repository.findAll()).thenReturn(List.of(order1, order2));

        List<Order> result = orderService.getAllOrders();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }
}