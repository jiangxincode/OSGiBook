package org.osgichina.petstore.util;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.springframework.osgi.context.BundleContextAware;

/**
 * Web资源管理器，用于向HttpService注册和注销Web资源
 * @author chris
 *
 */
public class WebResourceMgr implements BundleContextAware, ServiceListener{
	private BundleContext bundleContext;
	
	private ServiceReference ref;
	
	private WebConfigMgr webConfigMgr;
	
	private String relativeResourcePath;
	
	private String name;
	
	public String getRelativeResourcePath() {
		return relativeResourcePath;
	}

	public void setRelativeResourcePath(String relativeResourcePath) {
		this.relativeResourcePath = relativeResourcePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WebConfigMgr getWebConfigMgr() {
		return webConfigMgr;
	}

	public void setWebConfigMgr(WebConfigMgr webConfigMgr) {
		this.webConfigMgr = webConfigMgr;
	}

	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
		
	}

	public void serviceChanged(ServiceEvent event) {
		switch (event.getType()){
        case ServiceEvent.REGISTERED:
            registerWebResource();
            break;

        case ServiceEvent.UNREGISTERING:
            unregisterWebResource();
            break;
		}
	}
	
	public void start() throws InvalidSyntaxException{
		registerWebResource();
		this.bundleContext.addServiceListener(this, "(objectClass=" + HttpService.class.getName() +
        ")");
	
	}

	private String getResourceAlias(){
		return webConfigMgr.getResourcePath() + "/" + getRelativeResourcePath();		
	}
	
	/**
	 * 注册资源
	 */
	private void registerWebResource(){
        try {
            HttpService httpService = getHttpService();
            if(null != httpService){
       			httpService.registerResources(getResourceAlias(), getName(), null);
            }
        } 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 注销资源
	 */
	private void unregisterWebResource(){
        try {
            HttpService httpService = getHttpService();
            if(null != httpService){
        		httpService.unregister(getResourceAlias());
            }
        }
        catch(Exception e){
        	e.printStackTrace();
        }
	}

	private HttpService getHttpService(){
		if (ref == null){
            ref = bundleContext.getServiceReference(HttpService.class.getName());
        }
	 
        if (ref != null){
             return (HttpService) bundleContext.getService(ref);
        }
        return null;
		
	}
	
}
