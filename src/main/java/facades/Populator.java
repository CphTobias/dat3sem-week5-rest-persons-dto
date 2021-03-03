/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.person.Person;
import entities.person.PersonRepository;
import entities.renameme.RenameMe;
import entities.renameme.RenameMeRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        PersonRepository repo = PersonFacade.getInstance(emf);

        Person person1 = new Person("Bob", "TheBuilder", "2131321");
        Person person2 = new Person("Bob2", "TheBuilder2", "2131322");

        try {
            em.getTransaction().begin();
            em.persist(person1);
            em.persist(person2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        populate();
    }
}
