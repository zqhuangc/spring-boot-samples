package com.melody.opensource.springbootwebservicedemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Web Services 配置类
 *
 * @author zqhuangc
 * @see WsConfigurerAdapter
 * @see WebMvcConfigurerAdapter
 */
@Configuration
public class WebServicesConfiguration extends WsConfigurerAdapter {


    @Bean("user")
    @Autowired
    public Wsdl11Definition userWsdl11Definition(XsdSchema userXsdSchema) {
        DefaultWsdl11Definition userWsdl11Definition = new DefaultWsdl11Definition();

        userWsdl11Definition.setPortTypeName("UserServicePort");
        userWsdl11Definition.setLocationUri("/web-services");
        userWsdl11Definition.setTargetNamespace("http://melody.com/schemas");
        userWsdl11Definition.setSchema(userXsdSchema);

        return userWsdl11Definition;
    }


    @Bean
    public XsdSchema userXsdSchema() {
        return new SimpleXsdSchema(new ClassPathResource("META-INF/schemas/user.xsd"));
    }

}
