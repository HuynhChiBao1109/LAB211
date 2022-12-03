package DTO;

import java.time.Year;

public class CD {

    private String ID;
    private String collectionName;
    private String type;
    private String title;
    private double unitPrice;
    private int publishingYear;
    public static String ID_PATTERN = "CD\\d{3}";
    public static final int YEAR_MIN = 1000;
    public static final int YEAR_MAX = Year.now().getValue();
    public static final double PRICE_MIN = 0;
    public static final double PRICE_MAX = 100000;
    public static final String SEPARATOR = ",";
    public static int maxCD = 10;
    public static String fileName = "CD.dat";

    public CD(String ID, String collectionName, String type, String title, double unitPrice, int publishingYear) {
        this.ID = ID;
        this.collectionName = collectionName;
        this.type = type;
        this.title = title;
        this.unitPrice = unitPrice;
        this.publishingYear = publishingYear;
    }

    public CD(String line) {
        String[] parts = line.split(SEPARATOR);
        ID = parts[0].trim();
        collectionName = parts[1].trim();
        type = parts[2].trim();
        title = parts[3].trim();
        unitPrice = Double.parseDouble(parts[4]);
        publishingYear = Integer.parseInt(parts[5]);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    @Override
    public String toString() {
        return ID + SEPARATOR + collectionName + SEPARATOR + type + SEPARATOR + title + SEPARATOR + unitPrice + SEPARATOR + publishingYear;
    }

}
