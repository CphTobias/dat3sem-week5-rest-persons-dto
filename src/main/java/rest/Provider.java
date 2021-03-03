package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.person.PersonRepository;
import entities.renameme.RenameMeRepository;
import facades.FacadeExample;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

public class Provider {

    public static Repository repo;
    public static Gson gson;

    static {
        repo = createRepository();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    private static Repository createRepository() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();

        RenameMeRepository renameMeRepository = FacadeExample.getFacadeExample(emf);
        PersonRepository personRepository = PersonFacade.getInstance(emf);

        return new Repository(
            renameMeRepository,
            personRepository
        );
    }
}

class Repository {

    private final RenameMeRepository renameMeRepository;
    private final PersonRepository personRepo;

    Repository(RenameMeRepository renameMeRepository, PersonRepository personRepo) {
        this.renameMeRepository = renameMeRepository;
        this.personRepo = personRepo;
    }

    public RenameMeRepository getRenameMeRepo() {
        return renameMeRepository;
    }

    public PersonRepository getPersonRepo() {
        return personRepo;
    }
}