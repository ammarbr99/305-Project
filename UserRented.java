
import java.util.ArrayList;


public class UserRented {
    String customer;
    String movie;
    String rentDate;
    int status;
    public UserRented(String customer, String movie, String rentDate,int status) {
        this.customer = customer;
        this. movie =  movie;
        this.rentDate = rentDate;
        this.status= status;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }
    
  

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

  
    
    
    
}
