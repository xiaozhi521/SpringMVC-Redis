package com.controller.annotation;

import com.alibaba.fastjson.JSONObject;
import com.bean.Student;
import com.util.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class StudentController {

    @RequestMapping(value = "/addStudent.mvc", method = RequestMethod.GET)
    public String addstudent(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("message","这是使用配置文件方式,request");
        modelMap.addAttribute("message1","这是使用配置文件方式,ModelMap");
//        if(request.getParameter("name") != "123"){
//            return "/hello";
//        }
        return "/student/student";
    }

    @RequestMapping(value = "/student.mvc", method = RequestMethod.GET)
    public ModelAndView student() {
        Student student = new Student();
        student.setAge(23);
        student.setId(12);
        student.setName("qingFeng");
        return new ModelAndView("/student/modelAndView", "model", student);
    }

    @RequestMapping(value = "/addStudentOK.mvc", method = RequestMethod.POST)
    public String addStudentOK(@ModelAttribute("SpringWeb")Student student,ModelMap model) {
        model.addAttribute("name", student.getName());
        model.addAttribute("age", student.getAge());
        model.addAttribute("id", student.getId());

        return "/student/result";
    }

    /**
     *  SpringMVC 404异常
     * @return
     */
    @RequestMapping(value = "/notFoundException.mvc")
    public String notFoundException() {
        Student student = new Student();
        return student.getName();
    }

}
