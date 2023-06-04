package controller;

import client.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import view.AdministratorView;
import view.AngajatView;
import view.LoginView;
import view.ManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class LoginController {
    private Client client;
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public LoginController() {
        client = new Client();
        client.startConnection("127.0.0.1", 6666);

        LoginView loginView = new LoginView();
        loginView.setVisible(true);

        loginView.btnLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = loginView.getNume();
                String password = loginView.getCont();

                String limba = loginView.getRolUtilizatorInput();
                int k = 0;
                if(limba.equals("Romana")){
                    k=0;
                }
                else if(limba.equals("Engleza")){
                    k = 1;
                }
                else
                    k=2;

                String logInResponse = client.sendMessage("login/" + username + "," + password);

                Utilizator utilizator = null;
                JsonNode rootNode = null;
                try {
                    rootNode = objectMapper.readTree(logInResponse);
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }
                String type = rootNode.get("rol").asText();
                if ("ANGAJAT".equals(type)) {
                    try {
                        utilizator = objectMapper.reader().forType(Angajat.class).readValue(logInResponse);
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                } else if ("MANAGER".equals(type)) {
                    try {
                        utilizator = objectMapper.reader().forType(Manager.class).readValue(logInResponse);
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                } else if ("ADMINISTRATOR".equals(type)) {
                    try {
                        utilizator = objectMapper.reader().forType(Administrator.class).readValue(logInResponse);
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                }


                if (utilizator instanceof Angajat) {
                    AngajatController angajatController = new AngajatController((Angajat) utilizator, k, client);
                }
                if (utilizator instanceof Administrator) {
                    AdministratorController administratorController = new AdministratorController((Administrator) utilizator, k, client);
                }
                if (utilizator instanceof Manager) {
                    ManagerController managerController = new ManagerController((Manager) utilizator, k, client);
                }
            }

        });

    }



}
