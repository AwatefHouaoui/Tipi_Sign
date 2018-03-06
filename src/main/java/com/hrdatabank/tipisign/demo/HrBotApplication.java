package com.hrdatabank.tipisign.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
@EnableConfigurationProperties
//@EntityScan(basePackages = { "org.test.entities" })
//@ComponentScan(basePackages = { "org.test.metier" })
//@EnableJpaRepositories(basePackages = { "org.test.dao" })
public class HrBotApplication {
	static Path downloadedContentDir;

	public static void main(String[] args) throws IOException {
		downloadedContentDir = Files.createTempDirectory("line-bot");
		SpringApplication.run(HrBotApplication.class, args);
	}

}
