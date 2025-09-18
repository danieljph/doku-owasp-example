package com.doku.doku_owasp_example.insecure_deserialization.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Daniel Joi Partogi Hutapea
 */
@Configuration
public class JacksonConfig implements WebMvcConfigurer
{
    @Bean
    public ObjectMapper objectMapper()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping();
        return objectMapper;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping();

        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(objectMapper);

        converters.add(jsonMessageConverter);
    }
}
