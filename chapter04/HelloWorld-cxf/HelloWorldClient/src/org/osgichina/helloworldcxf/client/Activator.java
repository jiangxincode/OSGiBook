package org.osgichina.helloworldcxf.client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgichina.helloworldcxf.HelloWorldService;

/**
 * @author chris
 *
 */
public class Activator implements BundleActivator {
	private ServiceTracker tracker;
	
	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(final BundleContext bundleContext) throws Exception {
		//创建ServiceTracker，捕获HelloWorldService添加到OSGi框架的事件
        tracker = new ServiceTracker(bundleContext, HelloWorldService.class.getName(), null) {
            @Override
        	public Object addingService(ServiceReference reference) {
                Object result = super.addingService(reference);
                //获取服务
                HelloWorldService helloWorldService = (HelloWorldService)bundleContext.getService(reference);
                //使用服务
                System.out.println("call say hello at " + helloWorldService.sayHello());               
                return result;
            }
        };
        tracker.open();
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		tracker.close();
	}
}
