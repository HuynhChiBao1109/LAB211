/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.IOException;
import java.util.List;
import tools.MyTools;

/**
 *
 * @author baohc
 */
public class AccountChecker {

    private String accFile ;
    private static String SEPARATOR = ",";

    public AccountChecker() throws IOException {
        setUpAccFile();
    }

    private void setUpAccFile() throws IOException {
        Config cR = new Config();
        accFile = cR.getAccountFile();
    }

    public boolean check(Account acc) throws IOException {
        List<String> lines = MyTools.readLineFromFile(accFile);
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);

            if (parts.length < 3) {
                return false;
            }

            if (parts[0].equalsIgnoreCase(acc.getAccName()) && parts[1].equals(acc.getPsw()) && parts[2].equalsIgnoreCase(acc.getRole())) {
                return true;
            }
        }
        return false;
    }
}
