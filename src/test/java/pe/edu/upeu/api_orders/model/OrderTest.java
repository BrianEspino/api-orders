package pe.edu.upeu.api_orders.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void noArgsConstructorTest() {

        Order order=new Order();

        order.setId(1L);
        order.setCustomer("Hector");
        order.setAmount(1000.0);

        assertEquals(1L,order.getId());
        assertEquals("Hector",order.getCustomer());
        assertEquals(1000.0,order.getAmount());
    }

    @Test
    void allArgsConstructorTest() {

        Order order=
                new Order(
                        2L,
                        "Brian",
                        1500.0
                );

        assertEquals(2L,order.getId());
        assertEquals("Brian",order.getCustomer());
        assertEquals(1500.0,order.getAmount());
    }
}