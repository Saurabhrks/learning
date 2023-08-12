package controllerdff.controllerdiff;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Restcntlr{

    @RequestMapping(method = RequestMethod.GET, value = "/userdata")
    public String getUser(){
        User user= new User();
        user.setId(1);
        user.setName("Vishal");
        user.setCity("Thane");
        return "saurabh";
    }
}
