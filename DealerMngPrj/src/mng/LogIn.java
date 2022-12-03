package mng;

import data.Account;
import data.AccountChecker;
import data.DealerList;
import java.io.IOException;
import tools.MyTool;

public class LogIn {

    private Account acc = null;

    public LogIn(Account acc) {
        this.acc = acc;
    }

    public static Account inputAccount() throws IOException {
        System.out.println("--------------Login------------");
        String accName = MyTool.readNonBlank("Enter AccName");
        String pwd = MyTool.readNonBlank("Enter PassWord");
        String role = "";

        if (accName.equalsIgnoreCase("e001") && pwd.equals("12345678")) {
            role = "BOSS";
        }

        if (accName.equalsIgnoreCase("e002") && pwd.equals("23456789")) {
            role = "ACC-1";
        }

        if (accName.equalsIgnoreCase("e003") && pwd.equals("34567890")) {
            role = "ACC-2";
        }
        System.out.println("Your role: " + role);
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
            cont = false;
            AccountChecker accChk = new AccountChecker();
            acc = inputAccount();
            valid = accChk.check(acc);
            if (!valid) {
                cont = MyTool.readBool("Invalid account - Try agin?");
            }
            if (!valid && !cont) {
                System.exit(0);
            }
        } while (cont);
        System.out.println("HELLO");
        LogIn logInObj = new LogIn(acc);
        if (acc.getRole().equalsIgnoreCase("ACC-1")) {
            String[] options = {"Add new dealer", "Search a dealer", "remove a dealer", "Update a dealer", "Print all dealers", "Print Un-continuing dealers", "Print Continuing dealers", "Write to file"};
            Menu menu = new Menu(options);
            DealerList dList = new DealerList(logInObj);
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
                            boolean res = MyTool.readBool("Data changed. Write to file?");
                            if (res == true) {
                                dList.writeDealerToFile();
                            }
                        }
                }
            } while (choice > 0 && choice < 9);
            System.out.println("Finish");
        }
    }
}
