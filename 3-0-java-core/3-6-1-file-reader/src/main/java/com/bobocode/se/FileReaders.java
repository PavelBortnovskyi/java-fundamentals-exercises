package com.bobocode.se;

import com.bobocode.util.ExerciseNotCompletedException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * {@link FileReaders} provides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName) {
        String result = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            result = sb.toString().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
        //throw new ExerciseNotCompletedException(); //todo
    }
}
