package org.Angel.www.domain;

import java.util.Date;

public class ClockData {

	private Date workDate;
	private String employeeNo;
	private Date clockInTime;
	private Date clockOffTime;
	private String diffInStatus;
	private String diffOffStatus;
	public ClockData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClockData(Date workDate, String employeeNo, Date clockInTime, Date clockOffTime, String diffInStatus,
			String diffOffStatus) {
		super();
		this.workDate = workDate;
		this.employeeNo = employeeNo;
		this.clockInTime = clockInTime;
		this.clockOffTime = clockOffTime;
		this.diffInStatus = diffInStatus;
		this.diffOffStatus = diffOffStatus;
	}
	/**
	 * @return the workDate
	 */
	public Date getWorkDate() {
		return workDate;
	}
	/**
	 * @param workDate the workDate to set
	 */
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	/**
	 * @return the employeeNo
	 */
	public String getEmployeeNo() {
		return employeeNo;
	}
	/**
	 * @param employeeNo the employeeNo to set
	 */
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	/**
	 * @return the clockInTime
	 */
	public Date getClockInTime() {
		return clockInTime;
	}
	/**
	 * @param clockInTime the clockInTime to set
	 */
	public void setClockInTime(Date clockInTime) {
		this.clockInTime = clockInTime;
	}
	/**
	 * @return the clockOffTime
	 */
	public Date getClockOffTime() {
		return clockOffTime;
	}
	/**
	 * @param clockOffTime the clockOffTime to set
	 */
	public void setClockOffTime(Date clockOffTime) {
		this.clockOffTime = clockOffTime;
	}
	/**
	 * @return the diffInStatus
	 */
	public String getDiffInStatus() {
		return diffInStatus;
	}
	/**
	 * @param diffInStatus the diffInStatus to set
	 */
	public void setDiffInStatus(String diffInStatus) {
		this.diffInStatus = diffInStatus;
	}
	/**
	 * @return the diffOffStatus
	 */
	public String getDiffOffStatus() {
		return diffOffStatus;
	}
	/**
	 * @param diffOffStatus the diffOffStatus to set
	 */
	public void setDiffOffStatus(String diffOffStatus) {
		this.diffOffStatus = diffOffStatus;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClockData [workDate=" + workDate + ", employeeNo=" + employeeNo + ", clockInTime=" + clockInTime
				+ ", clockOffTime=" + clockOffTime + ", diffInStatus=" + diffInStatus + ", diffOffStatus="
				+ diffOffStatus + "]";
	}
	
	
}


