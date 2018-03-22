package pl.mw.test.osgi;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component(immediate = true)
public class RepositoryService {
    private static final Logger log = LoggerFactory.getLogger(MainActivator.class);

    public void test() {
        log.info("All good.");
    }
}
