package com.bj58.web.pc.controllers;

import javax.servlet.http.Cookie;

import com.bj58.wf.mvc.ActionResult;
import com.bj58.wf.mvc.annotation.GET;
import com.bj58.wf.mvc.annotation.Path;
import com.bj58.ycs.tool.webutil.actionresult.ActionResult4JSON;
import com.bj58.ycs.tool.webutil.tools.ParamHelper;
import com.gshy.web.service.entity.Employee;

@Path("/login")
public class LoginController extends BaseController{
	
	@Path("/fast")
	@GET
	public ActionResult fastLogin(){
		try {
			String email = ParamHelper.getString(beat, "email", "").trim();
			String password = ParamHelper.getString(beat, "password", "").trim();
			log.info("email is " + email + "password is " + password);
			Employee emp = employeeBLL.getByEmail(email);
			if (emp == null) {
				return new ActionResult4JSON("{\"ret\":\"-1\",\"msg\":\"用户名或密码错误!\"}");
			}
			String dataPassword = emp.getPassword();
			if (password.equals(dataPassword)) {
				Cookie cookie = new Cookie("uid", emp.getId()+"");
				cookie.setMaxAge(-1); // 关闭浏览器失效
				beat.getResponse().addCookie(cookie);
				return new ActionResult4JSON("{\"ret\":\"1\",\"msg\":\"登陆成功!\"}");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ActionResult4JSON("{\"ret\":\"-1\",\"msg\":\"用户名或密码错误!\"}");
	}
}
