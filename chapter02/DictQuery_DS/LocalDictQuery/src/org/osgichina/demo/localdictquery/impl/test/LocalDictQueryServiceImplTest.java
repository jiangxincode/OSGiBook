package org.osgichina.demo.localdictquery.impl.test;

import org.osgichina.demo.localdictquery.impl.LocalDictQueryServiceImpl;

import junit.framework.TestCase;

public class LocalDictQueryServiceImplTest extends TestCase {
	
	/**
	 * ���Ե������ֵ��е����
	 */
	public void testWordExists(){
		LocalDictQueryServiceImpl dict = new LocalDictQueryServiceImpl();
		assertEquals("����", dict.queryWord("test"));
	}
	
	/**
	 * ���Ե��ʲ����ֵ��е����
	 */
	public void testWornNotExisted(){
		LocalDictQueryServiceImpl dict = new LocalDictQueryServiceImpl();
		assertEquals("N/A", dict.queryWord("not_existed_word"));
	}
	
	/**
	 *��������ĵ�����null����� 
	 */
	public void testQueryWordIsNull(){
		LocalDictQueryServiceImpl dict = new LocalDictQueryServiceImpl();
		try{
			dict.queryWord(null);
			assertTrue(false);
		}
		catch(NullPointerException e){
			assertTrue(true);
		}
		
	}

}
