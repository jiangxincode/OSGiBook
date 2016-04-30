/*
 * 
 * OSGI Demo
 */
package org.osgichina.demo;
/**
 * desc: HelloWorld
 * 
 * @author chris
 */
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class HelloWorldActivator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("HelloWorldBundle started!");
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("HelloWorldBundle stopped!");
	}
}
