package model;
import model.*;

public class Auto extends Veicolo{

    private int numeroPorte;
    private String alimentazione;


    public Auto(int idVeicolo, String marca, String modello,String targa, String stato,
                int numeroPorte, String alimentazione) {
        super(idVeicolo, marca, modello, targa, stato);
        this.numeroPorte = numeroPorte;
        this.alimentazione= alimentazione;
    }

    public int getNumeroPorte() {
        return numeroPorte;
    }
    public void setNumeroPorte(int numeroPorte){
        this.numeroPorte= numeroPorte;
    }
    public String getAlimentazione() {
        return alimentazione;
    }
    public void setAlimentazione(String alimentazione){
        this.alimentazione = alimentazione;
    }
    @Override
    public String toString() {
        return super.toString() + ": " +numeroPorte+ " porte = " + alimentazione;
    }

}
