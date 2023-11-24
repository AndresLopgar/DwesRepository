package com.andres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.andres.repository.EmpleadosRepositorio;

@SpringBootApplication
@ComponentScan(basePackages = "com.andres")
public class NominaSpringBootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(NominaSpringBootApplication.class, args);
	}

	@Autowired
	 @SuppressWarnings("unused")
	private EmpleadosRepositorio repositorio;

	@Override
	public void run(String... args) throws Exception {

	}
}
