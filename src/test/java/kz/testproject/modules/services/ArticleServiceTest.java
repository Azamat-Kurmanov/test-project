package kz.testproject.modules.services;

import kz.testproject.modules.dto.ArticleEntity;
import kz.testproject.modules.dto.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class ArticleServiceTest {

    private ArticleRepository articleRepository = Mockito.mock(ArticleRepository.class);

    @Test
    @DisplayName("Get list of articles")
    void listOfArticles() {
        ArticleService articleService = new ArticleService(articleRepository);
        List<ArticleEntity> list = articleService.listOfArticles();
        Mockito.when(articleRepository.findAll()).thenReturn(list);
    }

    @Test
    @DisplayName("Should find Article by Id")
    void getArticleById() {
        ArticleEntity article = new ArticleEntity(123L, "Заголовок статьи", "Serikov Serik", "Контент статьи", LocalDateTime.now());
        Mockito.when(articleRepository.findById(123L)).thenReturn(Optional.of(article));
    }
}