package pl.mw.test.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainActivator implements BundleActivator {
    private static final Logger log = LoggerFactory.getLogger(MainActivator.class);

    private ServiceRegistration serviceRegistration;

    public void start(BundleContext ctx) {
        log.info("MainActivator started.");

        serviceRegistration = ctx.registerService(
                RepositoryService.class,
                new RepositoryService(),
                null
        );

        ServiceReference ref = ctx.getServiceReference(RepositoryService.class);
        RepositoryService service = (RepositoryService) ctx.getService(ref);

        log.info("Number of repos: {}", service.getRepos().size());
        service.getRepos().forEach(r -> log.info(r.toString()));
    }

    public void stop(BundleContext context) {
        log.info("MainActivator stopped.");

        serviceRegistration.unregister();
    }
}
