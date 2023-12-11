package uz.iftixortalim.crmspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.iftixortalim.crmspring.dto.ProductDTO;
import uz.iftixortalim.crmspring.repository.ProductRepository;
import uz.iftixortalim.crmspring.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public ResponseEntity<List<ProductDTO>> getAll() {
        return null;
    }
}
