package dev.patika.Library_Management_Systems_RestAPI.Dto.Response.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    //Kullanıcıya (İstemciye) gösterecek bilgiler
    private int bookId;
    private String bookName;
    private int publicationYear;

    private int authorId; //Bir Kitabın, bir Yazarı olabilir
    private int publisherId;  //Bir Kitabın, bir Yayınevi olabilir
}
