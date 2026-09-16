package wally;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/** Tests storage using temporary files without touching the user's save file. */
public class SaveTest {
    @TempDir
    private Path directory;

    @Test
    public void constructor_missingFile_createsParentDirectoriesAndEmptyFile() throws Exception {
        Path file = directory.resolve("nested/save.txt");
        Tasklist tasks = new Tasklist();
        new Save(tasks, file);
        assertTrue(Files.isRegularFile(file));
        assertEquals("", Files.readString(file));
        assertEquals(0, tasks.getSize());
    }

    @Test
    public void writeToSave_allTaskTypes_roundTripsAndOverwrites() throws Exception {
        Path file = directory.resolve("save.txt");
        Tasklist tasks = new Tasklist();
        Save save = new Save(tasks, file);
        tasks.addTask(new ToDo("read"));
        tasks.addTask(new Deadline("submit", "2026-09-14 12:00"));
        tasks.addTask(new Event("meet", "2026-09-14 10:00", "2026-09-14 11:00"));
        save.writeToSave(tasks);
        String expected = "todo read\ndeadline submit /by 2026-09-14 12:00\n"
                + "event meet /from 2026-09-14 10:00 /to 2026-09-14 11:00\n";
        assertEquals(expected, Files.readString(file));
        Tasklist loaded = new Tasklist();
        new Save(loaded, file);
        assertEquals(3, loaded.getSize());
        for (int i = 1; i <= 3; i++) {
            assertEquals(tasks.getTask(i).toString(), loaded.getTask(i).toString());
        }
        save.writeToSave(new Tasklist());
        assertEquals("", Files.readString(file));
    }

    @Test
    public void constructor_invalidSavedCommands_keepsValidTasks() throws Exception {
        Path file = directory.resolve("save.txt");
        Files.writeString(file, "unknown\ntodo read\ntodo READ\n"
                + "deadline invalid /by 2026-02-30 10:00\ntodo write\n");
        Tasklist tasks = new Tasklist();
        new Save(tasks, file);
        assertEquals(2, tasks.getSize());
        assertEquals("read", tasks.getTask(1).getDescription());
        assertEquals("write", tasks.getTask(2).getDescription());
    }

    @Test
    public void storage_unwritablePath_reportsIoErrors() throws Exception {
        Path parentFile = directory.resolve("file.txt");
        Files.writeString(parentFile, "keep");
        PrintStream original = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (PrintStream captured = new PrintStream(output, true, StandardCharsets.UTF_8)) {
            System.setOut(captured);
            Save save = new Save(new Tasklist(), parentFile.resolve("save.txt"));
            assertTrue(output.toString(StandardCharsets.UTF_8).contains("Exception caught:"));
            output.reset();
            save.writeToSave(new Tasklist());
            assertTrue(output.toString(StandardCharsets.UTF_8).contains("Exception caught:"));
        } finally {
            System.setOut(original);
        }
        assertEquals("keep", Files.readString(parentFile));
    }
}
