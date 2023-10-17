package hr.java.production.model;

public class Store {

    private String name;
    private String webAdress;
    private Item[] items;

    public Store(String name, String webAdress, Item[] items) {
        this.name = name;
        this.webAdress = webAdress;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebAdress() {
        return webAdress;
    }

    public void setWebAdress(String webAdress) {
        this.webAdress = webAdress;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}
