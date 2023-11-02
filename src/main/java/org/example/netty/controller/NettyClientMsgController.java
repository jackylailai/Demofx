package org.example.netty.controller;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.Getter;
import lombok.Setter;
import org.example.modelDTO.MissionDTO;
import org.example.modelDTO.MsgDTO;
import org.example.modelDTO.NettyDTO;
import org.example.netty.service.NettyClientCommonService;
import org.example.netty.service.NettyClientTeamService;
import org.example.utils.func.CodeDecoder;
import org.example.utils.func.DTOParser;
import org.example.utils.text.NettyCode;

public class NettyClientMsgController {
    private static NettyClientMsgController controller;


    @Getter
    @Setter
    private static String clientCtxId = "";

    @Getter
    @Setter
    private static NettyDTO nettyDTO;

    public ChannelHandlerContext ctx;
    public NettyClientCommonService nettyClientCommonService;
    public NettyClientTeamService nettyClientTeamService;

    private NettyClientMsgController(){
        nettyClientCommonService = new NettyClientCommonService();
        nettyClientTeamService = new NettyClientTeamService();
    }

    public static NettyClientMsgController getInstance(){
        if(controller == null){
            controller = new NettyClientMsgController();
        }
        return controller;
    }

    public void setCtx(ChannelHandlerContext sCtx){
        ctx = sCtx;
    }

    public MsgDTO parseToMsgDTO(String msg){

        return nettyClientCommonService.toMsgDTO(msg);
    }

    public MsgDTO parseMsgDTO(int cmdCode,
                              String msg,
                              String name,
                              long level,
                              int team){

        return nettyClientCommonService.createMsgDTO(cmdCode, msg, name, level, team);
    }



    public void sendMsg(String msg){
        System.out.println("sendMsg : " + msg);

        nettyClientCommonService.createMsgDTO(NettyCode.CMD_NORMAL_MSG, msg, clientCtxId, 0L, 0);
        ctx.writeAndFlush( Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
    }

    public void sendCMD(int cmdCode){
        System.out.println("sendCMD() cmdCode : " + cmdCode);
        String teamStrs = "";
        if(cmdCode == NettyCode.TEAM_WAITING_COACH_DISPATCH){
            teamStrs = DTOParser.parseDTOsToString(nettyClientTeamService.getTeamDTOList().toArray());
        }

        MsgDTO msgDTO;
        if(teamStrs.isEmpty()){
            msgDTO = nettyClientCommonService.createMsgDTO(cmdCode);
        }else{
            msgDTO = nettyClientCommonService.createMsgDTO(cmdCode, teamStrs);
        }
        String cmdMsg = nettyClientCommonService.parseDTOToString(msgDTO);
        System.out.println("sendCMD() cmdMsg : " + cmdMsg);

        ctx.writeAndFlush( Unpooled.copiedBuffer( cmdMsg, CharsetUtil.UTF_8));
    }

    public void sendCMDMsg(int cmdCode, String msg){
//
        System.out.println("sendCMD() cmdCode : " + cmdCode);
        MsgDTO msgDTO = nettyClientCommonService.createMsgDTO(cmdCode, msg);
        String cmdMsg = nettyClientCommonService.parseDTOToString(msgDTO);
        System.out.println("sendCMD() cmdMsg : " + cmdMsg);

        ctx.writeAndFlush( Unpooled.copiedBuffer( cmdMsg, CharsetUtil.UTF_8));
    }

    public void sendMissionMsg(String classIndex, String courseIndex, String unitIndex, String msg){
        System.out.println("sendMissionMsg ;; classIndex: " + classIndex + " ;; courseIndex : " + courseIndex + " ;; msg : " + msg);

        MissionDTO missionDTO = new MissionDTO();
        missionDTO.setClassIndex(classIndex);
        missionDTO.setCourseIndex(courseIndex);
        missionDTO.setUnitIndex(unitIndex);
        missionDTO.setTeamList(msg);

        String missionStr = DTOParser.parseDTOToString(missionDTO);

        MsgDTO msgDTO = nettyClientCommonService.createMsgDTO(NettyCode.TEAM_WAITING_NEXT, missionStr);
        String cmdMsg = nettyClientCommonService.parseDTOToString(msgDTO);
        System.out.println("sendCMD() cmdMsg : " + cmdMsg);

        ctx.writeAndFlush( Unpooled.copiedBuffer( cmdMsg, CharsetUtil.UTF_8));
    }

    public void treatMsg(String sourceMsg){
        System.out.println("treatMsg : " + sourceMsg);
        MsgDTO msgDTO = nettyClientCommonService.toMsgDTO(sourceMsg);

        int cmd = msgDTO.getCmd();
        int cmdType = cmd / 10000;
        String msg = msgDTO.getMsg();
        String from = msgDTO.getFrom();
        long level = msgDTO.getLevel();
        int team = msgDTO.getTeam();
        System.out.println("cmd : "  + cmd);
        System.out.println("cmdType : "  + cmdType);
        System.out.println("msg : "  + msg);
        System.out.println("from : "  + from);
        System.out.println("level : "  + level);
        System.out.println("team : "  + team);

        String decodeMsg = "";

        switch (cmdType) {

            case NettyCode.CMD_NORMAL,NettyCode.CMD -> {
                decodeMsg = CodeDecoder.getCodeMsg("CMD", cmd);
                if(cmd == NettyCode.CMD_CONNECT){
                    setClientCtxId(msg);
                    System.out.println("check set clientCtxId : " + getClientCtxId());
                }else{
                    nettyClientCommonService.treatMsgDTO(cmd, from, msg);
                }

//                if(cmd == NettyCode.CMD_LOGIN){
//                    if(clientCtxId.equals(msg)){
//                        nettyDTO = nettyClientCommonService.toNettyDTO(msg);
//                    }else{
//                        //提示別的使用者登入
//                    }
//                }

            }

            case NettyCode.TEAM -> {
                decodeMsg = CodeDecoder.getCodeMsg("TEAM", cmd);
                nettyClientTeamService.treatMsgDTO(cmd, from, msg);
            }
        }

//        System.out.println("decodeMsg : " + decodeMsg);
    }
}
