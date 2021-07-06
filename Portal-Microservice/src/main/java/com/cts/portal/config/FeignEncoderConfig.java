package com.cts.portal.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;

import feign.codec.Encoder;

@Configuration
public class FeignEncoderConfig {
	@Bean
	public Encoder feignEncoder() {
	    ObjectFactory<HttpMessageConverters> objectFactory = () ->
	            new HttpMessageConverters(new FormHttpMessageConverter());
	    return new SpringEncoder(objectFactory);
	}
}
