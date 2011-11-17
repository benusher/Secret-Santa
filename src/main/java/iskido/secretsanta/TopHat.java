package iskido.secretsanta;

import java.util.ArrayList;

import static com.iskido.porpoise.Presentation.unCamel;

public class TopHat {

    private Person person;
    private final ArrayList<Person> people;

    public TopHat() {
        people = new ArrayList<Person>();
    }

    public void add(Person person) {
        if (people.contains(person)) {
            throw new IllegalArgumentException(String.format("[%s] is already in the %s", person.name(), unCamel(this)));
        }

        people.add(person);
    }

    public Person draw() {
        if (people.isEmpty()) {
            throw new EmptyTopHatException();
        } else {
            return people.remove(0);
        }
    }
}
