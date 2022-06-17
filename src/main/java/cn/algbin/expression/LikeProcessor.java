package cn.algbin.expression;

public class LikeProcessor implements ExpressionProcessor {

    @Override
    public boolean process(String target, String value) {
        // "\\" is escaped to "\"
        value = value.replace(".", "\\.");
        // ... escape any other potentially problematic characters here
        value = value.replace("?", ".");
        value = value.replace("%", ".*");
        return target.matches(value);
    }
}
