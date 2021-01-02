package com.example.demoDay1.app;

import com.example.demoDay1.app.model.*;
import com.example.demoDay1.config.MyUserPrincipal;
import org.apache.commons.io.IOUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping(path = "/order")
public class OrderController {


    private final OrderRepository orderRepository;
    private final OrderDetailRepository detailRepository;
    private final BookRepository bookRepository;

    private ZipOutputStream zipFile;

    public OrderController(OrderRepository orderRepository, OrderDetailRepository detailRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.detailRepository = detailRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/buyBook")
    public String buyBook(HttpServletRequest request, Model model, @RequestParam("id") Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            BookInfo bookInfo = new BookInfo(book.get());
            ShoppingCartInfo cartInfo = ShoppingCart.getShoppingCartInfo(request);
            cartInfo.addBook(bookInfo, 1);
        }
        return "redirect:/order/shoppingCart";
    }

    @GetMapping("/shoppingCart")
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        ShoppingCartInfo myCart = ShoppingCart.getShoppingCartInfo(request);
        if (myCart != null) {
            model.addAttribute("shoppingCart", myCart);
        } else {
            model.addAttribute("shoppingCart", new ShoppingCartInfo());
        }
        return "shoppingCart";
    }

    @GetMapping("/shoppingCartCounter")
    public String goToShoppingCartCounter(HttpServletRequest request, Model model, Principal principal) {
        ShoppingCartInfo cart = ShoppingCart.getShoppingCartInfo(request);
        if (cart == null || cart.isEmpty()) {
            return "redirect:/order/shoppingCart";
        }

        if (principal != null) {
            MyUserPrincipal myUserPrincipal = (MyUserPrincipal) ((Authentication) principal).getPrincipal();
            CustomerInfo customer = new CustomerInfo(myUserPrincipal.getPerson());
            cart.setCustomerInfo(customer);
        }
        if (cart.getCustomerInfo() == null) {
            return "redirect:/login";
        }

        model.addAttribute("shoppingCart", cart);
        return "shoppingCartCounter";
    }

    @PostMapping("/shoppingCartCounter")
    public String payAndDownload(HttpServletRequest request, Model model) {
        ShoppingCartInfo cart = ShoppingCart.getShoppingCartInfo(request);
        Order newOrder;
        if (cart == null) {
            return "redirect:/order/shoppingCart";
        }
        if (cart.getCustomerInfo() == null || cart.getCustomerInfo().getPerson() == null)
            return "redirect:/login";
        try {
            long id = orderRepository.getMaxId();
            long ordernum = orderRepository.getMaxOrdernumFromCustomer(cart.getCustomerInfo().getPerson().getId());
            System.out.println(cart.getCustomerInfo().getPerson().getId());
            Order order = new Order(id + 1, cart.getSumTotal(), 0, new Date(System.currentTimeMillis()), ordernum + 1, cart.getCustomerInfo().getPerson());
            newOrder = orderRepository.save(order);

            long maxId = detailRepository.getMaxId();
            zipFile = new ZipOutputStream(new FileOutputStream("ebooks.zip"));

            for (ShoppingCartLineInfo line : cart.getCartLines()) {
                maxId = maxId + 1;
                Optional<Book> b = bookRepository.findById(line.getBookInfo().getId());
                OrderDetail orderDetail = new OrderDetail(maxId, line.getQuantity(), line.getSum(), b.get(), newOrder);
                detailRepository.save(orderDetail);

                ZipEntry entry = new ZipEntry(b.get().getPath());
                entry.setMethod(ZipEntry.DEFLATED);
                zipFile.putNextEntry(entry);
                zipFile.write(b.get().getBfiles());
                zipFile.closeEntry();
            }
            zipFile.close();
//            cart.setOrderNum(newOrder);// newOrder.getOrdernum()
            model.addAttribute("zipfile", new String("ebooks.zip"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        ShoppingCart.storeLastOrderedShoppingCart(request, cart);
        ShoppingCart.removeShoppingCart(request);
        return "redirect:/order/order";
    }

    @GetMapping("/order")
    public String getOrder(HttpServletRequest request, Model model) {
        ShoppingCartInfo lastOrder = ShoppingCart.getLastShoppingCartInfoSession(request);

        if (lastOrder == null)
            return "redirect:/order/shoppingCart";
        model.addAttribute("lastOrder", lastOrder);
        return "order";
    }

    @GetMapping("/orderList")
    public String listOrders(HttpServletRequest request, Model model, Principal principal) {
        List<Order> orderList = new ArrayList<>();

        if (principal != null) {
            MyUserPrincipal myuserprincipal = (MyUserPrincipal) ((Authentication) principal).getPrincipal();
            Person p = myuserprincipal.getPerson();
            orderList = orderRepository.getOrdersByCustomer(p.getId());
        }
        model.addAttribute("orderlist", orderList);
        return "orderList";

    }

    @GetMapping("/ebooks.zip")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FileInputStream in = new FileInputStream("ebooks.zip");
        response.setContentType("application/zip");
        response.setHeader("Content-disposition", "attachment; filename=ebooks.zip");

        IOUtils.copy(in, response.getOutputStream());
    }


}
