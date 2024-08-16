package oort.cloud.studyproject.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void paymentTest(){
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        Payment payment = Payment.prepare(1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1000), LocalDateTime.now(clock));

        Assertions.assertThat(payment.getConvertedAmount()).isEqualTo(BigDecimal.valueOf(10000));
        Assertions.assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }

}