package cn.jjsunw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "cn.jjsunw.mapper")
public class Application {

	public static void main(String[] args) {
		System.out.println("--------------------------SpringbootInitialize Application start--------------------------");
		SpringApplication.run(Application.class, args);
		System.out.println("--------------------------SpringbootInitialize Application started------------------------");
	}
}
