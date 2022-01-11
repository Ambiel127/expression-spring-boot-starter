package com.mth.expression.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类
 *
 * @author MaTianHao
 * @date 2022/1/7
 */
@ConfigurationProperties(prefix = "expression")
public class ExpressEngineProperties {

    /**
     * 默认值 var
     */
    private String variable = "var";


    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

}
