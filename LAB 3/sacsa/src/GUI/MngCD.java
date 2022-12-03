/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.CD;
import DTO.CDList;
import DTO.MyTool;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MngCD {

    public static void main(String[] args) throws IOException {

        CDList list = new CDList();
        list.readLineSaveToList();
        list.addList();
        File file = new File(CD.fileName);
        String[] options = {"Add CD to the catalog ", "Search CD by CD title ", "Display the catalog. ",
                             "Update CD", "Save to file", "Print all list CD from file."};
        int choice = 0;
        int choiceUpdate;
        String ask = "n";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n CD managing Program ");
            choice = menu.getChoice(options);

            switch (choice) {
                case 1:
                    list.addNewCD();
                    break;
                case 2:
                    list.searchTitle();
                    break;
                case 3:
                    list.printAllCDInList();
                    break;
                case 4:
                    System.out.println("1. Update CD");
                    System.out.println("2. Delete CD information");
                    System.out.print("Choose 1 or 2: ");
                    choiceUpdate = sc.nextInt();
                    switch (choiceUpdate) {
                        case 1:
                            list.updateCD();
                            break;
                        case 2:
                            list.deleteCD();
                            break;
                    }
                    break;
                case 5:
                    list.saveToFile();
                    break;
                case 6:
                    list.printAllListFromFile();
                    break;
                default:
                    System.out.println("Bye ");
            }
            System.out.print("Do you want continue ? [y or n]: ");
            ask = sc.nextLine();

        } while (choice > 0 && choice < 7 && ask.matches("[yY]"));
    }
}
