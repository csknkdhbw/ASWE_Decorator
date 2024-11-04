package de.softknk.eventdecorator;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import de.softknk.Event;

public class FileLoggingDecorator extends EventDecorator {
    private String filePath;

    public FileLoggingDecorator(Event wrapperEvent, String filePath) {
        super(wrapperEvent);
        this.filePath = filePath;
    }

    @Override
    public void eventNotify() {
        appendToFile(filePath, getMessage());
        // Notify wrapper object
        wrapperEvent.eventNotify();
    }

    /**
     * Appends the specified content to a file.
     * @param filePath the path of the file to append to
     * @param content the content to append to the file
     */
    private void appendToFile(String filePath, String content) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // 'true' enables append mode
            // Add current date and time
            writer.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            writer.write(": ");
            writer.write(content);
            writer.write(System.lineSeparator()); // Optional: Adds a new line after content
            System.out.println("Content successfully appended to " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}