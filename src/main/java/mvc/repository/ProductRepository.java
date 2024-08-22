package mvc.repository;

import mvc.entity.BookEntity;
import mvc.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity,Integer> {


}
