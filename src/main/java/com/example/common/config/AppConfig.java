package com.example.common.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class AppConfig {

    /**
     * 메시지 프러퍼티 빈(classpath:messages/front_messages.properties)
     */
    @Bean
    public MessageSource messageSource() {
        //messageSource 객체 생성
        ResourceBundleMessageSource messageSource = new
                ResourceBundleMessageSource();
        //messageSource 로 사용할 프러퍼티 파일 이름들
        //src/main/resources/messages,errors 경로로 지정
        messageSource.setBasenames("messages.front_messages");
        //메시지 인코딩 지정
        messageSource.setDefaultEncoding("UTF-8");
        //리턴값을 빈으로
        return messageSource;
    }
}
