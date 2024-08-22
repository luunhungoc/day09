package mvc.repository;


import mvc.entity.OrderDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetailEntity,Integer> {


//    List<OrderDetailsEntity> findByAuthor(String author);
//    List<OrderDetailsEntity> findByNameAndAuthor(String name,String author);
//    List<OrderDetailsEntity> findByNameOrAuthor(String name, String author);
//    OrderDetailsEntity findByBookDetailsIsbn(String isbn);
//
//
//    List<BookEntity> findByBookDetailsPriceLessThan(int price);
//    List<BookEntity> findByBookDetailsPriceLessThanEqual(int price);
//    List<BookEntity> findByBookDetailsPriceGreaterThanEqual(int price);
//
//    List<BookEntity> findByPriceLessThan(int price);
//

//
//    List<BookEntity> getAll();
}
