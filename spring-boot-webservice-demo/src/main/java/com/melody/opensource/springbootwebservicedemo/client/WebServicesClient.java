package com.melody.opensource.springbootwebservicedemo.client;

import com.melody.opensource.springbootwebservicedemo.domain.User;
import com.melody.opensource.springbootwebservicedemo.domain.UserIdRequest;
import com.melody.opensource.springbootwebservicedemo.domain.UserResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.time.Instant;

/**
 * Web Services 客户端（Spring 实现）
 *
 * @author zqhuangc
 */
public class WebServicesClient {

    public static void main(String[] args) {

        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();

        jaxb2Marshaller.setClassesToBeBound(UserIdRequest.class, UserResponse.class, User.class);

        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller);

        //构造 SOAP 请求
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(1L);
        userIdRequest.setTimestamp(Instant.now().toEpochMilli());

        // http://localhost:8080/melody/user
        UserResponse userResponse = (UserResponse) webServiceTemplate.marshalSendAndReceive("http://localhost:8080/melody/web-services/user", userIdRequest);

        System.out.println(userResponse);

    }

}
