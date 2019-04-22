package org.Angel.www.service;

import java.util.List;

import org.Angel.www.domain.Employee;
import org.Angel.www.domain.ClockData;


public interface EmployeeService {

	public boolean login(Employee employee);
	public boolean register(Employee employee);
}
