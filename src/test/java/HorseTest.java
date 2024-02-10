import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class HorseTest {
    private final static String CORRECT_NAME = "name";
    private final static double CORRECT_SPEED = 1;
    private final static double CORRECT_DISTANCE = 1;

    @Test
    void whenNameIsNullThenThrowsIllegalArgumentException() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, CORRECT_SPEED, CORRECT_DISTANCE)
        );
    }

    @Test
    void whenNameIsNullThenExceptionHasMessage() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, CORRECT_SPEED, CORRECT_DISTANCE)
        );

        Assertions.assertEquals("Name cannot be null.", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "", " ", "\t", "\n", "\n\t",
            " \t", " \n", " \t\n"
    })
    void whenNameIsBlankThenThrowsIllegalArgumentException(String name) {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, CORRECT_SPEED, CORRECT_DISTANCE)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "", " ", "\t", "\n", "\n\t",
            " \t", " \n", " \t\n"
    })
    void whenNameIsBlankThenExceptionHasMessage(String name) {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, CORRECT_SPEED, CORRECT_DISTANCE)
        );

        Assertions.assertEquals("Name cannot be blank.", illegalArgumentException.getMessage());
    }

    @Test
    void whenSpeedIsNegativeThenThrowsIllegalArgumentException() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(CORRECT_NAME, -1, CORRECT_DISTANCE)
        );
    }

    @Test
    void whenSpeedIsNegativeThenExceptionHasMessage() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(CORRECT_NAME, -1, CORRECT_DISTANCE)
        );
        Assertions.assertEquals("Speed cannot be negative.", illegalArgumentException.getMessage());
    }

    @Test
    void whenDistanceIsNegativeThenThrowsIllegalArgumentException() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(CORRECT_NAME, CORRECT_SPEED, -1)
        );
    }

    @Test
    void whenDistanceIsNegativeThenExceptionHasMessage() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(CORRECT_NAME, CORRECT_SPEED, -1)
        );
        Assertions.assertEquals("Distance cannot be negative.", illegalArgumentException.getMessage());
    }

    @Test
    void getName() {
        Horse horse = new Horse(CORRECT_NAME, CORRECT_SPEED, CORRECT_DISTANCE);
        Assertions.assertEquals(CORRECT_NAME, horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse(CORRECT_NAME, CORRECT_SPEED, CORRECT_DISTANCE);
        Assertions.assertEquals(CORRECT_SPEED, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse(CORRECT_NAME, CORRECT_SPEED, CORRECT_DISTANCE);
        Assertions.assertEquals(CORRECT_DISTANCE, horse.getDistance());
    }

    @Test
    void whenConstructorWithoutDistanceThenGetterReturnsZero() {
        Horse horse = new Horse(CORRECT_NAME, CORRECT_SPEED);
        Assertions.assertEquals(0, horse.getDistance());
    }

    @Test
    void moveCallsGetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse(CORRECT_NAME, CORRECT_SPEED, CORRECT_DISTANCE);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9})
    void move(double value) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);
            Horse horse = new Horse(CORRECT_NAME, CORRECT_SPEED, CORRECT_DISTANCE);
            double expected = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);

            horse.move();

            Assertions.assertEquals(expected, horse.getDistance());
        }
    }
}