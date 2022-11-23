package is.hi.hbv501g.habittracker.Services.Implementation;

import is.hi.hbv501g.habittracker.Persistence.Entities.User;
import is.hi.hbv501g.habittracker.Persistence.Repositories.UserRepository;
import is.hi.hbv501g.habittracker.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public User findByID(long ID) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User login(User user) {
        User actualUser = findByUsername(user.getUsername());
        if(actualUser != null){
            if(actualUser.getPassword().equals(user.getPassword())) return actualUser;
        }
        return null;
    }

    @Override
    public void deleteByID(long id) {
        User user = userRepository.findByID(id);
        userRepository.delete(user);
    }
}
