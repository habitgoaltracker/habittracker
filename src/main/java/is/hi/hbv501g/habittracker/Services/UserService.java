package is.hi.hbv501g.habittracker.Services;

import is.hi.hbv501g.habittracker.Persistence.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    /**
     * Finds a user by its username.
     * @param userName Users' username.
     * @return the user that has an equivalent ID (if any).
     */
    User findByUsername(String userName);

    /**
     * Finds a user by its ID.
     * @param ID the ID of user.
     * @return the user that has an equivalent ID (if any).
     */
    User findByID(long ID);

    /**
     * Finds all users in database.
     * @return list of users.
     */
    List<User> findAll();

    /**
     * Saves user in database.
     * @return user to be saved.
     */
    User save(User user);

    /**
     * Logs user in application if password matches the password stored in database.
     * @param user user to be logged in.
     * @return user to be logged in.
     */
    User login(User user);


    /**
     * Deletes user from database by its id.
     * @param id id of user to be removed.
     */
    void deleteByID(long id);
}
