package koya;

import static koya.task.Task.loadTaskToList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import koya.task.Task;

public class Storage {
    static final String FOLDER_PATH = "data";
    static final String FILE_PATH = "./data/koya.txt";

    public static void updateFile() {
        try {
            rewriteFile(FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rewriteFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : Koya.list) {
            fw.write(task.toTextFile() + "\n");
        }
        fw.close();
    }

    public static void appendToFile(String filePath, Task taskToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(taskToAdd.toTextFile() + "\n");
        fw.close();
    }

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

    public static void checkFolderExists() {
        File folder = new File(FOLDER_PATH);
        if (!folder.exists() && !folder.mkdirs()) {
            throw new RuntimeException("Unable to create folder " + FOLDER_PATH);
        }
    }
}