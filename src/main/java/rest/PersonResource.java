package rest;

import dtos.person.PersonDTO;
import dtos.person.PersonsDTO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class PersonResource extends Provider {

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        PersonDTO personDTO = repo.getPersonRepo().getPersonById(id);
        return Response.ok(gson.toJson(personDTO)).build();
    }

    @GET
    @Path("/all")
    public Response getAll() {
        PersonsDTO personsDTO = repo.getPersonRepo().getAllPersons();
        return Response.ok(gson.toJson(personsDTO)).build();
    }
}
