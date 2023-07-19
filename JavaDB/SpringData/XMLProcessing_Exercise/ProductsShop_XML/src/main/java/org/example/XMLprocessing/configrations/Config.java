package org.example.XMLprocessing.configrations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.XMLprocessing.entities.categories.ImportCategoryWrapperDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {


    @Bean
    public ModelMapper createModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }


//public JAXBContext createJAXBContext() throws JAXBException {
//    JAXBContext jaxbContextImortCategoryWrapper = JAXBContext.newInstance(ImportCategoryWrapperDTO.class);
//
//}
//
//public Marshaller createMarshaller() throws JAXBException {
//    JAXBContext jaxbContextImortCategoryWrapper = JAXBContext.newInstance(ImportCategoryWrapperDTO.class);
//    Marshaller marshallerImportCategoryWrapper = jaxbContextImortCategoryWrapper.createMarshaller();
//    return marshallerImportCategoryWrapper;
//}

}


