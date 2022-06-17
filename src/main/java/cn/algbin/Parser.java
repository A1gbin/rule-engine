package cn.algbin;

import static cn.algbin.Constant.Relation.AND;
import static cn.algbin.Constant.Relation.OR;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static Expression parse(String expression) throws Exception {
        Expression result = new Expression();
        parse(expression, 0, result);
        return result;
    }


    // todo ! support
    public static int parse(String expression, int begin, Expression result) throws Exception {
        char[] expre = expression.toCharArray();
        List<Object> objectList = new ArrayList<>();
        result.objectList = objectList;
        for (int i = begin; i < expre.length; i++) {
            if (expre[i] == ' ') {
                continue;
            }
            if (expre[i] == '[') {
                i++;
                int count = 0;
                while (i < expre.length && expre[i] != ']') {
                    if (!Character.isDigit(expre[i])) {
                        throw new Exception("error format expression");
                    }
                    count *= 10;
                    count += expre[i] - '0';
                    i++;
                }
                objectList.add(count);
                continue;
            }
            if (expre[i] == ']') {
                throw new Exception("error format expression");
            }
            if (expre[i] == '(') {
                Expression experssion = new Expression();
                int parse = parse(expression, i + 1, experssion);
                objectList.add(experssion);
                i = parse + 1;
                if (i >= expre.length) {
                    return expre.length;
                }
                continue;
            }
            if (expre[i] == ')') {
                if (begin == 0) {
                    throw new Exception("error format expression");
                }
                return i;
            }
            if (i + 2 < expre.length && AND.equalsIgnoreCase(expression.substring(i, i + 3))) {
                checkPreviousElement(objectList);
                objectList.add(AND);
                i += 2;
                continue;
            }
            if (i + 1 < expre.length && OR.equalsIgnoreCase(expression.substring(i, i + 2))) {
                checkPreviousElement(objectList);
                objectList.add(OR);
                i++;
                continue;
            }
            throw new Exception("could not parse relation");
        }
        if (begin != 0) {
            throw new Exception("error format expression");
        }
        result.objectList = objectList;
        return expre.length;
    }

    private static void checkPreviousElement(List<Object> objectList) throws Exception {
        if (objectList.isEmpty() || objectList.get(objectList.size() - 1) instanceof String) {
            throw new Exception("error format expression");
        }
    }
}
