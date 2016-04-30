package org.osgichina.demo.remotedictquery.impl.test;

import org.osgichina.demo.remotedictquery.impl.RemoteDictQueryServiceImpl;

import junit.framework.TestCase;

public class RemoteDictQueryServiceImplTest extends TestCase {
	
	/**
	 * 测试单词在字典中的情况
	 */
	public void testWordExists(){
		RemoteDictQueryServiceImpl dict = new RemoteDictQueryServiceImpl();
		assertEquals("天空", dict.queryWord("sky"));
	}
	
	/**
	 * 测试单词不在字典中的情况
	 */
	public void testWornNotExisted(){
		RemoteDictQueryServiceImpl dict = new RemoteDictQueryServiceImpl();
		assertEquals("N/A", dict.queryWord("not_existed_word"));
	}
	
	/**
	 *测试输入的单词是null的情况 
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
