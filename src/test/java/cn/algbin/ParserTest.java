package cn.algbin;

import static cn.algbin.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParserTest {

    @ParameterizedTest
    @CsvSource(value = {
            "([1] and [2] or ([3] and [4] and ([5] or[6]))),1",
            "1,0,false",
    })
    void test(String expression, Integer size) throws Exception {
        Expression element = new Expression();
        parse("([1] and [2] or ([3] and [4] and ([5] or[6])))", 0, element);
        assertEquals(1, element.objectList.size());
    }


}