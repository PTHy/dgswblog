package kr.hs.dgsw.dgswblog.Controller;

import kr.hs.dgsw.dgswblog.Domain.User;
import kr.hs.dgsw.dgswblog.Protocol.ResponseFormat;
import kr.hs.dgsw.dgswblog.Protocol.ResponseType;
import kr.hs.dgsw.dgswblog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseFormat getUser(@PathVariable Long id){
        User user = this.userService.view(id);

        if (user == null)
            return new ResponseFormat(ResponseType.FAIL, null);
        return new ResponseFormat(ResponseType.USER_VIEW, user, id);
    }

    @GetMapping("/user")
    public ResponseFormat getUser () {
        List<User> list = this.userService.list();

        return new ResponseFormat(ResponseType.USER_GET, list);
    }

    @PostMapping("/user")
    public ResponseFormat add(@RequestBody User u) {
        User user = this.userService.add(u);

        if (user == null)
            return new ResponseFormat(ResponseType.FAIL, null);
        return new ResponseFormat(ResponseType.USER_ADD, user);
    }

    @PostMapping("/user/login")
    public ResponseFormat login(@RequestBody User u) {
        User user = this.userService.login(u);

        if (user == null)
            return new ResponseFormat(ResponseType.FAIL, null);
        return new ResponseFormat(ResponseType.USER_LOGIN, user);
    }

    @PutMapping("/user/{id}")
    public ResponseFormat update(@RequestBody User u, @PathVariable Long id) {
        User user = this.userService.update(u, id);

        if (user == null)
            return new ResponseFormat(ResponseType.FAIL, null);
        return new ResponseFormat(ResponseType.USER_UPDATE, user, id);
    }

    @DeleteMapping("/user/{id}")
    public ResponseFormat delete(@PathVariable Long id) {
        boolean chk = this.userService.delete(id);

        if (!chk)
            return new ResponseFormat(ResponseType.FAIL, null);
        return new ResponseFormat(ResponseType.USER_DELETE, null);
    }
}
