package oort.cloud.studyproject.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import oort.cloud.studyproject.DataConfig;
import oort.cloud.studyproject.order.Order;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class OrderRepository {
    @PersistenceContext
    private EntityManager em;
    public void save(Order order){
        em.persist(order);
    }
}
