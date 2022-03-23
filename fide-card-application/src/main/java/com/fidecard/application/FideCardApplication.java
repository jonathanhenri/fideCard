package com.fidecard.application;

import com.fidecard.application.config.ConfigPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import java.util.Locale;

@SpringBootApplication(scanBasePackageClasses = {ConfigPackage.class})
public class FideCardApplication extends SpringBootServletInitializer {
	
	static {
		Locale.setDefault(new Locale("pt", "BR"));
		System.setProperty("user.language", "pt");
		System.setProperty("user.region", "BR");
		System.setProperty("file.encoding", "UTF-8");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FideCardApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FideCardApplication.class);
	}

}
