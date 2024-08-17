package oort.cloud.studyproject;

import oort.cloud.studyproject.order.Order;
import oort.cloud.studyproject.order.OrderService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class OrderClient {
    public static void main(String[] args) {
        BeanFactory ac = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService service = ac.getBean(OrderService.class);

        Order order = service.createOrder("100", BigDecimal.TEN);
        System.out.println(order);
    }
}
