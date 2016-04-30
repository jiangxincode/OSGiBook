package org.osgichina.petstore.bootstrap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgichina.petstore.bootstrap.actionhandler.ActionHandler;
import org.osgichina.petstore.bootstrap.actionhandler.ActionHandlerMap;
import org.osgichina.petstore.bootstrap.pagetemplate.DefaultPage;
import org.osgichina.petstore.bootstrap.pagetemplate.PageFooter;
import org.osgichina.petstore.bootstrap.pagetemplate.PageHeader;
import org.springframework.osgi.context.BundleContextAware;

/**
 * 总控的Servlet，空置整个页面的生成
 * @author chris
 *
 */
public class ControllerServlet extends HttpServlet implements BundleContextAware, ServiceListener{

	private static final long serialVersionUID = 1L;

	/**
	 * 注入的BundleContext
	 */
	private BundleContext bundleContext;

	/**
	 * 注入的页脚对象，负责页脚的渲染
	 */
	private PageFooter pageFooter;
	
	/**
	 * 注入的页眉对象，负责页眉的渲染
	 */
	private PageHeader pageHeader;

	/**
	 * HttpService的服务引用
	 */
	private ServiceReference ref;
	
	/**
	 * 保存请求路径和对应ActionHandler的Map
	 */
	private ConcurrentHashMap<String, ActionHandler> actionHandlerMap = new ConcurrentHashMap<String, ActionHandler>();

	/**
	 * 所有加载的模块的提供的默认页面列表
	 */
	private List<DefaultPage> defaultPages = new ArrayList<DefaultPage>();
	
	/**
	 * Web应用的请求URI前缀
	 */
	private String servletPath;
	
	/**
	 * Web应用的资源URI的前缀
	 */
	private String resourcePath;
	

	public String getServletPath() {
		return servletPath;
	}

	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}


	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	/** 
     * Web GET Method 
	 */
    public void doGet(HttpServletRequest request,  HttpServletResponse response)
        throws IOException{   	
    	StringBuilder sb = new StringBuilder();
    	PageFooter pageFooter = this.pageFooter;
    	PageHeader pageHeader = this.pageHeader;
    	
    	//获取请求的路径信息
    	String pathInfo = request.getPathInfo();
    	
    	ActionHandler actionHandler = null;
    	
    	//请求类似于http://localhost:8080/petstore或者http://localhost:8080/petstore/productlist
    	//这里petstore就是我们设定的servletPath，而pathInfo就是/petstore后面的部分
    	//对于http://localhost:8080/petstore这个请求，pathInfo就是null
    	//对于http://localhost:8080/petstore/productlist这个请求，pathInfo就是productlist
    	if(pathInfo != null && pathInfo.length() > 0){
    		//路径不为空
    		String tempString = null;
    		//将连续的2个"//"替换为"/"，知道不存在"//"
    		while(true){
    			tempString = pathInfo.replaceAll("//", "/");
    			if(tempString.equals(pathInfo)){
    				break;
    			}
    		}
    		//去掉最前端的"/"
    		pathInfo = pathInfo.substring(1);
    		
    		//查找请求中的参数，得到的PathInfo是去掉参数的
    		int index = pathInfo.indexOf("?");
    		if(index > 0){
    			pathInfo = pathInfo.substring(0, index - 1);
    		}
    	}
    	
    	if(null == pathInfo || pathInfo.length() == 0){
    		//如果请求的信息没有相关的路径信息，则尝试使用默认页面
        	if(this.defaultPages.size() > 0){
        		pathInfo = this.defaultPages.get(0).getUri();
        	}
    	}
    	
		if(pathInfo != null){
			//根据pathInfo获取对应的handler。
			actionHandler = this.actionHandlerMap.get(pathInfo.toLowerCase());
		}

    	sb.append("<html><head>\n");
    	if(null != pageHeader){
    		//生成Header需要加入到头部的信息，就是在<body>之前的，一般是CSS的引用等
    		sb.append(pageHeader.getHeadInfo(resourcePath));
    	}

    	if(null != actionHandler){
    		//生成的主体页面需要加入到头部的信息，就是在<body>之前的，一般是CSS的引用等
    		sb.append(actionHandler.getHeadInfo(resourcePath));
    	}
    	
    	if(null != pageFooter){
    		//生成Footer需要加入到头部的信息，就是在<body>之前的，一般是CSS的引用等
    		sb.append(pageFooter.getHeadInfo(resourcePath));
    	}

    	//构造<body>的内容
    	sb.append("</head><body>\n");
    	sb.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n");
    	if(null != pageHeader){
    		//先加入Header
    		sb.append("<tr>\n");
    		sb.append(pageHeader.getPageHeader(servletPath, resourcePath));
    		sb.append("</tr>\n");
    	}
    	sb.append("<tr>\n");
    	sb.append("<td colspan=\"2\"><hr/></td>\n");
    	sb.append("</tr>\n");
    	sb.append("<tr>\n");
    	sb.append("<td colspan=\"2\">&nbsp;</td>\n");
    	sb.append("</tr>\n");

    	if(null != actionHandler){
    		//加入主体页面
    		sb.append("<tr>\n");
    		sb.append(actionHandler.handleRequest(request, servletPath, resourcePath));
    		sb.append("</tr>\n");
    	}
    	
    	sb.append("<tr>\n");
    	sb.append("<td colspan=\"2\"><hr/></td>\n");
    	sb.append("</tr>\n");
    	sb.append("<tr>\n");
    	sb.append("<td colspan=\"2\">&nbsp;</td>\n");
    	sb.append("</tr>\n");
	
    	if(null != pageFooter){
    		//加入Footer
    		sb.append("<tr>\n");
    		sb.append(pageFooter.getPageFooter(servletPath, resourcePath));
    		sb.append("</tr>\n");
    	}
    	sb.append("</table>");
    	sb.append("</body>");
    	sb.append("</html>");
    	response.setContentType("text/html");
    	response.setCharacterEncoding("gbk");
    	
    	ServletOutputStream output=response.getOutputStream();
    	output.println(sb.toString());
    	output.close();
    }
	
	/** 
     * Web Post Method 
	 */
    public void doPost(HttpServletRequest request,  HttpServletResponse response)
		      throws IOException{
		doGet(request, response);
	}

	public void start() throws Exception{
		registerServlet();
		this.bundleContext.addServiceListener(this, "(objectClass=" + HttpService.class.getName() + ")");
	}

	/**
	 * 获取HttpService
	 * @return HttpService
	 */
	private HttpService getHttpService(){
		if (ref == null){
            ref = bundleContext.getServiceReference(HttpService.class.getName());
        }
	 
        if (ref != null){
             return (HttpService) bundleContext.getService(ref);
        }
        return null;
		
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.ServiceListener#serviceChanged(org.osgi.framework.ServiceEvent)
	 */
	public void serviceChanged(ServiceEvent event) {
		switch (event.getType()){
        case ServiceEvent.REGISTERED:
        	//HttpService注册到OSGi容器，注册Servlet
            registerServlet();
            break;

        case ServiceEvent.UNREGISTERING:
        	//HttpService从OSGi容器注销，注销Servlet
            unregisterServlet();
            break;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.osgi.context.BundleContextAware#setBundleContext(org.osgi.framework.BundleContext)
	 */
	public void setBundleContext(BundleContext bundleContext ) {
		this.bundleContext = bundleContext ;
	}	
	
	/**
	 * 注入pageFooter对象
	 * @param pageFooter
	 */
	public void setPageFooter(PageFooter pageFooter){
		this.pageFooter = pageFooter;
	}

	/**
	 * 注入pageHeader对象
	 * @param pageHeader
	 */
	public void setPageHeader(PageHeader pageHeader){
		this.pageHeader = pageHeader;
	}

	/**
	 * 加入新的DefaultPage对象，并根据DefaultPage的priority进行排序
	 * @param defaultPage
	 * @param serviceProps
	 */
	public synchronized void onBind(DefaultPage defaultPage, Map<?,?> serviceProps){
		List<DefaultPage> defaultPages = new ArrayList<DefaultPage>(this.defaultPages.size() + 1);
		defaultPages.addAll(this.defaultPages);
		defaultPages.add(defaultPage);
		Collections.sort(defaultPages, new Comparator<DefaultPage>(){

			public int compare(DefaultPage o1, DefaultPage o2) {
				return o1.getPriority() - o2.getPriority();
			}
		});
		this.defaultPages = defaultPages;
	}

	/**
	 * 注销DefaultPage对象
	 * @param defaultPage
	 * @param serviceProps
	 */
	public synchronized void onUnbind(DefaultPage defaultPage, Map<?,?> serviceProps){
		List<DefaultPage> defaultPages = new ArrayList<DefaultPage>(this.defaultPages);
		defaultPages.remove(defaultPage);
		this.defaultPages = defaultPages;
	}

	/**
	 * 加入新的ActionHandlerMap对象
	 * @param actionHandlerMap
	 * @param serviceProps
	 */
	public synchronized void onBind(ActionHandlerMap actionHandlerMap, Map<?,?> serviceProps){
		Map<String, ActionHandler> map = actionHandlerMap.getActionHandlerMap();
		for(Map.Entry<String, ActionHandler> entry : map.entrySet()){
			if(null != this.actionHandlerMap.putIfAbsent(entry.getKey().toLowerCase(), entry.getValue())){
				throw new RuntimeException("注册的URI的Handler已经存在");
			}
		}
	}
	
	/**
	 * 注销ActionHandlerMap对象
	 * @param actionHandlerMap
	 * @param serviceProps
	 */
	public synchronized void onUnbind(ActionHandlerMap actionHandlerMap, Map<?,?> serviceProps){
		Map<String, ActionHandler> map = actionHandlerMap.getActionHandlerMap();
		for(Map.Entry<String, ActionHandler> entry : map.entrySet()){
			this.actionHandlerMap.remove(entry.getKey().toLowerCase());
		}
	}
	
	/*
	 * 注册Web应用
	 */
	private void registerServlet(){
        try {
            HttpService httpService = getHttpService();
            if(null != httpService){
       			httpService.registerServlet(servletPath, this, null, null);
            }
        } 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 卸载Web应用
	 */
	private void unregisterServlet(){
        try {
            HttpService httpService = getHttpService();
            if(null != httpService){
        		httpService.unregister(servletPath);
            }
        }
        catch(Exception e){
        	e.printStackTrace();
        }
	}
}
