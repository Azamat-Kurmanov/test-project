package kz.testproject.modules.controllers;

import kz.testproject.modules.dto.ArticleEntity;
import kz.testproject.modules.dto.data.Statistics;
import kz.testproject.modules.services.ArticleService;
import kz.testproject.modules.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleController {

    private ArticleService articleService;
    private UserService userService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping("/")
    public List<ArticleEntity> getListOfArticles() {
        return articleService.listOfArticles();
    }

    @GetMapping("/{id}")
    public ArticleEntity getArticleEntitiesById(@PathVariable Long id){
        return articleService.getArticleById(id);
    }

    @PostMapping("/addArticle")
    public ArticleEntity saveArticle(@RequestBody @Valid ArticleEntity articleEntity) {
        return articleService.saveArticle(articleEntity);
    }

    @DeleteMapping("/deleteArticle/{id}")
    public void deleteArticleById(@PathVariable Long id){
        articleService.deleteArticleById(id);
    }

    @GetMapping("/withinPublicDate/{day}/{month}/{year}")
    public List<ArticleEntity> getArticleWithinPublicationDates(
            @PathVariable int day,
            @PathVariable int month,
            @PathVariable int year
    ){
        return articleService.getListOfArticlesByDate(day, month, year);
    }

    @GetMapping("/statistics/{userId}")
    public List<Statistics> getStatistic(
            @PathVariable Long userId
    ) {
        return userService.getStatisticsData(userId);
    }
}
