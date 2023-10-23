package org.example.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import javafx.application.Platform;

import static org.example.controller.OnlineUnitController.*;
import static org.example.controller.QuizController.operationCounts;

public class ClientHandler extends SimpleChannelInboundHandler<String> {
    private int curTime = 0;
    private final int beatTime =25; // 心跳時間間隔為 6 秒
    public static ChannelHandlerContext ctxFromHandler;
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
        ctxFromHandler=ctx;
        Platform.runLater(() -> {
//            System.out.println("read");
            System.out.println(servermessage);
//            NettyClientApp.appendMessageToChatArea(servermessage);
//            sendMessageToServer("get",ctx);
            switch (servermessage) {
                case "CHANNEL1":
                    System.out.println("分配到1，執行畫面處理");
                    showQuizDetails(unitIdFromOnlineUnit,eventFromOnlineUnit,1);
                    break;
                case "CHANNEL2":
                    System.out.println("分配到2準備封鎖");
                    blockImageFunc();
                    break;
                case "score1":
                    System.out.println("跳轉成績1給人");
                    displayImageFunc();
                    operationCounts=3;//先亂設定不要起衝突就好 目前是在answer那邊會增加
                    showQuizDetails(unitIdFromOnlineUnit,eventFromOnlineUnit,2);
                    break;
                case "score2":
                    System.out.println("跳轉成績2給人");
                    break;
                default:
                    break;
            }
        });
    }
    public static void sendMessageToServer(String message, ChannelHandlerContext ctx) {
        System.out.println("client訊息:"+message);
        System.out.println("channel: ctx   :"+ctx);
        ByteBuf responseByteBuf = ctx.alloc().buffer();
        responseByteBuf.writeBytes(message.getBytes(CharsetUtil.UTF_8));
        ctx.writeAndFlush(responseByteBuf);
    }
}