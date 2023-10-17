package hr.java.production.main;
import hr.java.production.model.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        //UNOS PODATAKA ZA KATEGORIJE
        Integer pomocni = 0;
        Integer redni_broj = 1;
        Category newCategory = null;
        Category [] categoryField = new Category[3];

        unosKategorije(inputScanner, redni_broj, newCategory, categoryField);
        //UNOS PODATAKA ZA ARTIKLE
            Item newItem = null;
            Item [] itemField = new Item[5];

        redni_broj = 0;
        unosPodatakaArtikl(inputScanner, redni_broj, categoryField, newItem, itemField);

        Factory newFactory = null;
        Adress kompletnaAdresa = null;
        Factory [] factorieField = new Factory[2];
        Item [] novoArtiklPolje = new Item[3];

        //UNOS PODATAKA ZA TVORNICU
        Factory finalnaTvornica = null;
        redni_broj = 0;
        unosPodatakaTvornica(inputScanner, redni_broj, itemField, factorieField, novoArtiklPolje, finalnaTvornica);

        //UNOS PODATAKA ZA DUCAN
        redni_broj = 0;
        Store ducan = null;
        Store [] storeField = new Store[2];
        Item [] artiklPoljeDucan = new Item[3];
        unosPodatakaDucan(inputScanner, redni_broj, itemField, ducan, storeField, artiklPoljeDucan);

        //ispisSvihUnesenihPojmova(factorieField, novoArtiklPolje, storeField, artiklPoljeDucan);
        //ODREĐIVANJE TVORNICE KOJA PROIZVODI ARTIKL S NAJVECIM VOLUMENOM

        Factory tvornicaNajveciVolumen = null;
        BigDecimal maxVolumen = BigDecimal.ZERO;

        tvornicaNajveciVolumen = getFactoryWithBiggestItemVolume(factorieField, tvornicaNajveciVolumen, maxVolumen);

        //ODREĐIVANJE DUCANA KOJI SADRŽI ARTIKL S NAJMANJOM CIJENOM
        Store trgovinaNajmanjaCijena = null;
        BigDecimal minCijena = BigDecimal.valueOf(Double.MAX_VALUE);

        trgovinaNajmanjaCijena = getStoreWithCheapestItem(storeField, trgovinaNajmanjaCijena, minCijena);


        System.out.println("Tvornica s najvećim volumenom: " + tvornicaNajveciVolumen.getName());
        System.out.println("Trgovina s najmanjom cijenom: " + trgovinaNajmanjaCijena.getName());

    }
    private static void unosPodatakaDucan(Scanner inputScanner, Integer redni_broj, Item[] itemField, Store ducan, Store[] storeField, Item[] artiklPoljeDucan) {
        Integer pomocni;
        for(int i = 0; i < 2 ; i ++){
            do{
                System.out.print("Unesite naziv " + (redni_broj +1) + ". ducana: ");
                String storeName = inputScanner.nextLine();

                System.out.print("Unesite web adresu " + (redni_broj +1) + ". ducana: ");
                String storeWebAdress = inputScanner.nextLine();

                System.out.println("Popis artikala koje mozete odabrati ducanu.");

                int redni_broj2 = 0;
                for (Item artikli: itemField) {
                    System.out.println( (redni_broj2+1) + ". artikl:" + artikli.getName());
                    redni_broj2++;
                }
                for(int j = 0 ; j < 3 ; j++){
                    System.out.println("Odaberite " + (j+1) +". artikl koji cete dodati ducanu ");
                    int odabir = inputScanner.nextInt();
                    inputScanner.nextLine();
                    switch (odabir) {
                        case 1:
                            System.out.println("Prvi artikl dodan u ducan.");
                            artiklPoljeDucan[j] = itemField[0];

                            break;
                        case 2:
                            System.out.println("Drugi artikl dodan u ducan.");
                            artiklPoljeDucan[j] = itemField[1];
                            break;
                        case 3:
                            System.out.println("Treći artikl dodan u ducan.");
                            artiklPoljeDucan[j] = itemField[2];
                            break;
                        case 4:
                            System.out.println("Četvrit artikl dodan u ducan.");
                            artiklPoljeDucan[j] = itemField[3];
                            break;
                        case 5:
                            System.out.println("Peti artikl dodan u ducan.");
                            artiklPoljeDucan[j] = itemField[4];
                            break;
                        default:
                            System.out.println("Nevažeći unos. Molimo pokušajte ponovno.");
                            break;
                    }
                }

                if(storeName.length() == 0 || artiklPoljeDucan.length == 0 || storeWebAdress.length() == 0){
                    System.out.print("Molimo unesite ispravne podatke, polja ne smiju ostati prazna, a brojčana polja ne smiju biti jednaka 0 ili manja od 0.");
                    pomocni = 0;
                }
                else{
                    ducan = new Store(storeName, storeWebAdress, artiklPoljeDucan);

                    pomocni = 1;
                }


            }while(pomocni == 0);

            storeField[i] = ducan;

            redni_broj++;
        }
    }
    private static void unosPodatakaTvornica(Scanner inputScanner, Integer redni_broj, Item[] itemField, Factory[] factorieField, Item[] novoArtiklPolje, Factory finalnaTvornica) {
        Integer pomocni;
        Adress kompletnaAdresa;
        for(int i = 0; i < 2 ; i ++){
            do{
                System.out.print("Unesite naziv " + (redni_broj +1) + ". tvornice: ");
                String factoryName = inputScanner.nextLine();

                System.out.println("Unesite adresu " + (redni_broj +1) + ". tvornice: ");
                System.out.println("Unesite ime ulice: ");
                String streetName = inputScanner.nextLine();

                System.out.println("Unesite ime ulice: ");
                String houseNum = inputScanner.nextLine();

                System.out.println("Unesite ime grada: ");
                String cityName = inputScanner.nextLine();

                System.out.println("Unesite poštanski broj: ");
                String postal = inputScanner.nextLine();

                kompletnaAdresa = new Adress(streetName, houseNum, cityName, postal);

                System.out.println("Popis artikala koje mozete odabrati za tvornicu.");

                int redni_broj2 = 0;
                for (Item artikli: itemField) {
                    System.out.println( (redni_broj2+1) + ". artikl:" + artikli.getName());
                    redni_broj2++;
                }
                for(int j = 0 ; j < 3 ; j++){
                    System.out.println("Odaberite " + (j+1) +". artikl koji cete dodati tvornici ");
                    int odabir = inputScanner.nextInt();
                    inputScanner.nextLine();
                    switch (odabir) {
                        case 1:
                            System.out.println("Prvi artikl dodan u tvornicu.");
                            novoArtiklPolje[j] = itemField[0];
                            break;
                        case 2:
                            System.out.println("Drugi artikl dodan u tvornicu.");
                            novoArtiklPolje[j] = itemField[1];
                            break;
                        case 3:
                            System.out.println("Treći artikl dodan u tvornicu.");
                            novoArtiklPolje[j] = itemField[2];

                            break;
                        case 4:
                            System.out.println("Četvrit artikl dodan u tvornicu.");
                            novoArtiklPolje[j] = itemField[3];

                            break;
                        case 5:
                            System.out.println("Peti artikl dodan u tvornicu.");
                            novoArtiklPolje[j] = itemField[4];

                            break;
                        default:
                            System.out.println("Nevažeći unos. Molimo pokušajte ponovno.");
                            break;
                    }
                }

                if(factoryName.length() == 0 || novoArtiklPolje.length == 0 || kompletnaAdresa == null){
                    System.out.print("Molimo unesite ispravne podatke, polja ne smiju ostati prazna, a brojčana polja ne smiju biti jednaka 0 ili manja od 0.");
                    pomocni = 0;
                }
                else{
                    finalnaTvornica = new Factory(factoryName, kompletnaAdresa, novoArtiklPolje);
                    pomocni = 1;
                }


            }while(pomocni == 0);

            factorieField[i] = finalnaTvornica;

            redni_broj++;
        }
    }
    private static void unosPodatakaArtikl(Scanner inputScanner, Integer redni_broj, Category[] categoryField, Item newItem, Item[] itemField) {
        Integer pomocni;
        for(int i = 0; i < 5 ; i ++){
            do {
                System.out.print("Unesite naziv " + (redni_broj +1) + ". artikla: ");
                String itemName = inputScanner.nextLine();

                System.out.println("Dostupne kategorije su: ");
                for(Category kategorija : categoryField){
                    System.out.println("Naziv: " + kategorija.getName());
                }

                System.out.print("Unesite kategoriju " + (redni_broj +1) + ". artikla: ");
                String categoryName = inputScanner.nextLine();
                Category itemCategory = new Category(categoryName);

                System.out.print("Unesite širinu " + (redni_broj +1) + ". artikla: ");
                BigDecimal itemWidth = inputScanner.nextBigDecimal();
                inputScanner.nextLine();

                System.out.print("Unesite visinu " + (redni_broj +1) + ". artikla: ");
                BigDecimal itemHeight = inputScanner.nextBigDecimal();
                inputScanner.nextLine();

                System.out.print("Unesite duljinu " + (redni_broj +1) + ". artikla: ");
                BigDecimal itemLength = inputScanner.nextBigDecimal();
                inputScanner.nextLine();

                System.out.print("Unesite iznos troškova proizvodnje " + (redni_broj +1) + ". artikla: ");
                BigDecimal itemProductionCost = inputScanner.nextBigDecimal();
                inputScanner.nextLine();

                System.out.print("Unesite prodajnu cijenu " + (redni_broj +1) + ". artikla: ");
                BigDecimal itemSellingPrice = inputScanner.nextBigDecimal();
                inputScanner.nextLine();

                if(itemName.length() == 0 || itemCategory == null || itemWidth.compareTo(BigDecimal.ZERO) <= 0 ||
                   itemHeight.compareTo(BigDecimal.ZERO) <= 0 || itemLength.compareTo(BigDecimal.ZERO) <= 0 ||
                   itemProductionCost.compareTo(BigDecimal.ZERO) <= 0 || itemSellingPrice.compareTo(BigDecimal.ZERO) <= 0){
                    System.out.print("Molimo unesite ispravne podatke, polja ne smiju ostati prazna, a brojčana polja ne smiju biti jednaka 0 ili manja od 0.");
                    pomocni = 0;
                }
                else{
                    newItem = new Item(itemName, itemCategory, itemWidth, itemHeight, itemLength, itemProductionCost, itemSellingPrice);
                    pomocni = 1;
                }

            }while(pomocni == 0);
            itemField[i] = newItem;
            redni_broj++;
        }
    }
    private static void unosKategorije(Scanner inputScanner, Integer redni_broj, Category newCategory, Category[] categoryField) {
        Integer pomocni;
        for(int i = 0; i < 3 ; i ++){
        do {
            System.out.print("Unesite naziv " + redni_broj + ". kategorije: ");
            String categoryName = inputScanner.nextLine();

            System.out.print("Unesite opis " + redni_broj + ". kategorije: ");
            String categoryDesc = inputScanner.nextLine();

            if(categoryName.length() == 0 || categoryDesc.length() == 0 ){
                System.out.println("Molimo unesite ispravne podatke, polja ne smiju ostati prazna");
                pomocni = 0;
            }
            else{
                newCategory = new Category(categoryName, categoryDesc);
                pomocni = 1;
            }

        }while(pomocni == 0);
        categoryField[i] = newCategory;
        redni_broj++;
        }
    }
    private static void ispisSvihUnesenihPojmova(Factory[] factorieField, Item[] novoArtiklPolje, Store[] storeField, Item[] artiklPoljeDucan) {
        for (Factory tvornica: factorieField) {
            System.out.println("Ime: " + tvornica.getName() + ", Adresa: " + tvornica.getAdress());
        }
        for(Item artikl : novoArtiklPolje){
            System.out.println("Ime: " + artikl.getName() + " Kategorija: " + artikl.getCategory() + "Širina: " + artikl.getWidth() + "Visina: " + artikl.getHeight()
            + "Duljina: " + artikl.getLength() + "Cijena proizvodnje: "  + artikl.getProductionCost() +  " Prodajna cijena: " + artikl.getSellingPrice());
        }

        for (Store ducan2: storeField) {
            System.out.println("Ime: " + ducan2.getName() + ", Web Adresa: " + ducan2.getWebAdress());
        }
        for(Item artikl : artiklPoljeDucan){
            System.out.println("Ime: " + artikl.getName() + " Kategorija: " + artikl.getCategory() + "Širina: " + artikl.getWidth() + "Visina: " + artikl.getHeight()
            + "Duljina: " + artikl.getLength() + "Cijena proizvodnje: "  + artikl.getProductionCost() +  " Prodajna cijena: " + artikl.getSellingPrice());
        }
    }
    private static Store getStoreWithCheapestItem(Store[] storeField, Store trgovinaNajmanjaCijena, BigDecimal minCijena) {
        for (Store trgovina : storeField) {
            for (Item artikl : trgovina.getItems()) {
                if (artikl.getSellingPrice().compareTo(minCijena) < 0) {
                    minCijena = artikl.getSellingPrice();
                    trgovinaNajmanjaCijena = trgovina;
                }
            }
        }
        return trgovinaNajmanjaCijena;
    }
    private static Factory getFactoryWithBiggestItemVolume(Factory[] factorieField, Factory tvornicaNajveciVolumen, BigDecimal maxVolumen) {
        for (Factory tvornica : factorieField) {
            for (Item artikl : tvornica.getItems()) {
                BigDecimal volumen = artikl.calculateVolume();
                if (volumen.compareTo(maxVolumen) > 0) {
                    maxVolumen = volumen;
                    tvornicaNajveciVolumen = tvornica;
                }
            }
        }
        return tvornicaNajveciVolumen;
    }
}