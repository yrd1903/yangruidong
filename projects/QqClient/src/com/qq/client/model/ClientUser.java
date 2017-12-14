package com.qq.client.model;

import com.qq.common.User;

public class ClientUser {
	
	ClientConServer ccs = null;
	
	public ClientUser(){
		ccs = new ClientConServer();
	}
	
	public boolean checkUserAndConnect(User u){
		boolean res = ccs.checkUser(u);
		if(res){
			ccs.connectServer(u);
		}
		return res;
	}
}



