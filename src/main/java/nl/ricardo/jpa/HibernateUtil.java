package nl.ricardo.jpa;


import nl.ricardo.model.RAnswer;
import nl.ricardo.model.RPerson;
import nl.ricardo.model.RQuestion;
import nl.ricardo.model.RPersonAnswer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {
    private static SessionFactory factory = null;

    private HibernateUtil(){}

    /**
     * Instantiates the {@link HibernateUtil#factory SessionFactory}
     * @return
     */
    private static SessionFactory setupSessionFactory(){
        SessionFactory factory;
        factory = new Configuration()
                .addAnnotatedClass(RAnswer.class)
                .addAnnotatedClass(RPerson.class)
                .addAnnotatedClass(RQuestion.class)
                .addAnnotatedClass(RPersonAnswer.class)
                .configure("hibernate.xml")
                .buildSessionFactory();
        return factory;
    }

    /**
     * -- Singleton -- <br>
     * Creates a new {@link HibernateUtil#factory SessionFactory} object if one doesn't already exist.
     * Calls the {@link #setupSessionFactory()} method to configure the SessionFactory
     * @return configured factory class.
     */
    public static SessionFactory getSessionFactory(){
        if(factory == null){
            factory = setupSessionFactory();
        }

        return factory;
    }

}
