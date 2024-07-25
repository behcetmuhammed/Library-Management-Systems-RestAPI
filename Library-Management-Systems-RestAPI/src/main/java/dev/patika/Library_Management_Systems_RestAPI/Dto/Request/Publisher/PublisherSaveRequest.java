package dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Publisher;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherSaveRequest {

    @NotNull
    @Size(min = 1, max = 255, message = "Yayınevi ismi 1 ile 255 karakter arasında olmalıdır")
    private String publisherName;

    //@Past(message = "Yayınevi kuruluş tarihi geçmiş bir tarih olmalıdır")
    private int publisherEstablishmentYear;

    @Size(min = 1, max = 255, message = "Yayınevi adresi 1 ile 255 karakter arasında olmalıdır")
    private String publisherAddress;
}
