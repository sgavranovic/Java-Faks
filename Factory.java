package hr.java.production.model;

public class Factory {

    private String name;
    private Adress adress;
    public Item[] items;

    public Factory(String name, Adress adress, Item[] items) {
        this.name = name;
        this.adress = adress;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public void dodajArtikl(Item artikl, int indeks){
        items[indeks]= artikl;

    }
}
