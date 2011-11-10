package iskido.secretsanta;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PersonTest {

    @Test
    public void aPersonHasAName() throws Exception {
        String name = "Arnold";
        Person namedPerson = new Person(name);

        assertThat(namedPerson.name(), is(name));
    }
}
