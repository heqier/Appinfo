package cn.appinfodb.service.dev_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.dev_user.Dev_UserMapper;
import cn.appinfodb.pojo.Dev_User;

@Service
public class Dev_UserServiceImpl implements Dev_UserService{
	
	@Autowired
	private Dev_UserMapper dev_UserMapper;
	
	//µÇÂ½ÑéÖ¤
	public Dev_User doLogin(Dev_User user) {
		Dev_User duser=null;
		try {
			duser=dev_UserMapper.doLogin(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duser;
	}
	
}
