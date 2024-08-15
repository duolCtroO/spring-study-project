package oort.cloud.studyproject.exrate;

import oort.cloud.studyproject.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExProvider implements ExRateProvider {

    private final ExRateProvider exRateProvider;
    private BigDecimal cachedExRate;
    private LocalDateTime cachedExpiryTime;

    public CachedExProvider(ExRateProvider exRateProvider) {
        this.exRateProvider = exRateProvider;
    }

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if(cachedExRate == null || cachedExpiryTime.isBefore(LocalDateTime.now())){
            cachedExRate = this.exRateProvider.getExRate(currency);
            cachedExpiryTime = LocalDateTime.now().plusSeconds(3);
            System.out.println("Cache Update");
        }
        return cachedExRate;
    }
}
