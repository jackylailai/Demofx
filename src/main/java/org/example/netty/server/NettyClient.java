package org.example.netty.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import org.example.netty.handler.ClientHandler;

import java.util.concurrent.TimeUnit;

public class NettyClient {

    private Channel channel;
    public Channel getChannel() {
        return channel;
    }
    public static String localhostip;

    public void connectToServer() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        localhostip="192.168.50.16";//left one
//        localhostip="192.168.50.48";
//        localhostip="localhost";
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new IdleStateHandler(0,58,0, TimeUnit.SECONDS));
                    ch.pipeline().addLast(new ClientHandler());
                }
            });

            ChannelFuture channelFuture = bootstrap.connect(localhostip, 6666).sync();
            channel = channelFuture.channel();
            System.out.println("connecttoserver的channel"+channel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void close() {
        if (channel != null) {
            channel.close();
        }
    }
}