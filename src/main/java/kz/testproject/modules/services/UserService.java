package kz.testproject.modules.services;

import kz.testproject.modules.dto.data.Statistics;
import kz.testproject.modules.dto.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final List<User> userList = new ArrayList<>();
    private static final String ADMIN = "admin";

    private ArticleService articleService;

    @Autowired
    public UserService(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * The real project would have a separate user management system with user data
     * for this reason user data are not collected and stored in the database.
     * For the test project, I just created an array with user data.
     */
    static{
        userList.add(new User(1L, "Nick", "user"));
        userList.add(new User(2L, "Azamat", "admin"));
    }

    public User getUserById(Long id){
        return userList.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    /**
     * User verification and articles extraction by particular date
     * @param userId
     * @param day
     * @param month
     * @param year
     * @return
     */
    public Statistics getStatisticsData(Long userId, Integer day, Integer month, Integer year) {
        Statistics statistics = null;
        User user = getUserById(userId);
        if (user!=null && user.getRole()==ADMIN){
            statistics = new Statistics(
                LocalDate.of(year, month, day),
                articleService.numberOfArticleForOneDate(day, month, year)
            );
        }
        return statistics;
    }
}
