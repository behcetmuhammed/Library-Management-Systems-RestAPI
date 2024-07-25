package dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySaveRequest {

    @NotNull
    @Size(min = 1, max = 255, message = "Kategori ismi 1 ile 255 karakter arasında olmalıdır")
    private String categoryName;

    @Size(min = 1, max = 255, message = "Kategori açıklaması 1 ile 255 karakter arasında olmalıdır")
    private String categoryDescription ;

}
