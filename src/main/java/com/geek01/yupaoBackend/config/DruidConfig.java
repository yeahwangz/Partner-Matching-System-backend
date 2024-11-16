package com.geek01.yupaoBackend.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * druid连接池自定义配置
 */
/*@Configuration*/
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.druid")
    public DataSource druid(){
        return new DruidDataSource();
    }

/*    //配置Druid监控
    *//**
     * 1、配置管理后台的Servlet
     * 2、配置一个Web监控的filter
     *//*
    //配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet(){
        ServletRegistrationBean<StatViewServlet> servletServletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//        Map<String,String> initParams=new HashMap<>();
        //增加配置
//        initParams.put("loginUsername","admin");//登录key 固定的loginUsername
//        initParams.put("loginPassword","123456");//登录密码 固定loginPassword
//        initParams.put("allow",""); //允许谁可以访问，默认都可访问
//        initParams.put("deny","172.16.208.208");//禁止谁能访问

        //bean.setInitParameters(initParams); //初始化参数
        servletServletRegistrationBean.addInitParameter("loginUsername","admin");
        servletServletRegistrationBean.addInitParameter("loginPassword","123456");
        servletServletRegistrationBean.addInitParameter("allow","");
        //servletServletRegistrationBean.addInitParameter("deny","172.16.208.208");
        return servletServletRegistrationBean;
    }

    //配置一个Web监控的filter
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter(){
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.css,*.png,*.jpg,*.js,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/**"));
        bean.setInitParameters(initParams);
        //添加过滤规则
//        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息
//        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return  bean;
    }*/
}
