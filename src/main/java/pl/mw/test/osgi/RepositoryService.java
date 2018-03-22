package pl.mw.test.osgi;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.repository.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component(immediate = true)
public class RepositoryService {

    private List<Repository> repos = new CopyOnWriteArrayList<>();

    @Reference(cardinality = ReferenceCardinality.MULTIPLE)
    void addRepository(Repository repo) {
        repos.add(repo);
    }

    void removeRepository(Repository repo) {
        repos.remove(repo);
    }

    public List<Repository> getRepos() {
        return repos;
    }
}
