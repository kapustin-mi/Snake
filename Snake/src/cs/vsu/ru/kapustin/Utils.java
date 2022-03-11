package cs.vsu.ru.kapustin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static List<String> readListFromFile(String fileName) {
        File file = new File(fileName);
        Scanner scn = null;
        try {
            scn = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> lines = new ArrayList<>();

        while (scn.hasNext()) {
            lines.add(scn.nextLine());
        }

        List<String> data = new ArrayList<>();
        String[] dataOfLine;

        for (String line : lines) {
            dataOfLine = line.split(" ");
            data.addAll(List.of(dataOfLine));
        }

        return data;
    }
}
