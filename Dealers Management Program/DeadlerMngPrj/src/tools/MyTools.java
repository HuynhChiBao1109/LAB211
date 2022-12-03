/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author baohc
 */
public class MyTools {

    public static final Scanner sc = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static boolean validPassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[a-zA-Z]+.*")
                && str.matches(".*[\\d]+.*")
                && str.matches(".*[\\w]+.*");
    }

    public static Date parseDate(String dataStr, String dateFormat) {
        SimpleDateFormat df = (SimpleDateFormat) SimpleDateFormat.getInstance();
        df.applyPattern(dateFormat);
        try {
            long t = df.parse(dataStr).getTime();
            return new Date(t);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String dataToStr(Date date, String dateFormat) {
        SimpleDateFormat df = (SimpleDateFormat) SimpleDateFormat.getInstance();
        df.applyPattern(dateFormat);
        try {
            String t = String.valueOf(date);
            return new String(t);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.print(message);
            input = sc.nextLine();
        } while (input.isEmpty());
        return input;
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid);
        return input;
    }

    public static boolean readBool(String message) {
        String input = "";
        System.out.print(message + "[1/0-Y/N-T/F]: ");
        input = sc.nextLine();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'T' || c == 'Y');
    }

    public static List<String> readLineFromFile(String fileName) throws FileNotFoundException, IOException {
        FileReader file = new FileReader(fileName);
        BufferedReader str = new BufferedReader(file);
        List<String> list = new ArrayList<>();
        
        while (str.ready()) {
            String s = str.readLine();
            list.add(s);
        }
        file.close();
        str.close();

        return list;
    }

    public static void writeFile(String fileName, List list) throws IOException {
        FileWriter writeFile = new FileWriter(fileName);

        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += list.get(i) + "\n";
        }
        writeFile.write(str);
        writeFile.close();
    }

//    public static void main(String[] args) {
//        System.out.println("");
//        System.out.println("Test with phone numbers");
//        System.out.println(validStr("012345678", "\\d{9}|\\d{11}"));
//        System.out.println(validStr("01234567891", "\\d{9}|\\d{11}"));
//        System.out.println(validStr("12345678", "\\d{9}|\\d{11}"));
//        System.out.println("");
//        //Test password - ok
//        System.out.println(validPassword("qwerty", 8));
//        System.out.println(validPassword("qwertyABC", 8));
//        System.out.println(validPassword("12345678", 8));
//        System.out.println(validPassword("qbc123456", 8));
//        System.out.println(validPassword("qbc@123456", 8));
//        System.out.println("");
//        // ID format D000 - ok
//        System.out.println("Tests with IDs");
//        System.out.println(validStr("A0001", "D\\d{3}"));
//        System.out.println(validStr("10001", "D\\d{3}"));
//        System.out.println(validStr("D0001", "D\\d{3}"));
//        System.out.println(validStr("D101", "D\\d{3}"));
//        System.out.println("");
//        //Test date format - ok
//        Date d = parseDate("2022:12:07", "yyyy:MM:dd");
//        System.out.println(d);
//        System.out.println(dataToStr(d, "yyyy:MM:dd")); // test ok
//        d = parseDate("12/07/2022", "MM/dd/yyyy");
//        System.out.println(d);
//        d = parseDate("2022/07/12", "MM/dd/yyyy");
//        System.out.println(d);
//        d = parseDate("2000/29/02", "yyyy/dd/MM");
//        System.out.println(d);
//        d = parseDate("2000/30/02", "yyyy/dd/MM");
//        System.out.println(d);
//        d = parseDate("2000/40/16", "yyyy/dd/MM");
//        System.out.println(d);
//        // Test input data - ok
//        String input = readNonBlank("Input a non-blank string");
//        System.out.println(input + "2222");
//        input = readPattern("phone 9/11 digits", "\\d{9}|\\d{11}");
//        System.out.println(input);
//        input = readPattern("ID- format X00000", "x\\d{5}");
//        System.out.println(input);
//        boolean b = readBool("Input boolean");
//        System.out.println(b);
//    }
}


