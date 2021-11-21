
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

public class Employee_ManageMovies extends javax.swing.JFrame {

    ArrayList<Movie> list;
    static java.sql.Connection con; // Used to get connection to datatbase
    Statement st; // Used to create retrieve query
    PreparedStatement ps; // Used to create retrieve query
    ResultSet rs;

    public Employee_ManageMovies() throws SQLException {

        initComponents();
        list = new ArrayList<Movie>();
        DataBaseConnection.start();
    }

    Thread DataBaseConnection = new Thread() {
        @Override
        public void run() {
            DataBaseConnection();
            try {
                st = con.createStatement();
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
                    list.add(movie);
                }
               printTable();
             

            } catch (SQLException ex) {
                Logger.getLogger(Employee_ManageMovies.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public static java.sql.Connection DataBaseConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Employee_ManageMovies.con = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Users?useSSL=false", "root", "");
            return Employee_ManageMovies.con;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Cannot establish connction to database", "Error Occur", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        }
        return null;

    }
    public void printTable(){
         DefaultTableModel dm = (DefaultTableModel) table.getModel();
                while (dm.getRowCount() > 0) {
                    dm.removeRow(0);
                }

                DefaultTableModel model = (DefaultTableModel) table.getModel();

                Object[] row = new Object[5];

                for (int i = 0; i < list.size(); i++) {

                    row[0] = list.get(i).getTitle();
                    row[1] = list.get(i).getYear();
                    row[2] = list.get(i).getPrice();
                    row[3] = list.get(i).getFormat();
                    row[4] = list.get(i).getQuantity();
                    model.addRow(row);
                }
    
    }

    @SuppressWarnings("unchecked")

//        ResultSet rs = st.executeQuery("select * from movies");
//        String title;
//        double price;
//        String format;
//        String year;
//        int quantity;
//
//        while (rs.next()) {
//            title = rs.getString(1);
//            price = rs.getDouble(2);
//            format = rs.getString(3);
//            year = rs.getString(4);
//            quantity = rs.getInt(5);
//            System.out.println(title);
//            System.out.println(price);
//            System.out.println(format);
//            System.out.println(quantity);
//
//            Movie movie = new Movie(title, price, format, year, quantity);
//            list.add(movie);
//        }
//            DefaultTableModel dm = (DefaultTableModel) jTable1.getModel();
//            while (dm.getRowCount() > 0) {
//                dm.removeRow(0);
//            }
//
//            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//
//            Object[] row = new Object[5];
//
//            for (int i = 0; i < list.size(); i++) {
//
//                row[0] = list.get(i).getTitle();
//                row[1] = list.get(i).getYear();
//                row[2] = list.get(i).getPrice();
//                row[3] = list.get(i).getFormat();
//                row[4] = list.get(i).getQuantity();
//                model.addRow(row);
//            }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Text_Quantity = new javax.swing.JTextField();
        Text_Title = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title ", "Year", "Price", "Format", "Quantity"
            }
        ));
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 69, 842, 155));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Resupply  a Movie");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 242, 153, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Delete a movie");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 242, 178, -1));

        Text_Quantity.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Text_Quantity.setText("0");
        getContentPane().add(Text_Quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 243, 53, -1));

        Text_Title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(Text_Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(337, 243, 76, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Enter Quantity:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 246, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Enter Title:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 246, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title ", "Quantity"
            }
        ));
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 326, -1, 86));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Almost Out of Stock:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Manage Movies");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 27, 862, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ammar\\Desktop\\Updated Pro\\305_Project\\305_Project(Updated)\\32.jpg")); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String title = Text_Title.getText();
            int oldQuantity = 0;
            int index = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTitle().equalsIgnoreCase(title)) {
                    oldQuantity = list.get(i).getQuantity();
                    list.get(i).setQuantity(oldQuantity+ Integer.parseInt(Text_Quantity.getText()));
                    index = i;
                    break;
                }
            }

            int newQuantity = Integer.parseInt(Text_Quantity.getText()) + oldQuantity;

            PreparedStatement ps = con.prepareStatement("UPDATE movies set Quantity=? where Title = ? ");
             ps.setInt(1, newQuantity);
            ps.setString(2, list.get(index).getTitle());
            printTable();
           
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_ManageMovies.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    int choice = JOptionPane.showConfirmDialog(null, "Are you Sure Do you Want To Delete This Movie ?", "Delete", JOptionPane.YES_NO_OPTION);
    if(choice==0){
        try {
            PreparedStatement ps = con.prepareStatement("delete  from movies where title = ?");
            ps.setString(1, Text_Title.getText());
            ps.executeUpdate();
            int index=0;
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getTitle().equalsIgnoreCase(Text_Title.getText())){
                index = i;
                list.remove(list.get(i));
                }
            }
            printTable();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_ManageMovies.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    else {
       
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
            java.util.logging.Logger.getLogger(Employee_ManageMovies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee_ManageMovies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee_ManageMovies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee_ManageMovies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Employee_ManageMovies().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Employee_ManageMovies.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Text_Quantity;
    private javax.swing.JTextField Text_Title;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
