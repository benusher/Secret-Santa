package iskido.secretsanta;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Mockito.mock;

public class TopHatTest {

    private TopHat topHat;

    @Before
    public void setUp() throws Exception {
        topHat = new TopHat();
    }

    @Test
    public void drawSomeoneFromTheHatWhoHasBeenAdded() throws Exception {
        Person addedPerson = mock(Person.class);
        topHat.add(addedPerson);

        Person drawnPerson = topHat.draw();

        assertThat(drawnPerson, is(addedPerson));
    }

    @Test
    public void throwsAnExceptionWhenRequestedToDrawFromAnEmptyHat() throws Exception {
        try {
            topHat.draw();

            Assert.fail(String.format("Expected [%s]", EmptyTopHatException.class.getSimpleName()));
        } catch (EmptyTopHatException success) {
        }
    }

    @Test
    public void drawPeopleFromTheHatWhoHaveBeenAdded() throws Exception {
        Person someoneAdded = mock(Person.class);
        Person someoneElseAdded = mock(Person.class);
        Set<Person> peopleAdded = new HashSet<Person>();
        peopleAdded.add(someoneAdded);
        peopleAdded.add(someoneElseAdded);

        topHat.add(someoneAdded);
        topHat.add(someoneElseAdded);

        Person someoneDrawn = topHat.draw();
        Person someoneElseDrawn = topHat.draw();

        Set<Person> peopleDrawn = new HashSet<Person>();
        peopleDrawn.add(someoneDrawn);
        peopleDrawn.add(someoneElseDrawn);

        assertThat(peopleDrawn, is(peopleAdded));
    }

    @Test
    public void throwsAnExceptionWhenAddingTheSamePersonMoreThanOnce() throws Exception {
        Person person = SecretSantaDataFixtures.anyPerson();
        topHat.add(person);

        try {
            topHat.add(person);

            Assert.fail(String.format("Expected [%s]", IllegalArgumentException.class.getSimpleName()));
        } catch (IllegalArgumentException expected) {
            assertThat(expected.getMessage(), containsString(person.name()));
        }
    }
}
