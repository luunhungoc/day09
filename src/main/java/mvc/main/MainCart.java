package mvc.main;

import mvc.config.Config;
import mvc.entity.*;
import mvc.repository.*;
import mvc.session.CartSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.*;

public class MainCart {
    static ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    static ProductRepository productRepository = (ProductRepository) context.getBean("productRepository");
//    static CategoryRepository categoryRepository = (CategoryRepository) context.getBean("categoryRepository");
//    static OrderDetailRepository orderDetailRepository = (OrderDetailRepository) context.getBean("orderDetailsRepository");
//    static OrderRepository orderRepository = (OrderRepository) context.getBean("orderRepository");


    public static void main(String[] args) {

showAll();
//        createNewProduct("Java A-Z","Java books",190);
//        createNewProduct("IT","IT books",550);
//        createNewProduct("Pen","Pen des",10);
//        createNewProduct("T-shirt","White and plain",80);
//        createNewProduct("Ruler","Centimet",60);
//          findAll();


        System.out.println("=========");
//        createNewOrder();
//        createNewOrderDetailsEntry();
//        createNewOrderDetailsEntryWithNewOrder();
//        findAllOrdersAndOrderDetails();
//        findByOrderId(1);
//        findOrderByCurrentMonth();
//        findOrderByTotalAmountGreaterThan(1000);
//        findByProductNameContaining("java");
    }

public static void showAll(){
    ProductEntity product = productRepository.findById(1).get();
    List<CartSession> cartSessionList = new ArrayList<>();
    CartSession cartList= new CartSession();
    cartList.setProduct(product);
    cartList.setQuantity(1);

    ProductEntity product2 = productRepository.findById(2).get();
    CartSession cartList2= new CartSession();
    cartList2.setProduct(product);
    cartList2.setQuantity(2);

    cartSessionList.add(cartList);
    cartSessionList.add(cartList2);

    System.out.println(cartSessionList);
}
    public static void findAll() {
        List<ProductEntity> productEntityList = (List<ProductEntity>) productRepository.findAll();
        if (productEntityList != null) {

            System.out.println("They are: ");
            for (ProductEntity p : productEntityList) {
                System.out.println(p.toString());
            }
        }
    }

    public static ProductEntity createNewProduct(String name, String des, double unitPrice){
        ProductEntity pEntity = new ProductEntity();

        pEntity.setName(name);
        pEntity.setDescription(des);
        pEntity.setUnitPrice(unitPrice);

        productRepository.save(pEntity);
        return pEntity;
    }



}