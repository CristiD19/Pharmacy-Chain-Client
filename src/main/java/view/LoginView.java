package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import model.*;
import net.sds.mvvm.bindings.Bind;
import net.sds.mvvm.bindings.Binder;
import net.sds.mvvm.bindings.BindingType;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class LoginView extends JFrame implements ItemListener {
    //AngajatView angajatView = new AngajatView();
    //AdministratorView administratorView = new AdministratorView();
    //ManagerView managerView = new ManagerView();
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JPanel contentPane;
    private JLabel usernamelNewLabel;
    private JLabel contNewLabel;
    private JButton btnLogin;
    private JButton btnRegister;
    private JLabel blovoNewLabel;
    private JTextField textField;
    private JComboBox rolOptions;
    private JTextField idFarmacie;
    private JLabel parolaNewLabel;
    private JLabel lblRol;
    private JLabel lblIdulFarmaciei;

    public LoginView() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        usernamelNewLabel = new JLabel("Nume:");
        usernamelNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernamelNewLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        usernamelNewLabel.setBounds(206, 88, 106, 35);
        contentPane.add(usernamelNewLabel);

        contNewLabel = new JLabel("Cont:");
        contNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contNewLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        contNewLabel.setBounds(206, 133, 106, 35);
        contentPane.add(contNewLabel);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnLogin.setFont(new Font("Calibri", Font.BOLD, 18));
        btnLogin.setBounds(176, 338, 136, 35);
        contentPane.add(btnLogin);

        btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Calibri", Font.BOLD, 18));
        btnRegister.setBounds(420, 339, 136, 32);
        contentPane.add(btnRegister);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(391, 136, 149, 28);
        contentPane.add(usernameTextField);
        usernameTextField.setColumns(10);

        passwordTextField = new JTextField();
        passwordTextField.setBounds(391, 187, 149, 28);
        contentPane.add(passwordTextField);
        passwordTextField.setColumns(10);

        blovoNewLabel = new JLabel("LoginView");
        blovoNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        blovoNewLabel.setFont(new Font("Calibri", Font.BOLD, 28));
        blovoNewLabel.setBounds(261, 10, 205, 32);
        contentPane.add(blovoNewLabel);

        parolaNewLabel = new JLabel("Parola:");
        parolaNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        parolaNewLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        parolaNewLabel.setBounds(206, 185, 106, 35);
        contentPane.add(parolaNewLabel);

        lblRol = new JLabel("Limba:");
        lblRol.setHorizontalAlignment(SwingConstants.CENTER);
        lblRol.setFont(new Font("Calibri", Font.BOLD, 18));
        lblRol.setBounds(206, 230, 106, 35);
        contentPane.add(lblRol);

        String[] stringChoices = new String[]{"Romana", "Engleza", "Esperanto"};
        rolOptions = new JComboBox<String>(stringChoices);
        rolOptions.setFont(new Font("Calibri", Font.BOLD, 18));
        rolOptions.setBounds(400, 230, 125, 28);
        contentPane.add(rolOptions);
        rolOptions.addItemListener(this);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(391, 91, 149, 28);
        contentPane.add(textField);


        lblIdulFarmaciei = new JLabel("Id-ul Farmaciei:");
        lblIdulFarmaciei.setHorizontalAlignment(SwingConstants.CENTER);
        lblIdulFarmaciei.setFont(new Font("Calibri", Font.BOLD, 18));
        lblIdulFarmaciei.setBounds(206, 275, 136, 35);
        contentPane.add(lblIdulFarmaciei);

        idFarmacie = new JTextField();
        idFarmacie.setFont(new Font("Tahoma", Font.PLAIN, 18));
        idFarmacie.setColumns(10);
        idFarmacie.setBounds(391, 275, 149, 28);
        contentPane.add(idFarmacie);

        

   }

    public JLabel getUsernamelNewLabel(){
        return usernamelNewLabel;
    }

    public JLabel getContNewLabel(){
        return contNewLabel;
    }

    public JLabel getBlovoNewLabel(){
        return blovoNewLabel;
    }
    
    public JLabel getParolaNewLabel(){
        return parolaNewLabel;
    }

    public JLabel getLblRol(){
        return lblRol;
    }

    public JLabel getLblIdulFarmaciei(){
        return lblIdulFarmaciei;
    }

    public String getNume(){
        return this.usernameTextField.getText();
    }

    public JButton getBtnLogin(){
        return btnLogin;
    }

    public JButton getBtnRegister(){
        return btnRegister;
    }

    public String getCont(){
        return this.passwordTextField.getText();
    }

    public String getPassword(){
        return this.textField.getText();
    }

    public String getRolUtilizatorInput(){
        return (String) this.rolOptions.getSelectedItem();
    }

    public int getIdFarmacie() { return Integer.parseInt(this.idFarmacie.getText());}

    public void btnLoginListener(ActionListener actionListener){
        this.btnLogin.addActionListener(actionListener);
    }

    public void btnRegisterListener(ActionListener actionListener){
        this.btnRegister.addActionListener(actionListener);
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        String limba = (String) rolOptions.getSelectedItem();
        String[][] l = getTextFromFile();
        for(String s : l[1])
            System.out.println(s + "\n");

        if(limba.equals("Romana")){
            getUsernamelNewLabel().setText(l[0][0]);
            getContNewLabel().setText(l[0][1]);
            getBtnLogin().setText(l[0][2]);
            getBtnRegister().setText(l[0][3]);
            getBlovoNewLabel().setText(l[0][4]);
            getParolaNewLabel().setText(l[0][5]);
            getLblRol().setText(l[0][6]);
            getLblIdulFarmaciei().setText(l[0][7]);
        }
        else if(limba.equals("Engleza")){

            getUsernamelNewLabel().setText(l[1][0]);
            getContNewLabel().setText(l[1][1]);
            getBtnLogin().setText(l[1][2]);
            getBtnRegister().setText(l[1][3]);
            getBlovoNewLabel().setText(l[1][4]);
            getParolaNewLabel().setText(l[1][5]);
            getLblRol().setText(l[1][6]);
            getLblIdulFarmaciei().setText(l[1][7]);
        }
        else if(limba.equals("Esperanto")){

            getUsernamelNewLabel().setText(l[2][0]);
            getContNewLabel().setText(l[2][1]);
            getBtnLogin().setText(l[2][2]);
            getBtnRegister().setText(l[2][3]);
            getBlovoNewLabel().setText(l[2][4]);
            getParolaNewLabel().setText(l[2][5]);
            getLblRol().setText(l[2][6]);
            getLblIdulFarmaciei().setText(l[2][7]);
        }
    }



    public String[][] getTextFromFile() {
        String[][] matrix = new String[3][8];

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/files/LoginL.csv"));
            int i = 0;
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] fields;

                fields = line.split(",");
                for (int j = 0; j < 8; j++) {
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

