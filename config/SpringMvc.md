# Spring Web MVC 使用技巧总结

requestUrl ---> DispaterServlet --->HalderMapper ---> Controller  ---> Model(service,dao) --->Controller --->VierResoure --->jsp --->Controller ---> Response --->Client


1:SpringIOC  控制反转
	将Bean组件注入spring工厂,Bean与Bean之间的依赖关键，由spring统一管理.
	利用xml配置文件配置注入bean与扫描包,
	利用注解开发
	
	
	
	
2:springAOP


spring 解决中文乱码问题，直接配置filter在web.xml配置文件中，定义一个处理中文乱码的过滤器，

springMVC 拦截器的使用
	DispatcherSerlvet 处理请求requestUrl  
	HanderMapping  分发请求到 Controller
	Controller 调用 Model  ModelAndView 将结果返回到 ViewResolver
	视图解析器  找到对应得jsp   el  标签。
	最后输出响应输出到客户端浏览器
	
 1: preHandle         在Controller之前执行拦截器
 2: postHandle        在ModelAndView之后执行拦截器,ViewResolver之前
 3: afterCompletion   在jsp之后执行拦截器
 
 
	
	
	
	
	
	
	



