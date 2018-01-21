package pl.mw.test.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainActivator implements BundleActivator {
    private static final Logger log = LoggerFactory.getLogger(MainActivator.class);

    public void start(BundleContext context) {
        log.info("MainActivator started.");
    }

    public void stop(BundleContext context) {
        log.info("MainActivator stopped.");
    }
}
