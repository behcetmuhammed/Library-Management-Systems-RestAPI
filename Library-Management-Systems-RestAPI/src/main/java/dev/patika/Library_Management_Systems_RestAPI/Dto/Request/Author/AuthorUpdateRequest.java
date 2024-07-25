package dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Author;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {

    @NotNull(message = "Yazar ismi boş veya null olamaz")
    @Size(min = 1, max = 255, message = "Yazar ismi 1 ile 255 karakter arasında olmalıdır")
    private String authorName;

    @Past(message = "Yazar doğum tarihi geçmiş bir tarih olmalıdır")
    private LocalDate authorBirthDate ;

    @Size(min = 1, max = 255, message = "Yazar ülke ismi 1 ile 255 karakter arasında olmalıdır")
    private String authorCountry ;
}
