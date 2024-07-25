package dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateRequest {

    private int categoryId;
    private String categoryName;
    private String categoryDescription ;
}
