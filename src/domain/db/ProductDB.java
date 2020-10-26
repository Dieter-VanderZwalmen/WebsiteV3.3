package domain.db;

import domain.model.Product;

import java.util.ArrayList;

public class ProductDB {
    private ArrayList<Product> products;

    public ProductDB() {
        this.products = new ArrayList<>();
        //lijst opvullen
        Product Tijgerbrood = new Product("tijgerbrood", "Wit brood", 75,"per snee", 30);
        Product Croissant = new Product("croissant", "boterachtig gebakje", 45,"per stuk", 220);
        Product Stokbrood = new Product("stokbrood", "langwerpig wit brood", 807,"per brood", 300);
        Product Rijsttaartje = new Product("rijsttaartje", "klein rijstaartje voor 1 persoon", 317,"per stuk",200);

        products.add(Tijgerbrood);
        products.add(Croissant);
        products.add(Stokbrood);
        products.add(Rijsttaartje);

    }

        public Product findProduct(String naam) {
            for (Product x : products) {
                if (x.getNaam().equalsIgnoreCase(naam)) {
                    return x;
                }
            }
            return null;
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
}
