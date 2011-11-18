package iskido.secretsanta;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static iskido.secretsanta.SecretSantaDataFixtures.anyPerson;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TopHatTest {

    private TopHat topHat;
    private Randomatic randomatic;

    @Before
    public void setUp() throws Exception {
        randomatic = mock(Randomatic.class);
        topHat = new TopHat(randomatic);
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
        Person person = anyPerson();
        topHat.add(person);

        try {
            topHat.add(person);

            Assert.fail(String.format("Expected [%s]", IllegalArgumentException.class.getSimpleName()));
        } catch (IllegalArgumentException expected) {
            assertThat(expected.getMessage(), containsString(person.name()));
        }
    }

    @Test
    public void peopleAreDrawnFromTheTopHatInARandomOrder() throws Exception {
        Person firstPersonAdded = anyPerson();
        Person secondPersonAdded = anyPerson();
        Person thirdPersonAdded = anyPerson();

        topHat.add(firstPersonAdded);
        topHat.add(secondPersonAdded);
        topHat.add(thirdPersonAdded);

        when(randomatic.nextInt(anyInt())).thenReturn(2).thenReturn(0).thenReturn(0);

        assertThat(topHat.draw(), is(thirdPersonAdded));
        assertThat(topHat.draw(), is(firstPersonAdded));
        assertThat(topHat.draw(), is(secondPersonAdded));
    }
}
