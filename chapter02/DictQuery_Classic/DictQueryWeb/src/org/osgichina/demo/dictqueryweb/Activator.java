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
		//����Servlet����
		servlet = new DictQueryServlet(bundleContext);
		
		//��HttpServiceע��Servlet
		registerServlet();
		
		//�����HttpService�ļ���
		context.addServiceListener(this, "(objectClass=" + HttpService.class.getName() +
	            ")");
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		try {
			//ע��Servlet
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
            	//HttpServiceע�ᵽOSGi������ʱ�򣬽���Servlet��ע��
                registerServlet();
                break;

            case ServiceEvent.UNREGISTERING:
            	//HttpService��OSGi����ע����ʱ��ע��Servlet
                unregisterServlet();
                break;
        }
	}
	
	// ------------------------------------------------Private Method
	
	/*
	 * ע��WebӦ��
	 */
	private void registerServlet(){
		if (ref == null){
            ref = bundleContext.getServiceReference(HttpService.class.getName());
        }
	 
        if (ref != null){
            try {
                HttpService http = (HttpService) bundleContext.getService(ref);
                if(null != http){
                	//ע��Servlet
					http.registerServlet("/demo/dictquery", servlet, null, null);
					//ע����Դ
					http.registerResources("/demo/page","page",null);
					System.out.println("�������ֵ��ѯwebģ�飬��ͨ��/demo/page/dictquery.htm����");
                }
            } 
			catch (Exception e) {
				e.printStackTrace();
			}
        }
	}
	
	/*
	 * ж��WebӦ��
	 */
	private void unregisterServlet(){
		if (ref != null) {
            try {
                HttpService http = (HttpService) bundleContext.getService(ref);
                if(null != http){
	                http.unregister("/demo/dictquery");
	                http.unregister("/demo/page");
	                System.out.println("��ж���ֵ��ѯwebģ�飡");
                }
            }
            catch(Exception e){
            	e.printStackTrace();
            }
        }
	}
}
