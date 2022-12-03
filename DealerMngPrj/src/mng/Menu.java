package mng;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends ArrayList<String> {

    Scanner sc = new Scanner(System.in);

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
        int i = 1;
        for (String thi : this) {
            System.out.println(i + "-" + thi);
            i++;
        }
        int choice;
        System.out.print("Enter Choice: ");
        choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
}
