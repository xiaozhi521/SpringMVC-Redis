#### @Controller
    - 用于标连控制层组件
    - @Controller 标记在一个类上，使用它标记的类就是一个SpringMVC Controller 对象
    - 分发处理器将会扫描使用了该注解的类的方法，并检测该方法是否使用了 @RequestMapping 注解
    - 可以把 request 请求 header 部分的值绑定到方法参数上
#### @RestController
    - 相当于 @Controller 和 @RequestMapping 的组合效果
#### @Component
    - 泛指组件，当组件不好归类的时候，我们可以使用这个组件进行标注
#### @Repository
    - 用于注解 dao 层
#### @Service
    - 用于标注业务层组件
  

#### @ResponseBody
    - 异步请求
    - 该方法将 @Controller 方法返回的对象，通过适当的 HttpMessageConverter 转换为指定格式后，写入到 Response 对象的 body 数据区
    - 返回的数据不是 html标签页面，而是其他某种格式的数据时(如：json，xml等)使用
#### @RequestMapping
    - 一个用来处理请求地址映射的注解，可用于类或方法上
    - 用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径
#### @Autowired
    - 它可以对类成员变量，方法及构造函数进行标注，完成自动装配的工作。通过 @Autowired 的使用来消除set、get方法
#### @PathVariable
    - 用于将请求URL中的模板变量映射到功能处理方法的参数上，即取出url模板中的变量作为参数 
    - {xxx}占位 @PathVariable("xxx") 绑定到操作方法入参中
#### @RequestParam
    - 主要用于在SpringMVC后台控制层获取参数，类似 request.getParameter(String name)
    - value参数名
    - required是否必需。如果为true,请求参数中必需包含对应参数，不存在将抛出异常
    - public String handle(@RequestParam(value="userName",required=false)String userName,@RequestParam(value="age")String age){
         return "success";
     }
#### @RequestHeader
    - 可以把Request请求header部分的值绑定到方法的参数上
    - public String handle(@RequestHeader("Accept-Encoding")String encoding,@RequestHeader("Keep-Alive")String keepAlive){
        return "success";
      }
#### @CookieValue
    - 可让处方法入参绑定某个Cookie值
    - public String handle(@CookieValue("sessionId"，required=false)String sessionId){
        return "success";
     }
####MVC  Handler 方法可以接受哪些 ServletAPI 类型参数
    - HttpServletRequest, HttpServletResponse, HttpSession
    - java.security.Principal,Locale, 
    - InputStream, OutputStream, 
    - Reader, Writer











