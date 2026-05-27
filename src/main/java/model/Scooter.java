package model;
import model.*;
public class Scooter extends Veicolo{

    private int cilindrata;


    public Scooter(int idVeicolo, String marca, String modello, String targa, String stato, int cilindrata) {
        super(idVeicolo, marca, modello, targa, stato);
        this.cilindrata = cilindrata;
    }

    public int getCilindrata(){
        return cilindrata;
    }
    public void setCilindrata(String cilindrata){
        this.cilindrata= cilindrata;



        @Override
        public String toString() {
            return super.toString() + " - " + cilindrata + "cc";
    }
}
