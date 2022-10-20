/*

package IHProject.project.Security.service;

import IHProject.project.Security.Repository.RoleRepo;
import IHProject.project.Security.Repository.UserRepo;
import IHProject.project.Security.Role;
import IHProject.project.Security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Optional<User> user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user().add(role);

    }

    @Override
    public Optional<User> getUser(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    @Override
    public List<User> getUserList() {
        return userRepo.findAll();
    }


}
*/