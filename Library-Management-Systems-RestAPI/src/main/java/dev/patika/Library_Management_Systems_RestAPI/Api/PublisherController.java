package dev.patika.Library_Management_Systems_RestAPI.Api;

import dev.patika.Library_Management_Systems_RestAPI.Business.Abstruct.IPublisherService;
import dev.patika.Library_Management_Systems_RestAPI.Core.config.modelMapper.IModelMapperService;
import dev.patika.Library_Management_Systems_RestAPI.Core.result.Result;
import dev.patika.Library_Management_Systems_RestAPI.Core.result.ResultData;
import dev.patika.Library_Management_Systems_RestAPI.Core.utilies.ResultHelper;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Category.CategorySaveRequest;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Category.CategoryUpdateRequest;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.CursorResponse;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Publisher.PublisherSaveRequest;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Publisher.PublisherUpdateRequest;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Response.Category.CategoryResponse;
import dev.patika.Library_Management_Systems_RestAPI.Dto.Response.Publisher.PublisherResponse;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Category;
import dev.patika.Library_Management_Systems_RestAPI.Entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {


    private final IPublisherService publisherService;
    private final IModelMapperService modelMapper;

    public PublisherController(IPublisherService publisherService, IModelMapperService modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    //SAVE (Yeni bir kullanıcı ekleme - CREATE)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) //oluşturma (POST) statüsü
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest) {
        Publisher savePublisher = this.modelMapper.forRequest().map(publisherSaveRequest, Publisher.class);
        this.publisherService.save(savePublisher);
        return ResultHelper.created(this.modelMapper.forResponse().map(savePublisher, PublisherResponse.class));
    }

    //GET (Kullanıcı listesini çekme - READ)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> get(@PathVariable("id") int id) {
        Publisher publisher = this.publisherService.get(id);

        PublisherResponse publisherResponse = this.modelMapper.forResponse().map(publisher, PublisherResponse.class);
        return ResultHelper.succes(publisherResponse);
    }

    //GET (cursor: gösterge/imleçler)
    @GetMapping() //?page=1&pageSize=10
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<PublisherResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Publisher> publisherPage = this.publisherService.cursor(page, pageSize);
        Page<PublisherResponse> publisherResponsePage = publisherPage
                .map(publisher -> this.modelMapper.forResponse().map(publisher, PublisherResponse.class));

        return ResultHelper.cursor(publisherResponsePage);
    }

    //PUT ( bilgilerini güncelleme - UPDATE)
    @PutMapping()
    @ResponseStatus(HttpStatus.OK) //oluşturma (POST) statüsü
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest) {
        Publisher updatePublisher = this.modelMapper.forRequest().map(publisherUpdateRequest, Publisher.class);
        this.publisherService.update(updatePublisher);

        return ResultHelper.succes(this.modelMapper.forResponse().map(updatePublisher, PublisherResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.publisherService.delete(id);
        return ResultHelper.ok();
    }
}
