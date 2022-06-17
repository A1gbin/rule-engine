package cn.algbin.expression;

import java.util.regex.Pattern;

public class RegexProcessor implements ExpressionProcessor {

    @Override
    public boolean process(String target, String value) {
        Pattern compile = Pattern.compile(value);
        return compile.matcher(target).matches();
    }
}
