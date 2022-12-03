/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author baohc
 */
public class Menu extends ArrayList<String> {

    public Menu() {
        super();
    }

    public Menu(String[] items) {
        super();
        for (String item : items) {
            this.add(item);
        }
    }

    public int getChoice(String title) {
        System.out.println(title);
        int i = 0;
        for (String thi : this) {
            System.out.println(i + 1 + "/ " + thi);
            i++;
        }
        int choice;
        System.out.print("Enter Choice: ");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        sc.nextLine();
        return choice;
    } 

}
