package dev.patika.Library_Management_Systems_RestAPI.Dto.Request.Publisher;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class PublisherUpdateRequest {

    private int publisherId;

    private String publisherName;

    private int publisherEstablishmentYear;

    private String publisherAddress;
}
