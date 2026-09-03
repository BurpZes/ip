package wally;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void processCommand_byeCommand_returnsTerminationResponse() {
        assertEquals("TERMINATE_PROGRAM", Parser.processCommand("bye", null));
    }
}
