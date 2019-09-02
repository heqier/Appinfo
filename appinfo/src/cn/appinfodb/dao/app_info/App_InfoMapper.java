package cn.appinfodb.dao.app_info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.App_Info;

public interface App_InfoMapper {
	//����������ѯApp��Ϣ�б�
	public List<App_Info> getApp_InfoList(App_Info info);
	//����������ѯapp��Ϣ����
	public Integer getApp_InfoCount(App_Info info);
	//����app��Ϣ
	public int addApp_Info(App_Info info);
	//��ѯ�Ƿ������ͬ��APKName
	public App_Info getApp_InfoByAPKName(@Param("APKName")String APKName);
	//����id��ѯapp��Ϣ
	public App_Info getApp_InfoById(@Param("id")Integer id);
	//�޸�app��Ϣ
	public int modifyApp_Info(App_Info info);
}
