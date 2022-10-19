package com.ynhj.nativemysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NativeMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(NativeMysqlApplication.class, args);
	}

}
