package com.demo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class DemoApplication {
	// 数据列表每页的数据量
    public final static int PAGE_SIZE = 5;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
