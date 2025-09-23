package com.doku.doku_owasp_example.xxe.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Daniel Joi Partogi Hutapea
 */
@Configuration
public class JacksonXmlConfig
{
    @Bean
    public XmlMapper xmlMapper()
    {
        return new XmlMapper(); // Use Jackson version 2.7.3 to simulate vulnerability (com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.7.3)
    }
}
