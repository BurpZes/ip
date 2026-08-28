package wally;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasklistTest {
    @Test
    public void addTaskNGetTaskTest() {
        Tasklist taskings = new Tasklist();
        ToDo test1 = new ToDo("test1");
        taskings.addTask(test1);
        assertEquals(test1, taskings.getTask(1));
    }
}
