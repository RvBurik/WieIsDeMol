package nl.ricardo.jpa;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudJPA<T> extends BaseJPA {

    protected Class classType;

    protected static final Logger LOGGER = Logger.getLogger(CrudJPA.class.getName());
    protected static final String SELECT = " SELECT ";
    protected static final String FROM = " FROM ";
    protected static final String WHERE = " WHERE ";
    protected static final String AND = " AND ";
    protected static final String INNER_JOIN = " INNER JOIN ";
    protected static final String ON = " ON ";
    protected static final String LIMIT = " LIMIT ";

    public CrudJPA(Class classType){
        super();

        this.classType = classType;
    }

    /**
     * Function gets the latest {@link Session}, starts a {@link Transaction} and creates a {@link org.hibernate.query.Query Query}
     * which searches for a single result based on the given parameters.
     * Can be seen as:<br><br>
     * <i>SELECT FIRST FROM T WHERE field = value</i>
     * @param field Name of the field where the query should search on
     * @param id Value which should match the value of the given field
     * @return {@link T} object which represents the results of the query.
     */
    public T findOne(String field, Long id){

        T entity = null;

        Session session = this.factory.getCurrentSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            entity = (T)session.createQuery(FROM + this.classType.getName() + WHERE + field + " = '" + id + "'").uniqueResult();
            tx.commit();

        }
        catch (Exception e){

            this.handleException(e, tx, "Unable to find one: #" + field + id);

        }

        return entity;
    }


    /**
     * Function gets the latest {@link Session}, starts a {@link Transaction} and creates a new {@link org.hibernate.query.Query Query}
     * which searches for a single result based on the 4 given parameters. Can be seen as: <br><br>
     * <i>SELECT * FROM T WHERE field = value AND field2 = value2</i>
     * @param field Name of the first field where the query should search on
     * @param value Value which should match the value of the given first field
     * @param field2 Name of the second field where the query should search on
     * @param value2 Value which should match the value of the given second field
     * @return {@link T} object which represents the results of the query.
     */
    public T findOneByMoreFields(String field, String value, String field2, String value2){
        T entity = null;

        Session session = this.factory.getCurrentSession();
        Transaction tx = null;
        try {

            tx = session.beginTransaction();

            entity = (T)session.createQuery(FROM + this.classType.getName() + WHERE + field + " = '" + value + "' AND " + field2 + " = '" + value2 + "'").uniqueResult();
            tx.commit();

        }
        catch(Exception e){

            this.handleException(e, tx, "Unable to find multiple: " + field + " " + value + " " + field2 + " " + value2);

        }

        return entity;

    }

    /**
     * Function which gets the latest {@link Session}, starts a {@link Transaction} and creates a new {@link org.hibernate.query.Query Query}
     * which searches for all results based on the query. Can be seen as: <br><br>
     * <i>SELECT * FROM T</i>
     * @return
     */
    public List<T> findAll() {
        List<T> entities = null;

        Session session = this.factory.getCurrentSession();
        Transaction tx = null;
        try {

            tx = session.beginTransaction();

            entities = session.createQuery(FROM + this.classType.getName()).list();
            tx.commit();

        }

        catch(Exception e) {

            this.handleException(e, tx, "Unable to find all");

        }

        return entities;
    }

    /**
     * Function which gets the latest {@link Session}, starts a {@link Transaction} and creates a new {@link org.hibernate.query.Query Query}
     * which searches for all results based on the given parameters. Can be seen as: <br><br>
     * <i>SELECT * FROM T WHERE field = value</i>
     * @param field Name of the first field where the query should search on
     * @param value Value which should match the value of the given first field
     * @return {@link List} of {@link T} which represents the given results
     */
    public List<T> findAllFilteredBy(String field, String value){
        List<T> entities = null;
        Session session = this.factory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            entities = session.createQuery(FROM + this.classType.getName() + WHERE + field + " = '" + value + "'").list();
            tx.commit();
        }
        catch(Exception e){
            this.handleException(e, tx, "Unable to filter all by " + field + " = " + value);
        }
        return entities;
    }

    /**
     * Function which handles the Exceptions given by the CRUDJPA functions.
     * @param e {@link Exception} to throw
     * @param tx {@link Transaction} which the error took place in
     * @param message message as String to show the user
     */
    protected void handleException(Exception e, Transaction tx, String message){
        LOGGER.log(Level.WARNING, message, e);

        if(tx != null && tx.isActive()){
            tx.rollback();
        }
    }

}
