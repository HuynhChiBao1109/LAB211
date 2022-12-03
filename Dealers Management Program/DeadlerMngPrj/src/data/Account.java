/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author baohc
 */
public class Account {
    private String accName;
    private String psw;
    private String role;

    public Account() {
    }

    public Account(String accName, String psw, String role) {
        this.accName = accName;
        this.psw = psw;
        this.role = role;
    }

    public String getAccName() {
        return accName;
    }

    public String getPsw() {
        return psw;
    }

    public String getRole() {
        return role;
    }   
}
