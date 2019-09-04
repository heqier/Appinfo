package cn.appinfodb.service.user;

import cn.appinfodb.pojo.Backend_User;

public interface Backend_UserService {
	public Backend_User getuser(String userCode, String userPassword);
}
