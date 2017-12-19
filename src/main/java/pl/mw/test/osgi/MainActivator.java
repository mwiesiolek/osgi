package pl.mw.test.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class MainActivator implements BundleActivator {
    public void start(BundleContext context) throws Exception {
        System.out.println("Start 1");
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Stop 1");
    }
}
