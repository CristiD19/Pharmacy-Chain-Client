package model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Observable;


public class MedicamentInFarmacie extends Observable {


    private int id;

    private Farmacie farmacie;

    private Medicament medicament;

    private int stoc;

    public MedicamentInFarmacie(Farmacie farmacie, Medicament medicament, int stoc) {
        this.farmacie = farmacie;
        this.medicament = medicament;
        this.stoc = stoc;
    }

    public MedicamentInFarmacie() {

    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicamentInFarmacie that = (MedicamentInFarmacie) o;
        return id == that.id && stoc == that.stoc && Objects.equals(farmacie, that.farmacie) && Objects.equals(medicament, that.medicament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, farmacie, medicament, stoc);
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", farmacie=" + farmacie.getId() +
                ", medicament=" + medicament +
                ", stoc=" + stoc +
                '}';
    }

    public void setUpdate() {
        setChanged();
        notifyObservers(this);
        System.out.println("S-a adaugat Medicament-ul cu Id-ul: " + id);
    }
}
