package view;
import Model.DB;
import Model.User;
import controller.Messages;

import javax.swing.*;

import java.awt.BorderLayout;
import java.sql.ResultSet;

public class MyAbs extends JPanel{
    private AppFrame frame;
    private JTable table;
    private int id;

    public MyAbs(User user) {
        id= user.id;
        frame= new AppFrame("Mes Absences",550,450,false);

        //frame.getContentPane().add(this, BorderLayout.CENTER);
        String[][] where = {
                {"id_etudiant" , "=" , ""+id}
    };
        int nb = DB.count("*" , "absence", where);
        ResultSet myRs = DB.get("*" , "absence", where);
        String[] entetes = {"numseance","date_abs","id_etudiant","id_enseignant","id_matiere"};
            String[][] donnees = new String[nb][5];
            for(int i =0;i<nb;i++){
                try{
                    myRs.next();
                    donnees[i][0] =myRs.getString("numseance");
                    donnees[i][1] =myRs.getString("date_abs");
                    donnees[i][2] =myRs.getString("id_etudiant");
                    donnees[i][3] =myRs.getString("id_enseignant");
                    donnees[i][4] =myRs.getString("id_matiere");
                }
                catch(Exception ex){
                    Messages.showError(1);
                }
                JScrollPane j = new JScrollPane();
        table = new JTable(donnees , entetes);
        j.setViewportView(table);
        frame.add(j, BorderLayout.CENTER);
    }
}
}