package com.bj58.web.pc.controllers;

import java.util.Date;
import java.util.List;

import com.bj58.wf.mvc.ActionResult;
import com.bj58.wf.mvc.annotation.POST;
import com.bj58.wf.mvc.annotation.Path;
import com.bj58.ycs.tool.webutil.actionresult.ActionResult4JSON;
import com.bj58.ycs.tool.webutil.tools.ParamHelper;
import com.gshy.web.service.entity.Employee;

@Path("/regist")
public class RegistController extends BaseController{
	
	@Path("/add")
	public ActionResult addEmp() {
		return ActionResult.view("addemp");
	}
	
	
	@Path("/list")
	public ActionResult registList(){
		try {
			List<Employee> employees = employeeBLL.getAllEmployeeValid();
			commonTools(beat);
			beat.getModel().add("employees",employees);
			return ActionResult.view("/employeelist");
		} catch (Exception e) {
			log.info("获取员工列表出错",e);
		}
		return ActionResult.view("/employeelist");
	}
	
	/**
	 * 快速注册
	 * @return
	 */
	@Path("/fast")
	@POST
	public ActionResult fastRegist(){
		try {
			String phone = ParamHelper.getString(beat,"phone","").trim();
			String userName = ParamHelper.getString(beat,"userName","").trim();
			String email = ParamHelper.getString(beat, "email", "").trim();
			String password = ParamHelper.getString(beat, "password", "").trim();
			log.info("phone: "+phone);
			log.info("userName: "+userName);
			log.info("email: "+email);
			log.info("password: "+password);
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
			emp.setUserName(userName);
			emp.setPhone(phone);
			long resultId = employeeBLL.addEmployee(emp);
			log.info("注册结果ID: "+resultId);
			return new ActionResult4JSON("{\"ret\":\"1\",\"msg\":\"success!\"}");
		} catch (Exception e) {
			log.error("RegistController fastRegist error",e);
			e.printStackTrace();
		}
		return new ActionResult4JSON("{\"ret\":\"-1\",\"msg\":\"fail!\"}");
	}
	
	/**
	 * 离职
	 */
	@Path("/remove")
	@POST
	public ActionResult remove(long empId){
		try {
			Employee emp = employeeBLL.getById(empId);
			if(emp==null){
				return new ActionResult4JSON("{\"ret\":\"-1\",\"msg\":\"此用户不存在!\"}");
			}
			employeeBLL.removeById(empId);
			return new ActionResult4JSON("{\"ret\":\"1\",\"msg\":\"success!\"}");
		} catch (Exception e) {
			log.error("RegistController editMsg error",e);
			e.printStackTrace();
		}
		return new ActionResult4JSON("{\"ret\":\"-1\",\"msg\":\"fail!\"}");
		
	}
}
