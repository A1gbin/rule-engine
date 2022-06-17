package cn.algbin;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

class ValidProcessorTest {

    @Test
    void test() throws Exception {
        String json = new String(ValidProcessor.class.getClassLoader().getResourceAsStream("json.json").readAllBytes());
        String expression = "([1] and [2] or ([3] and [4] and ([5] or[6])))";
        HashMap<Integer, Element> expressionMap = new HashMap<>();
        Expression parse = Parser.parse(expression);
        ValidProcessor validProcessor = new ValidProcessor(json);
        expressionMap.put(1, new Element("$['book'][0]['title']", "=", "Beginning JSON"));
        expressionMap.put(2, new Element("$['book'][0]['author']", "=", "Ben Smith"));
        expressionMap.put(3, new Element("$['book'][1]['title']", "=", "Beginning JSON"));
        expressionMap.put(4, new Element("$['book'][0]['title']", "=", "Beginning JSON"));
        expressionMap.put(5, new Element("$['book'][0]['title']", "=", "Beginning JSON"));
        expressionMap.put(6, new Element("$['book'][0]['title']", "=", "Beginning JSON"));
        boolean valid = validProcessor.isValid(expressionMap, parse);
        assertTrue(valid);
    }
}