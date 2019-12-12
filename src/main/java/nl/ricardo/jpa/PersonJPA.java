package nl.ricardo.jpa;


import nl.ricardo.model.RPerson;

public class PersonJPA extends CrudJPA<RPerson> {

    public PersonJPA() {
        super(RPerson.class);
    }
}
