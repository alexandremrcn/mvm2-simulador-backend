package br.com.mvm2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"br.com.mvm2","mvm2services"})
@SpringBootApplication
public class Mvm2RestSimulator {

	public static void main(String[] args) {
		SpringApplication.run(Mvm2RestSimulator.class, args);
	}

}
