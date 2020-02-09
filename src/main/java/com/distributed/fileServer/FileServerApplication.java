package com.distributed.fileServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class FileServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileServerApplication.class, args);
		try {
			System.out.println(InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
