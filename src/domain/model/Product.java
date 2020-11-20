package domain.model;

public class Product {
    //gegevens
    private String naam,beschrijving,eenheid,locatie;
    private int calorieen,gram;

    //constructor
    /*Javabean gedoe? 7/42
    public Product{}()
    public String getNaam(){return naam;}
    public void setNaam(String naam){this.naam = naam;}
    */

    public Product() {
    }

    public Product(String naam, String beschrijving, int calorieen, String eenheid, int gram,String locatie) {
        setNaam(naam);
        setBeschrijving(beschrijving);
        setCalorieen(calorieen);
        setEenheid(eenheid);
        setGram(gram);
        setLocatie(locatie);
    }


    //methodes


    public void setNaam(String naam) {
        if(naam == null || naam.trim().isEmpty())throw new IllegalArgumentException("Naam mag niet leeg zijn.");
        this.naam = naam;
    }

    public void setBeschrijving(String beschrijving) {
        if(beschrijving == null || beschrijving.trim().isEmpty())throw new IllegalArgumentException("Beschrijving mag niet leeg zijn.");
        this.beschrijving = beschrijving;
    }

    public void setCalorieen(int calorieen) {
        if(calorieen<=0)throw new IllegalArgumentException("Calorieen moet een getal boven 0 zijn.");
        this.calorieen = calorieen;
    }
    public void setEenheid(String eenheid) {
        this.eenheid = eenheid;
    }

    public String getEenheid() {
        return eenheid;
    }

    public void setGram(int gram) {
        if(gram<=0)throw new IllegalArgumentException("Gram moet een getal boven 0 zijn.");
        this.gram = gram;
    }
    public void setLocatie(String locatie) {
        if(locatie == null ||locatie.trim().isEmpty())throw new IllegalArgumentException("Locatie mag niet leeg zijn.");
        this.locatie = locatie;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public int getCalorieen() {
        return calorieen;
    }

    public int getGram() {
        return gram;
    }

    public String getLocatie() {
        return locatie;
    }

    public double getProcent(int calorieen){
        return calorieen/2000.0*100;
    }

    //momenteel overbodig? kan gebruikt worden om zelfde product niet 2keer te laten toevoegen?
    /*
    @Override
    public boolean equals(Product o) {
        if(this.naam.equals(o.getNaam())) return true;
        else return false;
    }*/

    @Override
    public String toString() {
        return this.naam + " is een "+ this.beschrijving+" met " + this.calorieen +" calorieen " + this.getEenheid() +" dit is "+ this.gram + " gram.";
    }


}
