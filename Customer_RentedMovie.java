

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


public class Customer_RentedMovie extends javax.swing.JFrame {

    ArrayList<UserRented> rentedMovies;

    static java.sql.Connection con; // Used to get connection to datatbase
    Statement st; // Used to create retrieve query
    PreparedStatement ps; // Used to create retrieve query
    ResultSet rs;
    static String name;
    ArrayList<Movie> listMovies;

    public Customer_RentedMovie(String name) throws SQLException {
        this.name = name;
        initComponents();
        rentedMovies = new ArrayList<UserRented>();
        listMovies = new ArrayList<Movie>();
        DataBaseConnection.start();

    }

    Thread DataBaseConnection = new Thread() {
        @Override
        public void run() {
            DataBaseConnection(); // this method connect to the database
            try {

                st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from rentedMovies");

                String UserName;
                String title;
                String date;
                int status;

                while (rs.next()) {
                    UserName = rs.getString(1);
                    title = rs.getString(2);
                    date = rs.getString(3);
                    status = rs.getInt(4);

                    UserRented movie = new UserRented(UserName, title, date, status);
                    rentedMovies.add(movie);
                }

//             
               
                printTable();

            } catch (SQLException ex) {
                Logger.getLogger(Employee_ManageMovies.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public static java.sql.Connection DataBaseConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Customer_RentedMovie.con = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Users?useSSL=false", "root", "");
            return Customer_RentedMovie.con;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Cannot establish connction to database", "Error Occur", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        }
        return null;

    }

    public void printTable() {

        DefaultTableModel dm = (DefaultTableModel) table.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        Object[] row = new Object[2];
        int index = 0;
        for (int i = 0; i < rentedMovies.size(); i++) {
            if (rentedMovies.get(i).getCustomer().equalsIgnoreCase(name) && rentedMovies.get(index).isStatus() == 1) {
                index = i;
                row[0] = rentedMovies.get(index).getMovie();
                row[1] = rentedMovies.get(index).getRentDate();
                model.addRow(row);
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        Text_title = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title", "Date"
            }
        ));
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 67, 414, 91));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 278, 84, -1));

        Text_title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Text_title.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text_titleActionPerformed(evt);
            }
        });
        getContentPane().add(Text_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 192, 105, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Enter Movie Title:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 195, -1, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Return Film");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 191, 111, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Return Movie");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 434, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ammar\\Desktop\\Updated Pro\\305_Project\\305_Project(Updated)\\32.jpg")); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Customer c = new Customer(name);
        this.dispose();
        c.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Text_titleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text_titleActionPerformed

    }//GEN-LAST:event_Text_titleActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int index = 0;
        for (int i = 0; i < rentedMovies.size(); i++) {
            if (name.equalsIgnoreCase(rentedMovies.get(i).getCustomer())) {
                index = i;
                if (Text_title.getText().equalsIgnoreCase(rentedMovies.get(index).getMovie())) {
                    try {
                        ResultSet rs = st.executeQuery("select * from movies");
                        String title;
                        double price;
                        String format;
                        String year;
                        int quantity;
                        while (rs.next()) {
                            title = rs.getString(1);
                            price = rs.getDouble(2);
                            format = rs.getString(3);
                            year = rs.getString(4);
                            quantity = rs.getInt(5);
                            Movie movie = new Movie(title, price, format, year, quantity);
                            listMovies.add(movie);
                        }
                        
                        int indexM = 0;
                        int currentQ = 0;
                        
                        for (int j = 0; j < listMovies.size(); j++) { // knowing how many of that film are there so we can incresse the number
                            if (listMovies.get(i).getTitle().equalsIgnoreCase(Text_title.getText())) {
                                indexM = i;
                                currentQ = listMovies.get(indexM).getQuantity();
                            }
                        }
                        
                        PreparedStatement ps = con.prepareStatement("UPDATE movies set Quantity=? where Title = ? ");
                        ps.setInt(1,currentQ+1);
                        ps.setString(2, Text_title.getText());
                        ps.executeUpdate();
                       
                        
                        
                        
                        rentedMovies.remove(index);
                        ps = con.prepareStatement("delete  from rentedMovies where movie = ?");
                        ps.setString(1, Text_title.getText());
                        ps.executeUpdate();
                        printTable();
                        
                         break;
                    } catch (SQLException ex) {
                        Logger.getLogger(Customer_RentedMovie.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
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
            java.util.logging.Logger.getLogger(Customer_RentedMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer_RentedMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer_RentedMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer_RentedMovie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Customer_RentedMovie(name).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Customer_RentedMovie.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Text_title;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
