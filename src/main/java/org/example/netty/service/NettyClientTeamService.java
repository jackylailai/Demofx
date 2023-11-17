package org.example.netty.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;
import org.example.controller.OnlineController;
import org.example.modelDTO.MsgDTO;
import org.example.modelDTO.TeamDTO;
import org.example.utils.func.DTOParser;
import org.example.utils.text.NettyCode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.example.controller.OnlineUnitController.*;
import static org.example.controller.QuizController.*;
import static org.example.controller.TsController.jsonNodeForUser;
import static org.example.controller.TsController.onlineControllerStatic;
import static org.example.netty.handler.ClientHandler.callChromeApp;
import static org.example.netty.handler.ClientHandler.ctxId;

public class NettyClientTeamService {




    private static List<TeamDTO> teamDTOList = new ArrayList<>();
    public static List<TeamDTO> getTeamDTOList() {
        return teamDTOList;
    }

    public static void setTeamDTOList(List<TeamDTO> teamDTOList) {
        NettyClientTeamService.teamDTOList = teamDTOList;
    }


    public MsgDTO treatMsgDTO(int cmd, String from, String msg){
        System.out.println("team treatMsgDTO;; cmd: " + cmd + " ; from : " + from + " ; msg : " + msg);
        MsgDTO msgDTO = new MsgDTO();
        switch(cmd){
            case NettyCode.TEAM_WAITING_UPDATE:                   //00
                msgDTO.setCmd(NettyCode.TEAM_WAITING_UPDATE);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_WAIT:                   //01
                msgDTO.setCmd(NettyCode.TEAM_WAITING_WAIT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_WAIT_TIMEOUT:           //02
                msgDTO.setCmd(NettyCode.TEAM_WAITING_WAIT_TIMEOUT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_JOIN:                   //03
                msgDTO.setCmd(NettyCode.TEAM_WAITING_JOIN);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_JOIN_FAIL:              //04
                msgDTO.setCmd(NettyCode.TEAM_WAITING_JOIN_FAIL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_COACH_GET_ALL:         //05
//                TeamDTO[] teamDTOS = DTOParser.parseStringToDTOs(msg, TeamDTO[].class);
                TeamDTO[] teamDTOs;
                try {
                    teamDTOs = (TeamDTO[]) DTOParser.parseStringToDTO(msg, TeamDTO[].class);

                } catch (JsonProcessingException e) {
                    System.out.println("JsonProcessingException : " + e.getMessage());
                    throw new RuntimeException(e);
                }

                System.out.println("teamDTOs : " + teamDTOs.length);



                boolean findTeam = false;
                for(TeamDTO teamDTO: teamDTOs){
                    String ctxId = teamDTO.getCtxId();
                    findTeam = false;
                    System.out.println("teamDTO before : " + teamDTO);
                    for(TeamDTO teamDTO2 : teamDTOList){
                        String ctxId2 = teamDTO2.getCtxId();
                        if(ctxId.equals(ctxId2)){
                            findTeam = true;
                            break;
                        }
                    }
                    if(!findTeam){
                        teamDTOList.add(teamDTO);
                    }
                }


                //此方法不行
//                teamDTOList = Arrays.stream(teamDTOs).sorted().toList();
                for(TeamDTO teamDTO : teamDTOList){
                    System.out.println("teamDTO after : " + teamDTO);
                }

                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_GET_ALL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_COACH_DISPATCH:         //06
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_DISPATCH);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_COACH_DISPATCH_FAIL:    //07
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_DISPATCH_FAIL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;
//處理~連線需求 連線後教官給予指令配段完到下一步
            case NettyCode.TEAM_WAITING_NEXT:                   //08
                msgDTO.setCmd(NettyCode.TEAM_WAITING_NEXT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                onlineControllerStatic.showUpOnlineUnitButton();//確保是同一個controller
                break;

            case NettyCode.TEAM_COURSE_STEP_WAITING:            //09
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_WAITING);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                System.out.println("//有人在執行 其他人先等等");
                System.out.println("準備封鎖");
                blockImageFunc();
                break;

            case NettyCode.TEAM_COURSE_STEP_STARTING:           //10
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_STARTING);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                System.out.println("是"+msgDTO.getFrom()+"的開始訊息");
                if(Objects.equals(msgDTO.getFrom(), jsonNodeForUser.get("name").asText())){
                    System.out.println("是本人的開始訊息");
                }else{
                    System.out.println("跳轉成績1給1學員，你是學員2準備考試");
                    displayImageFunc();
                    operationCounts=3;//先亂設定不要起衝突就好 目前是在answer那邊會增加
                    callChromeApp();
                    showQuizDetails(unitIdFromOnlineUnit,eventFromOnlineUnit,2);
                }
                break;

            case NettyCode.TEAM_COURSE_STEP_FINISH:
                System.out.println("收到結束的指令");//11
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_FINISH);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_ALL_FINISH:              //12
                msgDTO.setCmd(NettyCode.TEAM_COURSE_ALL_FINISH);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_ALL_FAIL:                //13
                msgDTO.setCmd(NettyCode.TEAM_COURSE_ALL_FAIL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_DEL_TEAM:                //14
                msgDTO.setCmd(NettyCode.TEAM_COURSE_DEL_TEAM);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;
        }

        return msgDTO;
    }
}
