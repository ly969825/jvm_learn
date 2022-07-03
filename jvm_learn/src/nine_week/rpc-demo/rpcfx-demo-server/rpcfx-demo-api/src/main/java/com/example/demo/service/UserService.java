package nine_week.rpc;

import com.example.demo.model.User;

public interface UserService {

    /**
     * find by id
     * @param id id
     * @return user
     */
    User findById(Integer id);
}
