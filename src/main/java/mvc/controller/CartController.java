package mvc.controller;

import mvc.entity.BookEntity;
import mvc.entity.OrderDetailEntity;
import mvc.entity.OrdersEntity;
import mvc.entity.ProductEntity;
import mvc.formregistration.User;
import mvc.repository.OrderRepository;
import mvc.repository.ProductRepository;
import mvc.session.CartSession;
import org.hibernate.criterion.Order;
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

    @RequestMapping(value="/checkOut")
    public String checkout(Model model,HttpSession session) {
        CartSession cartSession = (CartSession) session.getAttribute("cartSession");
        OrdersEntity orderList = new OrdersEntity();
        model.addAttribute("orderList",orderList);
        model.addAttribute("customerName",orderList.getCustomerName());
        model.addAttribute("customerAddress",orderList.getCustomerAddress());
        return "cart/checkOut";
    }

    @RequestMapping(value="/placeOrder")
    public String placeOrder(OrdersEntity order, HttpServletRequest request){
        HttpSession session = request.getSession();
        CartSession cartSession = (CartSession) session.getAttribute("cartSession");

        String customerName=(String) session.getAttribute("customerName");
        String customerAddress=(String) session.getAttribute("customerAddress");

        if (cartSession != null && !cartSession.getProduct().getOrderDetailEntityList().isEmpty()) {
            List<OrderDetailEntity> orderDetailsList = cartSession.getProduct().getOrderDetailEntityList();

            for (OrderDetailEntity details : orderDetailsList) {
                details.setOrder(order);
            }
            // Set customer information
            order.setCustomerName(customerName);
            order.setCustomerAddress(customerAddress);
            order.setOrderDetailsEntityList(orderDetailsList);

            // Save the order and orderDetailsList to the database
            orderRepository.save(order);

            // Clear the cart
//            session.removeAttribute("cartSession");
        }

        return "redirect:/orders";
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

    @RequestMapping(value = "/orders")
    public String showOrders(Model model, HttpServletRequest request) {
        OrdersEntity orderList = (OrdersEntity) request.getSession().getAttribute("orderList");
        model.addAttribute("orderList", orderList);
        return "cart/orders";
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
