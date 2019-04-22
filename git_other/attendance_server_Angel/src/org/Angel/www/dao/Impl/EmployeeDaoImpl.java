package org.Angel.www.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Angel.www.dao.EmployeeDao;
import org.Angel.www.domain.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private Connection conn;

	public EmployeeDaoImpl(Connection conn) {
		this.conn = conn;
	}

	public EmployeeDaoImpl() {

	}

	@Override
	public boolean insertEmployee(Employee employee) {

		try {
			PreparedStatement pstmt = conn
					.prepareStatement("insert into t_employee(" + "employee_no," + "employee_name," + "login_name,"
							+ "pass_word," + "job," + "hiredate," + "sal," + "role) " + "values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, employee.getEmployeeNo());
			pstmt.setString(2, employee.getEmployeeName());
			pstmt.setString(3, employee.getLoginName());
			pstmt.setString(4, employee.getPassword());
			pstmt.setString(5, employee.getJob());
			pstmt.setDate(6, new java.sql.Date(employee.getHiredate().getTime()));
			pstmt.setFloat(7, employee.getSal());
			pstmt.setInt(8, employee.getRole());

			return pstmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Employee> queryEmployeeList(Employee inEmployee) {

		List<Employee> employeeList = new ArrayList<>();

		try {
			PreparedStatement pstmt = conn
					.prepareStatement("select * from t_employee where login_name=? and pass_word=? and role=?");
			pstmt.setString(1, inEmployee.getLoginName());
			pstmt.setString(2, inEmployee.getPassword());
			pstmt.setInt(3, inEmployee.getRole());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int employeeId = rs.getInt("employee_id");
				String employeeNo = rs.getString("employee_no");
				String employeeName = rs.getString("employee_name");
				String loginName = rs.getString("login_name");
				String passWord = rs.getString("pass_word");
				String job = rs.getString("job");
				java.util.Date hiredate = rs.getDate("hiredate");
				Float sal = rs.getFloat("sal");
				Integer role = rs.getInt("role");

				Employee outEmployee = new Employee();
				outEmployee.setEmployeeId(employeeId);
				outEmployee.setEmployeeNo(employeeNo);
				outEmployee.setEmployeeName(employeeName);
				outEmployee.setLoginName(loginName);
				outEmployee.setPassword(passWord);
				outEmployee.setJob(job);
				outEmployee.setHiredate(hiredate);
				outEmployee.setSal(sal);
				outEmployee.setRole(role);

				employeeList.add(outEmployee);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employeeList;
	}

}
