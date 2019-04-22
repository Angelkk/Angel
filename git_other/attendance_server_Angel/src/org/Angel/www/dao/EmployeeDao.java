package org.Angel.www.dao;

import java.util.List;

import org.Angel.www.domain.Employee;




public interface EmployeeDao {
	
	public boolean insertEmployee(Employee employee);

	public List<Employee> queryEmployeeList(Employee employee);

}