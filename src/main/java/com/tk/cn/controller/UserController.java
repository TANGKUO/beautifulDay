package com.tk.cn.controller;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.tk.cn.entity.UserEntity;
import com.tk.cn.service.UserService;
import com.tk.cn.utils.CommonUtil;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.tk.com
 * </p>
 * 
 * @author tangkuo
 * @date 2017年3月11日 下午3:57:34
 */
@Controller
@RequestMapping({ "/sys/user" })
public class UserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@RequestMapping({ "/index" })
	public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "base/userList";
	}

	@RequestMapping({ "/add" })
	public String add(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "base/userAdd";
	}

	@RequestMapping({ "/edit" })
	public String edit(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		UserEntity userEntity = userService.getOneById(Long.parseLong(id));
		model.put("userEntity", userEntity);

		return "base/userAdd";
	}

	@RequestMapping({ "/del" })
	public String delete(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		UserEntity userEntity = userService.getOneById(Long.parseLong(id));
		userService.del(userEntity);
		return "base/userList";
	}

	@RequestMapping({ "/view" })
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		UserEntity userEntity = userService.getOneById(Long.parseLong(id));
		model.put("userEntity", userEntity);
		return "base/userView";
	}

	@RequestMapping({ "/save" })
	public String save(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserEntity userEntity = new UserEntity();
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			userEntity.setId(Long.valueOf(id));
		}
		userEntity.setLoginName(loginName);
		userEntity.setPassword(password);
		userEntity.setUserName(userName);
		userEntity.setEmail(email);
		userEntity.setMobile(mobile);
		userEntity.setCreateTime(new Timestamp(new Date().getTime()));
		userService.save(userEntity);
		return "base/userList";
	}

	@RequestMapping({ "/list" })
	public void list(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setCharacterEncoding("utf-8");
		Gson gs = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		String userName = request.getParameter("userName");
		String tpage = request.getParameter("page");
		String row = request.getParameter("rows");
		int page = Integer.parseInt(tpage);
		int pageSize = Integer.parseInt(row);// 每页显示的页数
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName(userName);
		List<Map<String, Object>> list = userService.getRightList(userEntity, page, pageSize);
		int total = userService.findCount(userEntity);
		logger.info("total ==" + total);
		if (CommonUtil.isNotNull(list)) {
			result.put("total", total);
			result.put("rows", list);
		} else {
			result.put("total", 0);
			result.put("rows", 0);
		}
		String jsonstring = gs.toJson(result);
		PrintWriter pw;
		pw = response.getWriter();
		logger.info("jsonstring ==" + jsonstring);
		pw.print(jsonstring);
	}

}
