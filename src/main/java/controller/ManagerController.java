package controller;

import client.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import model.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import view.AngajatView;
import view.ManagerView;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerController {

    private ManagerView managerView;
    Client client;
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public ManagerController(Manager manager, int limba, Client client) {
        this.managerView = new ManagerView(manager, limba);
        managerView.setVisible(true);


        managerView.btnCautareMedicamentListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = managerView.getNumeMedicamentInput() + "," + managerView.getIdFromTableFarmacie();
                String response = client.sendMessage("cautareMedicamentManager/" + data);


                MedicamentInFarmacie medicamentInFarmacie = null;
                try {
                    medicamentInFarmacie = objectMapper.reader().forType(MedicamentInFarmacie.class).readValue(response);
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }

                managerView.setClientTableText(medicamentInFarmacie.getId(), 1, 0);
                managerView.setClientTableText(medicamentInFarmacie.getMedicament().isDisponibil().toString(), 1, 1);
                managerView.setClientTableText(medicamentInFarmacie.getMedicament().getNume(), 1, 2);
                managerView.setClientTableText(medicamentInFarmacie.getMedicament().getPret(), 1, 3);
                managerView.setClientTableText(medicamentInFarmacie.getMedicament().getProducator(), 1, 4);
                managerView.setClientTableText(medicamentInFarmacie.getMedicament().isValabil(), 1, 5);
                managerView.setClientTableText(medicamentInFarmacie.getStoc(), 1, 6);

                for (int i = 1; i < 13; i++) {
                    managerView.setClientTableText("", i + 1, 0);
                    managerView.setClientTableText("", i + 1, 1);
                    managerView.setClientTableText("", i + 1, 2);
                    managerView.setClientTableText("", i + 1, 3);
                    managerView.setClientTableText("", i + 1, 4);
                    managerView.setClientTableText("", i + 1, 5);
                    managerView.setClientTableText("", i + 1, 6);
                }
            }
        });

        managerView.btnFiltrareMedicamenteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String data = String.valueOf(managerView.getIdFromTableFarmacie());
                String response = client.sendMessage("filtrareMedicamenteManager/" + data);

                List<MedicamentInFarmacie> medicamentInFarmacieList = null;
                try {
                    medicamentInFarmacieList = objectMapper.readValue(response, new TypeReference<List<MedicamentInFarmacie>>() {
                    });
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }

                for (int i = 0; i < medicamentInFarmacieList.size(); i++) {
                    // String s = Reflection.retrieveProperties(utilizatorList.get(i));
                    //String[] string = s.split(System.lineSeparator());
                    managerView.setClientTableText(medicamentInFarmacieList.get(i).getId(), i + 1, 0);
                    managerView.setClientTableText(medicamentInFarmacieList.get(i).getMedicament().isDisponibil().toString(), i + 1, 1);
                    managerView.setClientTableText(medicamentInFarmacieList.get(i).getMedicament().getNume(), i + 1, 2);
                    managerView.setClientTableText(medicamentInFarmacieList.get(i).getMedicament().getPret(), i + 1, 3);
                    managerView.setClientTableText(medicamentInFarmacieList.get(i).getMedicament().getProducator(), i + 1, 4);
                    managerView.setClientTableText(medicamentInFarmacieList.get(i).getMedicament().isValabil(), i + 1, 5);
                    managerView.setClientTableText(medicamentInFarmacieList.get(i).getStoc(), i + 1, 6);
                }

                for (int i = medicamentInFarmacieList.size(); i < 13; i++) {
                    managerView.setClientTableText("", i + 1, 0);
                    managerView.setClientTableText("", i + 1, 1);
                    managerView.setClientTableText("", i + 1, 2);
                    managerView.setClientTableText("", i + 1, 3);
                    managerView.setClientTableText("", i + 1, 4);
                    managerView.setClientTableText("", i + 1, 5);
                    managerView.setClientTableText("", i + 1, 6);
                }
            }
        });


        managerView.btnListaMedicamenteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String data = String.valueOf(managerView.getIdFromTableFarmacie());
                String response = client.sendMessage("listaMedicamenteManager/" + data);

                List<MedicamentInFarmacie> medicamentInFarmacieList = null;
                try {
                    medicamentInFarmacieList = objectMapper.readValue(response, new TypeReference<List<MedicamentInFarmacie>>() {
                    });
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }


                for (int i = 0; i < medicamentInFarmacieList.size(); i++) {
                    managerView.setClientTableText(medicamentInFarmacieList.get(i).getId(), i + 1, 0);
                    managerView.setClientTableText(medicamentInFarmacieList.get(i).getMedicament().isDisponibil().toString(), i + 1, 1);
                    managerView.setClientTableText(medicamentInFarmacieList.get(i).getMedicament().getNume(), i + 1, 2);
                    managerView.setClientTableText(medicamentInFarmacieList.get(i).getMedicament().getPret(), i + 1, 3);
                    managerView.setClientTableText(medicamentInFarmacieList.get(i).getMedicament().getProducator(), i + 1, 4);
                    managerView.setClientTableText(medicamentInFarmacieList.get(i).getMedicament().isValabil(), i + 1, 5);
                    managerView.setClientTableText(medicamentInFarmacieList.get(i).getStoc(), i + 1, 6);
                }

                for (int i = medicamentInFarmacieList.size(); i < 13; i++) {
                    managerView.setClientTableText("", i + 1, 0);
                    managerView.setClientTableText("", i + 1, 1);
                    managerView.setClientTableText("", i + 1, 2);
                    managerView.setClientTableText("", i + 1, 3);
                    managerView.setClientTableText("", i + 1, 4);
                    managerView.setClientTableText("", i + 1, 5);
                    managerView.setClientTableText("", i + 1, 6);
                }
            }
        });

        managerView.setBtnCreareFisiereListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileWriter fileWriterTxt;
                FileWriter fileWriterCsv;
                FileWriter fileWriterJson;

                try {
                    fileWriterTxt = new FileWriter("MedicamenteInFarmacieManager.txt");
                    fileWriterCsv = new FileWriter("MedicamenteInFarmacieManager.csv");
                    fileWriterJson = new FileWriter("MedicamenteInFarmacieManager.json");

                    Gson gson = new Gson();

                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document document = builder.newDocument();

                    Element root = document.createElement("Farmacie");
                    document.appendChild(root);


                    fileWriterCsv.append("Id, Disponibilitate, Nume, Pret, Producator, Valabilitate,Stoc" + "\n");

                    String response = client.sendMessage("listaFarmacii/l");
                    List<Farmacie> listaFarmacii = objectMapper.readValue(response, new TypeReference<List<Farmacie>>() {
                    });

                    for (Farmacie farmacie : listaFarmacii) {

                        String data = String.valueOf(farmacie.getId());
                        String response2 = client.sendMessage("listaMedicamenteManagerFisiere/" + data);
                        List<MedicamentInFarmacie> medicamentInFarmacieList = null;
                        try {
                            medicamentInFarmacieList = objectMapper.readValue(response, new TypeReference<List<MedicamentInFarmacie>>() {
                            });
                        } catch (JsonProcessingException ex) {
                            ex.printStackTrace();
                        }


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
                    }

                    File xmlFile = new File("MedicamenteInFarmacieManager.xml");
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

        managerView.btnCreareGraficListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = client.sendMessage("listaFarmacii/l");
                List<Farmacie> listaFarmacii = null;
                try {
                    listaFarmacii = objectMapper.readValue(response, new TypeReference<List<Farmacie>>() {});
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                }
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                DefaultPieDataset dataset2 = new DefaultPieDataset();
                for (Farmacie farmacie : listaFarmacii) {

                    String data = String.valueOf(farmacie.getId());
                    String response2 = client.sendMessage("listaMedicamenteManagerFisiere/" + data);
                    List<MedicamentInFarmacie> medicamentInFarmacieList = null;
                    try {
                        medicamentInFarmacieList = objectMapper.readValue(response, new TypeReference<List<MedicamentInFarmacie>>() {
                        });
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                    // count the number of movies released each year
                    Map<Integer, Integer> pretMedicament = new HashMap<>();
                    for (MedicamentInFarmacie medicamentInFarmacie : medicamentInFarmacieList) {
                        System.out.println(medicamentInFarmacie.getMedicament().getNume());
                        int pret = medicamentInFarmacie.getMedicament().getPret();
                        pretMedicament.put(pret, pretMedicament.getOrDefault(pret, 0) + 1);
                    }

                    // create a dataset for the chart

                    for (int pret : pretMedicament.keySet()) {
                        int count = pretMedicament.get(pret);
                        dataset.addValue(count, "Medicamente", Integer.toString(pret));
                    }

                    Map<String, Integer> categoryCounts2 = new HashMap<>();
                    for (MedicamentInFarmacie medicamentInFarmacie : medicamentInFarmacieList) {
                        String producator = medicamentInFarmacie.getMedicament().getProducator();
                        categoryCounts2.put(producator, categoryCounts2.getOrDefault(producator, 0) + 1);
                    }

                    for (String category : categoryCounts2.keySet()) {
                        int count = categoryCounts2.get(category);
                        dataset2.setValue(category, count);
                    }
                }

                // create the chart
                JFreeChart chart = ChartFactory.createBarChart(
                        "Pretul Medicamentelor",
                        "Pret",
                        "Medicamente",
                        dataset
                );

                // display the chart in a window
                ChartFrame frame = new ChartFrame("Pretul Medicamentelor", chart);
                frame.pack();
                frame.setVisible(true);
                JFreeChart chart2 = ChartFactory.createPieChart(
                        "Producatori Farmacii",
                        dataset2,
                        true, // legend
                        true, // tooltips
                        false // urls
                );

                ChartFrame frame2 = new ChartFrame("Producatori Farmacii", chart2);
                frame2.pack();
                frame2.setVisible(true);

            }
        });

        String response = client.sendMessage("listaFarmacii/l");
        List<Farmacie> farmacieList = null;
        try {
            farmacieList = objectMapper.readValue(response, new TypeReference<List<Farmacie>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < farmacieList.size(); i++) {
            managerView.setFarmacieTableText(farmacieList.get(i).getId(), i + 1, 0);
            managerView.setFarmacieTableText(farmacieList.get(i).getName(), i + 1, 1);
        }
    }

}
