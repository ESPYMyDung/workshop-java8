package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;

//import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

    // ----tag::PersonToAccountMapper[]----
    interface PersonToAccountMapper<T,V> {
        T map(V p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    private List<Account> map(List<Person> personList, PersonToAccountMapper<Account, Person> mapper) {
        // TODO implémenter la méthode
    	List<Account> comptes = new ArrayList<>();
    	
    	for (Person indv:personList)
    	{
    		comptes.add(mapper.map(indv));
    	}
    	
    	return comptes;
    }
    // end::map[]


    // ----tag::test_map_person_to_account[]----
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);
        
        PersonToAccountMapper<Account, Person> PersToAcc = new PersonToAccountMapper<Account, Person>()
        {
        	@Override
        	public Account map(Person p)
        	{
        		Account compteCourant = new Account();
        		compteCourant.setBalance(100);
        		compteCourant.setOwner(p);
        		
        		return compteCourant;
        	}
        };

        // TODO transformer la liste de personnes en liste de comptes
        // TODO tous les objets comptes ont un solde à 100 par défaut
        List<Account> result = map(personList, PersToAcc);

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(hasProperty("balance", is(100))));
        assertThat(result, everyItem(hasProperty("owner", notNullValue())));
    }
    // end::test_map_person_to_account[]

    // ----tag::test_map_person_to_firstname[]----
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);
        
        PersonToAccountMapper<String, Person> PersToAcc = new PersonToAccountMapper<String, Person>()
        {
        	@Override
        	public String map(Person p)
        	{
       		
        		return p.getFirstname();
        	}
        };
        
        //(String t) -> personList.stream().PersToAcc().getOwner().getFirstName().collect(Collectirs.toList());

        //personList.stream().
        
        // TODO transformer la liste de personnes en liste de prénoms
        List<String> result = personList.stream().map( c -> c.getFirstname() ).collect(Collectors.toList());


        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(instanceOf(String.class)));
        assertThat(result, everyItem(startsWith("first")));
    }
    // end::test_map_person_to_firstname[]
}
