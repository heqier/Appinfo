package cn.appinfodb.dao.app_version;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.App_Version;

public interface App_VersionMapper {
	//��ѯ��ʷ�汾��Ϣ�б�
	public List<App_Version> getApp_VersionList(@Param("id")Integer id);
	//����app�汾��Ϣ
	public int addApp_Version(App_Version version);
	//����id��ѯapp�汾��Ϣ
	public App_Version App_VersionById(@Param("id")Integer id);
	//�޸�app�汾��Ϣ
	public int modifyApp_Version(App_Version version);
	//����appIdɾ��app�汾��Ϣ
	public int delApp_Version(@Param("appId")Integer appId);
}
