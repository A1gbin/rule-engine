package cn.algbin.expression;

import java.util.Objects;

public class EqualProcessor implements ExpressionProcessor {

    @Override
    public boolean process(String target, String value) {
        return Objects.equals(target, value);
    }
}
