package nl.ricardo.service;

import nl.ricardo.controller.PersonController;
import nl.ricardo.model.RPerson;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("person")
public class UserService {

    @Inject
    private PersonController controller;

    public UserService(){}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RPerson> getAllPersons() {

        return controller.all();
    }
}