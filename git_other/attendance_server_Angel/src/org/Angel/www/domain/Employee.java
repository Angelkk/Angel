package org.Angel.www.domain;

import java.io.Serializable;
import java.util.Date;

/*
定义员工表
*/
/*create table t_employee
(
	employee_id   int auto_increment primary key,
	employee_no   varchar(20),
	employee_name varchar(20),
	login_name    varchar(20),
	pass_word     varchar(20),
	job           varchar(20),
	hiredate      date,
	sal           numeric(7,2),
	role          int
);*/

public class Employee implements Serializable {
	private Integer employeeId;
	private String employeeNo;
	private String employeeName;
	private String loginName;
	private String password;
	private String job;
	private Date hiredate;
	private Float sal;
	private Integer role;

	public Employee() {

	}

	public Employee(Integer employeeId, String employeeNo, String employeeName, String loginName, String password,
			String job, Date hiredate, Float sal, Integer role) {
		this.employeeId = employeeId;
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.loginName = loginName;
		this.password = password;
		this.job = job;
		this.hiredate = hiredate;
		this.sal = sal;
		this.role = role;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public Float getSal() {
		return sal;
	}

	public void setSal(Float sal) {
		this.sal = sal;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeNo=" + employeeNo + ", employeeName=" + employeeName
				+ ", loginName=" + loginName + ", password=" + password + ", job=" + job + ", hiredate=" + hiredate
				+ ", sal=" + sal + ", role=" + role + "]";
	}

}
