package cn.appinfodb.dao.app_category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.App_Category;

public interface App_CategoryMapper {
	//��ѯһ������
	public List<App_Category> categoryLevel1List(@Param("parentId")Integer parentId);
}
