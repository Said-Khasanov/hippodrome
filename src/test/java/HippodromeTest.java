import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class HippodromeTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void whenHorsesIsNullThenThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void whenHorsesIsNullThenExceptionHasMessage() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );

        Assertions.assertEquals("Horses cannot be null.", illegalArgumentException.getMessage());
    }

    @Test
    void whenHorsesIsEmptyThenThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
    }

    @Test
    void whenHorsesIsEmptyThenExceptionHasMessage() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList())
        );

        Assertions.assertEquals("Horses cannot be empty.", illegalArgumentException.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String name = "Horse " + (i + 1);
            double speed = i % 10 + 1;
            horses.add(new Horse(name, speed));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : hippodrome.getHorses()) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        Horse expected = new Horse("Horse 2", 1, 3);

        horses.add(new Horse("Horse 1", 1, 1));
        horses.add(new Horse("Horse 2", 1, 2));
        horses.add(expected);
        Hippodrome hippodrome = new Hippodrome(horses);

        Assertions.assertEquals(expected, hippodrome.getWinner());
    }
}