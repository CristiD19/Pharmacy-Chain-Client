package view;

import model.Angajat;
import net.sds.mvvm.bindings.Bind;
import net.sds.mvvm.bindings.Binder;
import net.sds.mvvm.bindings.BindingType;
import net.sds.mvvm.properties.Property;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;

public class AngajatView extends JFrame  {

    private JPanel contentPane;
    private JTable table;
    private JTextField numeMedicamentTextField ;
    private JTextField pretTextField ;
    private JButton btnAdaugaMedicament;
    private JButton btnAcutalizareMedicament;
    private JButton btnListaMedicamente;
    private JButton btnStergeMedicament;
    private JButton btnFiltrareMedicamente;
    private JLabel producatorLabel_1;
    private JTextField producatorTextField;
    private JLabel clientNameLabel;
    private JCheckBox disponibilCheckBox;
    private JCheckBox valabilCheckBox;
    private JTable table_1;
    private JTextField stocTextField;
    private JButton btnCautareMedicament;
    private JLabel selectedIdAngajat;
    private JLabel selectedTable1;
    private JLabel selectedTable2;
    private JButton btnFisiere;
    private JButton btnCautareDupaNume;
    private JLabel disponibilNewLabel;
    private JLabel valabilNewLabel;
    private JLabel stocLabel;
    private  JLabel pretNewLabel;
    private String l[][];


    public AngajatView(Angajat angajat, int limba) {

        l = getTextFromFile();
        selectedIdAngajat = new JLabel();
        selectedTable1 = new JLabel();
        selectedTable2 = new JLabel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1250, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel(l[limba][0]);
        lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(324, 10, 239, 54);
        contentPane.add(lblNewLabel);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                        {l[limba][15], l[limba][16], l[limba][17], l[limba][18] ,l[limba][19], l[limba][20] },
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                },
                new String[] {
                        "Id", "Disponibilitate", "Nume", "Pret", "Producator", "Valabilitate"
                }
        ));
        table.setBounds(24, 74, 345, 291);
        contentPane.add(table);

        btnAdaugaMedicament = new JButton(l[limba][1]);
        btnAdaugaMedicament.setFont(new Font("Calibri", Font.BOLD, 14));
        btnAdaugaMedicament.setBounds(10, 397, 170, 35);
        contentPane.add(btnAdaugaMedicament);

        btnAcutalizareMedicament = new JButton(l[limba][2]);
        btnAcutalizareMedicament.setFont(new Font("Calibri", Font.BOLD, 14));
        btnAcutalizareMedicament.setBounds(190, 397, 179, 35);
        contentPane.add(btnAcutalizareMedicament);

        btnListaMedicamente = new JButton(l[limba][3]);
        btnListaMedicamente.setFont(new Font("Calibri", Font.BOLD, 14));
        btnListaMedicamente.setBounds(550, 397, 147, 35);
        contentPane.add(btnListaMedicamente);

        btnStergeMedicament = new JButton(l[limba][5]);
        btnStergeMedicament.setFont(new Font("Calibri", Font.BOLD, 14));
        btnStergeMedicament.setBounds(379, 397, 161, 35);
        contentPane.add(btnStergeMedicament);

        btnFiltrareMedicamente = new JButton(l[limba][4]);
        btnFiltrareMedicamente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnFiltrareMedicamente.setFont(new Font("Calibri", Font.BOLD, 14));
        btnFiltrareMedicamente.setBounds(725, 397, 161, 35);
        contentPane.add(btnFiltrareMedicamente);

        btnCautareMedicament = new JButton(l[limba][6]);
        btnCautareMedicament.setFont(new Font("Calibri", Font.BOLD, 14));
        btnCautareMedicament.setBounds(906, 397, 161, 35);
        contentPane.add(btnCautareMedicament);

        btnCautareDupaNume = new JButton(l[limba][7]);
        btnCautareDupaNume.setFont(new Font("Calibri", Font.BOLD, 14));
        btnCautareDupaNume.setBounds(1090, 397, 136, 35);
        contentPane.add(btnCautareDupaNume);

        btnFisiere = new JButton(l[limba][8]);
        btnFisiere.setFont(new Font("Calibri", Font.BOLD, 14));
        btnFisiere.setBounds(1133, 297, 93, 68);
        contentPane.add(btnFisiere);


        clientNameLabel = new JLabel(l[limba][9]);
        clientNameLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        clientNameLabel.setBounds(853, 69, 83, 28);
        contentPane.add(clientNameLabel);

        disponibilNewLabel = new JLabel(l[limba][10]);
        disponibilNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        disponibilNewLabel.setBounds(853, 118, 83, 28);
        contentPane.add(disponibilNewLabel);

        valabilNewLabel = new JLabel(l[limba][11]);
        valabilNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        valabilNewLabel.setBounds(853, 172, 83, 28);
        contentPane.add(valabilNewLabel);

        pretNewLabel = new JLabel(l[limba][12]);
        pretNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        pretNewLabel.setBounds(853, 224, 83, 28);
        contentPane.add(pretNewLabel);

        numeMedicamentTextField = new JTextField();
        numeMedicamentTextField .setBounds(977, 66, 125, 31);
        contentPane.add(numeMedicamentTextField );
        numeMedicamentTextField .setColumns(10);

        pretTextField = new JTextField();
        pretTextField.setBounds(977, 221, 125, 31);
        contentPane.add(pretTextField);
        pretTextField.setColumns(10);



        disponibilCheckBox = new JCheckBox("");
        disponibilCheckBox.setBounds(974, 118, 93, 35);
        contentPane.add(disponibilCheckBox);

        valabilCheckBox = new JCheckBox("");
        valabilCheckBox.setBounds(974, 172, 93, 21);
        contentPane.add(valabilCheckBox);

        producatorLabel_1 = new JLabel(l[limba][13]);
        producatorLabel_1.setFont(new Font("Calibri", Font.BOLD, 14));
        producatorLabel_1.setBounds(853, 280, 83, 28);
        contentPane.add(producatorLabel_1);

        producatorTextField = new JTextField();
        producatorTextField.setColumns(10);
        producatorTextField.setBounds(977, 277, 125, 31);
        contentPane.add(producatorTextField);

        stocLabel = new JLabel(l[limba][14]);
        stocLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        stocLabel.setBounds(853, 337, 83, 28);
        contentPane.add(stocLabel);

        stocTextField = new JTextField();
        stocTextField.setColumns(10);
        stocTextField.setBounds(977, 334, 125, 31);
        contentPane.add(stocTextField);





        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
                new Object[][] {
                        {l[limba][15], l[limba][16], l[limba][17], l[limba][18] ,l[limba][19], l[limba][20], l[limba][21] },
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
                        {null, null, null, null, null, null, null},
                },
                new String[] {
                        "Id", "Disponibilitate", "Nume", "Pret", "Producator", "Valabilitate","Stoc"
                }
        ));
        table_1.setBounds(379, 74, 420, 291);
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

    public JButton getBtnAdaugaMedicament() {
        return btnAdaugaMedicament;
    }

    public JButton getBtnAcutalizareMedicament() {
        return btnAcutalizareMedicament;
    }

    public JButton getBtnListaMedicamente() {
        return btnListaMedicamente;
    }

    public JButton getBtnStergeMedicament() {
        return btnStergeMedicament;
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

    public JTable getTable_1() {
        return table_1;
    }

    public JTextField getStocTextField() {
        return stocTextField;
    }

    public JButton getBtnCautareMedicament() {
        return btnCautareMedicament;
    }

    public JLabel getSelectedIdAngajat() {
        return selectedIdAngajat;
    }

    public JLabel getSelectedTable1() {
        return selectedTable1;
    }

    public JLabel getSelectedTable2() {
        return selectedTable2;
    }

    public JButton getBtnFisiere() {
        return btnFisiere;
    }

    public JButton getBtnCautareDupaNume() {
        return btnCautareDupaNume;
    }

    public JLabel getDisponibilNewLabel() {
        return disponibilNewLabel;
    }

    public JLabel getValabilNewLabel() {
        return valabilNewLabel;
    }

    public JLabel getStocLabel() {
        return stocLabel;
    }

    public JLabel getPretNewLabel() {
        return pretNewLabel;
    }

    public void btnCautareDupaNumeListener(ActionListener actionListener){
        this.btnCautareDupaNume.addActionListener(actionListener);
    }

    public void btnFisiereListener(ActionListener actionListener){
        this.btnFisiere.addActionListener(actionListener);
    }

    public void btnAdaugaMedicamentListener(ActionListener actionListener) {
        this.btnAdaugaMedicament.addActionListener(actionListener);
    }

    public void btnFiltrareMedicamenteListener(ActionListener actionListener){
        this.btnFiltrareMedicamente.addActionListener(actionListener);
    }

    public void btnCautareMedicamentListener(ActionListener actionListener) {
        this.btnCautareMedicament.addActionListener(actionListener);
    }

    public void btnAcutalizareMedicamentListener(ActionListener actionListener){
        this.btnAcutalizareMedicament.addActionListener(actionListener);
    }

    public void btnListaMedicamenteListener(ActionListener actionListener){
        this.btnListaMedicamente.addActionListener(actionListener);
    }

    public void btnStergeMedicamentListener(ActionListener actionListener){
        this.btnStergeMedicament.addActionListener(actionListener);
    }

    public String getPretInput(){
        return this.pretTextField.getText();
    }

    public String getNumeMedicamentInput(){
        return this.numeMedicamentTextField.getText();
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

    public int getStocInput(){
        return Integer.parseInt(this.stocTextField.getText());
    }


    public void setMedicamentTableText(Object t, int row, int col) {
        table.getModel().setValueAt(t, row, col);
    }

    public void setMedicamentInFarmacieTableText(Object t, int row, int col) {
        table_1.getModel().setValueAt(t, row, col);
    }

    public int getIdFromMedicament(){
        int value;
        int column = 0;
        int row = table.getSelectedRow();
        value = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
        return value;
    }

    public int getIdFromMedicamentInFarmacie(){
        int value;
        int column = 0;
        int row = table_1.getSelectedRow();
        value = Integer.parseInt(table_1.getModel().getValueAt(row, column).toString());
        return value;
    }

    public String[][] getTextFromFile() {
        String[][] matrix = new String[3][22];

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/files/AngajatL.csv"));
            int i = 0;
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] fields;

                fields = line.split(",");
                for (int j = 0; j < 22; j++) {
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

