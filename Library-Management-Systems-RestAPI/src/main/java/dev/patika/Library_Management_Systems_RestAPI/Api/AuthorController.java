package dev.patika.Library_Management_Systems_RestAPI.Api;

import dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct.IAuthorService;
import dev.patika.Library_Management_Systems_RestAPI.Core.config.modelMapper.IModelMapperService;
import dev.patika.Library_Management_Systems_RestAPI.Core.result.Result;
import dev.patika.Library_Management_Systems_RestAPI.Core.result.ResultData;
import dev.patika.Library_Management_Systems_RestAPI.Core.utilies.ResultHelper;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Author.AuthorSaveRequest;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Author.AuthorUpdateRequest;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.CursorResponse;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Response.Author.AuthorResponse;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Author;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {


    private final IAuthorService authorService;
    private final IModelMapperService modelMapper;

    public AuthorController(IAuthorService authorService, IModelMapperService modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    //SAVE (Yeni bir kullanıcı ekleme - CREATE)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) //oluşturma (POST) statüsü
    public ResultData<AuthorResponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
        Author saveAuthor = this.modelMapper.forRequest().map(authorSaveRequest, Author.class);
        this.authorService.save(saveAuthor);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAuthor, AuthorResponse.class));
    }

    //GET (Kullanıcı listesini çekme - READ)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> get(@PathVariable("id") int id) {
        Author author = this.authorService.get(id);

        AuthorResponse authorResponse = this.modelMapper.forResponse().map(author, AuthorResponse.class);
        return ResultHelper.succes(authorResponse);
    }

    //GET (cursor: gösterge/imleçler)
    @GetMapping() //?page=1&pageSize=10
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AuthorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Author> auhtorPage = this.authorService.cursor(page, pageSize);
        Page<AuthorResponse> authorResponsePage = auhtorPage
                .map(author -> this.modelMapper.forResponse().map(author, AuthorResponse.class));

        return ResultHelper.cursor(authorResponsePage);
    }

    //PUT ( bilgilerini güncelleme - UPDATE)
    @PutMapping()
    @ResponseStatus(HttpStatus.OK) //oluşturma (POST) statüsü
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest) {
        Author updateAuthor = this.modelMapper.forRequest().map(authorUpdateRequest, Author.class);
        this.authorService.update(updateAuthor);

        return ResultHelper.succes(this.modelMapper.forResponse().map(updateAuthor, AuthorResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.authorService.delete(id);
        return ResultHelper.ok();
    }
}
