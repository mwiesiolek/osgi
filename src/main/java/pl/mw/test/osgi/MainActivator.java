package pl.mw.test.osgi;

import org.apache.felix.bundlerepository.RepositoryAdmin;
import org.apache.felix.bundlerepository.Resolver;
import org.apache.felix.bundlerepository.Resource;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
        service.test();

        Executors.newScheduledThreadPool(1).schedule(() -> {
            try{
                ServiceReference<RepositoryAdmin> repositoryAdminServiceReference = ctx.getServiceReference(RepositoryAdmin.class);
                RepositoryAdmin repositoryAdmin = ctx.getService(repositoryAdminServiceReference);

                repositoryAdmin.addRepository("http://localhost:8181/cave/http/my-repo-2-repository.xml");
                Resource[] resources = repositoryAdmin.discoverResources("(&(symbolicname=osgi)(version=3.0.0-SNAPSHOT))");
                log.info("Resource found: {}", resources.length);

                Resolver resolver = repositoryAdmin.resolver();
                resolver.add(resources[0]);

                if(resolver.resolve()){
                    resolver.deploy(Resolver.START);
                }
            }catch(Exception e) {
                log.error(e.getMessage(), e);
            }

        }, 10, TimeUnit.SECONDS);
    }

    public void stop(BundleContext context) {
        log.info("MainActivator stopped.");

        serviceRegistration.unregister();
    }
}
