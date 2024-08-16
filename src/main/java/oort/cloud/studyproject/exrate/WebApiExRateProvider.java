package oort.cloud.studyproject.exrate;

import oort.cloud.studyproject.api.*;
import oort.cloud.studyproject.payment.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExRateProvider implements ExRateProvider {
    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency){
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.getExRate(url);
    }

}
