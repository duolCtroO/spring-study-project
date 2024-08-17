package oort.cloud.studyproject;

import oort.cloud.studyproject.data.JdbcOrderRepository;
import oort.cloud.studyproject.data.JpaOrderRepository;
import oort.cloud.studyproject.order.OrderRepository;
import oort.cloud.studyproject.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {
    @Bean
    public OrderRepository orderRepository(DataSource dataSource){
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(PlatformTransactionManager transactionManager
    ,OrderRepository orderRepository) {
        return new OrderService(orderRepository, transactionManager);
    }
}
