package nl.ricardo.controller;

import nl.ricardo.jpa.PersonJPA;
import nl.ricardo.model.RPerson;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class PersonController implements IController {

    @Inject
    private PersonJPA jpa;

    public PersonController(){}

    public List<RPerson> all() {

       return jpa.findAll();

    }

    public RPerson getPerson(Long id){

        return jpa.findOne("person_id", id);

    }

}
