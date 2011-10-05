package iskido.secretsanta;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class TopHatTest {

    private TopHat topHat;

    @Before
    public void setUp() throws Exception {
        topHat = new TopHat();
    }

    @Test
    public void aPersonAddedToSecretSantaIsAssigned() throws Exception {
        Person person = mock(Person.class);
        Pairing expectedPairing = new Pairing(person, person);
        topHat.add(person);

        Pairing actualPairing = topHat.drawPair();

        assertThat(actualPairing, is(expectedPairing));
    }
}
