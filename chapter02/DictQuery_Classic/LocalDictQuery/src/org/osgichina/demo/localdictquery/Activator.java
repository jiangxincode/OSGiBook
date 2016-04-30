package org.osgichina.demo.localdictquery;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgichina.demo.dictquery.query.QueryService;
import org.osgichina.demo.localdictquery.impl.LocalDictQueryServiceImpl;

public class Activator implements BundleActivator {
	private ServiceRegistration sr = null;
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		//注册服务
		sr = context.registerService(QueryService.class.getName(),
				new LocalDictQueryServiceImpl(), null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		//注销服务
		sr.unregister();
	}
}
