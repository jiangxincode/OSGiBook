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
 * @desc: �����ֵ��ѯ��ӦServlet
 *
 * @author chris
 */
public class DictQueryServletTest extends TestCase {

	/**
	 * ���Ե�¼�ɹ�ʱ
	 * 
	 * @throws Exception
	 */
	public void testDoPostWhenQuerySuccess() throws Exception{
		StringBuilder sb = new StringBuilder();
		mockTest("test", "N/A", sb);
		assertEquals(true, sb.indexOf("N/A")!=-1);
	}
	
	/**
	 * ���Ե�¼����ʱ
	 * 
	 * @throws Exception
	 */
	public void testDoPostWhenQueryException() throws Exception{
		StringBuilder sb = new StringBuilder();
		mockTest(null, "", sb);
		assertEquals(true, sb.indexOf("NullPointerException")!=-1);
	}
	
	/*
	 * Mock�����������
	 */
	private void mockTest(String queryWord, String queryResult, StringBuilder sb) throws Exception{
		
		//����MockControl
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
		
		//Mock��QueryService �ڲ���Declarative Services����ҪMock Service�Ļ�ȡ����
		QueryService queryService = (QueryService)control.createMock(
				QueryService.class);
		queryService.queryWord(queryWord);
		
		
		if(null == queryWord){
			expectLastCall().andThrow(new NullPointerException());
		}
		else{
			expectLastCall().andReturn(queryResult);
		}

		//Mock ServiceReference��BundleContext
		ServiceReference serviceRef = (ServiceReference)control.createMock(
				ServiceReference.class);
		BundleContext bc = (BundleContext)control.createMock(
				BundleContext.class);
		
		//����Ԥ����Ϊ�ͷ���ֵ
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
