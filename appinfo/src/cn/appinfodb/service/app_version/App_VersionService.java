package cn.appinfodb.service.app_version;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.App_Version;

public interface App_VersionService {
	//��ѯ��ʷ�汾��Ϣ�б�
	public List<App_Version> getApp_VersionList(Integer id);
	//����app�汾��Ϣ
	public boolean addApp_Version(App_Version version)throws Exception ;
	//����id��ѯapp�汾��Ϣ
	public App_Version App_VersionById(Integer id);
	//�޸�app�汾��ϲ
	public boolean modifyApp_Version(App_Version version);
	//����appIdɾ��app�汾��Ϣ
	public boolean delApp_Version(Integer appId);
}
