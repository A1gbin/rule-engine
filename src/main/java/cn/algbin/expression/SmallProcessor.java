package cn.algbin.expression;

import java.math.BigDecimal;

public class SmallProcessor implements ExpressionProcessor {

    @Override
    public boolean process(String target, String value) {
        BigDecimal t = new BigDecimal(target);
        BigDecimal v = new BigDecimal(value);
        return t.compareTo(v) < 0;
    }
}
