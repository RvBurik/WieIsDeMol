package nl.ricardo.jpa;

import org.hibernate.SessionFactory;

public abstract class BaseJPA {

    protected SessionFactory factory;

    protected BaseJPA(){
        this.factory = HibernateUtil.getSessionFactory();
    }

    protected BaseJPA(SessionFactory factory) {this.factory = factory;}

}
