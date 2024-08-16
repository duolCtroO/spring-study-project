package oort.cloud.studyproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import oort.cloud.studyproject.data.OrderRepository;
import oort.cloud.studyproject.order.Order;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory ac = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = ac.getBean(OrderRepository.class);
        JpaTransactionManager jpaTransactionManager = ac.getBean(JpaTransactionManager.class);
        //transaction begin
        try {
            new TransactionTemplate(jpaTransactionManager).execute(status -> {
                Order order = new Order("100", BigDecimal.TEN);
                repository.save(order);

                System.out.println(order);

                Order order2 = new Order("100", BigDecimal.TEN);
                repository.save(order2);
               return null;
            });
        }
        catch (DataIntegrityViolationException e){
            System.out.println("Order recovery");

        }
    }
}
