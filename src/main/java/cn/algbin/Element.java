package cn.algbin;

public class Element {

    public Element(String element, String expression, String value) {
        this.jsonPath = element;
        this.expression = expression;
        this.value = value;
    }

    private final String jsonPath;
    private final String expression;
    private final String value;

    public String getJsonPath() {
        return jsonPath;
    }

    public String getExpression() {
        return expression;
    }

    public String getValue() {
        return value;
    }
}
