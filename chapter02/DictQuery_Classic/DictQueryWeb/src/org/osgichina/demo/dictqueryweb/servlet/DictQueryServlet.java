package org.osgichina.demo.dictqueryweb.servlet;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgichina.demo.dictquery.query.QueryService;
/**
 * @desc 字典查询响应Servlet
 * @author chris
 */
public class DictQueryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private BundleContext context;
	
	public DictQueryServlet(BundleContext context){
		this.context=context;
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
    	//读取Request参数
    	String queryWord = request.getParameter("query_word");
    	response.setContentType("text/html");
    	ServletOutputStream output=response.getOutputStream();
    	
    	//获取服务
    	QueryService queryService = null;
    	ServiceReference serviceRef = context.getServiceReference(QueryService.class.getName());
    	if(null != serviceRef){
    		queryService = (QueryService) context.getService(serviceRef);
    	}
    	
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
