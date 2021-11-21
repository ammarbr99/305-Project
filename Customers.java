
import java.sql.Date;


public class Customers {
    
    String userName;
    String Password;
    String phone;
    String email;
    int subscription;
    String subscriptionExp;
     double wallet;
    public Customers(String userName, String Password, String phone, String email, int subscription, String subscriptionExp,double wallet) {
        this.userName = userName;
        this.Password = Password;
        this.phone = phone;
        this.email = email;
        this.subscription = subscription;
        this.subscriptionExp = subscriptionExp;
        this.wallet=wallet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSubscription() {
        return subscription;
    }

    public void setSubscription(int subscription) {
        this.subscription = subscription;
    }

    public String getSubscriptionExp() {
        return subscriptionExp;
    }

    public void setSubscriptionExp(String subscriptionExp) {
        this.subscriptionExp = subscriptionExp;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
    
    
    
}
