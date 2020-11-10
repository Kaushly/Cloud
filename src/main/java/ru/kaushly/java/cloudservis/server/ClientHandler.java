package ru.kaushly.java.cloudservis.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ClientHandler extends SimpleChannelInboundHandler<String> {

//    private UserPool users;

    public static final ConcurrentLinkedDeque<ChannelHandlerContext> channels =
            new ConcurrentLinkedDeque<>();

//    public ClientHandler(UserPool users) {
//        this.users = users;
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client connected");
        channels.add(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("Message from client: " + s);
        channels.forEach(c -> c.writeAndFlush(s));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client disconnected");
    }
}
