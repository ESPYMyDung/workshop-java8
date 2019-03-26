package java8.ex03;

import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.List;
//import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 03 - ForEach
 */
public class Lambda_03_Test
{

    // ----tag::PersonProcessor[]----
    interface PersonProcessor
    {
        void process(Person p);
    }
    // end::PersonProcessor[]

    // ----tag::forEach[]----
    private void forEach(List<Person> source, PersonProcessor processor)
    {
       // et on met quoi ici?
    	for (Person indv:source)
    	{
    		processor.process(indv);
    	}
    }
    // end::forEach[]


    // ----tag::test_verify_person[]----
    @Test
    public void test_verify_person() throws Exception
    {

        List<Person> personList = Data.buildPersonList(100);

        // TODO vérifier qu'une personne à un prénom qui commence par first
        // TODO vérifier qu'une personne à un nom qui commence par last
        // TODO vérifier qu'une personne à un age > 0
        PersonProcessor verifyPerson = p ->
        { //si le code en plusieurs ligne le metre dans un bloc
        	assertTrue(p.getAge()>0);
			assertTrue(p.getFirstname().startsWith("first"));
			assertTrue(p.getLastname().startsWith("last"));
		};


        assertThat(verifyPerson, notNullValue());

        forEach(personList, verifyPerson);
    }
    // end::test_verify_person[]

}
