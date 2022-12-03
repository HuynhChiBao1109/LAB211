package data;

import java.io.IOException;
import tools.MyTool;
import java.util.List;

public class AccountChecker {

    private String accFile;
    private static String SEPARATOR = ",";

    public AccountChecker() throws IOException {
        setUpAccFile();
    }

    private void setUpAccFile() throws IOException {
        Config cR = new Config();
        accFile = cR.getAccountFile();
    }
    
    public boolean check(Account acc) throws IOException {
        List<String> lines = MyTool.readLinesFromFile(accFile);
        for (String line : lines) {
            String[] parts = line.split(this.SEPARATOR);
            if (parts.length < 3) {
                return false;
            }
            if (parts[0].equalsIgnoreCase(acc.getAccName())
                    && parts[1].equals(acc.getPwd())
                    && parts[2].equalsIgnoreCase(acc.getRole())) {
                return true;
            }
        }
        return false;
    }
}
