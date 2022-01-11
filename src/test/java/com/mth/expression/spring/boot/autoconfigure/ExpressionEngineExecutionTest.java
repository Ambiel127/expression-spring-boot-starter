package com.mth.expression.spring.boot.autoconfigure;

import org.apache.commons.jexl3.JexlContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;


class ExpressionEngineExecutionTest {

    private ExpressionEngineExecution execution = new ExpressionEngineExecution(new ExpressEngineProperties());


    @Test
    void execExpression() {
        String expression = "200 + 2 * 300";
        Object o = execution.execExpression(expression);
        Assertions.assertEquals(o, 800);
    }

    @Test
    void testExecExpression() {
        String expression = "var1 + 2 * var2";
        Object o = execution.execExpression(expression, 200, 300);
        Assertions.assertEquals(o, 800);
    }

    @Test
    void testExecExpression1() {
        String expression = "var1 + 2 * var2";
        Object o = execution.execExpression(expression, Arrays.asList(200, 300));
        Assertions.assertEquals(o, 800);

        String expression1 = "200 + 2 * 300";
        Object o1 = execution.execExpression(expression1, (List<Object>) null);
        Assertions.assertEquals(o1, 800);
    }

    @Test
    void testExecExpression2() {
        String expression = "v1 + 2 * v2";
        HashMap<String, Object> paramMap = new HashMap<String, Object>() {{
            put("v1", 200);
            put("v2", 300);
        }};
        Object o = execution.execExpression(expression, paramMap);
        Assertions.assertEquals(o, 800);

        String expression1 = "200 + 2 * 300";
        Object o1 = execution.execExpression(expression1, (Map<String, Object>) null);
        Assertions.assertEquals(o1, 800);
    }

    @Test
    void expressionIsArray() {
        String expression = "array.size()";
        List<Object> list = new ArrayList<>();
        list.add("this is an array");
        list.add(2);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("array", list);
        Object o = execution.execExpression(expression, paramMap);
        Assertions.assertEquals(o, 2);
    }

    @Test
    void execScript() {
        String script = "if(2 > 1){age = 23;name = 'name1';}";
        Object o = execution.execScript(script);
        Assertions.assertEquals(o, "name1");
    }

    @Test
    void execScript1() {
        String script = "if(var1 > 1){age = 23;name = 'name1';}";
        Object o = execution.execScript(script, 2);
        Assertions.assertEquals(o, "name1");
    }

    @Test
    void execScript2() {
        String script = "if(var1 > 1){age = 23;name = 'name1';}";
        Object o = execution.execScript(script, Arrays.asList(2));
        Assertions.assertEquals(o, "name1");
    }

    @Test
    void execScript3() {
        String script = "if(v1 > 1){age = 23;name = 'name1';}";
        Map<String, Object> paramMap = new HashMap<String, Object>(){{
            put("v1", 2);
        }};
        Object o = execution.execScript(script, paramMap);
        Assertions.assertEquals(o, "name1");
    }

    @Test
    void execScriptBackContext() {
        String script = "if(2 > 1){age = 23;name = 'name1';}";
        JexlContext jexlContext = execution.execScriptBackContext(script);
        Assertions.assertEquals(jexlContext.get("name"), "name1");
        Assertions.assertEquals(jexlContext.get("age"), 23);
    }

    @Test
    void execScriptBackContext1() {
        String script = "if(var1 > 1){age = 23;name = 'name1';}";
        JexlContext jexlContext = execution.execScriptBackContext(script, 2);
        Assertions.assertEquals(jexlContext.get("name"), "name1");
        Assertions.assertEquals(jexlContext.get("age"), 23);
    }

    @Test
    void execScriptBackContext2() {
        String script = "if(var1 > 1){age = 23;name = 'name1';}";
        JexlContext jexlContext = execution.execScriptBackContext(script, Arrays.asList(2));
        Assertions.assertEquals(jexlContext.get("name"), "name1");
        Assertions.assertEquals(jexlContext.get("age"), 23);
    }

    @Test
    void execScriptBackContext3() {
        String script = "if(v1 > 1){age = 23;name = 'name1';}";
        Map<String, Object> paramMap = new HashMap<String, Object>(){{
            put("v1", 2);
        }};
        JexlContext jexlContext = execution.execScriptBackContext(script, paramMap);
        Assertions.assertEquals(jexlContext.get("name"), "name1");
        Assertions.assertEquals(jexlContext.get("age"), 23);
    }

}