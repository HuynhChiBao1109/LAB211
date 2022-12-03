/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productmng;

import java.util.Scanner;

/**
 *
 * @author baohc
 */
public class InputProduct {

    public static String inputName(String msg) {
        String name = "";
        System.out.print(msg);
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
        return name;
    }

    public static String inputID(String msg) {
        System.out.print(msg);
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        return id;
    }

    public static double inputPrice(String msg) {
        String strPrice = "-1";
            do {
                System.out.print(msg);
                Scanner sc = new Scanner(System.in);
                strPrice = sc.nextLine();
                strPrice = strPrice.trim();
                if (strPrice.isEmpty()) {
                    strPrice = "-1";
                }
            } while (Double.parseDouble(strPrice) < 0 || Double.parseDouble(strPrice) > 10000 );
        return Double.parseDouble(strPrice);
    }

    public static int inputQuantity(String msg) {
        String strQuantity = "-1";
        do {
            System.out.print(msg);
            Scanner sc = new Scanner(System.in);
            strQuantity = sc.nextLine();
            strQuantity = strQuantity.trim();
            if (strQuantity.isEmpty()) {
                strQuantity = "-1";
            }
        } while (Integer.parseInt(strQuantity) < 0 || Integer.parseInt(strQuantity) > 1000);
        return Integer.parseInt(strQuantity);
    }

    public static String inputStatus(String msg) {
        String status = "";
        String input = "";
        do {
            System.out.print(msg);
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            if (input.matches("[yY]")) {
                status = "Available";
            } else if (input.matches("nN")) {
                status = "Notavailbale";
            }
        } while (input.isEmpty());
        return status;
    }

    public static String updateCode(String msg) {
        String code = "";
        System.out.print(msg);
        Scanner sc = new Scanner(System.in);
        code = sc.nextLine();
        code = code.trim();
        return code;
    }

}
