package oort.cloud.studyproject.order;

import java.math.BigDecimal;

public record OrderReq(String no, BigDecimal total) {
}
