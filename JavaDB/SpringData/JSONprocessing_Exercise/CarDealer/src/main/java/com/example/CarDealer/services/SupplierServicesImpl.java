package com.example.CarDealer.services;

import com.example.CarDealer.entities.suppliers.Supplier;
import com.example.CarDealer.entities.suppliers.SupplierNotImporterDTO;
import com.example.CarDealer.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.CarDealer.constants.ErrorMessages.INVALID_ID;

@Service
public class SupplierServicesImpl implements SupplierServices {

    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SupplierServicesImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public long getCountOfSuppliersInDb() {
        return this.supplierRepository.count();
    }

    @Override
    public void addSuppliersToDB(List<Supplier> suppliersToAddToDB) {
        this.supplierRepository.saveAll(suppliersToAddToDB);
    }

    @Override
    public Supplier getSupplierByID(long id) {
        Optional<Supplier> supplierByID = this.supplierRepository.findById(id);

        return supplierByID.orElseThrow(() -> new IllegalArgumentException(INVALID_ID));
    }

    @Override
    @Transactional
    public List<SupplierNotImporterDTO> getNonImporters() {
        List<Supplier> byIsImporterFalse = this.supplierRepository.findByIsImporterFalse();

        List<SupplierNotImporterDTO> supplierNotImporterDTOS = byIsImporterFalse.stream()
                .map(supplier -> {
                    SupplierNotImporterDTO supplierNotImporterDTO = modelMapper.map(supplier, SupplierNotImporterDTO.class);
                    supplierNotImporterDTO.setPartsCount(supplier.getParts().size());
                    return supplierNotImporterDTO;
                })
                .toList();

        return supplierNotImporterDTOS;
    }
}
