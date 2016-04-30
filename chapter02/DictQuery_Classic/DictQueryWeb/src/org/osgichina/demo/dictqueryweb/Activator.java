package org.osgichina.demo.dictqueryweb;

import javax.servlet.Servlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgichina.demo.dictqueryweb.servlet.DictQueryServlet;


/**
 * @author chris
 *
 */
public class Activator implements BundleActivator, ServiceListener {

	// ------------------------------------------------Instance Variables
	
	private BundleContext bundleContext;
	
	private ServiceReference ref;
	
	private Servlet servlet;
	
	// ------------------------------------------------Public Method
	
	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		bundleContext = context;
		//创建Servlet对象
		servlet = new DictQueryServlet(bundleContext);
		
		//向HttpService注册Servlet
		registerServlet();
		
		//加入对HttpService的监听
		context.addServiceListener(this, "(objectClass=" + HttpService.class.getName() +
	            ")");
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		try {
			//注销Servlet
            unregisterServlet();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        servlet = null;
        bundleContext = null;
        ref = null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.ServiceListener#serviceChanged(org.osgi.framework.ServiceEvent)
	 */
	public void serviceChanged(ServiceEvent event) {
		switch (event.getType()){
            case ServiceEvent.REGISTERED:
            	//HttpService注册到OSGi容器的时候，进行Servlet的注册
                registerServlet();
                break;

            case ServiceEvent.UNREGISTERING:
            	//HttpService从OSGi容器注销的时候，注销Servlet
                unregisterServlet();
                break;
        }
	}
	
	// ------------------------------------------------Private Method
	
	/*
	 * 注册Web应用
	 */
	private void registerServlet(){
		if (ref == null){
            ref = bundleContext.getServiceReference(HttpService.class.getName());
        }
	 
        if (ref != null){
            try {
                HttpService http = (HttpService) bundleContext.getService(ref);
                if(null != http){
                	//注册Servlet
					http.registerServlet("/demo/dictquery", servlet, null, null);
					//注册资源
					http.registerResources("/demo/page","page",null);
					System.out.println("已启动字典查询web模块，请通过/demo/page/dictquery.htm访问");
                }
            } 
			catch (Exception e) {
				e.printStackTrace();
			}
        }
	}
	
	/*
	 * 卸载Web应用
	 */
	private void unregisterServlet(){
		if (ref != null) {
            try {
                HttpService http = (HttpService) bundleContext.getService(ref);
                if(null != http){
	                http.unregister("/demo/dictquery");
	                http.unregister("/demo/page");
	                System.out.println("已卸载字典查询web模块！");
                }
            }
            catch(Exception e){
            	e.printStackTrace();
            }
        }
	}
}
