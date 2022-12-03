/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Scanner;


public class menu {

    public static int  getChoice(Object[] options){
        
        for (int i = 0; i < options.length; i++){
            System.out.println((i + 1) + "-" + options[i]);
        }
        
        System.out.print("Choose 1... " + options.length + " :");
        
        Scanner sc = new Scanner (System.in);
        return Integer.parseInt(sc.nextLine());
    }
}
