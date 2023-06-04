package model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Locale;
import java.util.Objects;
import java.util.Observer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Utilizator {

    private String rol;


    private int id;


    private String nume;


    private String cont;


    private String parola;


    public Utilizator(){}

    public Utilizator(String nume, String cont, String parola) {
        this.nume = nume;
        this.cont = cont;
        this.parola = parola;
    }

    public Utilizator(int id, String nume, String cont, String parola) {
        this.id = id;
        this.nume = nume;
        this.cont = cont;
        this.parola = parola;
    }



    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizator that = (Utilizator) o;
        return id == that.id && Objects.equals(nume, that.nume) && Objects.equals(cont, that.cont) && Objects.equals(parola, that.parola);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, cont, parola);
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", cont='" + cont + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }
}


