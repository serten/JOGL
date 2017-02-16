package impl.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator{

	@Override
	public void start(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Activator: plugin activated");
		new Impl();
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
