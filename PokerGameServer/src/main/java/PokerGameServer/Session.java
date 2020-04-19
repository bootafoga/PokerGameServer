package PokerGameServer;

import io.netty.channel.Channel;

import java.util.HashMap;

public class Session extends HashMap<String, Channel> {

    private int MAX_PLAYERS = 1;

    public void refreshChannel(String token, Channel channel){
        addPlayer(token, channel);
    }

    public Channel getChannelByPlayer(String token){
        return get(token);
    }

    public void addPlayer(String token, Channel channel){
        this.put(token, channel);
    }

    public void deletePlayer(String token){
        this.remove(token);
    }

    public boolean isMaxPlayers(){
        return this.size() >= MAX_PLAYERS;
    }

}
