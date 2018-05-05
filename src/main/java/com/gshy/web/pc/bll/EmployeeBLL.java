package com.gshy.web.pc.bll;

import org.apache.commons.lang3.StringUtils;

import com.darengong.tools.dao.v2.DBQuery;
import com.gshy.web.pc.dao.EmployeeDAO;
import com.gshy.web.pc.entity.Employee;

public class EmployeeBLL {
	
	private static final EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
	
	public long addEmployee(Employee employee) throws Exception{
		return employeeDAO.add(employee);
	}
	
	public Employee getByEmail(String email) throws Exception{
		if(StringUtils.isBlank(email)){
			return null;
		}
		DBQuery dbQuery = new DBQuery();
		dbQuery.select().from("tbl_employee")
		.where().column("email").equal(email);
		return employeeDAO.getOne(dbQuery);
	}
}
