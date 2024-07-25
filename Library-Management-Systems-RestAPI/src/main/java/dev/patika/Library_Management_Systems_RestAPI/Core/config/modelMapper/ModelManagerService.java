package dev.patika.Library_Management_Systems_RestAPI.Core.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelManagerService implements IModelMapperService {

    private final ModelMapper modelMapper;

    @Autowired
    public ModelManagerService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true) // Çakışmaları göz ardı et
                .setMatchingStrategy(MatchingStrategies.STANDARD); // Eşleştirme stratejisini "standart" olarak ayarla
        return this.modelMapper;
    }

    @Override
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true) // Çakışmaları göz ardı et
                .setMatchingStrategy(MatchingStrategies.LOOSE); // Eşleştirme stratejisini "esnek" olarak ayarla
        return this.modelMapper;
    }
}
