
import com.mysql.cj.xdevapi.Result;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

// ammar albar 
// abduulrahman alqahtani 
public class FirstScreen extends javax.swing.JFrame {
    
    String admin = "admin";
    String adminPassword = "000";
    static Connection con; // Used to get connection to datatbase
    Statement st; // Used to create retrieve query
    PreparedStatement ps; // Used to create retrieve query

    public FirstScreen() {
        DataBaseConnection.start();
        initComponents();
    }

    @SuppressWarnings("unchecked")

    Thread DataBaseConnection = new Thread() {
        @Override
        public void run() {
            DataBaseConnection();
        }
    };

    public static Connection DataBaseConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            FirstScreen.con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Users?useSSL=false", "root", "");
            return FirstScreen.con;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Cannot establish connction to database", "Error Occur", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        }
        return null;

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button_signIn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        textPass_password = new javax.swing.JPasswordField();
        box = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        text_userName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button_signIn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        button_signIn.setText("LogIn");
        button_signIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_signInActionPerformed(evt);
            }
        });
        getContentPane().add(button_signIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 95, 33));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Sign Up");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 95, 33));

        textPass_password.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(textPass_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 196, 100, -1));

        box.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        box.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "User", "Admin", "Employee" }));
        box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxActionPerformed(evt);
            }
        });
        getContentPane().add(box, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 137, 89, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Password:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 199, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("User Name\\ID:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 140, -1, -1));

        text_userName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(text_userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 137, 100, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LogIn");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 33, 309, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ammar\\Desktop\\Updated Pro\\305_Project\\305_Project(Updated)\\32.jpg")); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // sign up button
        SignUp su = new SignUp();
        this.dispose();
        su.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void button_signInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_signInActionPerformed
        try {
            String userName = text_userName.getText(); // the user name that the user entred
            String password = textPass_password.getText(); // the password that the user entred
            String opetion = box.getSelectedItem().toString();
            if (userName.equals("") || password.equals("")) { // cheking if the user habe left an empty field or not
                throw new Exception_EmptyField(); // Exception_EmptyField = a custome runtime exception that will be thronw if an empty field was detected
            }
            
            if (opetion.equalsIgnoreCase("User")) {

                ps = con.prepareStatement("Select * from Users where UserName=? and Password=?"); // retriving data from database
                ps.setString(1, userName);
                ps.setString(2, password);
                ResultSet result = ps.executeQuery();

                if (result.next()) { // if there was a match then ...
                    Customer customer = new Customer(userName); // create an object of menu(next page) and display it while closing the FirstScreen page
                    this.dispose();
                    customer.setVisible(true);
                }
                else{
                 JOptionPane.showMessageDialog(null, "Wrong Input","Erorr!", JOptionPane.OK_OPTION);
                }

            } else if (opetion.equalsIgnoreCase("Admin")) {
                if (userName.equalsIgnoreCase(admin) && password.equalsIgnoreCase(adminPassword)) {
                    Admin admin = new Admin();
                    this.dispose();
                    admin.setVisible(true);
                }

            } else if (opetion.equalsIgnoreCase("Employee")) {

                ps = con.prepareStatement("Select * from employees where ID=? and Password=?"); // retriving data from database
                ps.setString(1, userName);
                ps.setString(2, password);
                ResultSet result = ps.executeQuery();
                if (result.next()) { // if there was a match then ...
                    Employee e = new Employee();
                    this.dispose();
                    e.setVisible(true);
                }
            } else { // if there was no match then the user will be asked to reEnter his info
                JOptionPane.showMessageDialog(null, "Wrong password Or user name , please try again", "Erorr !!", JOptionPane.OK_OPTION);
            }
        } catch (Exception_EmptyField ef) { // Empty Field exception
            JOptionPane.showMessageDialog(null, ef.getMessage(), ef.Erorr(), JOptionPane.OK_OPTION);
        } catch (SQLException ex) {
            Logger.getLogger(FirstScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_button_signInActionPerformed

    private void boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxActionPerformed

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
            java.util.logging.Logger.getLogger(FirstScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FirstScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FirstScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FirstScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FirstScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox box;
    private javax.swing.JButton button_signIn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField textPass_password;
    private javax.swing.JTextField text_userName;
    // End of variables declaration//GEN-END:variables
}
