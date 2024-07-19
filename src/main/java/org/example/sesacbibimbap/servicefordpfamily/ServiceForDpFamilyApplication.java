package org.example.sesacbibimbap.servicefordpfamily;

import org.example.sesacbibimbap.servicefordpfamily.service.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceForDpFamilyApplication implements CommandLineRunner {

	@Autowired
	private DataImportService dataImportService;
	public static void main(String[] args) {
		SpringApplication.run(ServiceForDpFamilyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataImportService.importData();
	}
}
