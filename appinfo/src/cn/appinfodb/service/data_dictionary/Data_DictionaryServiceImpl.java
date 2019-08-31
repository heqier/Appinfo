package cn.appinfodb.service.data_dictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.data_dictionary.Data_DictionaryMapper;
import cn.appinfodb.pojo.Data_Dictionary;

@Service
public class Data_DictionaryServiceImpl implements Data_DictionaryService{
	
	@Autowired
	private Data_DictionaryMapper  data_DictionaryMapper;
	
	@Override
	public List<Data_Dictionary> flatFormList() {
		List<Data_Dictionary>  dictionary=null;
		try {
			dictionary=data_DictionaryMapper.flatFormList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dictionary;
	}

	@Override
	public List<Data_Dictionary> statusList() {
		List<Data_Dictionary>  dictionary=null;
		try {
			dictionary=data_DictionaryMapper.statusList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dictionary;
	}

}
