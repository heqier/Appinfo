package cn.appinfodb.dao.app_info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.App_Info;

public interface App_InfoMapper {
	//����������ѯApp��Ϣ�б�
	public List<App_Info> getApp_InfoList(App_Info info);
	//����������ѯApp��Ϣ����
	public Integer getApp_InfoCount(App_Info info);
	//����App��Ϣ
	public int addApp_Info(App_Info info);
	//��ѯ�Ƿ������ͬ��APKName
	public App_Info getApp_InfoByAPKName(@Param("APKName")String APKName);
	//����id��ѯApp��Ϣ
	public App_Info getApp_InfoById(@Param("id")Integer id);
	//�޸�App��Ϣ
	public int modifyApp_Info(App_Info info);
	//�޸�versionId
	public int modifyApp_InfoByVersionId(@Param("id")Integer id,@Param("versionId")Integer versionId);
	//����Idɾ��App��Ϣ
	public int delApp_Info(@Param("id")Integer id);
}
