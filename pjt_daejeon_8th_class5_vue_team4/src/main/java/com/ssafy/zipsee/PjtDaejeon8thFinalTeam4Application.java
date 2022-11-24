package com.ssafy.zipsee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ssafy.zipsee.*.model.mapper")
public class PjtDaejeon8thFinalTeam4Application {

	public static void main(String[] args) {
		SpringApplication.run(PjtDaejeon8thFinalTeam4Application.class, args);
	}

}
