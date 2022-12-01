package kz.testproject.modules.services;

import kz.testproject.modules.dto.data.Statistics;
import kz.testproject.modules.dto.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private static final List<User> userList = new ArrayList<>();
    private static final String ADMIN = "admin";
    private static final Long MAX_NUMBER_OF_DAYS = 7L;
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
     * User verification and extraction of the number of articles by a particular date within 7 days
     * @param userId
     * @return
     */
    public List<Statistics> getStatisticsData(Long userId) {
        List<Statistics> statistics = new ArrayList<>();
        User user = getUserById(userId);
        if (user!=null && user.getRole()==ADMIN){

            LocalDateTime articlePublDateInit = LocalDateTime.now().minusDays(MAX_NUMBER_OF_DAYS); //----Initial date for the displaying
            LocalDateTime articleByMaxDate = articleService.getArticleByMaxDate(); //----The latest article published date

            if (articleByMaxDate!=null){
                Long counter = 1L;
                while(counter<=MAX_NUMBER_OF_DAYS){
                    LocalDateTime dateTime = articlePublDateInit.plusDays(counter);
                    statistics.add(new Statistics(
                            counter,
                            LocalDate.of(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth()),
                            articleService.numberOfArticleForOneDate(dateTime.getDayOfMonth(), dateTime.getMonthValue(), dateTime.getYear())
                    ));
                    counter ++;
                }

            }
        }
        return statistics;
    }
}
