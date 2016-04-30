package org.osgichina.demo.remotedictquery.impl.test;

import org.osgichina.demo.remotedictquery.impl.RemoteDictQueryServiceImpl;

import junit.framework.TestCase;

public class RemoteDictQueryServiceImplTest extends TestCase {
	
	/**
	 * ���Ե������ֵ��е����
	 */
	public void testWordExists(){
		RemoteDictQueryServiceImpl dict = new RemoteDictQueryServiceImpl();
		assertEquals("���", dict.queryWord("sky"));
	}
	
	/**
	 * ���Ե��ʲ����ֵ��е����
	 */
	public void testWornNotExisted(){
		RemoteDictQueryServiceImpl dict = new RemoteDictQueryServiceImpl();
		assertEquals("N/A", dict.queryWord("not_existed_word"));
	}
	
	/**
	 *��������ĵ�����null����� 
	 */
	public void testQueryWordIsNull(){
		RemoteDictQueryServiceImpl dict = new RemoteDictQueryServiceImpl();
		try{
			dict.queryWord(null);
			assertTrue(false);
		}
		catch(NullPointerException e){
			assertTrue(true);
		}
		
	}

}
