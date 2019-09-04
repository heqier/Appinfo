package cn.appinfodb.service.app_category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.app_category.App_CategoryMapper;
import cn.appinfodb.pojo.App_Category;

@Service
public class App_CategoryServiceImpl implements App_CategoryService{
	
	@Autowired
	private App_CategoryMapper app_CategoryMapper;

	@Override
	public List<App_Category> categoryLevel1List(Integer parentId) {
		List<App_Category> Category=null;
		try {
			Category=app_CategoryMapper.categoryLevel1List(parentId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Category;
	}

}
