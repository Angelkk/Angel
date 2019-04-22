package org.Angel.www.controller;

import java.util.List;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.Angel.www.domain.ClockData;
import org.Angel.www.domain.Employee;
import org.Angel.www.listener.RecevieCommandListener;
import org.Angel.www.service.ClockDataService;
import org.Angel.www.service.EmployeeService;
import org.Angel.www.service.Impl.ClockDataServiceImpl;
import org.Angel.www.service.Impl.EmployeeServiceImpl;
import org.Angel.www.utils.GsonUtils;
import org.Angel.www.utils.SocketUtils;


public class AttendaceServerController implements RecevieCommandListener{
	
	public final static int PORT = 30000;
	private EmployeeService employeeService;
	private ClockDataService clockDateService;
	private SocketUtils socketUtils;
	
	public AttendaceServerController(){
		this.employeeService = new EmployeeServiceImpl();
		this.clockDateService = new ClockDataServiceImpl();
	}
	


	public void service() {
		// TODO Auto-generated method stub
		ServerSocket serverSocket =null;
		try {
			serverSocket  = new ServerSocket(PORT);
			while (true) {
				System.out.println("考勤服务器启动，连接端口" + PORT);
			try {
				Socket socket = serverSocket.accept();
				socketUtils = new SocketUtils(socket, this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AttendaceServerController s = new AttendaceServerController();
		s.service();
		
	}


	@Override
	public void receiveCallBack(String command) {
		
		String flag =GsonUtils.compileJsonGetFlag(command);
		String message =  null;
		if (flag.equals("register")) {
			List<Employee>emoloyeeList = GsonUtils.compileJsonByEmployee(command);
			if (emoloyeeList !=null && emoloyeeList.size() > 0) {
				Employee employee = emoloyeeList.get(0);
				boolean isRegister = employeeService.register(employee);
				if (isRegister) {
					message = "用户注册成功";
				} else {
					message = "用户注册失败";
				}
				socketUtils.send(GsonUtils.booleanFlagJson(message));
			}
	}else if (flag.equals("employeelogin")) {
		List<Employee>emoloyeeList = GsonUtils.compileJsonByEmployee(command);
		if (emoloyeeList != null && emoloyeeList.size() > 0) {
			Employee employee =emoloyeeList.get(0);
			boolean isRegister = employeeService.login(employee);
			if (isRegister) {
				message = "员工登陆成功";
			} else {
				message = "员工登陆失败";
			}
			socketUtils.send(GsonUtils.booleanFlagJson(message));
		}
		
	}else if (flag.equals("adminlogin")) {
		List<Employee> employeeList = GsonUtils.compileJsonByEmployee(command);
		if (employeeList != null && employeeList.size()>0) {
			Employee employee = employeeList.get(0);
			boolean isRegister = employeeService.login(employee);
			if (isRegister) {
				message = "管理员登录成功";
			} else {
				message = "管理员登陆失败";
			}
			socketUtils.send(GsonUtils.booleanFlagJson(message));
		}
	}else if (flag.equals("adminCount")) {
		List<ClockData> clockDataList = clockDateService.adminCount();
		if (clockDataList.size()>0) {
			message = "员工信息查询成功";
		} else {
			message = "员工信息查询失败";
		}
		
		socketUtils.send(GsonUtils.compileJsonByClockDataList(message, clockDataList));
	}
		}
		
	}
	
	
