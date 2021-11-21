
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Customer_ViewMovies extends javax.swing.JFrame {

    ArrayList<Movie> rentedMovies; // here i store the rentedmovies
    ArrayList<Movie> listMovies; // list of movies
    ArrayList<Customers> listCustomers; // list of customers
    static java.sql.Connection con; // Used to get connection to datatbase
    Statement st; // Used to create retrieve query
    PreparedStatement ps; // Used to create retrieve query
    ResultSet rs;
    static String name;
    FileOutputStream fos;
    PrintWriter p;

    public Customer_ViewMovies(String name) throws SQLException {
        this.name = name;
        initComponents();
        listMovies = new ArrayList<Movie>();
        rentedMovies = new ArrayList<Movie>();
        listCustomers = new ArrayList<Customers>();
        DataBaseConnection.start();

    }

    Thread DataBaseConnection = new Thread() {
        @Override
        public void run() {
            DataBaseConnection(); // this method connect to the database
            // here i will read and store from 2 diffrents tables (Users , movies)
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
                    listMovies.add(movie);
                }
                st = con.createStatement();
                rs = st.executeQuery("select * from Users");
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
                    Customers customer = new Customers(userName, Password, phone, email, subscription, subscriptionExp, wallet);
                    listCustomers.add(customer);
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
            Customer_ViewMovies.con = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Users?useSSL=false", "root", "");
            return Customer_ViewMovies.con;

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

        Object[] row = new Object[4];
        int quantity = 0;
        for (int i = 0; i < listMovies.size(); i++) {
            quantity = listMovies.get(i).getQuantity();
            if (quantity == 0) {

            } else {
                row[0] = listMovies.get(i).getTitle();
                row[1] = listMovies.get(i).getYear();
                row[2] = listMovies.get(i).getPrice();
                row[3] = listMovies.get(i).getFormat();
            }

            model.addRow(row);
        }

    }

    public boolean CheckDate(String expDate) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 0);
            String currentDate = sdf.format(cal.getTime());
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy ");
            java.util.Date exp = new SimpleDateFormat("dd/mm/yyyy").parse(expDate);
            java.util.Date cur = new SimpleDateFormat("dd/mm/yyyy").parse(currentDate);
            if (exp.compareTo(cur) < 0) {
                return false;
            }

        } catch (ParseException ex) {
            Logger.getLogger(Customer_ViewMovies.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        Text_Title = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title ", "Year", "Price", "Format"
            }
        ));
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 67, 778, 122));

        Text_Title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(Text_Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 211, 150, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Enter Movie Title:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 214, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Rent");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(513, 204, 135, 35));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(727, 257, -1, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Buy");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 204, 135, 35));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Buy or Rent A Movie");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 798, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ammar\\Desktop\\Updated Pro\\305_Project\\305_Project(Updated)\\32.jpg")); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            fos = new FileOutputStream("C:\\Users\\Ammar\\Desktop\\Sales.txt", true);
            p = new PrintWriter(fos, true);
            boolean subscriber = false;
            int indexC = 0; // customer index
            int indexM = 0; // movie index
            String CustomerName = "";
            String title = Text_Title.getText();
            int currentQuantity;
            int newQuantity = 0;
            boolean cashStatus = false; //for cash
            Customers customer = null;
            String RentedMovie = "";
            double wallet = 0;
            boolean quantityStatus = true;
            boolean DateStatus = false;
            String subDate = "";
            for (int i = 0; i < listCustomers.size(); i++) {

                if (name.equalsIgnoreCase(listCustomers.get(i).getUserName())) {
                    indexC = i; // desired customer
                    CustomerName = listCustomers.get(indexC).getUserName();

                    String pass = listCustomers.get(indexC).getPassword();

                    String phone = listCustomers.get(indexC).getPhone();

                    String email = listCustomers.get(indexC).getEmail();

                    int sub = listCustomers.get(indexC).getSubscription();

                    subDate = listCustomers.get(indexC).getSubscriptionExp();

                    wallet = listCustomers.get(indexC).getWallet();

                    if (wallet >= 15) {

                        cashStatus = true;
                    }

                    customer = new Customers(CustomerName, pass, phone, email, sub, subDate, wallet);
                }
            }

            if (listCustomers.get(indexC).getSubscription() == 1) { // check if sub has expired or not 
                DateStatus = CheckDate(listCustomers.get(indexC).getSubscriptionExp());
            }

            if (DateStatus == false) { // sub has expired
                listCustomers.get(indexC).setSubscription(0);
                PreparedStatement ps = con.prepareStatement("UPDATE Users set Subscription=? where UserName = ? ");
                ps.setInt(1, 0);
                ps.setString(2, name);
                ps.executeUpdate();
            }
            boolean isAMovie = false;
            for (int i = 0; i < listMovies.size(); i++) {
                if (title.equalsIgnoreCase(listMovies.get(i).getTitle())) {
                    indexM = i;
                    currentQuantity = listMovies.get(i).getQuantity();
                    isAMovie = true;
                    if (currentQuantity == 0) {
                        JOptionPane.showMessageDialog(null, "This Film is out of Stock");
                        quantityStatus = false;
                    }
//                   
                    if (listCustomers.get(indexC).getSubscription() == 1) {
                        subscriber = true;
                        cashStatus = true;
                    }
                    if (cashStatus == false) {
                        JOptionPane.showMessageDialog(null, "your Wallet dosent have enough cash ");
                        quantityStatus = false;
                    }

                    if (quantityStatus == true) {
                        if (listCustomers.get(indexC).getSubscription() == 0) { // he is not a sub so substarct rent price 
                            wallet = wallet - 15;
                        }

                        String movieName = listMovies.get(indexM).getTitle();
                        double moviePrice = listMovies.get(indexM).getPrice();
                        String movieFormat = listMovies.get(indexM).getFormat();
                        String movieYear = listMovies.get(indexM).getYear();
                        newQuantity = currentQuantity - 1;
                        Movie movie = new Movie(title, moviePrice, movieFormat, movieYear, 0);
                        listMovies.get(indexM).setQuantity(newQuantity);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.DAY_OF_MONTH, 0);
                        String newDate = sdf.format(cal.getTime());

                        PreparedStatement ps = con.prepareStatement("UPDATE movies set Quantity=? where Title = ? "); // set new quantity (it should be less)
                        ps.setInt(1, newQuantity);
                        ps.setString(2, title);
                        ps.executeUpdate();

                        ps = con.prepareStatement("UPDATE Users set Wallet=? where UserName = ? "); // set new cusomer wallet it should be less(or same if he is sub)
                        ps.setDouble(1, wallet);
                        ps.setString(2, name);
                        ps.executeUpdate();

                        ps = con.prepareStatement("INSERT INTO RentedMovies VALUES (?, ?,?,?)"); //
                        ps.setString(1, listCustomers.get(indexC).getUserName());
                        ps.setString(2, listMovies.get(indexM).getTitle());
                        ps.setString(3, newDate);
                        ps.setInt(4, 1);
                        ps.executeUpdate();

                        // here i write in a text file 
                        p.println("Rent : ");
                        p.println("User : " + listCustomers.get(indexC).getUserName());
                        p.println("Rented Movie : " + listMovies.get(indexM).getTitle() + " " + listMovies.get(indexM).getYear() + " on : " + listMovies.get(indexM).getFormat());
                        p.println("================================================================================\n");
                        p.close();
                        JOptionPane.showMessageDialog(null, title + " " + listMovies.get(indexM).getYear() + " has been rented ");

                    }

                } else if (isAMovie == false && (listMovies.size() - i == 1)) {
                    JOptionPane.showMessageDialog(null, "Wrong Movie Name , plesse try again", "Error !", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Customer_ViewMovies.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Customer_ViewMovies.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Customer c = new Customer(name);
        this.dispose();
        c.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int indexC = 0; // customer index
        int indexM = 0; // movie index
        String CustomerName = "";
        String title = Text_Title.getText();
        int currentQuantity = 0;
        double wallet = 0;
        try {
            fos = new FileOutputStream("C:\\Users\\Ammar\\Desktop\\Sales.txt", true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Customer_ViewMovies.class.getName()).log(Level.SEVERE, null, ex);
        }
        p = new PrintWriter(fos, true);

        for (int i = 0; i < listCustomers.size(); i++) { // knowing the customer index + curent wallet
            if (name.equalsIgnoreCase(listCustomers.get(i).getUserName())) {
                indexC = i;
                wallet = listCustomers.get(indexC).getWallet();

            }
        }
        boolean isAMovie = false;
        for (int i = 0; i < listMovies.size(); i++) { // getting the quantity and movie index
            if (listMovies.get(i).getTitle().equalsIgnoreCase(title)) {
                indexM = i;
                currentQuantity = listMovies.get(indexM).getQuantity();
                isAMovie = true; // which means it is a true movie name 
            }

        }
        if (isAMovie == false) {
            JOptionPane.showMessageDialog(null, "Wrong Movie Name , plesse try again", "Error !", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (listMovies.get(indexM).getPrice() <= wallet) { // he has enough cash
            wallet = wallet - listMovies.get(indexM).getPrice();
            try {
                // cashStatus =true;
                ps = con.prepareStatement("UPDATE movies set quantity=? where title = ? ");
                ps.setInt(1, currentQuantity - 1);
                ps.setString(2, title);
                ps.executeUpdate();

                ps = con.prepareStatement("UPDATE Users set wallet=? where UserName = ? ");
                ps.setDouble(1, wallet);
                ps.setString(2, name);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "your Movie has been purchesed ");
                p.println("Purchase : ");
                p.println("User : " + listCustomers.get(indexC).getUserName());
                p.println("Movie : " + listMovies.get(indexM).getTitle() + " " + listMovies.get(indexM).getYear() + " on : " + listMovies.get(indexM).getFormat());
                p.println("================================================================================\n");
                p.close();
            } catch (SQLException ex) {
                Logger.getLogger(Customer_ViewMovies.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { // he dosnet have enough cash
            JOptionPane.showMessageDialog(null, "Your Wallet dosent have enough cash");

        }


    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Customer_ViewMovies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer_ViewMovies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer_ViewMovies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer_ViewMovies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Customer_ViewMovies(name).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Customer_ViewMovies.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Text_Title;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
