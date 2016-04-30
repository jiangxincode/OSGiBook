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
 * �ܿص�Servlet����������ҳ�������
 * @author chris
 *
 */
public class ControllerServlet extends HttpServlet implements BundleContextAware, ServiceListener{

	private static final long serialVersionUID = 1L;

	/**
	 * ע���BundleContext
	 */
	private BundleContext bundleContext;

	/**
	 * ע���ҳ�Ŷ��󣬸���ҳ�ŵ���Ⱦ
	 */
	private PageFooter pageFooter;
	
	/**
	 * ע���ҳü���󣬸���ҳü����Ⱦ
	 */
	private PageHeader pageHeader;

	/**
	 * HttpService�ķ�������
	 */
	private ServiceReference ref;
	
	/**
	 * ��������·���Ͷ�ӦActionHandler��Map
	 */
	private ConcurrentHashMap<String, ActionHandler> actionHandlerMap = new ConcurrentHashMap<String, ActionHandler>();

	/**
	 * ���м��ص�ģ����ṩ��Ĭ��ҳ���б�
	 */
	private List<DefaultPage> defaultPages = new ArrayList<DefaultPage>();
	
	/**
	 * WebӦ�õ�����URIǰ׺
	 */
	private String servletPath;
	
	/**
	 * WebӦ�õ���ԴURI��ǰ׺
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
    	
    	//��ȡ�����·����Ϣ
    	String pathInfo = request.getPathInfo();
    	
    	ActionHandler actionHandler = null;
    	
    	//����������http://localhost:8080/petstore����http://localhost:8080/petstore/productlist
    	//����petstore���������趨��servletPath����pathInfo����/petstore����Ĳ���
    	//����http://localhost:8080/petstore�������pathInfo����null
    	//����http://localhost:8080/petstore/productlist�������pathInfo����productlist
    	if(pathInfo != null && pathInfo.length() > 0){
    		//·����Ϊ��
    		String tempString = null;
    		//��������2��"//"�滻Ϊ"/"��֪��������"//"
    		while(true){
    			tempString = pathInfo.replaceAll("//", "/");
    			if(tempString.equals(pathInfo)){
    				break;
    			}
    		}
    		//ȥ����ǰ�˵�"/"
    		pathInfo = pathInfo.substring(1);
    		
    		//���������еĲ������õ���PathInfo��ȥ��������
    		int index = pathInfo.indexOf("?");
    		if(index > 0){
    			pathInfo = pathInfo.substring(0, index - 1);
    		}
    	}
    	
    	if(null == pathInfo || pathInfo.length() == 0){
    		//����������Ϣû����ص�·����Ϣ������ʹ��Ĭ��ҳ��
        	if(this.defaultPages.size() > 0){
        		pathInfo = this.defaultPages.get(0).getUri();
        	}
    	}
    	
		if(pathInfo != null){
			//����pathInfo��ȡ��Ӧ��handler��
			actionHandler = this.actionHandlerMap.get(pathInfo.toLowerCase());
		}

    	sb.append("<html><head>\n");
    	if(null != pageHeader){
    		//����Header��Ҫ���뵽ͷ������Ϣ��������<body>֮ǰ�ģ�һ����CSS�����õ�
    		sb.append(pageHeader.getHeadInfo(resourcePath));
    	}

    	if(null != actionHandler){
    		//���ɵ�����ҳ����Ҫ���뵽ͷ������Ϣ��������<body>֮ǰ�ģ�һ����CSS�����õ�
    		sb.append(actionHandler.getHeadInfo(resourcePath));
    	}
    	
    	if(null != pageFooter){
    		//����Footer��Ҫ���뵽ͷ������Ϣ��������<body>֮ǰ�ģ�һ����CSS�����õ�
    		sb.append(pageFooter.getHeadInfo(resourcePath));
    	}

    	//����<body>������
    	sb.append("</head><body>\n");
    	sb.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n");
    	if(null != pageHeader){
    		//�ȼ���Header
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
    		//��������ҳ��
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
    		//����Footer
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
	 * ��ȡHttpService
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
        	//HttpServiceע�ᵽOSGi������ע��Servlet
            registerServlet();
            break;

        case ServiceEvent.UNREGISTERING:
        	//HttpService��OSGi����ע����ע��Servlet
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
	 * ע��pageFooter����
	 * @param pageFooter
	 */
	public void setPageFooter(PageFooter pageFooter){
		this.pageFooter = pageFooter;
	}

	/**
	 * ע��pageHeader����
	 * @param pageHeader
	 */
	public void setPageHeader(PageHeader pageHeader){
		this.pageHeader = pageHeader;
	}

	/**
	 * �����µ�DefaultPage���󣬲�����DefaultPage��priority��������
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
	 * ע��DefaultPage����
	 * @param defaultPage
	 * @param serviceProps
	 */
	public synchronized void onUnbind(DefaultPage defaultPage, Map<?,?> serviceProps){
		List<DefaultPage> defaultPages = new ArrayList<DefaultPage>(this.defaultPages);
		defaultPages.remove(defaultPage);
		this.defaultPages = defaultPages;
	}

	/**
	 * �����µ�ActionHandlerMap����
	 * @param actionHandlerMap
	 * @param serviceProps
	 */
	public synchronized void onBind(ActionHandlerMap actionHandlerMap, Map<?,?> serviceProps){
		Map<String, ActionHandler> map = actionHandlerMap.getActionHandlerMap();
		for(Map.Entry<String, ActionHandler> entry : map.entrySet()){
			if(null != this.actionHandlerMap.putIfAbsent(entry.getKey().toLowerCase(), entry.getValue())){
				throw new RuntimeException("ע���URI��Handler�Ѿ�����");
			}
		}
	}
	
	/**
	 * ע��ActionHandlerMap����
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
	 * ע��WebӦ��
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
	 * ж��WebӦ��
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
