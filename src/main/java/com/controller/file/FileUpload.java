package com.controller.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Date;
import java.util.Iterator;

@Controller
@RequestMapping("/SpringmvcFile")
public class FileUpload {

    @RequestMapping("/addFile.mvc")
    public String addFile(){
        return "file/fileUpload";
    }
    /*
     * 通过流的方式上传文件
     * @RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
     */
    @RequestMapping("/fileUpload.mvc")
    public String fileUpload(@RequestParam("file") CommonsMultipartFile file) throws IOException {
        //用来检测程序运行时间
        long startTime = System.currentTimeMillis();
        System.out.println("fileName：" + file.getOriginalFilename());
        try {
            //获取输出流WEB-INF
            String filePath = getFilePath() + "WEB-INF\\fileUpload\\";
            File dir = new File(filePath);
            //如果文件夹不存在则创建
            if(!dir.exists()  && !dir .isDirectory()){
                dir.mkdirs();
            }
            OutputStream os = new FileOutputStream( filePath + file.getOriginalFilename());
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is = file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while ((temp = is.read()) != (-1)) {
                os.write(temp);
            }
            os.flush();
            os.close();
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法一的运行时间：" + String.valueOf(endTime - startTime) + "ms");
        return "file/success";
    }
    /*
     * 采用file.Transto 来保存上传的文件
     */
    @RequestMapping("/fileUpload2.mvc")
    public String fileUpload2(@RequestParam("file") CommonsMultipartFile file) throws IOException {
            long startTime = System.currentTimeMillis();
        System.out.println("fileName：" + file.getOriginalFilename());
        String path = "E:/" + new Date().getTime() + file.getOriginalFilename();

        File newFile = new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        long endTime = System.currentTimeMillis();
        System.out.println("方法二的运行时间：" + String.valueOf(endTime - startTime) + "ms");
        return "file/success";
    }
    /*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping("/springUpload.mvc")
    public String springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
        long startTime = System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String path = "E:/springUpload" + file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
        return "file/success";
    }

    /**
     *  使用 MultipartFile 上传文件
     * @param file
     * @return
     */
    @RequestMapping("/uploadMultipartFile.mvc")
    public ModelAndView uploadMultipartFile(MultipartFile file){
        //定义json 视图
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("file/success");
        //获取原文件名
        String fileName = file.getOriginalFilename();
        file.getContentType();
        //目标文件
        String path = "E:/" + new Date().getTime() + file.getOriginalFilename();
        File dest = new File(path);
        //保存文件
        try {
            file.transferTo(dest);

            System.out.println(dest.getPath());
            modelAndView.addObject("success",true);
            modelAndView.addObject("msg","上传文件成功");
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.addObject("success",false);
            modelAndView.addObject("msg","上传文件失败");
        }
        return modelAndView;
    }

    /**
     *  使用 part
     */
    @RequestMapping("/uploadPart.mvc")
    public ModelAndView uploadPart(Part file) throws IOException {
        //定义json 视图
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("file/success");
        //获取原文件名

        String fileName = file.getName();
        file.getContentType();
        //目标文件
        String path = "E:/" + new Date().getTime() + file.getName();
        File dest = new File(path);
        //保存文件
        file.write(path);
        return modelAndView;
    }

    /**
     * 返回当前项目的路径
     * @return
     */
    public static String getFilePath(){
        HttpSession session = getRequest().getSession();
        return session.getServletContext().getRealPath("/" );
    }
    /**
     * String url = "http://" + getRequest.getServerName() //服务器地址 + ":" +
     * getRequest.getServerPort() //端口号 +
     * getRequest.getContextPath() //项目名称 +
     * getRequest.getServletPath() //请求页面或其他地址 + "?" +
     * (getRequest.getQueryString()); //参数
     */
    public static String getDomainName(){
        return new StringBuilder("http://").append(getRequest().getServerName()).append(":").append(getRequest().getServerPort()).toString();
    }

    //获取Request
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
