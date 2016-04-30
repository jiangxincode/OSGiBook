package org.osgichina.helloworldcxf.impl;


import java.util.Dictionary;
import java.util.Hashtable;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgichina.helloworldcxf.HelloWorldService;

/**
 * @author chris
 *
 */
public class Activator implements BundleActivator {
    private ServiceRegistration registration;

    /* (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext bundleContext) throws Exception {
    	//设置服务的属性
    	Dictionary<String, String> props = new Hashtable<String, String>();

        props.put("osgi.remote.interfaces", "*");
        props.put("osgi.remote.configuration.type", "pojo");
        props.put("osgi.remote.configuration.pojo.address", "http://localhost:9000/hello_world");
        
        //注册服务
        registration = bundleContext.registerService(HelloWorldService.class.getName(), 
                                          new HelloWorldServiceImpl(), props);
    }

    /* (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext bc) throws Exception {
        registration.unregister();
    }
}
