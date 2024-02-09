import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HorseTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void whenNameIsNull() {
        int speed = 1;
        int distance = 1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed, distance));
    }

}