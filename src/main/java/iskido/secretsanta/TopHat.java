package iskido.secretsanta;

public class TopHat {

    private Person person;

    public void add(Person person) {
        this.person = person;
    }

    public Pairing drawPair() {
        return new Pairing(person, person);
    }
}
