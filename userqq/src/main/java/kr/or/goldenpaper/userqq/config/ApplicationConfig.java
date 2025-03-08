package kr.or.goldenpaper.userqq.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "kr.or.goldenpaper.userqq.dao" })
@Import({DBConfig.class})
public class ApplicationConfig {

}
