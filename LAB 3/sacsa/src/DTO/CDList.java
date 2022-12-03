/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CDList extends ArrayList< List<CD>> {

    List<CD> listAudio = new ArrayList<>();
    List<CD> listVideo = new ArrayList<>();

    private int cdCounter;

    public void addList() {
        this.add(listAudio);
        this.add(listVideo);
    }

    public boolean searchID(String id) {
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.get(i).size(); j++) {
                if (this.get(i).get(j).getID().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addNewCD() {
        if (cdCounter > CD.maxCD) {
            System.out.println("Space is full ! unable to add CD");
        } else {
            System.out.println("There are " + cdCounter + " CDs in the List");
            String collection, type, title, ID;
            double unitPrice;
            int year;

            collection = MyTool.inputCollection("Collection name (ex: game/movie/music): ");

            type = MyTool.inputType("Type (Select 1 or 2 ~ audio/video): ");

            title = MyTool.inputTitle("Title: ");

            do {
                ID = MyTool.inputID("ID (CD000): ");
            } while (searchID(ID));

            unitPrice = MyTool.inputPrice("Unit Price (" + CD.PRICE_MIN + "---" + CD.PRICE_MAX + "): ");

            year = MyTool.inputYear("Publishing year (" + CD.YEAR_MIN + "---" + CD.YEAR_MAX + "): ");

            CD cd = new CD(ID, collection, type, title, unitPrice, year);

            if (type.equals("audio")) {
                listAudio.add(cd);
            }

            if (type.equals("video")) {
                listVideo.add(cd);
            }
            cdCounter++;

            System.out.println("Add succesfully!");
        }
    }

    public void searchTitle() {
        String inputTitle = MyTool.inputNonBlank("Title to search: ");
        boolean check = false;
        System.out.println("There are " + this.cdCounter + "CD  in the archive list");
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.get(i).size(); j++) {
                if (this.get(i).get(j).getTitle().equals(inputTitle)) {
                    System.out.println(this.get(i).get(j));
                    check = true;
                }
            }
        }

        if (check == false) {
            System.out.println("Don't have this CD with title!");
        }
    }

    public void printAllCDInList() {
        if (cdCounter == 0) {
            System.out.println("List is empty!");
        } else {

            System.out.println("There are " + this.cdCounter + " CDs in the list storage.");
            System.out.print("Choice list you want to print : (1-Audio, 2-Video and 3-All): ");
            Scanner sc = new Scanner(System.in);
            int choice = 0;
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    if (listAudio.size() > 0) {
                        for (int i = 0; i < listAudio.size(); i++) {
                            System.out.println(listAudio.get(i));
                        }
                    } else {
                        System.out.println("List of Audio is empty!");
                    }
                    break;
                case 2:
                    if (listVideo.size() > 0) {
                        for (int i = 0; i < listVideo.size(); i++) {
                            System.out.println(listVideo.get(i));
                        }
                    } else {
                        System.out.println("List of Video is empty!");
                    }
                    break;
                case 3:
                    for (int i = 0; i < this.size(); i++) {
                        for (int j = 0; j < this.get(i).size(); j++) {
                            System.out.println(this.get(i).get(j));
                        }
                    }
                    break;

                default:
                    System.out.println("Something wrong");
                    break;
            }

        }
    }

    public void updateCD() {
        String id;
        int posList = -1;
        int posElement = -1;
        id = MyTool.inputID("search ID to update(CD000): ");
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.get(i).size(); j++) {
                if (this.get(i).get(j).getID().equals(id)) {
                    posList = i;
                    posElement = j;
                }
            }
        }
        if (posList < 0 || posElement < 0) {
            System.out.println("Don't have this CD");
        } else {
            System.out.println(this.get(posList).get(posElement));
            String name = "", title = "", price = "", year = "";
            //update name
            name = MyTool.inputNonBlank("New collection name(ex: game/movie/music): ");
            name = name.trim();
            if (!name.isEmpty()) {
                this.get(posList).get(posElement).setCollectionName(name);
            } else {
                System.out.println("Name don't change");
            }
            //update title
            title = MyTool.inputNonBlank("New title: ");
            title = title.trim();
            if (!title.isEmpty()) {
                this.get(posList).get(posElement).setTitle(title);
            } else {
                System.out.println("Title not changed");
            }
            //update price
            price = MyTool.inputNonBlank("New price (" + CD.PRICE_MIN + "---" + CD.PRICE_MAX + "): ");
            price = price.trim();
            try {
                if (Double.parseDouble(price) > CD.PRICE_MIN && Double.parseDouble(price) < CD.PRICE_MAX) {
                    this.get(posList).get(posElement).setUnitPrice(Double.parseDouble(price));
                } else {
                    System.out.println("Unit price don't change");
                }
            } catch (Exception e) {
                System.out.println("Unit price don't change");
            }
            //update year
            year = MyTool.inputNonBlank("New publishing year(" + CD.YEAR_MIN + "---" + CD.YEAR_MAX + "): ");
            year = year.trim();
            try {
                if (Integer.parseInt(year) > CD.YEAR_MIN && Integer.parseInt(year) < CD.YEAR_MAX && !year.isEmpty()) {
                    this.get(posList).get(posElement).setPublishingYear(Integer.parseInt(year));
                } else {
                    System.out.println("Publishing year don't change");
                }
            } catch (Exception e) {
                System.out.println("Publishing year don't change");
            }

            System.out.println("Update succesfully!");

        }
    }

    public void deleteCD() {
        String id;
        int posList = -1;
        int posElement = -1;
        id = MyTool.inputID("ID to delete(CD000): ");
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.get(i).size(); j++) {
                if (this.get(i).get(j).getID().equals(id)) {
                    posList = i;
                    posElement = j;
                }
            }
        }
        if (posList < 0 || posElement < 0) {
            System.out.println("Don't have this CD");
        } else {
            System.out.println("The CD: " + this.get(posList).get(posElement));
            System.out.println("Removed sucessfully!");
            this.get(posList).remove(posElement);

        }
        cdCounter--;
    }

    public void saveToFile() throws IOException {
        if (cdCounter == 0) {
            System.out.println("List is empty");
        } else {
            FileWriter fw = new FileWriter(CD.fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < this.size(); i++) {
                for (int j = 0; j < this.get(i).size(); j++) {
                    bw.write(this.get(i).get(j).toString() + "\n");
                }
            }
            System.out.println("Save a successfully!");
            bw.close();
            fw.close();
        }
    }

    public void printAllListFromFile() throws FileNotFoundException {
        File file = new File(CD.fileName);
        Scanner sc = new Scanner(file);
        ArrayList<CD> list = new ArrayList();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            CD cd = new CD(line);
            list.add(cd);
        }
        Collections.sort(list, new Comparator<CD>() {
            @Override
            public int compare(CD o1, CD o2) {
                return o1.getCollectionName().compareTo(o2.getCollectionName());
            }
        });

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

    public void readLineSaveToList() throws FileNotFoundException {
        File file = new File(CD.fileName);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            CD cd = new CD(line);
            if (cd.getType().equals("audio")) {
                listAudio.add(cd);
            }
            if (cd.getType().equals("video")) {
                listVideo.add(cd);
            }
        }
        cdCounter = listAudio.size() + listVideo.size();

    }

}
