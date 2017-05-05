package br.com.tpartner.spring;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.annotation.JsonInclude;


@EnableWebMvc
@ComponentScan("br.com.tpartner")
@Configuration
public class ClientWebConfig extends WebMvcConfigurerAdapter{

	public ClientWebConfig(){
		super();
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false).
        favorParameter(true).
        parameterName("mediaType").
        ignoreAcceptHeader(false).
        useJaf(false).
        defaultContentType(MediaType.APPLICATION_JSON);
	}
	
	public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Hibernate4Module());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        final DateFormat df = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
        mapper.setDateFormat(df);

        messageConverter.setObjectMapper(mapper);
        return messageConverter;

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jacksonMessageConverter());
        super.configureMessageConverters(converters);
    }
}
