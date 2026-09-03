package wally;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the Tasklist class.
 */
public class TasklistTest {

    /**
     * Tests the addTask and getTask methods.
     */
    @Test
    public void addTask_validTask_taskIsRetrievable() {
        Tasklist taskings = new Tasklist();
        ToDo test1 = new ToDo("test1");
        taskings.addTask(test1);
        assertEquals(test1, taskings.getTask(1));
    }
}
