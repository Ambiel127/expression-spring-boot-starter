package com.mth.expression.spring.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类
 *
 * @author MaTianHao
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(ExpressEngineProperties.class)
public class ExpressionEngineAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ExpressionEngineExecution expressionEngineExecution(ExpressEngineProperties properties) {
        return new ExpressionEngineExecution(properties);
    }

}
