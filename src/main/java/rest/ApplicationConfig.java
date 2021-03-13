package rest;

import java.util.HashSet;
import javax.ws.rs.ApplicationPath;
import utils.errorhandling.GenericExceptionMapper;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import utils.CorsFilter;

@ApplicationPath("")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(CorsFilter.class);
        resources.add(GenericExceptionMapper.class);
        resources.add(WadlResource.class);
        resources.add(RenameMeResource.class);
        resources.add(PersonResource.class);
        resources.add(CountryResource.class);
    }
    
}
