package cn.algbin.expression;

import java.util.HashMap;
import java.util.Map;

public class ExpressionFactory {

    private static Map<String, ExpressionProcessor> map = new HashMap<>();

    static {
        map.put("=", new EqualProcessor());
        map.put("!=", new NotEqualProcessor());
        map.put(">", new LargeProcessor());
        map.put("<", new SmallProcessor());
        map.put("like", new LikeProcessor());
        map.put("regex", new RegexProcessor());
    }

    public static ExpressionProcessor getProcessor(String key) {
        return map.get(key);
    }
}
