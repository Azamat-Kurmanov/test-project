package kz.testproject.modules.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "articles")
public class ArticleEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "The title cannot be null")
    @Max(value = 100, message = "The should not be greater than 100 symbols")
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull(message = "The author cannot be null")
    @Column(name = "author", nullable = false)
    private String author;

    @NotNull(message = "The content cannot be null")
    @Column(name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "datePublished", nullable = false)
    private LocalDateTime datePublished;
}
