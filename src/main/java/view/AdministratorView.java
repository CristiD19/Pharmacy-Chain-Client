package view;

import model.Administrator;
import model.Rol;
import model.Utilizator;
import net.sds.mvvm.bindings.Bind;
import net.sds.mvvm.bindings.Binder;
import net.sds.mvvm.bindings.BindingType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class AdministratorView extends JFrame  {

    private JPanel contentPane;
    private JTable table;
    private JTextField numeUtilizatorTextField ;
    private JTextField contUtilizatorTextField ;
    private JTextField parolaUtilizatorTextField ;
    private JTextField farmacieIdField;
    private JButton btnCreareUtilizator;
    private JButton btnCitireUtilizator;
    private JButton btnStergeUtilizator;
    private JButton btnActualizareUtilizator;
    private JButton btnListaUtilizatori;
    private JComboBox rolOptions;
    private JLabel lblid;
    private JTextField idField;
    private JButton btnFiltrareUtilizatori;
    private JLabel rolNewLabel;
    private JLabel clientNameLabel;
    private JLabel contNewLabel;
    private JLabel lblFarmacieid;
    private String [][] l;
    private JButton btnSmsUtilizatori;
    private JButton btnEmailUtilizatori;



    public AdministratorView(Administrator administrator, int limba) {

        l = getTextFromFile();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1050, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel tableNewLabel = new JLabel(l[limba][0]);
        tableNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        tableNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tableNewLabel.setBounds(324, 10, 239, 54);
        contentPane.add(tableNewLabel);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{
                        {l[limba][16], l[limba][17], l[limba][18], l[limba][19], l[limba][20]},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                },
                new String[]{
                        "Rol_utilizator", "Id", "Cont", "Nume", "Parola"
                }
        ));
        table.setBounds(24, 74, 441, 291);
        contentPane.add(table);

        btnCreareUtilizator = new JButton(l[limba][1]);
        btnCreareUtilizator.setFont(new Font("Calibri", Font.BOLD, 14));
        btnCreareUtilizator.setBounds(10, 397, 170, 35);
        contentPane.add(btnCreareUtilizator);

        btnCitireUtilizator = new JButton(l[limba][2]);
        btnCitireUtilizator.setFont(new Font("Calibri", Font.BOLD, 14));
        btnCitireUtilizator.setBounds(190, 397, 179, 35);
        contentPane.add(btnCitireUtilizator);

        btnStergeUtilizator = new JButton(l[limba][4]);
        btnStergeUtilizator.setFont(new Font("Calibri", Font.BOLD, 14));
        btnStergeUtilizator.setBounds(550, 397, 147, 35);
        contentPane.add(btnStergeUtilizator);

        btnActualizareUtilizator = new JButton(l[limba][3]);
        btnActualizareUtilizator.setFont(new Font("Calibri", Font.BOLD, 14));
        btnActualizareUtilizator.setBounds(379, 397, 161, 35);
        contentPane.add(btnActualizareUtilizator);

        clientNameLabel = new JLabel(l[limba][5]);
        clientNameLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        clientNameLabel.setBounds(520, 88, 83, 28);
        contentPane.add(clientNameLabel);

        contNewLabel = new JLabel(l[limba][6]);
        contNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        contNewLabel.setBounds(520, 126, 83, 28);
        contentPane.add(contNewLabel);

        JLabel parolaNewLabel = new JLabel(l[limba][7]);
        parolaNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        parolaNewLabel.setBounds(520, 164, 83, 28);
        contentPane.add(parolaNewLabel);

        rolNewLabel = new JLabel(l[limba][8]);
        rolNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        rolNewLabel.setBounds(520, 202, 83, 28);
        contentPane.add(rolNewLabel);

        numeUtilizatorTextField = new JTextField();
        numeUtilizatorTextField.setBounds(705, 85, 125, 31);
        contentPane.add(numeUtilizatorTextField);
       // numeUtilizatorTextField.setColumns(10);

        contUtilizatorTextField = new JTextField();
        contUtilizatorTextField.setBounds(705, 123, 125, 31);
        contentPane.add(contUtilizatorTextField);
       // contUtilizatorTextField.setColumns(10);

        parolaUtilizatorTextField = new JTextField();
        parolaUtilizatorTextField.setBounds(705, 161, 125, 31);
        contentPane.add(parolaUtilizatorTextField);
        //parolaUtilizatorTextField.setColumns(10);

        btnListaUtilizatori = new JButton(l[limba][9]);
        btnListaUtilizatori.setFont(new Font("Calibri", Font.BOLD, 14));
        btnListaUtilizatori.setBounds(707, 397, 161, 35);
        contentPane.add(btnListaUtilizatori);


        String[] stringChoices = new String[]{l[limba][13], l[limba][14], l[limba][15]};
        rolOptions = new JComboBox<String>(stringChoices);
        rolOptions.setFont(new Font("Calibri", Font.BOLD, 18));
        rolOptions.setBounds(705, 200, 125, 28);
        contentPane.add(rolOptions);

        lblFarmacieid = new JLabel(l[limba][10]);
        lblFarmacieid.setFont(new Font("Calibri", Font.BOLD, 14));
        lblFarmacieid.setBounds(520, 253, 83, 28);
        contentPane.add(lblFarmacieid);

        farmacieIdField = new JTextField();
        farmacieIdField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        farmacieIdField.setColumns(10);
        farmacieIdField.setBounds(705, 247, 125, 31);
        contentPane.add(farmacieIdField);

        lblid = new JLabel(l[limba][11]);
        lblid.setFont(new Font("Calibri", Font.BOLD, 14));
        lblid.setBounds(520, 303, 83, 28);
        contentPane.add(lblid);

        idField = new JTextField();
        idField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        idField.setColumns(10);
        idField.setBounds(705, 297, 125, 31);
        contentPane.add(idField);

        btnFiltrareUtilizatori = new JButton(l[limba][12]);
        btnFiltrareUtilizatori.setFont(new Font("Calibri", Font.BOLD, 14));
        btnFiltrareUtilizatori.setBounds(878, 402, 148, 28);
        contentPane.add(btnFiltrareUtilizatori);




    }

    public String[][] getL() {
        return l;
    }

    public void btnEmailUtilizatoriListener(ActionListener actionListener){
        this.btnEmailUtilizatori.addActionListener(actionListener);
    }
    public void btnSmsUtilizatoriiListener(ActionListener actionListener){
        this.btnSmsUtilizatori.addActionListener(actionListener);
    }

    public void btnFiltrareUtilizatoriListener(ActionListener actionListener){
        this.btnFiltrareUtilizatori.addActionListener(actionListener);
    }

    public void btnCreareUtilizatorListener(ActionListener actionListener) {
        this.btnCreareUtilizator.addActionListener(actionListener);
    }

    public void btnListaUtilizatoriListener(ActionListener actionListener) {
        this.btnListaUtilizatori.addActionListener(actionListener);
    }

    public void btnCitireUtilizatorListener(ActionListener actionListener){
        this.btnCitireUtilizator.addActionListener(actionListener);
    }

    public void btnStergeUtilizatorListener(ActionListener actionListener){
        this.btnStergeUtilizator.addActionListener(actionListener);
    }

    public void btnActualizareUtilizatorListener(ActionListener actionListener){
        this.btnActualizareUtilizator.addActionListener(actionListener);
    }

    public String getContUtilizatorInput(){
        return this.contUtilizatorTextField.getText();
    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public JTable getTable() {
        return table;
    }

    public JTextField getNumeUtilizatorTextField() {
        return numeUtilizatorTextField;
    }

    public JTextField getContUtilizatorTextField() {
        return contUtilizatorTextField;
    }

    public JTextField getParolaUtilizatorTextField() {
        return parolaUtilizatorTextField;
    }

    public JTextField getFarmacieIdField() {
        return farmacieIdField;
    }

    public JButton getBtnCreareUtilizator() {
        return btnCreareUtilizator;
    }

    public JButton getBtnCitireUtilizator() {
        return btnCitireUtilizator;
    }

    public JButton getBtnStergeUtilizator() {
        return btnStergeUtilizator;
    }

    public JButton getBtnActualizareUtilizator() {
        return btnActualizareUtilizator;
    }

    public JButton getBtnListaUtilizatori() {
        return btnListaUtilizatori;
    }

    public JComboBox getRolOptions() {
        return rolOptions;
    }

    public JLabel getLblid() {
        return lblid;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JButton getBtnFiltrareUtilizatori() {
        return btnFiltrareUtilizatori;
    }

    public JLabel getRolNewLabel() {
        return rolNewLabel;
    }

    public JLabel getClientNameLabel() {
        return clientNameLabel;
    }

    public JLabel getContNewLabel() {
        return contNewLabel;
    }

    public JLabel getLblFarmacieid() {
        return lblFarmacieid;
    }

    public String[][] getTextFromFile() {
        String[][] matrix = new String[3][23];

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/files/AdministratorL.csv"));
            int i = 0;
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] fields;

                fields = line.split(",");
                for (int j = 0; j < 23; j++) {
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

    public String getParolaUtilizatorInput(){
        return this.parolaUtilizatorTextField.getText();
    }

    public String getRolUtilizatorInput(){
        return (String) this.rolOptions.getSelectedItem();
    }

    public String getNumeUtilizatorInput(){
        return this.numeUtilizatorTextField.getText();
    }

    public int getIdUtilizatorInput(){
        return Integer.parseInt(this.idField.getText());
    }

    public int getFarmacieIdInput(){
        return Integer.parseInt(this.farmacieIdField.getText());
    }

    public void setUtilizatorTabelText(Object t, int row, int col) {
        table.getModel().setValueAt(t, row, col);
    }

    public int getIdFromTable(){
        int value;
        int column = 1;
        int row = table.getSelectedRow();
        value = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
        return value;
    }



}

