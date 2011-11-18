package iskido.secretsanta;

import java.util.Random;

public class Randomatic {

    private final Random random;

    public Randomatic() {
        random = new Random();
    }

    public int nextInt(int exclusiveMax) {
        return random.nextInt(exclusiveMax);
    }
}
