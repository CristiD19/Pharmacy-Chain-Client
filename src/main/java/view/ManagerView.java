package view;

import model.Manager;
import net.sds.mvvm.bindings.Bind;
import net.sds.mvvm.bindings.Binder;
import net.sds.mvvm.bindings.BindingType;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class ManagerView extends JFrame{

    private JPanel contentPane;
    private JTable table;
    private JTextField numeMedicamentTextField ;
    private JTextField pretTextField ;
    private JButton btnListaMedicamente;
    private JButton btnFiltrareMedicamente;
    private JLabel producatorLabel_1;
    private JTextField producatorTextField;
    private JLabel clientNameLabel;
    private JCheckBox disponibilCheckBox;
    private JCheckBox valabilCheckBox;
    private JButton btnCautareMedicament;
    private JTable table_1;
    private JLabel idTabelFarmacie;
    private JButton btnCreareFisiere;
    private JButton btnCreareGrafic;
    private JLabel disponibilNewLabel;
    private JLabel valabilNewLabel;
    private JLabel pretNewLabel;
    private JLabel managerNewLabel;
    private String l[][];


    public ManagerView(Manager manager, int limba) {
        l = getTextFromFile();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 950, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        managerNewLabel = new JLabel(l[limba][0]);
        managerNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        managerNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        managerNewLabel.setBounds(324, 10, 239, 54);
        contentPane.add(managerNewLabel);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                        {l[limba][11], l[limba][12], l[limba][13], l[limba][14], l[limba][15], l[limba][16], l[limba][17]},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                },
                new String[] {
                        "Id", "Disponibilitate", "Nume", "Pret", "Producator", "Valabilitate", "Stoc"
                }
        ));
        table.setBounds(24, 74, 400, 234);
        contentPane.add(table);

        idTabelFarmacie = new JLabel();



        clientNameLabel = new JLabel(l[limba][1]);
        clientNameLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        clientNameLabel.setBounds(520, 74, 83, 28);
        contentPane.add(clientNameLabel);

        disponibilNewLabel = new JLabel(l[limba][2]);
        disponibilNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        disponibilNewLabel.setBounds(520, 132, 83, 28);
        contentPane.add(disponibilNewLabel);

        valabilNewLabel = new JLabel(l[limba][3]);
        valabilNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        valabilNewLabel.setBounds(520, 188, 83, 28);
        contentPane.add(valabilNewLabel);

        pretNewLabel = new JLabel(l[limba][4]);
        pretNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        pretNewLabel.setBounds(520, 250, 83, 28);
        contentPane.add(pretNewLabel);

        numeMedicamentTextField = new JTextField();
        numeMedicamentTextField .setBounds(613, 71, 125, 31);
        contentPane.add(numeMedicamentTextField );
        numeMedicamentTextField .setColumns(10);

        pretTextField = new JTextField();
        pretTextField.setBounds(613, 247, 125, 31);
        contentPane.add(pretTextField);
        pretTextField.setColumns(10);



        disponibilCheckBox = new JCheckBox("");
        disponibilCheckBox.setBounds(609, 125, 93, 35);
        contentPane.add(disponibilCheckBox);

        valabilCheckBox = new JCheckBox("");
        valabilCheckBox.setBounds(609, 188, 93, 21);
        contentPane.add(valabilCheckBox);

        producatorLabel_1 = new JLabel(l[limba][5]);
        producatorLabel_1.setFont(new Font("Calibri", Font.BOLD, 14));
        producatorLabel_1.setBounds(520, 318, 83, 28);
        contentPane.add(producatorLabel_1);

        btnListaMedicamente = new JButton(l[limba][6]);
        btnListaMedicamente.setFont(new Font("Calibri", Font.BOLD, 14));
        btnListaMedicamente.setBounds(434, 418, 147, 35);
        contentPane.add(btnListaMedicamente);

        producatorTextField = new JTextField();
        producatorTextField.setColumns(10);
        producatorTextField.setBounds(613, 315, 125, 31);
        contentPane.add(producatorTextField);

        btnFiltrareMedicamente = new JButton(l[limba][7]);
        btnFiltrareMedicamente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnFiltrareMedicamente.setFont(new Font("Calibri", Font.BOLD, 14));
        btnFiltrareMedicamente.setBounds(591, 418, 161, 35);
        contentPane.add(btnFiltrareMedicamente);

        btnCautareMedicament = new JButton(l[limba][8]);
        btnCautareMedicament.setFont(new Font("Calibri", Font.BOLD, 14));
        btnCautareMedicament.setBounds(765, 418, 161, 35);
        contentPane.add(btnCautareMedicament);

        btnCreareFisiere = new JButton(l[limba][9]);
        btnCreareFisiere.setFont(new Font("Calibri", Font.BOLD, 14));
        btnCreareFisiere.setBounds(765, 287, 161, 97);
        contentPane.add(btnCreareFisiere);

        btnCreareGrafic = new JButton(l[limba][10]);
        btnCreareGrafic.setFont(new Font("Calibri", Font.BOLD, 14));
        btnCreareGrafic.setBounds(765, 160, 161, 97);
        contentPane.add(btnCreareGrafic);



        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
                new Object[][] {
                        {l[limba][18], l[limba][19]},
                        {null, null},
                        {null, null},
                        {null, null},
                        {null, null},
                },
                new String[] {
                        "Id", "Nume"
                }
        ));
        table_1.setBounds(24, 323, 400, 112);
        contentPane.add(table_1);


    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public JTable getTable() {
        return table;
    }

    public JTextField getNumeMedicamentTextField() {
        return numeMedicamentTextField;
    }

    public JTextField getPretTextField() {
        return pretTextField;
    }

    public JButton getBtnListaMedicamente() {
        return btnListaMedicamente;
    }

    public JButton getBtnFiltrareMedicamente() {
        return btnFiltrareMedicamente;
    }

    public JLabel getProducatorLabel_1() {
        return producatorLabel_1;
    }

    public JTextField getProducatorTextField() {
        return producatorTextField;
    }

    public JLabel getClientNameLabel() {
        return clientNameLabel;
    }

    public JCheckBox getDisponibilCheckBox() {
        return disponibilCheckBox;
    }

    public JCheckBox getValabilCheckBox() {
        return valabilCheckBox;
    }

    public JButton getBtnCautareMedicament() {
        return btnCautareMedicament;
    }

    public JTable getTable_1() {
        return table_1;
    }

    public JLabel getIdTabelFarmacie() {
        return idTabelFarmacie;
    }

    public JButton getBtnCreareFisiere() {
        return btnCreareFisiere;
    }

    public JButton getBtnCreareGrafic() {
        return btnCreareGrafic;
    }

    public JLabel getDisponibilNewLabel() {
        return disponibilNewLabel;
    }

    public JLabel getValabilNewLabel() {
        return valabilNewLabel;
    }

    public JLabel getPretNewLabel() {
        return pretNewLabel;
    }

    public JLabel getManagerNewLabel() {
        return managerNewLabel;
    }

    public void setBtnCreareFisiereListener(ActionListener actionListener){
        this.btnCreareFisiere.addActionListener(actionListener);
    }

    public void btnListaMedicamenteListener(ActionListener actionListener){
        this.btnListaMedicamente.addActionListener(actionListener);
    }

    public void btnCautareMedicamentListener(ActionListener actionListener){
        this.btnCautareMedicament.addActionListener(actionListener);
    }

    public void btnFiltrareMedicamenteListener(ActionListener actionListener){
        this.btnFiltrareMedicamente.addActionListener(actionListener);
    }

    public void btnCreareGraficListener(ActionListener actionListener){
        this.btnCreareGrafic.addActionListener(actionListener);
    }

    public String getPretInput(){
        return this.pretTextField.getText();
    }

    public String getProducatorInput(){
        return this.producatorTextField.getText();
    }

    public Boolean getValabilInput(){
        return this.valabilCheckBox.isSelected();
    }

    public Boolean getDisponibilInput(){
        return this.disponibilCheckBox.isSelected();
    }

    public String getNumeMedicamentInput(){
        return this.numeMedicamentTextField.getText();
    }

    public void setClientTableText(Object t, int row, int col) {
        table.getModel().setValueAt(t, row, col);
    }

    public void setFarmacieTableText(Object t, int row, int col) {
        table_1.getModel().setValueAt(t, row, col);
    }

    public int getIdFromTable(){
        int value;
        int column = 0;
        int row = table.getSelectedRow();
        value = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
        return value;
    }

    public int getIdFromTableFarmacie(){
        int value;
        int column = 0;
        int row = table_1.getSelectedRow();
        value = Integer.parseInt(table_1.getModel().getValueAt(row, column).toString());
        return value;
    }

    public String[][] getTextFromFile() {
        String[][] matrix = new String[3][20];

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/files/ManagerL.csv"));
            int i = 0;
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] fields;

                fields = line.split(",");
                for (int j = 0; j < 20; j++) {
                    matrix[i][j] = fields[j];
                }

                i++;
            }
        } catch(Exception exp){
            exp.printStackTrace();
            System.out.println("Exception while reading from CSV File");
        }

        return matrix;
    }

}

