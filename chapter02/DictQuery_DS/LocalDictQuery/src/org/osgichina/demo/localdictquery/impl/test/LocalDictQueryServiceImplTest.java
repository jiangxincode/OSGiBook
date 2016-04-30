package org.osgichina.demo.localdictquery.impl.test;

import org.osgichina.demo.localdictquery.impl.LocalDictQueryServiceImpl;

import junit.framework.TestCase;

public class LocalDictQueryServiceImplTest extends TestCase {
	
	/**
	 * 测试单词在字典中的情况
	 */
	public void testWordExists(){
		LocalDictQueryServiceImpl dict = new LocalDictQueryServiceImpl();
		assertEquals("测试", dict.queryWord("test"));
	}
	
	/**
	 * 测试单词不在字典中的情况
	 */
	public void testWornNotExisted(){
		LocalDictQueryServiceImpl dict = new LocalDictQueryServiceImpl();
		assertEquals("N/A", dict.queryWord("not_existed_word"));
	}
	
	/**
	 *测试输入的单词是null的情况 
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
