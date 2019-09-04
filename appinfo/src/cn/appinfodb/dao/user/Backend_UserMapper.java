package cn.appinfodb.dao.user;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.Backend_User;

public interface Backend_UserMapper {
	/**
	 * ²éÑ¯ËùÓĞ
	 */
	public Backend_User getuser(@Param("userCode") String userCode, @Param("userPassword") String userPassword);
}
