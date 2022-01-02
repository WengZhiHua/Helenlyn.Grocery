package dynamicdatasource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author brand
 * @Description: Web配置适配器
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午2:58
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final boolean JAXB_TO_PRESENT =
            ClassUtils.isPresent("javax.xml.bind.Binder",
                    Thread.currentThread().getContextClassLoader());
    private static final boolean JACKSON_TO_PRESENT =
            ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper",
                    Thread.currentThread().getContextClassLoader())
                    && ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator",
                    Thread.currentThread().getContextClassLoader());


    /**
     * @param @return 设定文件
     * @return UrlBasedViewResolver 返回类型
     * @throws
     * @Title: getUrlBasedViewResolver
     * @Description: 配置可以访问.html页面
     */
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setViewClass(JstlView.class);
        return internalResourceViewResolver;
    }

    /**
     * 配置访问html页面和静态资源扩展demo
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/html/**").addResourceLocations("/html/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        stringConverter.setWriteAcceptCharset(false);
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(stringConverter);
        converters.add(new ResourceHttpMessageConverter());
        converters.add(new SourceHttpMessageConverter());
        converters.add(new AllEncompassingFormHttpMessageConverter());
        if (JAXB_TO_PRESENT) {
            converters.add(new Jaxb2RootElementHttpMessageConverter());
        }

        if (JACKSON_TO_PRESENT) {
            MappingJackson2HttpMessageConverter convert = new MappingJackson2HttpMessageConverter();
            List<MediaType> supportedMediaTypes = new ArrayList();
            supportedMediaTypes.add(this.converterMediaType());
            convert.setSupportedMediaTypes(supportedMediaTypes);
            converters.add(convert);
        }

        this.customMediaTypeSupport(converters);
    }

    public MediaType converterMediaType() {
        return MediaType.APPLICATION_JSON;
    }

    public void customMediaTypeSupport(List<HttpMessageConverter<?>> converters) {
    }
}