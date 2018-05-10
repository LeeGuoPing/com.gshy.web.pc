package com.bj58.web.pc.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bj58.web.pc.service.AuditService;
import com.bj58.wf.mvc.BeatContext;
import com.bj58.wf.mvc.MvcController;
import com.bj58.ycs.tool.webutil.tools.DateTool;
import com.bj58.ycs.tool.webutil.tools.Md5Helper;
import com.bj58.ycs.tool.webutil.tools.NumberTool;
import com.gshy.web.service.bll.AdvanceMoneyBLL;
import com.gshy.web.service.bll.EmployeeBLL;
import com.gshy.web.service.bll.MortgageBLL;
import com.gshy.web.service.entity.Employee;

public class BaseController extends MvcController{
	
	protected static final AuditService auditService = new AuditService();
	
	protected static final EmployeeBLL employeeBLL = new EmployeeBLL();
	
	protected static final MortgageBLL mortgageBLL = new MortgageBLL();
	
	protected static final AdvanceMoneyBLL advanceMoneyBLL = new AdvanceMoneyBLL();
	
	protected static final Map<Long,Employee> employeeMap = new HashMap<Long, Employee>();
	
	static{
		try {
			initEmployeeMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String defaultEnc(){
		return doubleEnc("aaa123");
	}
	
	private synchronized static void initEmployeeMap() throws Exception {
		List<Employee> employees = employeeBLL.getAllEmployee();
		for (Employee employee : employees) {
			employeeMap.put(employee.getId(), employee);
		}
		
	}

	public String doubleEnc(String password){
		return Md5Helper.md5Encrypt(Md5Helper.md5Encrypt(password));
	}
	
	public Map<Long,Employee> getAllEmployee() throws Exception{
		if(employeeMap==null || employeeMap.size()==0){
			initEmployeeMap();
		}
		return employeeMap;
	}
	
	public void commonTools(BeatContext beat){
		beat.getModel().add("dateTool", DateTool.getInstance());
		beat.getModel().add("numberTool",NumberTool.getInstance());
	}
}
