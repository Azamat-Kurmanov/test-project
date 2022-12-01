package kz.testproject.modules.services;

import kz.testproject.modules.dto.ArticleEntity;
import kz.testproject.modules.dto.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * get list of articles from table
     * @return
     */
    public List<ArticleEntity> listOfArticles() {
        return articleRepository.findAll();
    }

    /**
     * get Article from table by id
     * @param id
     * @return
     */
    public ArticleEntity getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    /**
     * Add article to the table articles
     * @param articleEntity
     * @return
     */
    public ArticleEntity saveArticle(ArticleEntity articleEntity) {
        return articleRepository.save(articleEntity);
    }

    /**
     * Delete article By id
     * @param id
     * @return
     */
    public Boolean deleteArticleById(Long id) {
        articleRepository.deleteById(id);
        if (getArticleById(id)!=null) return false;
        return true;
    }

    /**
     * Return list of articles which were created in the particular date
     * @param day
     * @param month
     * @param year
     * @return
     */
    public List<ArticleEntity> getListOfArticlesByDate(Integer day, Integer month, Integer year) {
        List<ArticleEntity> findByPublication_date = null;
        if (day!=null & month!=null & year!=null) {
            LocalDateTime localDateTime1 = LocalDateTime.of(year, month, day, 0, 0, 0);
            LocalDateTime localDateTime2 = LocalDateTime.of(year, month, day, 23, 59, 59);
            findByPublication_date = articleRepository.findByDatePublishedBetween(localDateTime1, localDateTime2);
        }
        return findByPublication_date;
    }

    /**
     * Extracts the number of articles for the particular date
     * @param day
     * @param month
     * @param year
     * @return
     */
    public Integer numberOfArticleForOneDate(Integer day, Integer month, Integer year) {
        if (day!=null & month!=null & year!=null) {
            LocalDateTime localDateTime1 = LocalDateTime.of(year, month, day, 0, 0, 0);
            LocalDateTime localDateTime2 = LocalDateTime.of(year, month, day, 23, 59, 59);
            return articleRepository.numberOfArticleForOneDate(localDateTime1, localDateTime2);
        }
        return 0;
    }

    public LocalDateTime getArticleByMaxDate(){
        return articleRepository.getArticleByMaxDate();
    }
}
