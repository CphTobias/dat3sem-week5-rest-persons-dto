package rest;

import errorhandling.GenericExceptionMapper;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import utils.CorsFilter;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(CorsFilter.class);
        resources.add(GenericExceptionMapper.class);
        resources.add(WadlResource.class);
        resources.add(RenameMeResource.class);
        resources.add(PersonResource.class);
    }
    
}
