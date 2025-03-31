package koya;

import static koya.task.Task.loadTaskToList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import koya.task.Task;

/**
 * This class contains all relevant methods to storing, updating and deleting data from
 * a text file.
 */

public class Storage {
    static final String FOLDER_PATH = "data";
    static final String FILE_PATH = "./data/koya.txt";

    /**
     * This method updates the text file with the new information added
     */
    public static void updateFile() {
        try {
            rewriteFile(FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method rewrites the text file
     *
     * @param filePath File path
     */
    public static void rewriteFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : Koya.list) {
            fw.write(task.toTextFile() + "\n");
        }
        fw.close();
    }

    /**
     * This method appends the new task's information  at the end of the text file
     *
     * @param filePath  File path
     * @param taskToAdd Task to add to file
     */
    public static void appendToFile(String filePath, Task taskToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(taskToAdd.toTextFile() + "\n");
        fw.close();
    }

    /**
     * This method creates a file or load the tasks from the file into the program,
     * depending on whether the file exists or not.
     */
    public static void createLoadFile() {
        File file = new File(FILE_PATH);
        checkFolderExists();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Unable to create file " + FILE_PATH);
            }
        }
        loadTaskFromFile(file);
    }

    /**
     * This method loads all the task from the text file to the program.
     *
     * @param file File storing the information
     */
    private static void loadTaskFromFile(File file) {
        Scanner s = null;
        try {
            s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                Koya.list.add(loadTaskToList(line));
                Koya.taskCount++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method verifies whether a folder exists and creates one if none exists
     *
     * @throws RuntimeException If unable to create a folder
     */
    public static void checkFolderExists() {
        File folder = new File(FOLDER_PATH);
        if (!folder.exists() && !folder.mkdirs()) {
            throw new RuntimeException("Unable to create folder " + FOLDER_PATH);
        }
    }
}