package model;

import javax.persistence.*;
import javax.swing.*;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;


public class Angajat extends Utilizator implements Observer {

    private Farmacie farmacie;

    public Angajat(){}

    public Angajat(String nume, String cont, String parola, Farmacie farmacie) {
        super(nume, cont, parola);
        this.farmacie = farmacie;
    }

    public Farmacie getFarmacie() {
        return farmacie;
    }

    public void setFarmacie(Farmacie farmacie) {
        this.farmacie = farmacie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Angajat angajat = (Angajat) o;
        return Objects.equals(farmacie, angajat.farmacie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), farmacie);
    }

    @Override
    public void update(Observable o, Object arg) {
        JOptionPane.showMessageDialog(null, "User-ul " + this.getNume() + " a adaugat un medicament in farmacia cu id-ul " + farmacie.getId());

    }
}
