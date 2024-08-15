package oort.cloud.studyproject;

import oort.cloud.studyproject.exrate.CachedExProvider;
import oort.cloud.studyproject.payment.ExRateProvider;
import oort.cloud.studyproject.exrate.WebApiExRateProvider;
import oort.cloud.studyproject.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new WebApiExRateProvider();
    }

    @Bean
    public ExRateProvider cachedExRateProvider(){
        return new CachedExProvider(exRateProvider());
    }
}
