package wally;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseBackgroundColour_supportedColours_returnsCssColour() throws InvalidBackgroundColourException {
        assertEquals("black", Parser.parseBackgroundColour("black"));
        assertEquals("white", Parser.parseBackgroundColour("white"));
        assertEquals("lightblue", Parser.parseBackgroundColour("light blue"));
        assertEquals("lightblue", Parser.parseBackgroundColour("blue"));
        assertEquals("white", Parser.parseBackgroundColour(" WHITE "));
    }

    @Test
    public void parseBackgroundColour_unsupportedOrMissingColour_throwsWithMessage() {
        for (String colour : new String[] {"red", "", "blue extra", "#000000"}) {
            InvalidBackgroundColourException exception =
                    assertThrows(InvalidBackgroundColourException.class, () -> Parser.parseBackgroundColour(colour));
            assertEquals("Only black, white and blue supported", exception.getMessage());
        }
    }

    @Test
    public void processCommand_byeCommand_returnsTerminationResponse() {
        assertEquals("TERMINATE_PROGRAM", Parser.processCommand("bye", null));
    }

    @Test
    public void processCommand_scheduleCommand_ordersDatedTasksBeforeTodos() {
        Tasklist tasklist = new Tasklist();
        tasklist.addTask(new ToDo("write report"));
        tasklist.addTask(new Deadline("submit assignment", "2026-09-14 23:59"));
        tasklist.addTask(new Event("team meeting", "2026-09-12 10:00", "2026-09-12 11:00"));

        String expected = "Here are the tasks in your schedule:"
                + "\n3. [E][ ] team meeting (from: 12 Sep 2026, 10:00 to: 12 Sep 2026, 11:00)"
                + "\n2. [D][ ] submit assignment (by: 14 Sep 2026, 23:59)"
                + "\n1. [T][ ] write report";

        assertEquals(expected, Parser.processCommand("schedule", tasklist));
    }
}
