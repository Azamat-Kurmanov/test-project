package kz.testproject.modules.dto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
    List<ArticleEntity> findAll();
    List<ArticleEntity> findByDatePublishedBetween(LocalDateTime date1, LocalDateTime date2);
    @Query("SELECT COUNT(d.id) FROM articles d WHERE d.datePublished BETWEEN ?1 AND ?2")
    Integer numberOfArticleForOneDate(LocalDateTime date1, LocalDateTime date2);
    @Query("SELECT MAX(d.datePublished) FROM articles d")
    LocalDateTime getArticleByMaxDate();

}