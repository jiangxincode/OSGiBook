package org.osgichina.demo.dictqueryweb.servlet.test;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import static org.easymock.EasyMock.expectLastCall;
import org.osgichina.demo.dictquery.query.QueryService;
import org.osgichina.demo.dictqueryweb.servlet.DictQueryServlet;

/**
 * @desc: ≤‚ ‘◊÷µ‰≤È—ØœÏ”¶Servlet
 *
 * @author chris
 */
public class DictQueryServletTest extends TestCase {

	/**
	 * ≤‚ ‘µ«¬º≥…π¶ ±
	 * 
	 * @throws Exception
	 */
	public void testDoPostWhenQuerySuccess() throws Exception{
		StringBuilder sb = new StringBuilder();
		mockTest("test", "N/A", sb);
		assertEquals(true, sb.indexOf("N/A")!=-1);
	}
	
	/**
	 * ≤‚ ‘µ«¬º¥ÌŒÛ ±
	 * 
	 * @throws Exception
	 */
	public void testDoPostWhenQueryException() throws Exception{
		StringBuilder sb = new StringBuilder();
		mockTest(null, "", sb);
		assertEquals(true, sb.indexOf("NullPointerException")!=-1);
	}
	
	/*
	 * Mock≤‚ ‘¿‡µƒ“¿¿µ
	 */
	private void mockTest(String queryWord, String queryResult, StringBuilder sb) throws Exception{
		
		//ππ‘ÏMockControl
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
		
		
		
		control.replay();
		
		// Mock QueryService
		DictQueryServlet servlet = new DictQueryServlet();
		QueryService queryService = new MockQueryService(queryResult);
		servlet.setQueryService(queryService);
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
	
	class MockQueryService implements QueryService {

		private String result;
		
		public MockQueryService(String result){
			this.result = result;
		}
		
		@Override
		public String queryWord(String word) {
			if(null == word){
				throw new NullPointerException();
			}
			return result;
			
		}
		
	}
	
}
