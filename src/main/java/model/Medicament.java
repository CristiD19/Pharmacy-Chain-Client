package model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


public class Medicament {


    private int id;
    private String nume;
    private Boolean disponibil;
    private Boolean valabil;
    private int pret;
    private String producator;


    private List<MedicamentInFarmacie> medicamente;


    public Medicament(int id, String nume, Boolean disponibil, Boolean valabil, int pret, String producator) {
        this.id = id;
        this.nume = nume;
        this.disponibil = disponibil;
        this.valabil = valabil;
        this.pret = pret;
        this.producator = producator;
    }

    public Medicament() {

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Boolean isDisponibil() {
        return disponibil;
    }

    public void setDisponibil(Boolean disponibil) {
        this.disponibil = disponibil;
    }

    public Boolean isValabil() {
        return valabil;
    }

    public void setValabil(Boolean valabilitate) {
        this.valabil = valabil;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicament that = (Medicament) o;
        return id == that.id && disponibil == that.disponibil && valabil == that.valabil && pret == that.pret && Objects.equals(nume, that.nume) && Objects.equals(producator, that.producator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, disponibil, valabil, pret, producator);
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", disponibilitate=" + disponibil +
                ", valabilitate=" + valabil +
                ", pret=" + pret +
                ", producator='" + producator + '\'' +
                '}';
    }
}
