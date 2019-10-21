package ru.zagshak.buySupply;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuySupplyApplication {
	private static final Logger log = LoggerFactory.getLogger(BuySupplyApplication.class);

	public static void main(String[] args) {
		log.info("Started application.....");
		System.out.println("start");
		System.out.println("start2");
		System.out.println("dev");
		SpringApplication.run(BuySupplyApplication.class, args);
	}

}
