package cn.appinfodb.service.app_category;

import java.util.List;

import cn.appinfodb.pojo.App_Category;

public interface App_CategoryService {
	//��ѯһ������
	public List<App_Category> categoryLevel1List(Integer parentId);
}
