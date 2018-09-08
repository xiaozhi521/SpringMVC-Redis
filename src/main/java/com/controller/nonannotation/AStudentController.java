package com.controller.nonannotation;

import com.bean.Student;
import com.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/nonannotation")
public class AStudentController extends BaseController{

    /**
     *  返回字符串的形式
     * @return
     */
    @RequestMapping(value = "/addStudent.mvc", method = RequestMethod.GET)
    public String addstudent(ModelMap modelMap) {
        System.out.println(getRequest().getParameter("name"));
        getRequest().setAttribute("message","这是继承 baseController 方式,request");
        modelMap.addAttribute("message1","这是使用配置文件方式,ModelMap");
//        if(getRequest().getParameter("name") != "123"){
//            return forwardPath("/hello");
//        }
        return forwardPath("/student/student");
    }

    /**
     *  返回  ModelAndView  的形式
     * @return
     */
    @RequestMapping(value = "/student.mvc", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("/student/student", "command", new Student());
    }

//    @RequestMapping("/addStudentOK.mvc")
//    public String addStudentOK() {
//        getRequest().setAttribute("id",getRequest().getParameter("id"));
//        getRequest().setAttribute("age",getRequest().getParameter("age"));
//        getRequest().setAttribute("name",getRequest().getParameter("name"));
//        return forwardPath("/student/result");
//    }
    @RequestMapping(value = "/addStudentOK.mvc", method = RequestMethod.POST)
    public String addStudentOK(@ModelAttribute("SpringWeb")Student student,ModelMap model) {
        model.addAttribute("name", student.getName());
        model.addAttribute("age", student.getAge());
        model.addAttribute("id", student.getId());

        return "/student/result";
    }
}
