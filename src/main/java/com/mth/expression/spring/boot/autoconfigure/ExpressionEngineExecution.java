package com.mth.expression.spring.boot.autoconfigure;

import org.apache.commons.jexl3.*;

import java.util.*;

/**
 * @author MaTianHao
 */
public class ExpressionEngineExecution {

    private final ExpressEngineProperties properties;

    public ExpressionEngineExecution(ExpressEngineProperties properties) {
        this.properties = properties;
    }

    // 执行 expression 表达式，返回一个结果
    // ----------------------------------------

    /**
     * 执行 expression 表达式
     *
     * @param expression 字符串表达式
     * @return 表达式结果
     */
    public Object execExpression(String expression) {
        return execExpression(expression, new HashMap<>(0));
    }

    /**
     * 执行 expression 表达式
     *
     * @param expression 字符串表达式
     * @param arguments  变量数组
     * @return 表达式结果
     */
    public Object execExpression(String expression, Object... arguments) {
        HashMap<String, Object> paramMap = buildParamMap(Arrays.asList(arguments));
        return execExpression(expression, paramMap);
    }

    /**
     * 执行 expression 表达式
     *
     * @param expression 字符串表达式
     * @param arguments  变量集合
     * @return 表达式结果
     */
    public Object execExpression(String expression, List<Object> arguments) {
        HashMap<String, Object> paramMap = buildParamMap(arguments);
        return execExpression(expression, paramMap);
    }

    /**
     * 执行 expression 表达式
     *
     * @param expression 字符串表达式
     * @param arguments  变量Map
     * @return 表达式结果
     */
    public Object execExpression(String expression, Map<String, Object> arguments) {
        JexlContext jexlContext = createContext(arguments);
        JexlExpression jexlExpression = createExpression(expression);

        return jexlExpression.evaluate(jexlContext);
    }


    // 执行 script 表达式，返回一个结果
    // ----------------------------------------

    /**
     * 执行 script 脚本
     *
     * @param script 字符串脚本
     * @return 变量上下文
     */
    public Object execScript(String script) {
        return execScript(script, new HashMap<>(0));
    }

    /**
     * 执行 script 脚本
     *
     * @param script    字符串脚本
     * @param arguments 变量数组
     * @return 变量上下文
     */
    public Object execScript(String script, Object... arguments) {
        HashMap<String, Object> paramMap = buildParamMap(Arrays.asList(arguments));
        return execScript(script, paramMap);
    }

    /**
     * 执行 script 脚本
     *
     * @param script    字符串脚本
     * @param arguments 变量集合
     * @return 变量上下文
     */
    public Object execScript(String script, List<Object> arguments) {
        HashMap<String, Object> paramMap = buildParamMap(arguments);
        return execScript(script, paramMap);
    }

    /**
     * 执行 script 脚本
     *
     * @param script    字符串脚本
     * @param arguments 变量Map
     * @return 变量上下文
     */
    public Object execScript(String script, Map<String, Object> arguments) {
        JexlContext jexlContext = createContext(arguments);
        JexlScript jexlScript = createScript(script);

        return jexlScript.execute(jexlContext);
    }


    // 执行 script 表达式，返回 JexlContext 上下文
    // ----------------------------------------

    /**
     * 执行 script 脚本
     *
     * @param script 字符串脚本
     * @return 变量上下文
     */
    public JexlContext execScriptBackContext(String script) {
        return execScriptBackContext(script, new HashMap<>(0));
    }

    /**
     * 执行 script 脚本
     *
     * @param script    字符串脚本
     * @param arguments 变量数组
     * @return 变量上下文
     */
    public JexlContext execScriptBackContext(String script, Object... arguments) {
        HashMap<String, Object> paramMap = buildParamMap(Arrays.asList(arguments));
        return execScriptBackContext(script, paramMap);
    }

    /**
     * 执行 script 脚本
     *
     * @param script    字符串脚本
     * @param arguments 变量集合
     * @return 变量上下文
     */
    public JexlContext execScriptBackContext(String script, List<Object> arguments) {
        HashMap<String, Object> paramMap = buildParamMap(arguments);
        return execScriptBackContext(script, paramMap);
    }

    /**
     * 执行 script 脚本
     *
     * @param script    字符串脚本
     * @param arguments 变量Map
     * @return 变量上下文
     */
    public JexlContext execScriptBackContext(String script, Map<String, Object> arguments) {
        JexlContext jexlContext = createContext(arguments);
        JexlScript jexlScript = createScript(script);

        jexlScript.execute(jexlContext);
        return jexlContext;
    }


    /**
     * 创建变量上下文
     */
    private JexlContext createContext() {
        return createContext(null);
    }

    /**
     * 创建变量上下文并初始化变量
     */
    private JexlContext createContext(Map<String, Object> arguments) {
        JexlContext jexlContext = new MapContext();
        if (Objects.nonNull(arguments)) {
            arguments.forEach(jexlContext::set);
        }
        return jexlContext;
    }

    /**
     * 创建 expression 表达式解析器
     */
    private JexlExpression createExpression(String expression) {
        JexlBuilder builder = new JexlBuilder();
        JexlEngine engine = builder.create();
        return engine.createExpression(expression);
    }

    /**
     * 创建 script 表达式解析器
     */
    private JexlScript createScript(String script) {
        JexlBuilder builder = new JexlBuilder();
        JexlEngine engine = builder.create();
        return engine.createScript(script);
    }

    /**
     * 根据默认变量名构建参数Map
     */
    private HashMap<String, Object> buildParamMap(List<Object> arguments) {
        if (Objects.isNull(arguments)) {
            return null;
        }
        HashMap<String, Object> paramMap = new HashMap<>(arguments.size());
        for (int i = 0, argumentsLength = arguments.size(); i < argumentsLength; i++) {
            Object item = arguments.get(i);
            int paramIndex = i + 1;
            paramMap.put(properties.getVariable() + paramIndex, item);
        }
        return paramMap;
    }

}
