package com.controller.annotation;


import com.alibaba.fastjson.JSONObject;
import com.bean.Result;
import com.util.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller

public class HelloController {

    @RequestMapping("/hello.mvc")
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        model.addAttribute("ee", "12121");
        return "hello";
    }

    /**
     *  此方法废弃
     * @return
     */
    @RequestMapping(value = "/staticPage.mvc", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:/pages/final.html";
    }

    @RequestMapping("/getObj.mvc")
    @ResponseBody
    public Object s() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "test");
        jsonObject.put("address", "bj");
        Result result = new Result();
        result.setCode(1);
        result.setData(jsonObject);
        result.setMessage("success");
        return ResultUtil.returnObj(result);
    }

    @RequestMapping("/getMap.mvc")
    @ResponseBody
    public Map map() {
        Map map = new HashMap();
        map.put("name", "test");
        map.put("address", "bj");
        map.put("map", "this is map");
        return map;
    }


}
