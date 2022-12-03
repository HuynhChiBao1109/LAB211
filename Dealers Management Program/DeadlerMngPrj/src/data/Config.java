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
public class Config {

    private static final String configFile = "DealerData/config.txt";
    private String accountFile;
    private String dealerFile;
    private String deliveryFile;

    public Config() throws IOException {
        readData();
    }

    private void readData() throws IOException {
        List<String> lines = MyTools.readLineFromFile(configFile);
        for (String line : lines) {
            String[] parts = line.split(":");

            if (line.contains("accounts")) {
                accountFile = "DealerData/" + parts[1].trim();
            } else if (line.contains("dealers")) {
                dealerFile = "DealerData/" + parts[1].trim();
            } else if (line.contains("delivery")) {
                deliveryFile = "DealerData/" + parts[1].trim();
            }
        }
    }

    public String getAccountFile() {
        return accountFile;
    }

    public String getDealerFile() {
        return dealerFile;
    }

    public String getDeliveryFile() {
        return deliveryFile;
    }

}
