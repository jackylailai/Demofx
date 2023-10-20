package org.example.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import javafx.application.Platform;

import java.nio.charset.Charset;
import java.util.Date;

public class ClientHandler extends SimpleChannelInboundHandler<String> {
    private int curTime = 0;
    private final int beatTime =25; // 心跳時間間隔為 6 秒
//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        try {
//            System.out.println("client心跳發送: " + new Date());
//            if (evt instanceof IdleStateEvent) {
//                IdleStateEvent event = (IdleStateEvent) evt;
//                if (event.state() == IdleState.WRITER_IDLE) {
//                    if (curTime < beatTime) {
//                        curTime++;
////                        String heartbeatMessage = "心跳還在";
////                        ByteBuf heartbeatByteBuf = ctx.alloc().buffer();
////                        heartbeatByteBuf.writeBytes(heartbeatMessage.getBytes(CharsetUtil.UTF_8));
////                        ctx.writeAndFlush(heartbeatByteBuf);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            // 在 catch 區塊中輸出異常信息
//            System.err.println("Exception in userEventTriggered: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message) {
        Platform.runLater(() -> {
            System.out.println("read0");
            System.out.println(message);
//            NettyClientApp.appendMessageToChatArea(message); // 使用 NettyClientApp 的靜態方法
        });
    }
    //收到訊息是Read
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        String servermessage = byteBuf.toString(CharsetUtil.UTF_8);
        System.out.println("\n" + "收到 server 端" + ctx.channel().remoteAddress() + "的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
        Platform.runLater(() -> {
            System.out.println("read");
            System.out.println(servermessage);
//            NettyClientApp.appendMessageToChatArea(servermessage);
        });
    }
}