
public class Exception_EmptyField extends RuntimeException {

    public Exception_EmptyField() {

    }

    @Override
    public String getMessage() {
        return  "Empty Field found , please enter your user name and password";
    }
    
    public String Erorr(){
    return " Erorr !!!" ;
    }
    
    
}
