package controllerdff.controllerdiff;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Cntr {
    //    @GetMapping("/user")
    @RequestMapping(method = RequestMethod.GET, value = "/user" ,name = "list")
    public ResponseEntity<List<User>> getUser() {
        try {
            User user = new User();
            user.setId(2);
            user.setName("OM");
            user.setCity("Vashi");
            var list = new ArrayList<User>() {{
                add(user);
            }};
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


//    @RequestMapping(method = RequestMethod.GET, value = "/user")
//    public ResponseEntity<User> getUser(){
//        User user= new User();
//        user.setId(1);
//        user.setName("Vishal");
//        user.setCity("Thane");
//        return ResponseEntity.ok(user);
//    }
}
