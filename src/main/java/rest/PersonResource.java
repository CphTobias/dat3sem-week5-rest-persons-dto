package rest;

import dtos.person.PersonDTO;
import dtos.person.PersonsDTO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    public Response getById(@PathParam("id") int id) {
        PersonDTO personDTO = REPO.getPersonRepo().getPersonById(id);
        return Response.ok(GSON.toJson(personDTO)).build();
    }

    @GET
    public Response getAll() {
        PersonsDTO personsDTO = REPO.getPersonRepo().getAllPersons();
        return Response.ok(GSON.toJson(personsDTO)).build();
    }

    @POST
    public Response addPerson(PersonDTO personDTO) {
        PersonDTO createdPerson = REPO.getPersonRepo().addPerson(personDTO);
        return Response.status(201).entity(GSON.toJson(createdPerson)).build();
    }

    @PUT
    @Path("/{id}")
    public Response editPerson(@PathParam("id") int id, PersonDTO personDTO) {
        personDTO.setId(id);
        PersonDTO updatedPerson = REPO.getPersonRepo().editPerson(personDTO);
        return Response.ok(GSON.toJson(updatedPerson)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        PersonDTO deletedPerson = REPO.getPersonRepo().deletePerson(id);
        return Response.ok(GSON.toJson(deletedPerson)).build();
    }
}
