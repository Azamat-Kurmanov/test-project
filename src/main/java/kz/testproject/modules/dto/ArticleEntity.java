package kz.testproject.modules.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "The title cannot be null")
    @Length(min = 5, max = 100, message = "The length of title cannot be less than 5 and greater than 100 symbols")
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "The name of author has to be filled")
    @Column(name = "author", nullable = false)
    private String author;

    @NotBlank(message = "The content cannot be null")
    @Column(name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "datePublished", nullable = false)
    private LocalDateTime datePublished;
}
