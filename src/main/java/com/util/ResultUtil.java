package com.util;

import com.alibaba.fastjson.JSONObject;
import com.bean.Result;

public class ResultUtil {

//    private static Result result;

    public static Object returnObj(Result result){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",result.getCode());
        jsonObject.put("data",result.getData());
        jsonObject.put("message",result.getMessage());
        return jsonObject;
    }

    public static JSONObject returnObj(int code,Object data,String message){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("data",data);
        jsonObject.put("message",message);
        return jsonObject;
    }
}
