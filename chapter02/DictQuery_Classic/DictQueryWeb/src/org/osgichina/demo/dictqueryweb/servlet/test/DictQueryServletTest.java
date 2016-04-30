package org.osgichina.demo.dictqueryweb.servlet.test;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import static org.easymock.EasyMock.expectLastCall;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgichina.demo.dictquery.query.QueryService;
import org.osgichina.demo.dictqueryweb.servlet.DictQueryServlet;

/**
 * @desc: 测试字典查询响应Servlet
 *
 * @author chris
 */
public class DictQueryServletTest extends TestCase {

	/**
	 * 测试登录成功时
	 * 
	 * @throws Exception
	 */
	public void testDoPostWhenQuerySuccess() throws Exception{
		StringBuilder sb = new StringBuilder();
		mockTest("test", "N/A", sb);
		assertEquals(true, sb.indexOf("N/A")!=-1);
	}
	
	/**
	 * 测试登录错误时
	 * 
	 * @throws Exception
	 */
	public void testDoPostWhenQueryException() throws Exception{
		StringBuilder sb = new StringBuilder();
		mockTest(null, "", sb);
		assertEquals(true, sb.indexOf("NullPointerException")!=-1);
	}
	
	/*
	 * Mock测试类的依赖
	 */
	private void mockTest(String queryWord, String queryResult, StringBuilder sb) throws Exception{
		
		//构造MockControl
		IMocksControl control = EasyMock.createControl();
		

		// Mock HTTP Request
		HttpServletRequest request=(HttpServletRequest)control.createMock(
				HttpServletRequest.class);
		request.getParameter("query_word");
		expectLastCall().andReturn(queryWord);

		//Mock Http Response
		HttpServletResponse response=(HttpServletResponse)control.createMock(
				HttpServletResponse.class);
		response.getOutputStream();
		expectLastCall().andReturn(new MockServletOutputStream(sb));
		response.setContentType("text/html");
		
		//Mock的QueryService 在采用Declarative Services后不需要Mock Service的获取部分
		QueryService queryService = (QueryService)control.createMock(
				QueryService.class);
		queryService.queryWord(queryWord);
		
		
		if(null == queryWord){
			expectLastCall().andThrow(new NullPointerException());
		}
		else{
			expectLastCall().andReturn(queryResult);
		}

		//Mock ServiceReference和BundleContext
		ServiceReference serviceRef = (ServiceReference)control.createMock(
				ServiceReference.class);
		BundleContext bc = (BundleContext)control.createMock(
				BundleContext.class);
		
		//设置预期行为和返回值
		bc.getServiceReference(QueryService.class.getName());
		expectLastCall().andReturn(serviceRef);
		
		bc.getService(serviceRef);
		expectLastCall().andReturn(queryService);
		
		control.replay();
		
		// Mock QueryService
		DictQueryServlet servlet=new DictQueryServlet(bc);
		servlet.doPost(request, response);
	}
	
	class MockServletOutputStream extends ServletOutputStream{
		private StringBuilder sb;
		public MockServletOutputStream(StringBuilder sb){
			this.sb = sb;
		}
		
		public void write(int b) throws IOException {
			
		}
		
		public void println(String msg){
			sb.append(msg);
			sb.append("\n");
		}
		
	}
	
}
