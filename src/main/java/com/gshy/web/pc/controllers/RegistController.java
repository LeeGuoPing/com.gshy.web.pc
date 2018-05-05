package com.gshy.web.pc.controllers;

import java.util.Date;

import com.bj58.wf.mvc.ActionResult;
import com.bj58.wf.mvc.annotation.POST;
import com.bj58.wf.mvc.annotation.Path;
import com.bj58.ycs.tool.webutil.actionresult.ActionResult4JSON;
import com.bj58.ycs.tool.webutil.tools.ParamHelper;
import com.gshy.web.pc.entity.Employee;

@Path("/regist")
public class RegistController extends BaseController{
	
	/**
	 * 快速注册
	 * @return
	 */
	@Path("/fast")
	@POST
	public ActionResult fastRegist(){
		try {
			String phone = ParamHelper.getString(beat,"phone","");
			String userName = ParamHelper.getString(beat,"userName","");
			String email = ParamHelper.getString(beat, "email", "");
			String password = ParamHelper.getString(beat, "password", "");
			// 查询库中是否存在
			Employee employee = employeeBLL.getByEmail(email);
			if (employee != null) {
				return new ActionResult4JSON("{\"ret\":\"-1\",\"msg\":\"该用户已注册!\"}");
			}
			
			String encPassword = doubleEnc(password);
			
			Employee emp = new Employee();
			emp.setEmail(email);
			emp.setPassword(encPassword);
			emp.setAddTime(new Date());
			emp.setUseName(userName);
			emp.setPhone(phone);
			long resultId = employeeBLL.addEmployee(employee);
			log.info("注册结果ID: "+resultId);
			return new ActionResult4JSON("{\"ret\":\"1\",\"msg\":\"success!\"}");
		} catch (Exception e) {
			log.error("RegistController defaultRegist error",e);
		}
		return new ActionResult4JSON("{\"ret\":\"-1\",\"msg\":\"fail!\"}");
	}
}
