/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mng.LogIn;
import tools.MyTools;

/**
 *
 * @author baohc
 */
public class DealersList extends ArrayList<Dealer> {

    Scanner sc = new Scanner(System.in);
    LogIn logInObj = null;
    private static final String phonePattern = "\\d{9}|\\d{11}";
    private String dataFile = "";
    boolean changed = false;

    public DealersList(LogIn loginObj) {
        super();
        this.logInObj = loginObj;
    }

    private void loadDealerFromFile() throws IOException {
        List<String> readDealer = MyTools.readLineFromFile(dataFile);
        if (dataFile.isEmpty()) {
            System.out.print("Empty file !");
        } else {
            for (String element : readDealer) {
                Dealer newDealer = new Dealer(element);
                this.add(newDealer);
            }
        }
    }

    public void iniWithFile() throws IOException {
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        System.out.println(dataFile);
        loadDealerFromFile();
    }

    public DealersList getContinuingList() {
        DealersList result = new DealersList(logInObj);
        for (Dealer thi : this) {
            if (thi.isContinuing() == true) {
                result.add(thi);
            }
        }
        return result;
    }

    public DealersList getUnContinuingList() {
        DealersList result = new DealersList(logInObj);
        for (Dealer thi : this) {
            if (thi.isContinuing() == false) {
                result.add(thi);
            }
        }
        return result;
    }

    private int searchDealer(String ID) {
        int count = this.size();
        for (int i = 0; i < count; i++) {
            if (this.get(i).getID().endsWith(ID)) {
                return i;
            }
        }
        return -1;
    }

    public void searchDealer() {
        System.out.println("Enter ID to find: ");
        String IdDealer = sc.nextLine();
        int pos = searchDealer(IdDealer);
        if (pos < 0) {
            System.out.println("Not Found!");
        } else {
            System.out.println("Position of dealer is: " + pos);
        }
    }

    public void addDealer() {
        String ID;
        String name; // dealer's name
        String address; // dealer's address
        String phone; // 9 or 11 digits
        boolean continuing;
        int pos;
        do {
            ID = MyTools.readPattern("ID of new dealer", Dealer.idFormat);
            ID = ID.toUpperCase();
            pos = searchDealer(ID);
            if (pos > 0) {
                System.out.println("ID is duplicated");
            }
        } while (pos >= 0);
        name = MyTools.readNonBlank("Name of new dealer: ").toUpperCase();
        address = MyTools.readNonBlank("Address of new dealer: ");
        phone = MyTools.readPattern("Phone number: ", Dealer.phoneFormat);
        continuing = true; // default value for new dealer
        Dealer d = new Dealer(ID, name, address, phone, continuing);
        this.add(d);
        System.out.println("New dealer has been added");
        changed = true;
    }

    public void removeDealer() {
        String ID;
        int pos;
        ID = MyTools.readPattern("ID of new dealer", Dealer.idFormat);
        ID = ID.toUpperCase();
        pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Not Found!");
        } else {
            this.remove(pos);
            System.out.println("Remove succesfully!");
            changed = true;
        }
    }

    public void updateDealer() {
        System.out.println("Dealer's ID needs updating: ");
        String ID = MyTools.sc.nextLine();
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Dealer " + ID + " not Found!");
        } else {
            Dealer d = this.get(pos);
            String newName = "";
            System.out.print("New name, enter for omitting: ");
            newName = MyTools.sc.nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                d.setName(newName);
                changed = true;
            }
            String addr = "";
            System.out.print("New address, enter for omitting: ");
            addr = MyTools.sc.nextLine().trim().toUpperCase();
            if (!addr.isEmpty()) {
                d.setAddress(addr);
                changed = true;
            }
            String phone = "";
            System.out.print("New PHone, enter for omitting: ");
            phone = MyTools.sc.nextLine().trim().toUpperCase();
            if (!phone.isEmpty()) {
                d.setPhone(phone);
                changed = true;
            }

            System.out.println("Update succesfully !");
        }
    }

    public void printAllDealers() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            for (Dealer thi : this) {
                System.out.println(thi.toString());
            }
        }
    }

    public void printContinuingDealers() {
        this.getContinuingList().printAllDealers();
    }

    public void printUnContinuingDealers() {
        this.getUnContinuingList().printAllDealers();
    }

    public void writeDealerToFile() throws IOException {
        if (changed) {
            MyTools.writeFile("DealerData/dealers.txt", this);
            changed = false;
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

}
