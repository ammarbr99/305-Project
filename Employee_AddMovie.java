
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Employee_AddMovie extends javax.swing.JFrame {

    static java.sql.Connection con; // Used to get connection to datatbase
    Statement st; // Used to create retrieve query
    PreparedStatement ps; // Used to create retrieve query

    public Employee_AddMovie() {
         DataBaseConnection.start();
        initComponents();
       
    }
    Thread DataBaseConnection = new Thread() {
        @Override
        public void run() {
            DataBaseConnection();
        }
    };

    public static java.sql.Connection DataBaseConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Employee_AddMovie.con = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Users?useSSL=false", "root", "");
            return Employee_AddMovie.con;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Cannot establish connction to database", "Error Occur", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        }
        return null;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Text_Title = new javax.swing.JTextField();
        Text_Price = new javax.swing.JTextField();
        Text_Format = new javax.swing.JTextField();
        Text_Year = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Text_Quantity = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Text_Title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(Text_Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 86, 103, -1));

        Text_Price.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(Text_Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 86, 118, -1));

        Text_Format.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(Text_Format, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 153, 103, -1));

        Text_Year.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(Text_Year, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 153, 118, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Title");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 65, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Price");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 65, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Format");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 132, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Year");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 132, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 255, 92, -1));

        Text_Quantity.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(Text_Quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 200, 71, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Custom Quantity");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 203, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Enter Movie Information");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 413, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ammar\\Desktop\\Updated Pro\\305_Project\\305_Project(Updated)\\32.jpg")); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String title = Text_Title.getText();
            String stringPrice = Text_Price.getText();
            String Year = Text_Year.getText();
            String Format = Text_Format.getText();
         
            String stringQuantity = Text_Quantity.getText();

            if (title.equals("") || Year.equals("") || Format.equals("") || stringPrice.equals("") || stringQuantity.equals("")) {
                throw new Exception_EmptyField();
            }
            ps = con.prepareStatement("SELECT * FROM movies WHERE Title = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery(); //Execute the query and save the result]
            rs.last(); //Go to the last row
            if (rs.getRow() >= 1) { // there exist row or more with this title
               JOptionPane.showMessageDialog(null, title + " is Already there ! ");
            } else {
                      System.out.println("Dosent ex");
                ps = con.prepareStatement("INSERT INTO movies VALUES (?, ?,?,?,?)");
                ps.setString(1, title);
                ps.setDouble(2, Double.parseDouble(stringPrice));
                ps.setString(3, Format);
                ps.setString(4, Year);
                ps.setInt(5, Integer.parseInt(stringQuantity));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, title + " has been successfully added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee_AddMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception_EmptyField e){
        JOptionPane.showMessageDialog(null, e.getMessage(), e.Erorr(), JOptionPane.OK_OPTION);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Employee_AddMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee_AddMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee_AddMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee_AddMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee_AddMovie().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Text_Format;
    private javax.swing.JTextField Text_Price;
    private javax.swing.JTextField Text_Quantity;
    private javax.swing.JTextField Text_Title;
    private javax.swing.JTextField Text_Year;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
