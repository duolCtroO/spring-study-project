package oort.cloud.studyproject;

import oort.cloud.studyproject.api.ApiTemplate;
import oort.cloud.studyproject.exrate.CachedExProvider;
import oort.cloud.studyproject.exrate.RestTemplateExRateProvider;
import oort.cloud.studyproject.payment.ExRateProvider;
import oort.cloud.studyproject.exrate.WebApiExRateProvider;
import oort.cloud.studyproject.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
@ComponentScan
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new JdkClientHttpRequestFactory());
    }

    @Bean
    public ExRateProvider cachedExRateProvider(){
        return new CachedExProvider(exRateProvider());
    }

    @Bean
    public ApiTemplate apiTemplate(){
        return new ApiTemplate();
    }
}
