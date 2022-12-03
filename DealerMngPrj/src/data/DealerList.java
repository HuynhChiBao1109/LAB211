package data;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import tools.MyTool;
import mng.LogIn;

public class DealerList extends ArrayList<Dealer> {

    Scanner sc = new Scanner(System.in);
    LogIn logInObj = null;
    private String dataFile = "";
    boolean changed = false;

    public DealerList(LogIn loginObj) {
        super();
        this.logInObj = loginObj;
    }

    private void loadDealerFromFile() throws IOException {
        List<String> readDealer = MyTool.readLinesFromFile(dataFile);
        for (int i = 0 ; i < readDealer.size() ; i++) {
            String element = readDealer.get(i);
            Dealer newDealer = new Dealer(element);
            this.add(newDealer);
        }
    }

    public void iniWithFile() throws IOException {
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        loadDealerFromFile(); 
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
        String IdDealer = MyTool.readpattern("Enter ID to find Dealer: ", Dealer.ID_FORMAT);
        int pos = searchDealer(IdDealer);
        if (pos < 0) {
            System.out.println("Not Found");
        } else {
            System.out.println("Position is: " + (pos + 1));
            System.out.println(this.get(pos));
        }
    }

    public void addDealer() {
        String ID;
        String name;
        String addr;
        String phone;
        boolean continuing;
        int pos;
        do {
            ID = MyTool.readpattern("ID of new dealer(D000): ", Dealer.ID_FORMAT);
            ID = ID.toUpperCase();
            pos = searchDealer(ID);
            if (pos >= 0) {
                System.out.println("ID is duplicated");
            }
        } while (pos >= 0);
        name = MyTool.readNonBlank("Name of new dealer: ").toUpperCase();
        addr = MyTool.readNonBlank("Address of new dealer: ");
        phone = MyTool.readpattern("Phone number (10 number): ", Dealer.PHONE_FORMAT);
        continuing = true; // default value for new dealer
        Dealer d = new Dealer(ID, name, addr, phone, continuing);
        this.add(d);
        System.out.println("New dealer has been added");
        changed = true;
    }

    public void removeDealer() {
        String ID;
        int pos;
        ID = MyTool.readpattern("ID dealer to removed (D000) : ", Dealer.ID_FORMAT);
        ID = ID.toUpperCase();
        pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Not Found");
        } else if (this.get(pos).isContinuing() == false) {
            System.out.println("This ID has been removed");
        } else {
            this.get(pos).setContinuing(false);
            System.out.println("Removed");
            changed = true;
        }
    }

    public void updateDealer() {
        System.out.print("Dealer's ID needs updating: ");
        String ID = MyTool.sc.nextLine();
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Dealer " + ID + " Not Found");
        } else {
            System.out.println(this.get(pos));
            Dealer d = this.get(pos);
            String newName = ""; // update name
            System.out.print("New name, enter for update: ");
            newName = MyTool.sc.nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                d.setName(newName);
                changed = true;
            } else {
                System.out.println("Name don't update");
            }
            String addr = ""; // update address
            System.out.print("New address, enter for update: ");
            addr = MyTool.sc.nextLine().trim().toUpperCase();
            if (!addr.isEmpty()) {
                d.setAddr(addr);
                changed = true;
            } else {
                System.out.println("Address don't update");
            }
            String phone = ""; // update phone
            System.out.print("New phone, enter for update (10 number ): ");
            phone = MyTool.sc.nextLine().trim().toUpperCase();
            if (phone.matches(Dealer.PHONE_FORMAT)) {
                d.setPhone(phone);
                changed = true;
            } else {
                System.out.println("Phone don't update");
            }
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
        boolean exitDealer = false;
        for (int i =0; i < this.size(); i++) {
            if (this.get(i).isContinuing() == true) {
                System.out.println(this.get(i));
                exitDealer = true;
            }
        }
        if (!exitDealer) {
            System.out.println("Don't have Continuing Dealers");
        }
    }

    public void printUnContinuingDealers() {
        boolean exitDealer = false;
        for (int i =0; i < this.size(); i++) {
            if (this.get(i).isContinuing() == false) {
                System.out.println(this.get(i));
                exitDealer = true;
            }
        }
        if (!exitDealer) {
            System.out.println("Don't have Uncontinuing Dealers");
        }
    }

    public void writeDealerToFile() throws IOException {
        if (changed) {
            MyTool.writeFile("DealerData/dealers.txt", this);
            changed = false;
            System.out.println("Write to file succesfully!");
        } else {
            System.out.println("File don't change");
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

}
