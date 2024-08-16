package oort.cloud.studyproject.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

public interface ExRateExtractor {
    BigDecimal extract(String res) throws JsonProcessingException;
}
