package kz.testproject.modules.dto.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {
    private LocalDate datePublished;
    private Integer counter;
}
