package com.noirix;

import com.noirix.config.AmazonConfig;
import com.noirix.config.ApplicationBeans;
import com.noirix.config.PersistenceContextBeansConfiguration;
import com.noirix.config.WebBeansConfig;
import com.noirix.security.configuration.JwtTokenConfig;
import com.noirix.security.configuration.WebSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "com.noirix")
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({
        WebSecurityConfiguration.class,
        JwtTokenConfig.class,
        AmazonConfig.class,
        ApplicationBeans.class,
        WebBeansConfig.class,
        PersistenceContextBeansConfiguration.class})
public class SpringBootApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationStarter.class, args);
    }
}
