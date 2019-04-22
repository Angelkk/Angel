package org.Angel.www.service.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.Angel.www.dao.EmployeeDao;
import org.Angel.www.dao.Impl.EmployeeDaoImpl;
import org.Angel.www.db.JdbcUtils;
import org.Angel.www.domain.Employee;
import org.Angel.www.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDaoImpl employeeDao;

	public EmployeeServiceImpl() {
		employeeDao = new EmployeeDaoImpl(JdbcUtils.getConn());
	}

	@Override
	public boolean login(Employee employee) {
		// TODO Auto-generated method stub

		List<Employee> emplist = employeeDao.queryEmployeeList(employee);

		if (emplist != null && emplist.size() > 0) {
			return true;
		}

		return false;
	}

	
	@Override
	public boolean register(Employee employee) {

		JdbcUtils.startTransaction();
		boolean isRegisterSuccess = false;
		try {
			isRegisterSuccess = employeeDao.insertEmployee(employee);
			return isRegisterSuccess;
		} catch (Exception e) {
			try {
				JdbcUtils.getConn().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			JdbcUtils.commitTransaction();
		}

		return false;
	}

}