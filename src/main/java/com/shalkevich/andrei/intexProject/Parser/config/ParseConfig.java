package com.shalkevich.andrei.intexProject.Parser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Configuration class which points to source file of properties,
 * place to search for beans and calls PropertySourcesPlaceholderConfigurer
 * object to read the properties from file
 * 
 * @author Andrei Shalkevich
 */
@Configuration
@PropertySource("classpath:app.properties")
@ComponentScan("com.shalkevich.andrei.intexProject.Parser.service")
public class ParseConfig {

  /**
   * PropertySourcesPlaceholderConfigurer Bean registration method
   * 
   * @return PropertySourcesPlaceholderConfigurer object to resolve ${} in @Values
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
    return new PropertySourcesPlaceholderConfigurer();
  }
}
