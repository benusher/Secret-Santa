package iskido.secretsanta;

import java.util.ArrayList;
import java.util.List;

import static com.iskido.porpoise.Presentation.unCamel;

public class TopHat {

    private final List<Person> people;
    private final Randomatic randomatic;

    public TopHat(Randomatic randomatic) {
        this.randomatic = randomatic;
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
            return people.remove(randomatic.nextInt(people.size()));
        }
    }
}
