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

    private static List<User> userList = new ArrayList<>();
    private final String ADMIN = "admin";

    private ArticleService articleService;

    @Autowired
    public UserService(ArticleService articleService) {
        this.articleService = articleService;
    }

    static{
        userList.add(new User(1L, "Nick", "user"));
        userList.add(new User(2L, "Kevin", "user"));
        userList.add(new User(3L, "Azamat", "admin"));
    }

    public User getUserById(Long id){
        return userList.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public Statistics getListOfStatistics(Long userId, Integer day, Integer month, Integer year) {
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
