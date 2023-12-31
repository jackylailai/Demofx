package org.example.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import javafx.application.Platform;
import org.example.netty.controller.NettyClientMsgController;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.example.controller.OnlineUnitController.*;
import static org.example.controller.QuizController.operationCounts;

public class ClientHandler extends SimpleChannelInboundHandler<String> {
    private int curTime = 0;
    private final int beatTime =25; // 心跳時間間隔為 6 秒
    public static ChannelHandlerContext ctxFromHandler;
    public static String ctxId = "";

    // 	一啟動只要有import NettyClientHandler就會直接建立
    NettyClientMsgController nettyClientMsgController = NettyClientMsgController.getInstance();
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
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered : ");
//		System.out.println("channelRegistered : " + ctx.channel().remoteAddress().toString());
//		super.channelRegistered(ctx);
        nettyClientMsgController.setCtx(ctx);
    }
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
        System.out.println("channelRead : ");
        ByteBuf byteBuf = (ByteBuf) msg;
        String servermessage = byteBuf.toString(CharsetUtil.UTF_8);
        System.out.println("\n" + "收到 server 端" + ctx.channel().remoteAddress() + "的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
//        nettyClientMsgController.treatMsg(servermessage);
        ctxFromHandler=ctx;
        Platform.runLater(() -> {
//            System.out.println("read");
//            System.out.println(servermessage);
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
                    callChromeApp();
                    showQuizDetails(unitIdFromOnlineUnit,eventFromOnlineUnit,2);
                    break;
                case "score2":
                    System.out.println("跳轉成績2給人");
                    break;
                default:
                    break;
            }
        });
        JSONParser jsonParser = new JSONParser();
        Object jsonObj =jsonParser.parse(servermessage);
        JSONObject jsonObject = (JSONObject) jsonObj;
        nettyClientMsgController.treatMsg(servermessage);//把server的資料訊息處理
        if(ctxId.trim().isEmpty()){
            ctxId = jsonObject.get("msg").toString();

            System.out.println("ctxId " + ctxId);
        }

    }

    public static void callChromeApp() {
        String shortcutPath = "C:\\Users\\Public\\Desktop\\Google Chrome.lnk";
        if (operationCounts == null) {
            try {
                File shortcutFile = new File(shortcutPath);
                if (shortcutFile.exists()) {
                    Desktop.getDesktop().open(shortcutFile);
                    //                minimizeWindow("Google Chrome");
                } else {
                    System.out.println("Shortcut file does not exist.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendMessageToServer(String message, ChannelHandlerContext ctx) {
        System.out.println("client訊息:"+message);
        System.out.println("channel: ctx   :"+ctx);
        ByteBuf responseByteBuf = ctx.alloc().buffer();
        responseByteBuf.writeBytes(message.getBytes(CharsetUtil.UTF_8));
        ctx.writeAndFlush(responseByteBuf);
    }







    public static void sendCMD(int cmdType, String msg){
        String cmdMsg = "";
        System.out.println("sendCMD() cmdType : " + cmdType + "; msg : " + msg);
//		switch(cmdType){
//		case CommonString.CMD_CONNECTED_INT:
//				cmdMsg = CommonFunction.setResponse(CommonString.CMD_CONNECTED, msg);
//				break;
//		case CommonString.CMD_DISCONNECT_INT:
//				cmdMsg = CommonFunction.setResponse(CommonString.CMD_DISCONNECT , msg);
//				break;
//		case CommonString.CMD_LOGIN_INT:
//				cmdMsg = CommonFunction.setResponse(CommonString.CMD_LOGIN, msg);
//				break;
//		case CommonString.CMD_LOGOUT_INT:
//				cmdMsg = CommonFunction.setResponse(CommonString.CMD_LOGOUT, msg);
//				break;
//		}
        //from NettyDTO
//		cmdMsg = "{\"cmd\":"+ cmdType + ",\"from\":\""+ msg + "\",\"msg\":\""+ ctxId +  "\"}";
        cmdMsg = "{\"cmd\":"+ cmdType + ",\"from\":\""+ msg + "\",\"msg\":\""+ ""  +  "\"}";

        ctxFromHandler.writeAndFlush( Unpooled.copiedBuffer( cmdMsg, CharsetUtil.UTF_8));
    }
}