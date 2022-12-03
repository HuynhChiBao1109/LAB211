/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productmng;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author baohc
 */
public class ManagerProduct {

    public static void main(String[] args) {
        String[] options = {"Print ", "Create a Product ", "Check exits Product. ",
            "Search Product’ information by Name ", "Update Product", "Save Products to file.", "Print list Products from the file"};
        int choice = 0;
        ProductList list = new ProductList();
        String ask;
        int choiceUpdate;
        File file = new File("ProductData.dat");
        if (file.length() > 0) {
            list.readFileSaveToList();
        } else {
            System.out.println("Empty file");
        }
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n Student managing Program ");
            choice = Menu.getChoice(options);

            switch (choice) {
                case 1:
                    list.printListOfProduct();
                    break;
                case 2:
                    list.createProduct();
                    break;
                case 3:
                    list.checkExitsProduct();
                    break;
                case 4:
                    list.searchProductByName();
                    break;
                case 5:
                    System.out.println("1. Update Product");
                    System.out.println("2. Delete Product");
                    System.out.print("Choose 1 or 2: ");
                    choiceUpdate = sc.nextInt();
                    switch (choiceUpdate) {
                        case 1:
                            list.updateProduct();
                            break;
                        case 2:
                            list.deleteProduct();
                            break;
                    }

                    break;
                case 6:
                    list.saveProductToFile();
                    break;
                case 7:
                    list.printListProductFromFile();
                    break;
                default:
                    System.out.println("Bye ");
            }
            System.out.print("Do you want continue ( Y or N ): ");
            ask = sc.next();

        } while (choice > 0 && choice < 8 && ask.matches("[yY]"));
    }
}
