package cn.appinfodb.service.data_dictionary;

import java.util.List;

import cn.appinfodb.pojo.Data_Dictionary;

public interface Data_DictionaryService {
	//查询所属列表
	public List<Data_Dictionary> flatFormList();
	//查询app状态列表
	public List<Data_Dictionary> statusList();
}
