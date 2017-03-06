package com.li.exchange;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author LiMeiyuan
 * @version v1.00
 * @package: com.chinatalecom.tymh.toolTest2
 * @date 2016/4/21 16:40
 * @description:
 */

@Configuration
@EnableAutoConfiguration()
@EnableScheduling
public class WebAppConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 设置js css文件路径
         */
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

    }
}
