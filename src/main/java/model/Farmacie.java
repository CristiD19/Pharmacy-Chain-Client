package model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class Farmacie {


    private int id;

    private String name;

    private List<Angajat> angajati;

    private List<MedicamentInFarmacie> medicamente;

   // public MedicamentInFarmacie adaugareMedicament(Medicament medicament, int stoc){
       // MedicamentInFarmacie medicamentInFarmacie = new MedicamentInFarmacie(this, medicament, stoc);
        //medicamente.add(medicamentInFarmacie);
       // return medicamentInFarmacie;

    //}

   // public void stergeMedicament(MedicamentInFarmacie medicamentInFarmacie) {
    //    Boolean b = medicamente.remove(medicamentInFarmacie);
     //   System.out.println(b);

   // }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Angajat> getAngajati() {
        return angajati;
    }

    public void setAngajati(List<Angajat> angajati) {
        this.angajati = angajati;
    }

    public List<MedicamentInFarmacie> getMedicamente() {
        return medicamente;
    }

    public void setMedicamente(List<MedicamentInFarmacie> medicamente) {
        this.medicamente = medicamente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Farmacie farmacie = (Farmacie) o;
        return id == farmacie.id && Objects.equals(name, farmacie.name) && Objects.equals(angajati, farmacie.angajati) && Objects.equals(medicamente, farmacie.medicamente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, angajati, medicamente);
    }


}
