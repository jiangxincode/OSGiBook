package org.osgichina.demo.remotedictquery.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.osgichina.demo.dictquery.query.QueryService;

/**
 * @desc Զ�̲�ѯ����
 * @author chris
 *
 */
public class RemoteDictQueryServiceImpl implements QueryService {
	private static final ConcurrentHashMap<String, String> dict =
		new ConcurrentHashMap<String, String>();
	static {
		dict.put("sky", "���");
		dict.put("computer", "�����");
	}
	
	@Override
	public String queryWord(String word) {
		System.out.println("RemoteDictQueryServiceImpl.queryWord called!");
		String result = dict.get(word);
		if(null == result){
			result = "N/A";
		}
		return result;
	}
}
