package tools;

import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

public class MyTool {

    public static final Scanner sc = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static boolean validpassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[a-zA-Z]+.*")
                && // at least 1 character
                str.matches(".*[\\d]+.*")
                && // at least 1 digit 
                str.matches(".*[\\w]+.*");// at least 1 special char

    }

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static String readpattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid);
        return input;
    }

    public static boolean readBool(String message) {
        String input;
        System.out.print(message + "[1/0-Y/N-T/F]: ");
        input = sc.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static List<String> readLinesFromFile(String filename) throws IOException {
        FileReader file = new FileReader(filename);
        List<String> list = new ArrayList();
        if (!file.ready()) {
            System.out.println("File is empty");
        } else {
            BufferedReader read = new BufferedReader(file);

            while (file.ready()) {
                String line = read.readLine();
                list.add(line);
            }
            file.close();
            read.close();

        }
        return list;
    }

    public static void writeFile(String filename, List list) throws IOException {
        FileWriter file = new FileWriter(filename);
        BufferedWriter output = new BufferedWriter(file);

        if (list.isEmpty()) {
            System.out.println("Empty List");
        } else {
            for (int i = 0; i < list.size(); i++) {
                output.write(list.get(i).toString() + "\n");
            }
        }
        output.close();
        System.out.println("Save to File succesfully!");

    }
}
