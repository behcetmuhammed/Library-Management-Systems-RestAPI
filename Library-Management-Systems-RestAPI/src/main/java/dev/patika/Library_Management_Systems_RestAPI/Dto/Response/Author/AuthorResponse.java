package dev.patika.Library_Management_Systems_RestAPI.Dto.Response.Author;

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
public class AuthorResponse {

    //Kullanıcıya (İstemciye) gösterecek bilgiler
    private String authorName;
}
