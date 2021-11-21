


import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class CustomerManageWallet extends javax.swing.JFrame {

    ArrayList<Customers> list;
    static java.sql.Connection con; // Used to get connection to datatbase
    Statement st; // Used to create retrieve query
    PreparedStatement ps; // Used to create retrieve query
    ResultSet rs;
    static String name;
    public CustomerManageWallet(String name) throws SQLException {
        this.name = name;
        initComponents();
        list = new ArrayList<Customers>();
        DataBaseConnection.start();
    }

    Thread DataBaseConnection = new Thread() {
        @Override
        public void run() {
            DataBaseConnection();
            try {
                st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from Users");
                String userName;
                String Password;
                String phone;
                String email;
                int subscription;
                String subscriptionExp;
                double wallet;
                while (rs.next()) {
                    userName = rs.getString(1);
                    Password = rs.getString(2);
                    phone = rs.getString(3);
                    email = rs.getString(4);
                    subscription = rs.getInt(5);
                    subscriptionExp = rs.getString(6);
                    wallet = rs.getDouble(7);
                    Customers customer = new Customers (userName, Password, phone, email, subscription, subscriptionExp,wallet);
                    list.add(customer);
                }
                int index =0;
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getUserName().equalsIgnoreCase(name)){
                       Label_amount.setText(list.get(i).getWallet()+" SR");
                       index =i;
                    }
                }
               

            } catch (SQLException ex) {
                Logger.getLogger(Employee_ManageMovies.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public static java.sql.Connection DataBaseConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            CustomerManageWallet.con = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Users?useSSL=false", "root", "");
            return CustomerManageWallet.con;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Cannot establish connction to database", "Error Occur", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        }
        return null;

    }

  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Label_amount = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        Text_amount = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Total Amount");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 101, -1, -1));

        Label_amount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_amount.setText("0.0  SR");
        getContentPane().add(Label_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 136, -1, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 122, 62, 43));

        Text_amount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Text_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_amountActionPerformed(evt);
            }
        });
        getContentPane().add(Text_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 133, 71, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 240, 86, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Add to Your Wallet");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 27, 312, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("SR");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 136, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ammar\\Desktop\\Updated Pro\\305_Project\\305_Project(Updated)\\32.jpg")); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        int choice = JOptionPane.showConfirmDialog(null, "Are you Sure Do you Want To Transfer Money to Your Wallet ?", "Confermation", JOptionPane.YES_NO_OPTION);
        if(choice ==0){
        try {
            String textAmount = Text_amount.getText();
            double newAmount = 0;
            int index=0;
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getUserName().equalsIgnoreCase(name)){
                  newAmount= list.get(i).getWallet()+Math.abs(Double.parseDouble(textAmount));
                  list.get(i).setWallet(newAmount);
                  index=i;
                }
            }
            PreparedStatement ps = con.prepareStatement("UPDATE users set Wallet=? where UserName = ? ");
             ps.setDouble(1, newAmount);
            ps.setString(2, name);
               ps.executeUpdate();
            Label_amount.setText(newAmount+" SR");
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManageWallet.class.getName()).log(Level.SEVERE, null, ex);
        } catch(NumberFormatException n){
         JOptionPane.showMessageDialog(null, "plesse enter numrical values", "Error !", JOptionPane.ERROR_MESSAGE);
        }
        }
        else {
        
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Customer c = new Customer(name);
        this.dispose();
        c.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void Text_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text_amountActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerManageWallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerManageWallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerManageWallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerManageWallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CustomerManageWallet(name).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerManageWallet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label_amount;
    private javax.swing.JTextField Text_amount;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
