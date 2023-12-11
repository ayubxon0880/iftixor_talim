package uz.iftixortalim.crmspring.service;

import org.springframework.http.ResponseEntity;
import uz.iftixortalim.crmspring.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ResponseEntity<List<ProductDTO>> getAll();
}
