package dev.patika.Library_Management_Systems_RestAPI.Api;

import dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct.IBookService;
import dev.patika.Library_Management_Systems_RestAPI.Core.config.modelMapper.IModelMapperService;
import dev.patika.Library_Management_Systems_RestAPI.Core.result.Result;
import dev.patika.Library_Management_Systems_RestAPI.Core.result.ResultData;
import dev.patika.Library_Management_Systems_RestAPI.Core.utilies.ResultHelper;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Book.BookSaveRequest;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Book.BookUpdateRequest;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.CursorResponse;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Response.Book.BookResponse;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Book;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final IBookService bookService;
    private final IModelMapperService modelMapper;

    public BookController(IBookService bookService, IModelMapperService modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }


    //SAVE (Yeni bir kullanıcı ekleme - CREATE)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) //oluşturma (POST) statüsü
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);
        this.bookService.save(saveBook);

        return ResultHelper.created(this.modelMapper.forResponse().map(saveBook, BookResponse.class));
    }

    //GET (Kullanıcı listesini çekme - READ)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") int id) {
        Book book = this.bookService.get(id);

        BookResponse bookResponse = this.modelMapper.forResponse().map(book, BookResponse.class);
        return ResultHelper.succes(bookResponse);
    }

    //GET (cursor: gösterge/imleçler)
    @GetMapping() //?page=1&pageSize=10
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Book> bookPage = this.bookService.cursor(page, pageSize);
        Page<BookResponse> bookResponsePage = bookPage
                .map(book -> this.modelMapper.forResponse().map(book, BookResponse.class));

        return ResultHelper.cursor(bookResponsePage);
    }

    //PUT ( bilgilerini güncelleme - UPDATE)
    @PutMapping()
    @ResponseStatus(HttpStatus.OK) //oluşturma (POST) statüsü
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        Book updateBook = this.modelMapper.forRequest().map(bookUpdateRequest, Book.class);
        this.bookService.update(updateBook);

        return ResultHelper.succes(this.modelMapper.forResponse().map(updateBook, BookResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.bookService.delete(id);
        return ResultHelper.ok();
    }
}
