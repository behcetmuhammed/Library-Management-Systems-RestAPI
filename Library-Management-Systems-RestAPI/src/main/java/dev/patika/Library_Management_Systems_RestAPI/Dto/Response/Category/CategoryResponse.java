package dev.patika.Library_Management_Systems_RestAPI.Dto.Response.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    //Kullanıcıya (İstemciye) gösterecek bilgiler
    private int categoryId;
    private String categoryName;
    private String categoryDescription ;
}
