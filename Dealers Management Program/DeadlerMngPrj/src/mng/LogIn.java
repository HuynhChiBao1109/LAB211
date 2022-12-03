/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import data.Account;
import data.AccountChecker;
import data.DealersList;
import java.io.IOException;
import tools.MyTools;

/**
 *
 * @author baohc
 */
public class LogIn {

    private Account acc = null;

    public LogIn(Account acc) {
        this.acc = acc;
    }

    public static Account inputAccount() {
        System.out.println("--------------Login------------");
        String accName = MyTools.readNonBlank("Enter AccName: ");
        String pwd = MyTools.readNonBlank("Enter PassWord: ");
        String role = MyTools.readNonBlank("Enter role: ");
        Account LoginAccount = new Account(accName, pwd, role);
        return LoginAccount;
    }

    public Account getAcc() {
        return acc;
    }

    public static void main(String[] args) throws IOException {
        Account acc = null;
        boolean cont = false;
        boolean valid = false;

        do {
            AccountChecker accChk = new AccountChecker();
            acc = inputAccount();
            valid = accChk.check(acc);
            if (!valid) {
                cont = MyTools.readBool("Invalid account - Try again?");
            }
            if (!valid && !cont) {
                System.exit(0);
            }
        } while (cont);
        LogIn loginObj = new LogIn(acc);

        if (acc.getRole().equalsIgnoreCase("ACC-1")) {
            System.out.println("hello");
            String[] options = {"Add new dealer", "Search a dealer", "remove a dealer", "Update a dealer", "Print all dealers", "Print Un-continuing dealers", "Print Continuing dealers", "Write to file"};
            Menu menu = new Menu(options);
            DealersList dList = new DealersList(loginObj);
            dList.iniWithFile();
            int choice = 0;
            do {
                choice = menu.getChoice("------------Manageing dealers----------");
                switch (choice) {
                    case 1:
                        dList.addDealer();
                        break;
                    case 2:
                        dList.searchDealer();
                        break;
                    case 3:
                        dList.removeDealer();
                        break;
                    case 4:
                        dList.updateDealer();
                        break;
                    case 5:
                        dList.printAllDealers();
                        break;
                    case 6:
                        dList.printUnContinuingDealers();
                        break;
                    case 7:
                        dList.printContinuingDealers();
                        break;
                    case 8:
                        dList.writeDealerToFile();
                        break;
                    default:
                        if (dList.isChanged()) {
                            boolean res = MyTools.readBool("Data changed. Write to file?");
                            if (res == true) {
                                dList.writeDealerToFile();
                            }
                        }
                }
            } while (choice > 0 && choice < menu.size());
            System.out.println("Finish");
        }
    }
}
