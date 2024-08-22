package mvc.main;

import mvc.config.Config;
import mvc.entity.*;
import mvc.repository.BookRepository;
import mvc.repository.CategoryRepository;
import mvc.repository.OrderDetailRepository;
import mvc.repository.OrderRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class MainBook {
    static ApplicationContext context= new AnnotationConfigApplicationContext(Config.class);
    static BookRepository bookRepository=(BookRepository) context.getBean("bookRepository");
    static CategoryRepository categoryRepository =(CategoryRepository) context.getBean("categoryRepository");
    static OrderDetailRepository orderDetailRepository =(OrderDetailRepository) context.getBean("orderDetailsRepository");
    static OrderRepository orderRepository =(OrderRepository) context.getBean("orderRepository");


    public static void main(String[] args){

//        createNewBookEntryWithNewCategory();
//        createNewBookEntry();
        findAll();
//findBookById(4);
//        findByAuthor("Roger");
//        findByNameAndAuthor("Java A-Z","Roger");
//        findByNameOrAuthor("linux","Roger");
//        findByPriceLessThan(80);
//        findByBookDetailsIsbn("ISIBF1219323");
//        findByNameContaining("ava");
//        findByNameContainingOrAuthorContaining("ava","hom");
//        deleteBookById(1);
//        deleteAllBook();


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

    // ===========BOOKS============

    public static void deleteBookById(int i){
        bookRepository.deleteById(i);
        System.out.println("Deleted book id "+i);
    }
    public static void deleteAllBook(){
        bookRepository.deleteAll();
        System.out.println("Deleted all books");
    }

    public static void findBookById(int id){
        BookEntity bookEntity= bookRepository.findById(id).get();
        if(bookEntity!=null){
            System.out.println("\nFind book which id= "+id);

            System.out.println(bookEntity.toString());

        }
    }


    public static void findAll(){
        List<BookEntity> bookEntityList= (List<BookEntity>) bookRepository.findAll();
        if(bookEntityList!=null){

            System.out.println("They are: ");
            for(BookEntity bookEntity:bookEntityList){
                System.out.println(bookEntity.toString());
            }
        }
    }
    public static void findByAuthor(String author){
        List<BookEntity> bookEntityList=bookRepository.findByAuthor(author);
        if(bookEntityList!=null){
            System.out.println("Find "+bookEntityList.size()+" books which author = "+author);
            System.out.println("They are: ");
            for(BookEntity bookEntity:bookEntityList){
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByNameAndAuthor(String name,String author){
        List<BookEntity> bookEntityList=bookRepository.findByNameAndAuthor(name,author);
        if(bookEntityList!=null){
            System.out.println("\nFind "+bookEntityList.size()+" books which name= "+ name+ " and author = "+author);
            for(BookEntity bookEntity:bookEntityList){
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByNameOrAuthor(String name,String author){
        List<BookEntity> bookEntityList=bookRepository.findByNameOrAuthor(name,author);
        if(bookEntityList!=null){
            System.out.println("\nFind "+bookEntityList.size()+" books which name= "+ name+ "or author = "+author);
            for(BookEntity bookEntity:bookEntityList){
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByPriceLessThan(int price){
        List<BookEntity> bookEntityList=bookRepository.findByPriceLessThan(price);
        if(bookEntityList!=null){
            System.out.println("\nFind "+bookEntityList.size()+" books price less than "+price);
            for(BookEntity bookEntity:bookEntityList){
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByNameContaining(String name){
        List<BookEntity> bookEntityList=bookRepository.findByNameContaining(name);
        if(bookEntityList!=null){
            System.out.println("\nFind "+bookEntityList.size()+" books containing name "+name);
            for(BookEntity bookEntity:bookEntityList){
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByNameContainingOrAuthorContaining(String name, String author){
        List<BookEntity> bookEntityList=bookRepository.findByNameContainingOrAuthorContaining(name, author);
        if(bookEntityList!=null){
            System.out.println("\nFind "+bookEntityList.size()+" books containing name "+name+" or author "+author);
            for(BookEntity bookEntity:bookEntityList){
                System.out.println(bookEntity.toString());
            }
        }
    }


    public static void findByBookDetailsIsbn(String isbn){
        BookEntity bookEntity= bookRepository.findByBookDetailsIsbn(isbn);
        if(bookEntity!=null){
            System.out.println("\nFind book which isbn= "+isbn);

                System.out.println(bookEntity.toString());

        }
    }
//
//
//
    public static void createNewBookEntry(){
        CategoryEntity categoryEntity= new CategoryEntity();
        categoryEntity.setId(2);

        BookEntity bookEntity=createNewBook();
        bookEntity.setCategory(categoryEntity);
        bookRepository.save(bookEntity);
    }
    public static void createNewBookEntryWithNewCategory(){
        CategoryEntity categoryEntity= createNewCategory();
        categoryRepository.save(categoryEntity);

        BookEntity bookEntity=createNewBook();
        bookEntity.setCategory(categoryEntity);
        bookRepository.save(bookEntity);
    }

    private static CategoryEntity createNewCategory() {
        CategoryEntity categoryEntity=new CategoryEntity();

        categoryEntity.setName("IT");
        categoryEntity.setDescription("IT books");
        categoryRepository.save(categoryEntity);
        return categoryEntity;
    }

    private static BookEntity createNewBook() {
        BookDetailsEntity bookDetailsEntity = new BookDetailsEntity();
        bookDetailsEntity.setIsbn("ISIBF00090003");
        bookDetailsEntity.setNumberOfPage(1000);
        bookDetailsEntity.setPrice(100);
        bookDetailsEntity.setPublishDate(LocalDate.parse("2014-09-08"));

        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("Bruno");
        bookEntity.setName("Math basic level 1");
        bookEntity.setBookDetails(bookDetailsEntity);
        bookDetailsEntity.setBook(bookEntity);

        return bookEntity;

    }




// ===========ORDERS============

    public static void findAllOrdersAndOrderDetails(){
        List<OrdersEntity> ordersEntityList = (List<OrdersEntity>) orderRepository.findAll();
        if(ordersEntityList !=null){
            System.out.println("Find "+ ordersEntityList.size()+" orders:");
            System.out.println("They are: ");
            for(OrdersEntity ordersEntity : ordersEntityList){
                System.out.println(ordersEntity.toString());
            }
        }
    }

    public static void findByOrderId(int id){
        List<OrdersEntity> ordersEntityList =orderRepository.findByOrderId(id);
        if(ordersEntityList !=null){
            System.out.println("Find "+ ordersEntityList.size()+" orders which id = "+id);
            System.out.println("They are: ");
            for(OrdersEntity ordersEntity : ordersEntityList){
                System.out.println(ordersEntity.toString());
            }
        }
    }

    public static void findOrderByCurrentMonth(){
        List<OrdersEntity> ordersEntityList =orderRepository.findOrderByCurrentMonth();
        if(ordersEntityList.size()!=0){
            System.out.println("Found "+ ordersEntityList.size()+" order(s) in current month");
            System.out.println("They are: ");
            for(OrdersEntity o: ordersEntityList){
                System.out.println(o.toString());
            }

        }
    }
//7. List all orders which have total amount more than 1,000USD
    public static void findOrderByTotalAmountGreaterThan(double price){
        List<OrdersEntity> ordersEntityList =orderRepository.findOrderByTotalAmountGreaterThan(price);
        if(ordersEntityList !=null){
            System.out.println("\nFind "+ ordersEntityList.size()+" orders which have total amount more than "+price+" USD:");
            for(OrdersEntity ordersEntity : ordersEntityList){
                System.out.println(ordersEntity.toString());
            }
        }
    }
    //8. List all orders buy Java book.
    public static void findByProductNameContaining(String productName){
        List<OrdersEntity> ordersEntityList =orderRepository.findByProductNameContaining(productName);
        if(ordersEntityList !=null){
            System.out.println("Find "+ ordersEntityList.size()+" orders which productName = "+productName);
            System.out.println("They are: ");
            for(OrdersEntity ordersEntity : ordersEntityList){
                System.out.println(ordersEntity.toString());
            }
        }
    }

    public static void createNewOrderDetailsEntry(){
        OrdersEntity ordersEntity = createNewOrder();
        ordersEntity.setId(1);
        OrderDetailEntity orderDetailEntity =createNewOrderDetails();
        orderDetailEntity.setOrder(ordersEntity);
        orderDetailRepository.save(orderDetailEntity);
    }


    public static void createNewOrderDetailsEntryWithNewOrder(){
        OrdersEntity ordersEntity = createNewOrder();
        orderRepository.save(ordersEntity);

        OrderDetailEntity orderDetailEntity =createNewOrderDetails();
        orderDetailEntity.setOrder(ordersEntity);
        orderDetailRepository.save(orderDetailEntity);
    }

        private static OrdersEntity createNewOrder() {
            OrdersEntity ordersEntity =new OrdersEntity();

            ordersEntity.setOrderDate(LocalDate.now());
            ordersEntity.setCustomerName("Nguyen Du");
            ordersEntity.setCustomerAddress("Vietnam");
            orderRepository.save(ordersEntity);
        return ordersEntity;
    }

    private static OrderDetailEntity createNewOrderDetails() {
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();

        orderDetailEntity.setQuantity(2);


        OrdersEntity ordersEntity = new OrdersEntity();
        orderDetailEntity.setOrder(ordersEntity);

        return orderDetailEntity;

    }

}
