package dev.patika.Library_Management_Systems_RestAPI.Api;

import dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct.ICategoryService;
import dev.patika.Library_Management_Systems_RestAPI.Core.config.modelMapper.IModelMapperService;
import dev.patika.Library_Management_Systems_RestAPI.Core.result.Result;
import dev.patika.Library_Management_Systems_RestAPI.Core.result.ResultData;
import dev.patika.Library_Management_Systems_RestAPI.Core.utilies.ResultHelper;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Category.CategorySaveRequest;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Category.CategoryUpdateRequest;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.CursorResponse;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Response.Category.CategoryResponse;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private final IModelMapperService modelMapper;

    public CategoryController(ICategoryService categoryService, IModelMapperService modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    //SAVE (Yeni bir kullanıcı ekleme - CREATE)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) //oluşturma (POST) statüsü
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest) {
        Category saveCategory = this.modelMapper.forRequest().map(categorySaveRequest, Category.class);
        this.categoryService.save(saveCategory);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCategory, CategoryResponse.class));
    }

    //GET (Kullanıcı listesini çekme - READ)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") int id) {
        Category category = this.categoryService.get(id);

        CategoryResponse categoryResponse = this.modelMapper.forResponse().map(category, CategoryResponse.class);
        return ResultHelper.succes(categoryResponse);
    }

    //GET (cursor: gösterge/imleçler)
    @GetMapping() //?page=1&pageSize=10
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Category> categoryPage = this.categoryService.cursor(page, pageSize);
        Page<CategoryResponse> categoryResponsePage = categoryPage
                .map(category -> this.modelMapper.forResponse().map(category, CategoryResponse.class));

        return ResultHelper.cursor(categoryResponsePage);
    }

    //PUT ( bilgilerini güncelleme - UPDATE)
    @PutMapping()
    @ResponseStatus(HttpStatus.OK) //oluşturma (POST) statüsü
    public ResultData<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        Category updateCategory = this.modelMapper.forRequest().map(categoryUpdateRequest, Category.class);
        this.categoryService.update(updateCategory);

        return ResultHelper.succes(this.modelMapper.forResponse().map(updateCategory, CategoryResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.categoryService.delete(id);
        return ResultHelper.ok();
    }
}
