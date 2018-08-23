package br.com.splessons.lesson12.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private final int defaultPageSize;
    private final long maxAgeSeconds;

    @Autowired
    public WebMvcConfig(
            @Value("${app.pageSize.default}") int defaultPageSize,
            @Value("${app.maxAgeSeconds}") long maxAgeSeconds) {
        this.defaultPageSize = defaultPageSize;
        this.maxAgeSeconds = maxAgeSeconds;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setFallbackPageable(PageRequest.of(0, defaultPageSize));
        argumentResolvers.add(resolver);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                //.allowedOrigins("*")
                .allowedMethods(
                        HttpMethod.HEAD.name(),
                        HttpMethod.OPTIONS.name(),
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name()
                )
                .exposedHeaders(HttpHeaders.AUTHORIZATION)
                .allowCredentials(false)
                .maxAge(this.maxAgeSeconds);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Type converters

//        ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().build();
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
//        converters.add(converter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(??);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // configurer.setTaskExecutor(new TaskExecutorAdapter(asyncExecutor));
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //Anything following a dot in the url (in final position) should not be interpreted as a file extension,
        //since we are not using that feature to obtain the request media type
        configurer.setUseSuffixPatternMatch(false);
    }
}
