package org.example.elm_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用程序入口类。
 * 使用 Spring Boot 自动配置，启动整个外卖平台后端服务。
 */
@SpringBootApplication
public class ElmSpringApplication {

    /**
     * 程序主入口方法，启动 Spring Boot 应用。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(ElmSpringApplication.class, args);
    }

}
