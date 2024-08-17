package oort.cloud.studyproject.order;

import oort.cloud.studyproject.OrderConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceImplSpringTest {
    @Autowired
    OrderService orderService;
    @Autowired
    DataSource dataSource;

    @Test
    void createOrder(){
        var order = orderService.createOrder("100", BigDecimal.TEN);

        assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    void createOrders(){
        List<OrderReq> orderReqs = List.of(new OrderReq("100", BigDecimal.ONE), new OrderReq("200", BigDecimal.TWO));

        List<Order> orders = orderService.createOrders(orderReqs);
        Assertions.assertThat(orders).hasSize(2);
        orders.forEach(order -> {
            System.out.println(order);
        });
    }

    @Test
    void createDuplicatedOrders(){
        List<OrderReq> orderReqs = List.of(
                new OrderReq("200", BigDecimal.ONE)
                , new OrderReq("200", BigDecimal.TWO)
        );

        Assertions.assertThatThrownBy(() -> orderService.createOrders(orderReqs)).isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient jdbcClient = JdbcClient.create(dataSource);
        Long single = jdbcClient.sql("select count(*) from orders where no = '200'").query(Long.class).single();
        assertThat(single).isEqualTo(0);
    }
}
