/**
 * 定义包的种类
 */
package com.qq.common;

public interface MessageType {

	String message_success = "1";//登录成功
	String message_login_fail = "2";//登录失败
	String message_com_mes = "3";//普通信息包
	String message_get_onLineFriend = "4";//要求在线好友
	String message_ret_onLineFriend = "5";//返回在线好友
}
