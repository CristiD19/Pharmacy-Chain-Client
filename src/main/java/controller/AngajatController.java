package controller;

import client.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import view.AdministratorView;
import view.AngajatView;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class AngajatController {

    private AngajatView angajatView;
    Client client;
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public AngajatController(Angajat angajat, int limba, Client client) {
        this.angajatView = new AngajatView(angajat, limba);
        angajatView.setVisible(true);
        this.client = client;


        angajatView.btnFiltrareMedicamenteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String data = String.valueOf(angajat.getFarmacie().getId());

                String response = client.sendMessage("filtrareMedicament/" + data);

                List<MedicamentInFarmacie> medicamentInFarmacieList = null;
                try {
                    medicamentInFarmacieList = objectMapper.readValue(response, new TypeReference<List<MedicamentInFarmacie>>() {});
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }

                for (int i = 0; i < medicamentInFarmacieList.size(); i++) {
                    // String s = Reflection.retrieveProperties(utilizatorList.get(i));
                    //String[] string = s.split(System.lineSeparator());
                    angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacieList.get(i).getId(), i + 1, 0);
                    angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacieList.get(i).getMedicament().isDisponibil().toString(), i + 1, 1);
                    angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacieList.get(i).getMedicament().getNume(), i + 1, 2);
                    angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacieList.get(i).getMedicament().getPret(), i + 1, 3);
                    angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacieList.get(i).getMedicament().getProducator(), i + 1, 4);
                    angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacieList.get(i).getMedicament().isValabil(), i + 1, 5);
                    angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacieList.get(i).getStoc(), i + 1, 6);
                }

                for (int i = medicamentInFarmacieList.size(); i < 13; i++) {
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 0);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 1);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 2);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 3);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 4);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 5);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 6);
                }

            }
        });

        angajatView.btnAcutalizareMedicamentListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Boolean disponibil = angajatView.getDisponibilInput();
                String nume = angajatView.getNumeMedicamentInput();
                int pret = Integer.parseInt(angajatView.getPretInput());
                String producator = angajatView.getProducatorInput();
                Boolean valabil = angajatView.getValabilInput();
                int id = angajatView.getIdFromMedicamentInFarmacie();
                int stoc = angajatView.getStocInput();

                String data = disponibil + "," + nume + "," + pret + "," + producator + "," + valabil + "," + id + "," + stoc;


                String response = client.sendMessage("actualizareMedicament/" + data);

            }
        });

        angajatView.btnAdaugaMedicamentListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Farmacie farmacie = angajat.getFarmacie();
                String data = farmacie + ","  + angajatView.getIdFromMedicament() + "," + angajatView.getStocInput();

                String response = client.sendMessage("adaugaMedicament/" + data);
                MedicamentInFarmacie medicamentInFarmacie = null;
                try {
                    medicamentInFarmacie = objectMapper.reader().forType(MedicamentInFarmacie.class).readValue(response);
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }
                medicamentInFarmacie.addObserver(angajat);
                medicamentInFarmacie.setUpdate();

            }
        });

        angajatView.btnCautareMedicamentListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String data = angajatView.getNumeMedicamentInput() + "," + angajat.getFarmacie().getId();

                String response = client.sendMessage("cautareMedicament/" + data);

                MedicamentInFarmacie medicamentInFarmacie = null;
                try {
                    medicamentInFarmacie = objectMapper.reader().forType(MedicamentInFarmacie.class).readValue(response);
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }

                angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacie.getId(), 1, 0);
                angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacie.getMedicament().isDisponibil().toString(), 1, 1);
                angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacie.getMedicament().getNume(), 1, 2);
                angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacie.getMedicament().getPret(), 1, 3);
                angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacie.getMedicament().getProducator(), 1, 4);
                angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacie.getMedicament().isValabil(), 1, 5);
                angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacie.getStoc(), 1, 6);

                for (int i = 1; i < 12; i++) {
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 0);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 1);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 2);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 3);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 4);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 5);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 6);
                }

            }
        });

        angajatView.btnListaMedicamenteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String data = String.valueOf(angajat.getFarmacie().getId());

                String response = client.sendMessage("listaMedicament/" + data);

                List<MedicamentInFarmacie> medicamentInFarmacieList = null;
                try {
                    medicamentInFarmacieList = objectMapper.readValue(response, new TypeReference<List<MedicamentInFarmacie>>() {});
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }

                for (int i = 0; i < medicamentInFarmacieList.size(); i++) {
                    // String s = Reflection.retrieveProperties(utilizatorList.get(i));
                    //String[] string = s.split(System.lineSeparator());
                    angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacieList.get(i).getId(), i + 1, 0);
                    angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacieList.get(i).getMedicament().isDisponibil().toString(), i + 1, 1);
                    angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacieList.get(i).getMedicament().getNume(), i + 1, 2);
                    angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacieList.get(i).getMedicament().getPret(), i + 1, 3);
                    angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacieList.get(i).getMedicament().getProducator(), i + 1, 4);
                    angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacieList.get(i).getMedicament().isValabil(), i + 1, 5);
                    angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacieList.get(i).getStoc(), i + 1, 6);
                }

                for (int i = medicamentInFarmacieList.size(); i < 13; i++) {
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 0);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 1);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 2);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 3);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 4);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 5);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 6);
                }

            }
        });


        angajatView.btnStergeMedicamentListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Farmacie farmacie = angajat.getFarmacie();
                String data = String.valueOf(angajatView.getIdFromMedicamentInFarmacie()) + "," + farmacie;

                String response = client.sendMessage("stergeMedicament/" + data);


            }
        });

        angajatView.btnCautareDupaNumeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = angajatView.getNumeMedicamentInput() + "," + angajat.getFarmacie().getId();

                String response = client.sendMessage("cautareDupaNumeMedicament/" + data);

                MedicamentInFarmacie medicamentInFarmacie = null;
                try {
                    medicamentInFarmacie = objectMapper.reader().forType(MedicamentInFarmacie.class).readValue(response);
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }

                angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacie.getId(), 1, 0);
                angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacie.getMedicament().isDisponibil().toString(), 1, 1);
                angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacie.getMedicament().getNume(), 1, 2);
                angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacie.getMedicament().getPret(), 1, 3);
                angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacie.getMedicament().getProducator(), 1, 4);
                angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacie.getMedicament().isValabil(), 1, 5);
                angajatView.setMedicamentInFarmacieTableText(medicamentInFarmacie.getStoc(), 1, 6);

                for (int i = 1; i < 12; i++) {
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 0);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 1);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 2);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 3);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 4);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 5);
                    angajatView.setMedicamentInFarmacieTableText("", i + 1, 6);
                }
            }
        });


        angajatView.btnFisiereListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String data = String.valueOf(angajat.getFarmacie().getId());

                String response = client.sendMessage("listaMedicament/" + data);

                List<MedicamentInFarmacie> medicamentInFarmacieList = null;
                try {
                    medicamentInFarmacieList = objectMapper.readValue(response, new TypeReference<List<MedicamentInFarmacie>>() {});
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }

                FileWriter fileWriterTxt;
                FileWriter fileWriterCsv;
                FileWriter fileWriterJson;

                try {
                    fileWriterTxt = new FileWriter("MedicamenteInFarmacieAngajat.txt");
                    fileWriterCsv = new FileWriter("MedicamenteInFarmacieAngajat.csv");
                    fileWriterJson = new FileWriter("MedicamenteInFarmacieAngajat.json");

                    Gson gson = new Gson();

                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document document = builder.newDocument();

                    Element root = document.createElement("MedicamentInFarmacie");
                    document.appendChild(root);


                    fileWriterCsv.append("Id, Disponibilitate, Nume, Pret, Producator, Valabilitate,Stoc" + "\n");

                    for (MedicamentInFarmacie medicamentInFarmacie : medicamentInFarmacieList) {

                        fileWriterCsv.append(medicamentInFarmacie.getId() + ",");
                        fileWriterCsv.append(medicamentInFarmacie.getMedicament().isDisponibil().toString() + ",");
                        fileWriterCsv.append(medicamentInFarmacie.getMedicament().getNume() + ",");
                        fileWriterCsv.append(Integer.toString(medicamentInFarmacie.getMedicament().getPret()) + ",");
                        fileWriterCsv.append(medicamentInFarmacie.getMedicament().getProducator() + ",");
                        fileWriterCsv.append(medicamentInFarmacie.getMedicament().isValabil() + ",");
                        fileWriterCsv.append(medicamentInFarmacie.getStoc() + "\n");

                        fileWriterTxt.write(medicamentInFarmacie.toString() + "\n");


                        Element c = document.createElement("MedicamentInFarmacie");
                        root.appendChild(c);

                        Element id = document.createElement("Id");
                        id.appendChild(document.createTextNode(medicamentInFarmacie.getId() + ""));
                        c.appendChild(id);

                        Element disponibilitate = document.createElement("Disponibilitate");
                        disponibilitate.appendChild(document.createTextNode(medicamentInFarmacie.getMedicament().isDisponibil().toString()));
                        c.appendChild(disponibilitate);

                        Element nume = document.createElement("Nume");
                        nume.appendChild(document.createTextNode(medicamentInFarmacie.getMedicament().getNume()));
                        c.appendChild(nume);

                        Element pret = document.createElement("Pret");
                        pret.appendChild(document.createTextNode(Integer.toString(medicamentInFarmacie.getMedicament().getPret())));
                        c.appendChild(pret);

                        Element producator = document.createElement("Producator");
                        producator.appendChild(document.createTextNode(medicamentInFarmacie.getMedicament().getProducator() + ""));
                        c.appendChild(producator);

                        Element valabilitate = document.createElement("Valabilitate");
                        valabilitate.appendChild(document.createTextNode(medicamentInFarmacie.getMedicament().isValabil() + ""));
                        c.appendChild(valabilitate);

                        Element stoc = document.createElement("Stoc");
                        stoc.appendChild(document.createTextNode(medicamentInFarmacie.getStoc() + ""));
                        c.appendChild(stoc);


                        fileWriterJson.append(medicamentInFarmacie.toString() + "\n");

                    }

                    File xmlFile = new File("MedicamenteInFarmacieAngajat.xml");
                    javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
                    javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
                    javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
                    javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(xmlFile);
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                    transformer.transform(source, result);

                    fileWriterTxt.close();
                    fileWriterCsv.close();


                    fileWriterJson.close();

                } catch (Exception exp) {
                    System.out.println("Error while files for angajat");
                    exp.printStackTrace();
                }
            }
        });


        String response = client.sendMessage("listaMedicamentGeneral/l");

        List<Medicament> medicamentList = null;
        try {
            medicamentList = objectMapper.readValue(response, new TypeReference<List<Medicament>>() {});
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        int length = medicamentList.size();

        for (int i = 0; i < length; i++) {
            // String s = Reflection.retrieveProperties(utilizatorList.get(i));
            //String[] string = s.split(System.lineSeparator());
            angajatView.setMedicamentTableText(medicamentList.get(i).getId(), i + 1, 0);
            angajatView.setMedicamentTableText(medicamentList.get(i).isDisponibil().toString(), i + 1, 1);
            angajatView.setMedicamentTableText(medicamentList.get(i).getNume(), i + 1, 2);
            angajatView.setMedicamentTableText(medicamentList.get(i).getPret(), i + 1, 3);
            angajatView.setMedicamentTableText(medicamentList.get(i).getProducator(), i + 1, 4);
            angajatView.setMedicamentTableText(medicamentList.get(i).isValabil(), i + 1, 5);

        }


    }
}
