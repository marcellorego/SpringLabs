package hello.controller;

import hello.model.User;
import hello.model.UserCopy;
import hello.repository.UserCopyRepository;
import hello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/user") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @Autowired
    private UserCopyRepository userCopyRepository;

    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {

        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/addCopy") // Map ONLY GET Requests
    public @ResponseBody String addNewUserCopy (@RequestParam String name
            , @RequestParam String email) {

        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        UserCopy n = new UserCopy();
        n.setName(name);
        n.setEmail(email);
        userCopyRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path="/{userId}")
    public @ResponseBody ResponseEntity<User> getUserById(@PathVariable final Long userId) {
        // This returns a JSON or XML with the users
        User result = userRepository.findOne(userId);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
//        if (result.isPresent()) {
//            return ResponseEntity.ok(result.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }
}
