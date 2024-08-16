package oort.cloud.studyproject;

import oort.cloud.studyproject.payment.Payment;
import oort.cloud.studyproject.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment1);
        System.out.println("--------------------------------------------------------");
    }
}
