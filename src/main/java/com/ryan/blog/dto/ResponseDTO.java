package com.ryan.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Ryan
 * @Date 2020/7/22 14:56
 * version 1.0
 */
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@Data
public class ResponseDTO implements Serializable {
    private static volatile ResponseDTO responseDto;

    private Integer code;
    private String msg;
    private Object data;


    public ResponseDTO(){
    }

    public static ResponseDTO getInstance(){
        if (null==responseDto){
            synchronized (ResponseDTO.class){
                if (null==responseDto){
                    responseDto=new ResponseDTO();
                }
            }
        }
        return  responseDto;
    }


    /**
     * 返回错误信息
     * @return
     */
    public ResponseDTO err(String msg){
        responseDto.setCode(400);
        responseDto.setData(null);
        responseDto.setMsg(msg);
        return responseDto;
    }
    /**
     * 返回正确信息
     * @return
     */
    public ResponseDTO suc(Object data){
        responseDto.setCode(200);
        responseDto.setData(data);
        responseDto.setMsg("请求成功！");
        return responseDto;
    }

}
