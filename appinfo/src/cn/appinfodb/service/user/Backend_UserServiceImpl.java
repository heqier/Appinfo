package cn.appinfodb.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.user.Backend_UserMapper;
import cn.appinfodb.pojo.Backend_User;

@Service
public class Backend_UserServiceImpl implements Backend_UserService {

	@Autowired
	private Backend_UserMapper backend_user;

	@Override
	public Backend_User getuser(String userCode, String userPassword) {

		return backend_user.getuser(userCode, userPassword);
	}

}
