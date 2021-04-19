package br.com.dcruzb.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialConfig {
	@Autowired
	private Seeder seeder;
	
	@Bean
	public boolean instantiateDatabase() {
		seeder.seed();
		
		return true;
	}
}
