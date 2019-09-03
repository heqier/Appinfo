package cn.appinfodb.service.app_info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.app_info.App_InfoMapper;
import cn.appinfodb.pojo.App_Info;
@Service
public class App_InfoServiceImpl implements App_InfoService{
	
	@Autowired
	private App_InfoMapper infoMapper;
	
	@Override
	public List<App_Info> getApp_InfoList(App_Info info) {
		List<App_Info> infoList=null;
		try {
			infoList=infoMapper.getApp_InfoList(info);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return infoList;
	}

	@Override
	public Integer getApp_InfoCount(App_Info info) {
		Integer count=0;
		try {
			count=infoMapper.getApp_InfoCount(info);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public boolean addApp_Info(App_Info info) {
		boolean flag=false;
		try {
			if(infoMapper.addApp_Info(info)==1) {
				flag=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public App_Info getApp_InfoByAPKName(String APKName) {
		App_Info info=null;
		try {
			info=infoMapper.getApp_InfoByAPKName(APKName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public App_Info getApp_InfoById(Integer id) {
		App_Info info=null;
		try {
			info=infoMapper.getApp_InfoById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public boolean modifyApp_Info(App_Info info) {
		boolean flag=false;
		try {
			if(infoMapper.modifyApp_Info(info)==1) {
				flag=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean modifyApp_InfoByVersionId(Integer id, Integer versionId) {
		boolean flag=false;
		try {
			if(infoMapper.modifyApp_InfoByVersionId(id, versionId)==1) {
				flag=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delApp_Info(Integer id) {
		boolean flag=false;
		try {
			if(infoMapper.delApp_Info(id)==1) {
				flag=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

}
