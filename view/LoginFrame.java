package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.DB;
import Model.User;
import controller.Messages;

public class LoginFrame extends JPanel {
    private AppFrame frame;
    private JTextField loginField;
    private JPasswordField passField;
    private JLabel loginLabel;
    private JLabel passLabel;
    private JButton loginBtn;
    private JComboBox userCombo;
    private JLabel catLabel;


    public LoginFrame() {

        frame= new AppFrame("Login",370,250,false);

        frame.setContentPane(this);
        setLayout(null);

        loginLabel = new JLabel("Login :");
        loginLabel.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 13));
        loginLabel.setBounds(60, 30, 72, 19);
        this.add(loginLabel);

        loginField = new JTextField();
        loginField.setBounds(144, 29, 147, 23);
        loginField.setText("admin");
        this.add(loginField);

        loginField.setColumns(15);

        passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 13));
        passLabel.setBounds(60, 64, 72, 19);
        this.add(passLabel);

        passField = new JPasswordField("Admin",25);
        passField.setBounds(144, 63, 147, 23);
        this.add(passField);

        //Button
        loginBtn = new JButton("Connexion");
        loginBtn.setToolTipText("Connectez Vous a La plateforme");
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login= loginField.getText();
                String pass= String.valueOf(passField.getPassword());
                String userCat= (String) userCombo.getSelectedItem();

                if(login(login,pass,userCat.toLowerCase())) {
                    frame.dispose();
                }

            }
        });
        loginBtn.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 15));
        loginBtn.setBounds(144, 140, 107, 30);
        this.add(loginBtn);

        catLabel = new JLabel("Je suis :");
        catLabel.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 13));
        catLabel.setBounds(60, 101, 72, 19);
        add(catLabel);

        userCombo=new JComboBox();
        userCombo.setModel(new DefaultComboBoxModel(new String[] {"Etudiant", "Enseignant", "Responsable"}));
        userCombo.setSelectedIndex(0);
        userCombo.setEditable(true);
        userCombo.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 12));
        userCombo.setBounds(144, 100, 147, 23);
        add(userCombo);

    }


    private boolean login (String login, String pwd, String userTable) {

        if(login.isEmpty()||pwd.isEmpty())
        {
            Messages.showWarning(1);
            return false;}


        try {

            ResultSet rs= DB.get("*",userTable,new String[][] {{"login","=",login},{"pwd","=",pwd}});
            if(rs.next())
            {

                new ActionFrame(new User(rs,userTable));
                return true;
            }
        } catch (SQLException e) {
            Messages.showError(1);
        }
        Messages.showMessage("Nom d'utilisateur ou mot de passe erroné");
        return false;
    }

}
