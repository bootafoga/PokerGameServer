package PokerGameServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PokerGameServer {
    public static void main(String[] args) {
        SpringApplication.run(PokerGameServer.class, args);
    }
}
