package cn.appinfodb.service.data_dictionary;

import java.util.List;

import cn.appinfodb.pojo.Data_Dictionary;

public interface Data_DictionaryService {
	//��ѯ�����б�
	public List<Data_Dictionary> flatFormList();
	//��ѯapp״̬�б�
	public List<Data_Dictionary> statusList();
}
