package com.example.demo.configuration;import com.example.demo.Interceptor.AccessInterceptor;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Configuration;import org.springframework.web.servlet.config.annotation.InterceptorRegistry;import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;@Configurationpublic class IntercepterConfig  extends WebMvcConfigurerAdapter {    @Autowired    private AccessInterceptor accessInterceptor;    @Override    public void addInterceptors(InterceptorRegistry registry) {        //这里可以添加多个拦截器        registry.addInterceptor(accessInterceptor).addPathPatterns("/**").excludePathPatterns("/images/**");//拦截所有请求        super.addInterceptors(registry);    }}