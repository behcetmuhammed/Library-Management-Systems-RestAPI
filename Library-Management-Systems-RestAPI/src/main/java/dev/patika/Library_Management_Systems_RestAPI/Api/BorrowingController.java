package dev.patika.Library_Management_Systems_RestAPI.Api;

import dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct.IBorrowingService;
import dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct.IPublisherService;
import dev.patika.Library_Management_Systems_RestAPI.Core.config.modelMapper.IModelMapperService;
import dev.patika.Library_Management_Systems_RestAPI.Core.result.Result;
import dev.patika.Library_Management_Systems_RestAPI.Core.result.ResultData;
import dev.patika.Library_Management_Systems_RestAPI.Core.utilies.ResultHelper;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Borrowing.BorrowingSaveRequest;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Borrowing.BorrowingUpdateRequest;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.CursorResponse;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Publisher.PublisherSaveRequest;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Publisher.PublisherUpdateRequest;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Response.Borrowing.BorrowingResponse;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Response.Publisher.PublisherResponse;
import dev.patika.Library_Management_Systems_RestAPI.Entities.BookBorrowing;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/borrowings")
public class BorrowingController {

    private final IBorrowingService borrowingService;
    private final IModelMapperService modelMapper;

    public BorrowingController(IBorrowingService borrowingService, IModelMapperService modelMapper) {
        this.borrowingService = borrowingService;
        this.modelMapper = modelMapper;
    }

    //SAVE (Yeni bir kullanıcı ekleme - CREATE)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) //oluşturma (POST) statüsü
    public ResultData<BorrowingResponse> save(@Valid @RequestBody BorrowingSaveRequest borrowingSaveRequest) {
        BookBorrowing saveBorrowing = this.modelMapper.forRequest().map(borrowingSaveRequest, BookBorrowing.class);
        this.borrowingService.save(saveBorrowing);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBorrowing, BorrowingResponse.class));
    }

    //GET (Kullanıcı listesini çekme - READ)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BorrowingResponse> get(@PathVariable("id") int id) {
        BookBorrowing borrowing = this.borrowingService.get(id);

        BorrowingResponse borrowingResponse = this.modelMapper.forResponse().map(borrowing, BorrowingResponse.class);
        return ResultHelper.succes(borrowingResponse);
    }

    //GET (cursor: gösterge/imleçler)
    @GetMapping() //?page=1&pageSize=10
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BorrowingResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<BookBorrowing> borrowingPage = this.borrowingService.cursor(page, pageSize);
        Page<BorrowingResponse> borrowingResponsePage = borrowingPage
                .map(borrowing -> this.modelMapper.forResponse().map(borrowing, BorrowingResponse.class));

        return ResultHelper.cursor(borrowingResponsePage);
    }

    //PUT ( bilgilerini güncelleme - UPDATE)
    @PutMapping()
    @ResponseStatus(HttpStatus.OK) //oluşturma (POST) statüsü
    public ResultData<BorrowingResponse> update(@Valid @RequestBody BorrowingUpdateRequest borrowingUpdateRequest) {
        BookBorrowing updateBorrowing = this.modelMapper.forRequest().map(borrowingUpdateRequest, BookBorrowing.class);
        this.borrowingService.update(updateBorrowing);

        return ResultHelper.succes(this.modelMapper.forResponse().map(updateBorrowing, BorrowingResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.borrowingService.delete(id);
        return ResultHelper.ok();
    }
}
