package controller;

import client.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import view.AdministratorView;
import view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdministratorController {
    private AdministratorView administratorView;
    Client client;
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public AdministratorController(Administrator administrator, int limba, Client client) {
        this.administratorView = new AdministratorView(administrator, limba);
        administratorView.setVisible(true);
        this.client = client;

        administratorView.btnCreareUtilizatorListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rol = administratorView.getRolUtilizatorInput();
                String response = "";


                if (rol.equals(administratorView.getL()[limba][14])) {

                    Administrator administrator = new Administrator(administratorView.getNumeUtilizatorInput(), administratorView.getContUtilizatorInput(), administratorView.getParolaUtilizatorInput());
                    administrator.setRol("ADMINISTRATOR");
                    String userString = null;
                    try {
                        userString = objectMapper.writeValueAsString(administrator);
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                    response = client.sendMessage("creareUtilizator/" + userString);
                }
                if (rol.equals(administratorView.getL()[limba][13])) {

                    String logInResponse = client.sendMessage("getFarmacie/" + administratorView.getFarmacieIdInput());
                    Farmacie farmacie = null;
                    try {
                        farmacie = objectMapper.reader().forType(Farmacie.class).readValue(logInResponse);
                    } catch (JsonProcessingException exc) {

                    }
                    System.out.println(farmacie);
                    Angajat angajat = new Angajat(administratorView.getNumeUtilizatorInput(), administratorView.getContUtilizatorInput(), administratorView.getParolaUtilizatorInput(), farmacie);
                    angajat.setRol("ANGAJAT");
                    String userString = null;
                    try {
                        userString = objectMapper.writeValueAsString(angajat);
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                    response = client.sendMessage("creareUtilizator/" + userString);
                }
                if (rol.equals(administratorView.getL()[limba][15])) {

                    Manager manager = new Manager(administratorView.getNumeUtilizatorInput(), administratorView.getContUtilizatorInput(), administratorView.getParolaUtilizatorInput());
                    manager.setRol("MANAGER");
                    String userString = null;
                    try {
                        userString = objectMapper.writeValueAsString(manager);
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                    response = client.sendMessage("creareUtilizator/" + userString);
                }

               System.out.println(response);

            }
        });

        administratorView.btnListaUtilizatoriListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String response = client.sendMessage("listaUtilizator/l");

                List<Utilizator> utilizatorList = null;
                try {
                    utilizatorList = objectMapper.readValue(response, new TypeReference<List<Utilizator>>() {});
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }
                int length = utilizatorList.size();
                for(int i = 0; i < length; i++){
                    // String s = Reflection.retrieveProperties(utilizatorList.get(i));
                    //String[] string = s.split(System.lineSeparator());
                    administratorView.setUtilizatorTabelText(utilizatorList.get(i).getRol(), i+1, 0);
                    administratorView.setUtilizatorTabelText(Integer.toString(utilizatorList.get(i).getId()), i+1, 1);
                    administratorView.setUtilizatorTabelText(utilizatorList.get(i).getCont(), i+1, 2);
                    administratorView.setUtilizatorTabelText(utilizatorList.get(i).getNume(), i+1, 3);
                    administratorView. setUtilizatorTabelText(utilizatorList.get(i).getParola(), i+1, 4);
                }

                for(int i = utilizatorList.size(); i < 15; i++){
                    administratorView.setUtilizatorTabelText("", i+1, 0);
                    administratorView.setUtilizatorTabelText("", i+1, 1);
                    administratorView.setUtilizatorTabelText("", i+1, 2);
                    administratorView.setUtilizatorTabelText("", i+1, 3);
                    administratorView.setUtilizatorTabelText("", i+1, 4);
                }
            }
        });

        administratorView.btnCitireUtilizatorListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = String.valueOf(administratorView.getIdUtilizatorInput());
                String response = client.sendMessage("citireUtilizator/" + id);

                Utilizator utilizator = null;
                try {
                    utilizator = objectMapper.reader().forType(Utilizator.class).readValue(response);
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }

                administratorView.setUtilizatorTabelText(utilizator.getRol(), 1, 0);
                administratorView.setUtilizatorTabelText(Integer.toString(utilizator.getId()), 1, 1);
                administratorView.setUtilizatorTabelText(utilizator.getCont(), 1, 2);
                administratorView.setUtilizatorTabelText(utilizator.getNume(), 1, 3);
                administratorView.setUtilizatorTabelText(utilizator.getParola(), 1, 4);

                for(int i = 1; i < 15; i++){
                    administratorView.setUtilizatorTabelText("", i+1, 0);
                    administratorView. setUtilizatorTabelText("", i+1, 1);
                    administratorView. setUtilizatorTabelText("", i+1, 2);
                    administratorView. setUtilizatorTabelText("", i+1, 3);
                    administratorView. setUtilizatorTabelText("", i+1, 4);
                }

            }
        });


        administratorView.btnActualizareUtilizatorListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = administratorView.getNumeUtilizatorInput() + "," + administratorView.getContUtilizatorInput() + "," + administratorView.getParolaUtilizatorInput() + "," + administratorView.getIdFromTable();
                String response = client.sendMessage("updateUtilizator/" + data);
                System.out.println(response);
            }
        });

        administratorView.btnStergeUtilizatorListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = String.valueOf(administratorView.getIdFromTable());
                String response = client.sendMessage("stergeUtilizator/" + data);
                System.out.println(response);
            }
        });

        administratorView.btnFiltrareUtilizatoriListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String data = (String)administratorView.getRolUtilizatorInput();
                String response = client.sendMessage("filtrareUtilizator/" + data);

                List<Utilizator> utilizatorList = null;
                try {
                    utilizatorList = objectMapper.readValue(response, new TypeReference<List<Utilizator>>() {});
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }
                int length = utilizatorList.size();
                for(int i = 0; i < length; i++){
                    // String s = Reflection.retrieveProperties(utilizatorList.get(i));
                    //String[] string = s.split(System.lineSeparator());
                    administratorView.setUtilizatorTabelText(utilizatorList.get(i).getRol(), i+1, 0);
                    administratorView.setUtilizatorTabelText(Integer.toString(utilizatorList.get(i).getId()), i+1, 1);
                    administratorView.setUtilizatorTabelText(utilizatorList.get(i).getCont(), i+1, 2);
                    administratorView.setUtilizatorTabelText(utilizatorList.get(i).getNume(), i+1, 3);
                    administratorView. setUtilizatorTabelText(utilizatorList.get(i).getParola(), i+1, 4);
                }

                for(int i = utilizatorList.size(); i < 15; i++){
                    administratorView.setUtilizatorTabelText("", i+1, 0);
                    administratorView.setUtilizatorTabelText("", i+1, 1);
                    administratorView.setUtilizatorTabelText("", i+1, 2);
                    administratorView.setUtilizatorTabelText("", i+1, 3);
                    administratorView.setUtilizatorTabelText("", i+1, 4);
                }
            }
        });

        administratorView.btnEmailUtilizatoriListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        administratorView.btnSmsUtilizatoriiListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });



    }

}
