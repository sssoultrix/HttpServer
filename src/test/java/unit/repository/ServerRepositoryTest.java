package unit.repository;

import org.example.repository.ServerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerRepositoryTest {
    private ServerRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ServerRepository();
    }

    @Test
    void whenNumberGreaterThan100_thenThrowException() {
        int invalidNumber = 101;
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            repository.add(invalidNumber);
        });
        assertEquals("Number must be less than 100", exception.getMessage());
    }
    @Test
    void whenNumberIsNegative_thenTrowException() {
        int negativeNumber = -1;
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            repository.add(negativeNumber);
        });
        assertEquals("Number must be greater than 0", exception.getMessage());
    }
}
