package kr.hs.dgsw.dgswblog.Controller;

import kr.hs.dgsw.dgswblog.Domain.User;
import kr.hs.dgsw.dgswblog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id){
        return this.userService.view(id);
    }

    @GetMapping("/user")
    public List<User> getUser () {
        return this.userService.list();
    }

    @PostMapping("/user")
    public User add(@RequestBody User u) {
        return this.userService.add(u);
    }

    @PostMapping("/user/login")
    public User login(@RequestBody User u) { return this.userService.login(u); }

    @PutMapping("/user/{id}")
    public User update(@RequestBody User u, @PathVariable Long id) {
        return this.userService.update(u, id);
    }

    @DeleteMapping("/user/{id}")
    public boolean delete(@PathVariable Long id) {
        return this.userService.delete(id);
    }
}
