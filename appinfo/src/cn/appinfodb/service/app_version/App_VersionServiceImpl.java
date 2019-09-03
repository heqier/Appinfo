package cn.appinfodb.service.app_version;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.app_version.App_VersionMapper;
import cn.appinfodb.pojo.App_Version;

@Service
public class App_VersionServiceImpl implements App_VersionService{
	
	@Autowired
	private App_VersionMapper app_VersionMapper;

	@Override
	public List<App_Version> getApp_VersionList(Integer id) {
		List<App_Version> versionList=null;
		try {
			versionList=app_VersionMapper.getApp_VersionList(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return versionList;
	}

	@Override
	public boolean addApp_Version(App_Version version)throws Exception {
		try {
			if(app_VersionMapper.addApp_Version(version)==1) {
				return true;
			}
				return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public App_Version App_VersionById(Integer id) {
		App_Version version=null;
		try {
			version=app_VersionMapper.App_VersionById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return version;
	}

	@Override
	public boolean modifyApp_Version(App_Version version) {
		try {
			if(app_VersionMapper.modifyApp_Version(version)==1) {
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public boolean delApp_Version(Integer appId) {
		try {
			if(app_VersionMapper.delApp_Version(appId)==1) {
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}

}
