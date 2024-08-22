package mvc.controller;

import mvc.entity.BookEntity;
import mvc.entity.OrderDetailEntity;
import mvc.entity.OrdersEntity;
import mvc.entity.ProductEntity;
import mvc.formregistration.User;
import mvc.repository.OrderRepository;
import mvc.repository.ProductRepository;
import mvc.session.CartSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping(value = "/")
public class CartController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    public String addToOrder(OrdersEntity order,HttpSession session) {
        List<CartSession> cartList = (List<CartSession>) session.getAttribute("cartList");

        if (cartList != null && !cartList.isEmpty()) {
            List<OrderDetailEntity> orderDetailsList = new ArrayList<>();

            for (CartSession cartItem : cartList) {
                OrderDetailEntity orderDetails = new OrderDetailEntity();
                orderDetails.setProduct(cartItem.getProduct());
                orderDetails.setQuantity(cartItem.getQuantity());

                orderDetailsList.add(orderDetails);
            }

            order.setOrderDetailsEntityList(orderDetailsList);

             orderRepository.save(order);

        }

        return "cart/orders";
    }

    @RequestMapping(value="/orders",method = GET)
    public String showOrders(Model model,HttpServletRequest request) {
        List<OrdersEntity> ordersList = (List<OrdersEntity>) request.getSession().getAttribute("ordersList");
        model.addAttribute("ordersList", ordersList);
        return "cart/orders";
    }
    @RequestMapping(value="/checkOut", method=POST)
    public String checkOut(Model model,HttpSession session){
        List<CartSession> cartList = (List<CartSession>) session.getAttribute("cartList");

        OrdersEntity order = new OrdersEntity(); // Create an empty Order object
        model.addAttribute("order", order);

        return "cart/checkOut";
    }


    @RequestMapping(value = "/addToCart/{id}")
    public String addItem(@PathVariable int id,  HttpSession session) {
        List<CartSession> cartList = (List<CartSession>) session.getAttribute("cartList");
        if (cartList == null) {
            cartList = new ArrayList<>();
            session.setAttribute("cartList", cartList);
        }

        ProductEntity product = productRepository.findById(id).get();
        if (product != null) {
            boolean itemExists = false;
            for (CartSession item : cartList) {
                if (item.getProduct().getId() == id) {
                    item.setProduct(product);
                    item.setQuantity(item.getQuantity() + 1);
                    itemExists = true;
                    break;
                }
            }
            if (!itemExists) {
                cartList.add(new CartSession(product, 1));
            }
        }
        return "cart/cart";
    }

    @RequestMapping(value = "/removeItem/{id}")
    public String removeItem(@PathVariable int id, HttpSession session) {
        List<CartSession> cartList = (List<CartSession>) session.getAttribute("cartList");
        if (cartList != null) {
            cartList.removeIf(item -> item.getProduct().getId() == id);
        }
        return "cart/cart";

    }

    @RequestMapping(value = "/cart")
    public String showCart(Model model, HttpServletRequest request) {
        List<CartSession> cartList = (List<CartSession>) request.getSession().getAttribute("cartList");
        model.addAttribute("cartList", cartList);
        return "cart/cart";
    }
//    @RequestMapping(value = "/search", method = GET)
//    public String search(@RequestParam("searchInput") String searchInput, Model model) {
//        List<ProductEntity> resultList;
//        if (searchInput.isEmpty()) {
//            resultList = (List<ProductEntity>) productRepository.findAll();
//        } else {
//            resultList = productRepository.findByNameContainingOrIdContaining(searchInput, Integer.valueOf(searchInput));
//        }
//        model.addAttribute("productList", resultList);
//        return "book/productList";
//    }

}
