package com.league;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		if (args.length == 1) {
			File f = new File(args[0]);
			if (f.exists()) {
				CommandLineApplication.run(f);
			}
		} else {
			SpringApplication.run(Application.class, args);
		}
	}

}