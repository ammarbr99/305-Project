
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Customer_Subscription extends javax.swing.JFrame {

    ArrayList<Customers> list;
    static java.sql.Connection con; // Used to get connection to datatbase
    Statement st; // Used to create retrieve query
    PreparedStatement ps; // Used to create retrieve query
    ResultSet rs;
    static String name;

    public Customer_Subscription(String name) throws SQLException {
        this.name = name;
        initComponents();
        list = new ArrayList<Customers>(); // intiliaze the arrylist )
        DataBaseConnection.start(); // start the thread that will connect to the database
    }

    Thread DataBaseConnection = new Thread() {
        @Override
        public void run() {   // connect to the dataBase then retrive the table of customers and store them in an arrayList;
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
                while (rs.next()) {  // this loop will store every row , then create a customer object that wiill be stored in an array of customers
                    userName = rs.getString(1);
                    Password = rs.getString(2);
                    phone = rs.getString(3);
                    email = rs.getString(4);
                    subscription = rs.getInt(5);
                    subscriptionExp = rs.getString(6);
                    wallet = rs.getDouble(7);
                    Customers customer = new Customers(userName, Password, phone, email, subscription, subscriptionExp, wallet);
                    list.add(customer); // list = is an array that have all customers registred in a dataBase
                }

            } catch (SQLException ex) {
                Logger.getLogger(Employee_ManageMovies.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public static java.sql.Connection DataBaseConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Customer_Subscription.con = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Users?useSSL=false", "root", "");
            return Customer_Subscription.con;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Cannot establish connction to database", "Error Occur", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        }
        return null;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Subscription");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 36, 402, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("SUBSCRIBE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 104, 119, 55));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 266, 70, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("*Subscribe NOW to get access to ALL Available movies for *FREE*");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 189, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel3.setText("*Applies only to renting movies");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 221, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ammar\\Desktop\\Updated Pro\\305_Project\\305_Project(Updated)\\32.jpg")); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        double currentWallet = 0;
        int index = 0;
        double newAmount = 0;

        for (int i = 0; i < list.size(); i++) { // this loop search for the user by name , it is useful for knowing the index
            if (list.get(i).getUserName().equalsIgnoreCase(name)) { // also to now the current money of the user
                currentWallet = list.get(i).getWallet();
                index = i;
                break;
            }
        }

        if (list.get(index).getSubscription() == 1) { // a subscriber cant subscribe multiple times
            JOptionPane.showMessageDialog(null, " You are already a subscriber ! ");
            return;
        }

        if (currentWallet < 40) {  // user is low on cash
            JOptionPane.showMessageDialog(null, "there isn't enough cash in your wallet !", "Erorr", JOptionPane.ERROR_MESSAGE);
        } else {  // user isnt low on cash + user isnt a subscriber then he can subscribe 
            try {
                newAmount = currentWallet - 40; // after subsractin the sub price
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //  format of the date
                Calendar cal = Calendar.getInstance(); // today date
                cal.add(Calendar.DAY_OF_MONTH, 30); // adding 30 days to today , because sub is only for a month
                String newDate = sdf.format(cal.getTime()); // now i handle it as a string
                list.get(index).setWallet(newAmount);
                list.get(index).setSubscription(1);
                // now i have to make the changes on the table (Wallet + Sub)
                PreparedStatement ps = con.prepareStatement("UPDATE users set Wallet=? , Subscription=? ,subscription_expiry_date=? where UserName = ? ");
                ps.setDouble(1, newAmount);
                ps.setInt(2, 1);
                ps.setString(3, newDate);
                ps.setString(4, name);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Now you are a Subscriber");
            } catch (SQLException ex) {
                Logger.getLogger(Customer_Subscription.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Customer c = new Customer(name);
        this.dispose();
        c.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Customer_Subscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer_Subscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer_Subscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer_Subscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Customer_Subscription(name).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Customer_Subscription.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
