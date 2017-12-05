package service;





import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ServiceSOAP {
    @WebMethod
    public boolean add(String nickname, String password,
                       String firstName, String lastName, String birthday);

    @WebMethod
    public boolean update(String nickname, String password,
                          String firstName, String lastName, String birthday);

    @WebMethod
    public boolean delete(String nickname);

//    @WebMethod
//    public List<User> select();
}
