package oort.cloud.studyproject.exrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import oort.cloud.studyproject.payment.ExRateProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class WebApiExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String res = br.lines().collect(Collectors.joining());
        br.close();

        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(res, ExRateData.class);
        System.out.println("API ExRate : " + data.rates().get("KRW"));

        return data.rates().get("KRW");
    }
}
