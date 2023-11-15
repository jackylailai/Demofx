package org.example.utils.func;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DTOParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static String parseDTOToString(Object objDTO){
        String msgString;

        try {
            msgString = objectMapper.writeValueAsString(objDTO);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }

        return msgString;
    }

    public static String parseDTOsToString(Object[] objDTOs){
        System.out.println("parseDTOsToString : ");
        String msgString;

        try {
            msgString = objectMapper.writeValueAsString(objDTOs);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }

        return msgString;
    }

    public static Object parseStringToDTO(String msg, Class target) throws JsonProcessingException {

        return objectMapper.readValue(msg, target);
    }
}
