package dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest { //KİTAP GÜNCELLEME

    @Positive(message = "ID değeri pozitif olmak zorunda")
    private int bookId;

    @NotNull(message = "Kitap ismi boş veya null olamaz")
    @Size(min = 1, max = 255, message = "Kitap ismi 1 ile 255 karakter arasında olmalıdır")
    private String bookName;

    @Positive(message = "Yayın yılı pozitif bir değer olmalıdır")
    private int publicationYear;

    @Min(value = 0, message = "Kitap stoğu en az 0 olmalıdır")
    private int bookStock;

    private int authorId; //Bir Kitabın, bir Yazarı olabilir

    private int publisherId;  //Bir Kitabın, bir Yayınevi olabilir

    private List<Integer> categoryId; //Bir Kitabın, birden fazla kategorisi olabilir

}
