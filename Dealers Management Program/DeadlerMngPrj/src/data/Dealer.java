/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import tools.MyTools;
/**
 *
 * @author baohc
 */
public class Dealer implements Comparable <Dealer> {
    public static final char SEPARATOR = ',';
    public static final String idFormat = "D\\d{3}";
    public static final String phoneFormat = "\\d{9}|\\d{3}";
    private String ID;
    private String name;
    private String address;
    private String phone;
    private boolean continuing;

    public Dealer(String ID, String name, String address, String phone, boolean continuing) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.continuing = continuing;
    }

    public Dealer(String line) {
        String[] part = line.split(" " + SEPARATOR);
        ID = part[0].trim();
        name = part[1].trim();
        address = part[2].trim();
        phone = part[3].trim();
        continuing = MyTools.parseBool(part[4]);
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isContinuing() {
        return continuing;
    }

    public void setContinuing(boolean continuing) {
        this.continuing = continuing;
    }

    @Override
    public String toString() {
        return  ID + SEPARATOR + name + SEPARATOR + address + SEPARATOR + phone + SEPARATOR + continuing; 
    }
    
    public int compareTo(Dealer o) {
        return o.ID.compareTo(ID);
    }
    
}
