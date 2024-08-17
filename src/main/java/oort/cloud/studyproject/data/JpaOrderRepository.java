package oort.cloud.studyproject.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import oort.cloud.studyproject.order.Order;
import oort.cloud.studyproject.order.OrderRepository;

public class JpaOrderRepository implements OrderRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Order order){
        em.persist(order);
    }
}
