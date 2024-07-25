package dev.patika.Library_Management_Systems_RestAPI.Dto.Response.Publisher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponse {

    //Kullanıcıya (İstemciye) gösterecek bilgiler
    private int publisherId;
    private String publisherName;
    private String publisherAddress;
}
