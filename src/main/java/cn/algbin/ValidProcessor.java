package cn.algbin;


import static cn.algbin.Constant.Relation.AND;
import static cn.algbin.Constant.Relation.OR;

import cn.algbin.expression.ExpressionFactory;
import cn.algbin.expression.ExpressionProcessor;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.util.List;
import java.util.Map;

public class ValidProcessor {

    public ValidProcessor(String json) {
        this.json = json;
    }

    private final String json;


    public boolean isValid(Map<Integer, Element> elementMap, Expression expression) throws Exception {
        List<Object> objectList = expression.objectList;
        Boolean isValid = null;
        String lastExpression = null;
        for (Object o : objectList) {
            if (o instanceof Integer i) {
                boolean valid = calculate(elementMap.get(i));
                if (isValid == null) {
                    isValid = valid;
                } else {
                    if ("and".equals(lastExpression)) {
                        isValid &= valid;
                    } else {
                        isValid |= valid;
                    }
                }
            }
            if (o instanceof String s) {
                if (AND.equalsIgnoreCase(s) || OR.equalsIgnoreCase(s)) {
                    lastExpression = s;
                } else {
                    throw new Exception("could not process" + s);
                }
            }
            if (o instanceof Expression sub) {
                boolean valid = isValid(elementMap, sub);
                if (isValid == null) {
                    isValid = valid;
                } else {
                    if ("and".equals(lastExpression)) {
                        isValid &= valid;
                    } else {
                        isValid |= valid;
                    }
                }
            }
        }
        return Boolean.TRUE.equals(isValid);
    }

    private boolean calculate(Element element) throws Exception {
        DocumentContext context = JsonPath.parse(json);
        String read = context.read(element.getJsonPath());
        ExpressionProcessor processor = ExpressionFactory.getProcessor(element.getExpression());
        if (processor == null) {
            throw new Exception("could not process " + element.getExpression());
        }
        return processor.process(read, element.getValue());
    }


}
