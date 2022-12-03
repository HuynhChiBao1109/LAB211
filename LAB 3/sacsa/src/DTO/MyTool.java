/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Scanner;

public class MyTool {

    public static String inputCollection(String msg) {
            String output = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(msg);
            output = sc.nextLine();
            output = output.trim();

        } while (output.isEmpty());

        return output;
    }

    public static String inputType(String msg) {
        String output = "";
        String input;
        do {
            System.out.print(msg);
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            input = input.trim();
            if (input.equals("1")) {
                output = "audio";
            }
            if (input.equals("2")) {
                output = "video";
            }
        } while (input.isEmpty() || output.isEmpty());

        return output;
    }

    public static String inputTitle(String msg) {
        String output = "";
        do {
            System.out.print(msg);
            Scanner sc = new Scanner(System.in);
            output = sc.nextLine();
            output = output.trim();

        } while (output.isEmpty());

        return output;
    }

    public static String inputID(String msg) {
        String output = "";
        do {
            System.out.print(msg);
            Scanner sc = new Scanner(System.in);
            output = sc.nextLine();

        } while (output.isEmpty() || !output.matches(CD.ID_PATTERN));
        return output;
    }

    public static double inputPrice(String msg) {
        double price = -1;
        boolean check;
        do {
            try {
                 check = false;
                System.out.print(msg);
                Scanner sc = new Scanner(System.in);
                price = sc.nextDouble();
            } catch (Exception e) {
                check = true;
            }

        } while (price < CD.PRICE_MIN || price > CD.PRICE_MAX || check);
        return price;
    }

    public static int inputYear(String msg) {
        int year = -1;
        boolean check;
        do {
            try {
                check = false;
                Scanner sc = new Scanner(System.in);
                System.out.print(msg);
                year = sc.nextInt();
            } catch (Exception e) {
                check = true;
            }

        } while (check || year > CD.YEAR_MAX || year < CD.YEAR_MIN);
        return year;
    }
    
    public static String inputNonBlank(String msg) {
        String output = "";
        System.out.print(msg);
        Scanner sc = new Scanner(System.in);
        output = sc.nextLine();
        return output;
    }

}
