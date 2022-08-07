package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.repository.UserRepository;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.util.DataFiller;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VlpiServerApplication implements CommandLineRunner {

    final DataFiller dataFiller;
    final UserRepository userRepository;

    public VlpiServerApplication(DataFiller dataFiller, UserRepository userRepository) {
        this.dataFiller = dataFiller;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(VlpiServerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if(userRepository.count() == 0) {
            dataFiller.fillData();
        }
    }
}
