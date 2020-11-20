package domain.db;

import domain.model.Product;

import java.util.ArrayList;

public class ProductDB {
    private ArrayList<Product> products,productsB;


    public ProductDB() {
        this.products = new ArrayList<>();
        //lijst opvullen
        Product Tijgerbrood = new Product("tijgerbrood", "Wit brood", 75,"per snee", 30,"Beide");
        Product Croissant = new Product("croissant", "boterachtig gebakje", 45,"per stuk", 220,"Beide");
        Product Stokbrood = new Product("stokbrood", "langwerpig wit brood", 807,"per brood", 300,"Beide");
        Product Rijsttaartje = new Product("rijsttaartje", "klein rijstaartje voor 1 persoon", 317,"per stuk",200,"Rotselaar");
        Product AardbeienTaartje = new Product("aardbeientaartje","klein aardbeientaartje voor 1 persoon",312,"per stuk", 203,"Betekom");

        products.add(Tijgerbrood);
        products.add(Croissant);
        products.add(Stokbrood);
        products.add(Rijsttaartje);
        products.add(AardbeienTaartje);

    }

        public Product findProduct(String naam) {
            for (Product x : products) {
                if (x.getNaam().equalsIgnoreCase(naam)) {
                    return x;
                }
            }
            return null;
        }
        public ArrayList<Product> getProductsLocatie(String locatie){
            ArrayList<Product> lijst = new ArrayList<>();
            for(Product x : products){
                if(x.getLocatie().equals(locatie) || x.getLocatie().equals("Beide"))
                    lijst.add(x);
                }

            return lijst;
        }

    public ArrayList<Product> getProducts() {
        return products;
    }
    public void addProduct(Product product) {
        this.products.add(product);
    }
    public void removeProduct(Product product){
        products.remove(product);
    }
    public int getAantalProducten(String locatie){
        int teller = 0;
        for(Product x : products){
            if(x.getLocatie().equals(locatie)||x.getLocatie().equals("Beide")){
                teller++;
            }
        }
        return teller;
    }

}
