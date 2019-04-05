package com.itdan.manager;

import com.itdan.swagger.configruration.EnableMySwagger;
import com.itdan.swagger.configruration.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EntityScan(basePackages={"com.itdan.entity"})
//@Import(SwaggerConfiguration.class)//扫描swagger配置文件
@EnableMySwagger
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }

}
