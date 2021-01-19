package com.gstar.service;

import com.alibaba.fastjson.JSONObject;
import com.gstar.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserService {



	public String say(){
		User user = new User();
		user.setId(1);
		user.setName("张三");
		return JSONObject.toJSONString(user);
	}


}
