/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productmng;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author baohc
 */
public class ProductList {

    public ProductList() {
        super();
    }

    ArrayList<Product> list = new ArrayList<>();

    private boolean isNameDupplicated(String name) {

        name = name.trim();

        for (int i = 0; i < list.size(); i++) {
            if (name.equals(list.get(i).getProductName())) {
                return true;
            }
        }
        return false;
    }

    private boolean isIDDupplicated(String id) {

        id = id.trim();

        for (int i = 0; i < list.size(); i++) {
            if (id.equals(list.get(i).getProductID())) {
                return true;
            }
        }
        return false;
    }

    public void printListOfProduct() {
        if (list.isEmpty()) {
            System.out.println("Have no any Product");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }

    public void createProduct() {
        String newName, newID, newStatus;
        double newUnitPrice = -1;
        int newQuantity = -1;

        // Input Name
        do {

            newName = InputProduct.inputName("Product Name: ");
            newName = newName.trim();
            if (isNameDupplicated(newName)) {
                System.out.println("Name is duplicated!");
            }
        } while (isNameDupplicated(newName) || newName.contains(" ") || newName.length() < 5);

        //Input ID
        do {
            newID = InputProduct.inputID("Product ID (SE000): ");
            newID = newID.trim();
            if (isIDDupplicated(newID)) {
                System.out.println("ID is duplicated!");
            }
        } while (!newID.matches("[sS][eE][\\d]{3}") || newID.isEmpty() || isIDDupplicated(newID));
        //Input UnitPrice
        boolean checkPrice = false;
        do {
            try {
                newUnitPrice = InputProduct.inputPrice("Unit Price ( 0 --- 10000): ");
                checkPrice = false;
            } catch (Exception e) {
                System.out.println("You need input a number: ");
                checkPrice = true;
            }
        } while (checkPrice);
        //Input Quantity
        boolean checkQuantity = false;
        do {
            try {
                newQuantity = InputProduct.inputQuantity("Quantity ( 0 --- 1000 ): ");
                checkQuantity = false;
            } catch (Exception e) {
                System.out.println("You need input a number");
                checkQuantity = true;
            }
        } while (checkQuantity);
        //Input Status
        newStatus = InputProduct.inputStatus("Status available (y or n): ");

        Product product = new Product(newID, newName, newUnitPrice, newQuantity, newStatus);
        list.add(product);
        System.out.println("Create product succesfully!");
    }

    public void checkExitsProduct() {

        File file = new File("ProductData.dat");
        String checkName;
        if (file.length() == 0) {
            System.out.println("Don't have any product in file");
        } else {
            do {
                checkName = InputProduct.inputName("Name to check: ");
            } while (checkName.contains(" ") || checkName.length() < 5);
            try {
                Scanner sc = new Scanner(file);
                String product = "";
                String[] productInFile;
                String nameInFile = "";
                boolean check = false;
                while (sc.hasNextLine()) {
                    product = sc.nextLine();
                    productInFile = product.split(",");
                    if (productInFile[1].equals(checkName)) {
                        check = true;
                    }
                }

                if (check) {
                    System.out.println("Exits a product");
                } else {
                    System.out.println("No found product");
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void searchProductByName() {
        Scanner sc = new Scanner(System.in);
        String searchName = "";

        if (list.isEmpty()) {
            System.out.println("Have no any Product");
        } else {
            System.out.print("Key to search: ");
            searchName = sc.nextLine();

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getProductName().contains(searchName)) {
                    System.out.println(list.get(i));
                }
            }

        }
    }

    public void updateProduct() {

        if (list.isEmpty()) {
            System.out.println("Don't have any product in list");
        } else {

            String updateID = InputProduct.inputID("ID to update: ");
            boolean check = false;
            int pos = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getProductID().equals(updateID)) {
                    check = true;
                    pos = i;
                }
            }

            if (check == true) {
                System.out.println(list.get(pos));

                // input name
                String newName = InputProduct.updateCode("New name: ");
                if (!newName.isEmpty() && newName.length() >= 5 && !newName.contains(" ") && !isNameDupplicated(newName)) {
                    list.get(pos).setProductName(newName);
                } else {
                    System.out.println("Don't change Name");
                }

                String newID = list.get(pos).getProductID();
                // input Price
                String newPrice = "-1";
                newPrice = InputProduct.updateCode("New price ( 0 --- 10000 ): ");
                if (!newPrice.isEmpty() && Double.parseDouble(newPrice) > 0 && Double.parseDouble(newPrice) < 10000) {
                    list.get(pos).setUnitPrice(Double.parseDouble(newPrice));
                } else {
                    System.out.println("Don't change price");
                }
                //input quantity
                String newQuantity = "-1";
                newQuantity = InputProduct.updateCode("New quantity ( 0--1000 ): ");
                if (!newQuantity.isEmpty() && Integer.parseInt(newQuantity) > 0 && Integer.parseInt(newQuantity) < 1000) {
                    list.get(pos).setQuantity(Integer.parseInt(newQuantity));
                } else {
                    System.out.println("Update quantity fail try again");
                }
                // input status
                String newStatus = InputProduct.updateCode("New status availiable ( available or notavailable): ");
                if (!newStatus.isEmpty() && newStatus.equals("available")) {
                    list.get(pos).setStatus("Available");
                } else if (!newStatus.isEmpty() && newStatus.equals("notavailable")) {
                    list.get(pos).setStatus("Unavailable");
                } else if (!newStatus.isEmpty() && (!newStatus.equals("notavailable") || !newStatus.equals("available"))) {
                    System.out.println("Status don't change");
                }

                System.out.println("Update succesfully");

            } else {
                System.out.println("Don't have this id to update");
            }
        }
    }

    public void deleteProduct() {
        if (list.isEmpty()) {
            System.out.println("Don't have any product in list to delete");
        } else {

            String idDelete = InputProduct.inputID("ProductID to delete: ");
            boolean check = false;
            int pos = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getProductID().equals(idDelete)) {
                    pos = i;
                    check = true;
                }
            }

            if (check == true) {
                list.remove(pos);
                System.out.println("Delete Succesfully!");
            } else {
                System.out.println("ProductID does not exist");
            }
        }
    }

    public void saveProductToFile() {

        if (list.isEmpty()) {
            System.out.println("Don't have any product in list");
        } else {

            try {
                FileWriter writer = new FileWriter("ProductData.dat");
                BufferedWriter output = new BufferedWriter(writer);

                for (int i = 0; i < list.size(); i++) {
                    output.write(list.get(i).toString() + "\n");
                }
                output.close();

                System.out.println("Save Succesfully!");

            } catch (IOException ex) {
                Logger.getLogger(ProductList.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void printListProductFromFile() {

        File file = new File("ProductData.dat");
        ArrayList str = new ArrayList<>();
        if (file.length() <= 0) {
            System.out.println("File is empty");
        } else {
            try {
                Scanner sc = new Scanner(file);
                String product = "";
                String[] element;
                String nameFile, idFile, quantityFile, statusFile, priceFile;
//                String singleOfProduct = "";
                while (sc.hasNextLine()) {
                    product = sc.nextLine();
                    element = product.split(",");
                    idFile = element[0];
                    nameFile = element[1];
                    priceFile = element[2];
                    quantityFile = element[3];
                    statusFile = element[4];
                    Product productFile = new Product(idFile, nameFile, Double.valueOf(priceFile), Integer.valueOf(quantityFile), statusFile);
                    str.add(productFile);
                }

                Collections.sort(str, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        int alpha = o2.getQuantity() - o1.getQuantity();
                        if (alpha == 0) {
                            return (int) o1.getUnitPrice() - (int) o2.getUnitPrice();
                        }
                        return alpha;
                    }
                });

                for (int i = 0; i < str.size(); i++) {
                    System.out.println(str.get(i));
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ProductList.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void readFileSaveToList() {
        File file = new File("ProductData.dat");
        String nameFile, idFile, statusFile;
        double priceFile;
        int quantityFile;
        try {
            String line = "";
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {

                line = sc.nextLine();
                String[] element = line.split(",");
                idFile = element[0];
                nameFile = element[1];
                priceFile = Double.parseDouble(element[2]);
                quantityFile = Integer.parseInt(element[3]);
                statusFile = element[4];

                Product product = new Product(idFile, nameFile, priceFile, quantityFile, statusFile);
                list.add(product);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sortProduct(){
        Collections.sort(list, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
               int index = o1.getProductName().compareTo(o2.getProductName());
               if (index == 0) {
                   return (int)o2.getUnitPrice() - (int) o1.getUnitPrice();
               }
               return index;
            }
        });
    }

}
