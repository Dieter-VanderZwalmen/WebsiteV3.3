package domain.model;

public class Product {
    //gegevens
    private String naam,beschrijving,eenheid;
    private int calorieen,gram;

    //constructor

    public Product(String naam, String beschrijving, int calorieen,String eenheid, int gram) {
        setNaam(naam);
        setBeschrijving(beschrijving);
        setCalorieen(calorieen);
        setEenheid(eenheid);
        setGram(gram);
    }


    //methodes


    public void setNaam(String naam) {
        if(naam == null || naam.trim().isEmpty())throw new IllegalArgumentException("mag niet leeg zijn");
        this.naam = naam;
    }

    public void setBeschrijving(String beschrijving) {
        if(beschrijving == null || beschrijving.trim().isEmpty())throw new IllegalArgumentException("mag niet leeg zijn");
        this.beschrijving = beschrijving;
    }

    public void setCalorieen(int calorieen) {
        if(calorieen<=0)throw new IllegalArgumentException("mag niet leeg zijn");
        this.calorieen = calorieen;
    }
    private void setEenheid(String eenheid) {
        this.eenheid = eenheid;
    }

    public String getEenheid() {
        return eenheid;
    }

    public void setGram(int gram) {
        if(calorieen<=0)throw new IllegalArgumentException("mag niet leeg zijn");
        this.gram = gram;
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
