
import com.mysql.cj.xdevapi.Result;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SignUp extends javax.swing.JFrame {

    static java.sql.Connection con; // Used to get connection to datatbase
    Statement st; // Used to create retrieve query
    PreparedStatement ps; // Used to create retrieve query

    public SignUp() {
        DataBaseConnection.start();
        initComponents();
    }

    Thread DataBaseConnection = new Thread() {
        @Override
        public void run() {
            DataBaseConnection();
        }
    };

    public static Connection DataBaseConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            SignUp.con = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Users?useSSL=false", "root", "");
            return SignUp.con;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Cannot establish connction to database", "Error Occur", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        }
        return null;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Text_userName = new javax.swing.JTextField();
        Text_phone = new javax.swing.JTextField();
        Text_email = new javax.swing.JTextField();
        aaa = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        button_SignUp = new javax.swing.JButton();
        Text_password = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(Text_userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 113, 100, -1));
        getContentPane().add(Text_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 165, 100, -1));
        getContentPane().add(Text_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 165, 134, -1));

        aaa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        aaa.setText("User Name:");
        getContentPane().add(aaa, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 115, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Password:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 115, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Phone:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 167, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Email:");
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 167, -1, -1));

        button_SignUp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        button_SignUp.setText("Sign Up");
        button_SignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SignUpActionPerformed(evt);
            }
        });
        getContentPane().add(button_SignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 237, 113, -1));
        getContentPane().add(Text_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 113, 100, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SignUp");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 400, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ammar\\Desktop\\Updated Pro\\305_Project\\305_Project(Updated)\\32.jpg")); // NOI18N
        jLabel5.setToolTipText("");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 280));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_SignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SignUpActionPerformed
        try {
            String userName = Text_userName.getText(); // the user name that the user entred
            String password = Text_password.getText(); // the password that the user entred
            String phone = Text_phone.getText();
            String email = Text_email.getText();

            if (userName.equals("") || password.equals("") || phone.equals("") || email.equals("")) { // cheking if the user habe left an empty field or not
                throw new Exception_EmptyField(); // Exception_EmptyField = a custome runtime exception that will be thronw if an empty field was detected 
            }
             
            ps = con.prepareStatement("SELECT * FROM users WHERE UserName = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);      
            ps.setString(1, userName);       
            ResultSet rs = ps.executeQuery(); //Execute the query and save the result]    
            rs.last(); //Go to the last row
            if (rs.getRow() >= 1) { // if there is one row or more that has the same userName
              JOptionPane.showMessageDialog(null, "This Account Already exist !");
            } else { // no rows that has this name
                ps = con.prepareStatement("INSERT INTO users VALUES (?, ?,?,?,?,?,?)");
                ps.setString(1, userName);
                ps.setString(2, password);
                ps.setString(3, phone);
                ps.setString(4, email);
                ps.setInt(5, 0);
                ps.setString(6, null);
                ps.setDouble(7, 0.0);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Your account has been created");
                FirstScreen f = new FirstScreen();
                this.dispose();
                f.setVisible(true);
            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Plesee enter correct values", "Error !", JOptionPane.ERROR_MESSAGE);
        } 


    }//GEN-LAST:event_button_SignUpActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Text_email;
    private javax.swing.JPasswordField Text_password;
    private javax.swing.JTextField Text_phone;
    private javax.swing.JTextField Text_userName;
    private javax.swing.JLabel aaa;
    private javax.swing.JButton button_SignUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
