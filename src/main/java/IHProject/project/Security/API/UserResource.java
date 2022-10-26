
/*
This resource page is Security implementation




package IHProject.project.Security.API;

import IHProject.project.Security.entities.Role;
import IHProject.project.Security.entities.User;
import IHProject.project.Security.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/Users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUserList());

    }

    @PostMapping("user/save")
    public ResponseEntity<User> getUsers(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @PostMapping("user/role")
    public ResponseEntity<Role> getUsers(@RequestBody Role role) {
        return ResponseEntity.ok().body(userService.saveRole(role));
    }

    @PostMapping("/addRoleToUser")
    public ResponseEntity<?> addRoleToUser (@RequestBody RoleToUser roleToUser) {
        userService.addRoleToUser(roleToUser.getUsername(), roleToUser.getRoleName());;
        return ResponseEntity.ok().build();
    }
}

    //This DTO is just for the addRoleToUser endpoint
    @Data
    class RoleToUser {

    private String username;
    private String roleName;
    }

*/

