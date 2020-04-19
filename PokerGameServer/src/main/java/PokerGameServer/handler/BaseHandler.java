package PokerGameServer.handler;

import PokerGameServer.GameService;
import PokerGameServer.Session;
import PokerGameServer.client.PokerClient;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.scheduling.annotation.Async;

import java.nio.charset.Charset;
import java.util.Map;

public class BaseHandler extends ChannelInboundHandlerAdapter {

    private final Session session;
    private final GameService gameService;

    public BaseHandler(Session session, GameService gameService) {
        this.session = session;
        this.gameService = gameService;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(!session.isMaxPlayers() || true){
            Map<String, String> command = parseCommand((ByteBuf) msg);

            if(command.containsKey("error")){
                ctx.channel().writeAndFlush("300 wrong command");
                return;
            }

            session.addPlayer(command.get("token"), ctx.channel());

            if(session.isMaxPlayers()) {
                gameService.initGame(session);
            }
            ctx.channel().writeAndFlush("200");

        } else {
            ctx.channel().writeAndFlush("300 internal error");
        }
    }

    private Map<String, String> parseCommand(ByteBuf data){
        try{
            //Command example: game roulette 2181178001907594372 bet 500
            String[] rawData = (data).toString(Charset.defaultCharset()).trim().split(" ");

            return Map.of("token", rawData[2], "bet", rawData[4]);
        } catch (Exception e){
            return Map.of("error", "Wrong command");
        }
    }
}
