package java8.ex03;

import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 03 - Méthode statique
 */
public class Method_03_Test
{

    // tag::IDao[]
    interface IDao {
        List<Person> findAll();

        // TODO créer une méthode statique IDao getDefaultInstance()
        static IDao getDefaultInstance() //Object objet
        {
        	DaoA dao = new DaoA();
        	return dao;
        }
        // TODO cette méthode retourne une instance de la classe DaoA
    }
    // end::IDao[]

    
    //static pour pouvoir etre appeler par la classe mere ici interface
    //plus generalement, une classe satique est défini pour pouvoir etre appele ailleur sans intancier la classe mere
    //ne paux exister que dans une instance d'une autre classe.
    static class DaoA implements IDao
    {

        List<Person> people = Data.buildPersonList(20);

        @Override
        public List<Person> findAll()
        {
            return people;
        }

    }

    @Test
    public void test_getDefaultInstance() throws Exception
    {
        // TODO invoquer la méthode getDefaultInstance() pour que le test soit passant
        IDao result = null;
        
        result = IDao.getDefaultInstance();

        assertThat(result.findAll(), hasSize(20));
    }
}
