package iskido.secretsanta;

import static com.iskido.porpoise.BasicRandomDataFixtures.anyString;

public class SecretSantaDataFixtures {

    public static Person anyPerson() {
        return new Person(anyString());
    }
}
