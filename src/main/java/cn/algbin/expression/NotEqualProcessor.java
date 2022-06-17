package cn.algbin.expression;

public class NotEqualProcessor implements ExpressionProcessor {

    @Override
    public boolean process(String target, String value) {
        return !new EqualProcessor().process(target, value);
    }
}
