package com.shalkevich.andrei.intexProject.Parser.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import com.shalkevich.andrei.intexProject.Parser.service.FileSearcher;
import com.shalkevich.andrei.intexProject.Parser.service.ParseApplication;
import com.shalkevich.andrei.intexProject.Parser.service.ParserImplementator;

@Configuration
@PropertySource("classpath:app.properties")
public class ParseConfig {
  @Value("${path}")
  private String path;
  @Value("${inputFileExtension}")
  private String inputFileExtension;
  @Value("${outputFileExtension}")
  private String outputFileExtension;
  @Value("${outputFileName}")
  private String outputFileName;
  @Value("${delimiter}")
  private String delimiter;
  @Value("${isSeparateSaveMode}")
  private Boolean isSeparateSaveMode;

  /**
   * FileSearcher Bean instantiating method
   * 
   * @return fileSearcherObject for searching files for parsing
   */
  @Bean
  public FileSearcher fileSearcher() {
    return new FileSearcher(path, inputFileExtension);
  }

  /**
   * ParserImplementator Bean instantiating method
   * 
   * @return parserImplementatorObject for parsing and writing parsed strings to file
   */
  @Bean
  public ParserImplementator parserImplementator() {
    return new ParserImplementator(outputFileExtension, outputFileName, delimiter, path);
  }

  /**
   * ParseApplication Bean instantiating method
   * 
   * @return parseApplicationObject for parsing process providing
   */
  @Bean
  public ParseApplication getParseApplicationObject() {
    return new ParseApplication(fileSearcher(), parserImplementator(), isSeparateSaveMode);
  }

  /**
   * PropertySourcesPlaceholderConfigurer Bean registrating method
   * 
   * @return PropertySourcesPlaceholderConfigurer object to resolve ${} in @Values
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
    return new PropertySourcesPlaceholderConfigurer();
  }
}
