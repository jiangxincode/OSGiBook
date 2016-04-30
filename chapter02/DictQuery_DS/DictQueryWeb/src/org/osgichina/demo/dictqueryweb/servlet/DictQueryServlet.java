package org.osgichina.demo.dictqueryweb.servlet;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.http.HttpService;
import org.osgichina.demo.dictquery.query.QueryService;
/**
 * @desc �ֵ��ѯ��ӦServlet
 * @author chris
 */
public class DictQueryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private HttpService httpService;
	
	private QueryService queryService;
	
	
	/**
	 * ע��HttpService
	 * @param httpService
	 */
	public void setHttpService(HttpService httpService){
		this.httpService = httpService;
		try {
			httpService.registerServlet("/demo/dictquery", this, null, null);
			httpService.registerResources("/demo/page","page",null);
			System.out.println("�������ֵ��ѯ��Ӧģ�飬��ͨ��/demo/page/dictquery.htm����");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * ע��HttpService
	 * @param httpService
	 */
	public void unsetHttpService(HttpService httpService){
		if(httpService != this.httpService){
			return;
		}
		this.httpService.unregister("/demo/dictquery");
		this.httpService.unregister("/demo/page");
		System.out.println("��ж���ֵ��ѯ��Ӧģ�飡");
		
		this.httpService = null;
	}
	
	/**
	 * ע��QueryService
	 * @param queryService
	 */
	public void setQueryService(QueryService queryService){
		this.queryService = queryService;
	}
	
	/**
	 * ע��QueryService
	 * @param queryService
	 */
	public void unsetQueryService(QueryService queryService){
		if(queryService != this.queryService){
			return;
		}
		this.queryService = null;
	}
	
	/** 
     * Web Post Method 
	 */
    public void doPost(HttpServletRequest request,  HttpServletResponse response)
		      throws IOException{
		doGet(request, response);
	}

	/** 
     * Web GET Method 
	 */
    public void doGet(HttpServletRequest request,  HttpServletResponse response)
        throws IOException{
    	String queryWord = request.getParameter("query_word");
    	response.setContentType("text/html");
    	ServletOutputStream output=response.getOutputStream();
    	if(queryService == null){
    		output.println("No available dictquery service");
    		output.close();
    		return;
    	}
    	
    	try {
			output.println("Result is " + queryService.queryWord(queryWord));
			output.close();
			return;
		} 
    	catch (Exception e) {
			output.println("Error occurs");
			output.println(e.toString());
			output.close();
			return;
		}
    }
}
