package org.Angel.www.listener;

/*
 * 回调监听接口
 * 	receiveCallBack回调方法：当客户端发送指令 ，立刻回调此方法
 * */
public interface RecevieCommandListener {
	
	public void receiveCallBack(String command);

}