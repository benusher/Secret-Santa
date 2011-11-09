package iskido.secretsanta;

import java.util.ArrayList;

public class TopHat {

    private Person person;
    private final ArrayList<Person> people;

    public TopHat() {
        people = new ArrayList<Person>();
    }

    public void add(Person person) {
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
