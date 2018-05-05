package com.gshy.web.pc.dao;

import com.gshy.web.pc.dao.base.BaseDAO;
import com.gshy.web.pc.entity.Employee;

public class EmployeeDAO extends BaseDAO<Employee>{
	
	private static final EmployeeDAO employeeDAO = new EmployeeDAO();
	
	
	public static EmployeeDAO getInstance(){
		return employeeDAO;
	}
	
	private EmployeeDAO() {
		super(Employee.class);
	}

}
