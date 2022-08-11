package com.example.cinema;

//import com.example.cinema.service.FilesStorageService;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class CinemaApplication  {
//    @Resource
//    FilesStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

//    @Override
//    public void run(String... arg) throws Exception {
//        storageService.deleteAll();
//        storageService.init();
//    }

}
