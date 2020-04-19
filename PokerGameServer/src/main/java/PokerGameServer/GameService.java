package PokerGameServer;

import PokerGameServer.client.PokerClient;
import io.netty.channel.Channel;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GameService {
    @Async
    public void initGame(Session session) throws Exception {
        for (Map.Entry<String, Channel> entry: session.entrySet()) {
            PokerClient.connect("gameResults " + entry.getKey() + " LOSE");
        }
        session.clear();
    }
}
