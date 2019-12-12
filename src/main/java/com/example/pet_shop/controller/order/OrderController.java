package com.example.pet_shop.controller.order;


import com.example.pet_shop.model.order.Order;
import com.example.pet_shop.model.order.OrderProduct;
import com.example.pet_shop.model.order.OrderProductKey;
import com.example.pet_shop.model.product.Product;
import com.example.pet_shop.repository.order.OrderProductRepo;
import com.example.pet_shop.repository.order.OrderRepo;
import com.example.pet_shop.repository.product.ProductRepo;
import com.example.pet_shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/petshop.by")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    OrderRepo orderRepo;


    @Autowired
    ProductRepo productRepo;

    @Autowired
    CartService cartService;

    @Autowired
    OrderProductRepo orderProductRepo;


    @GetMapping(value = "/admin/orders")
    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }



    @PostMapping(value = "/admin/orders")
    public void createOrder(@RequestBody OrderProduct orderProd, Order order){
     //   Order order = new Order();
        double summ = 0;
        OrderProductKey productKey = new OrderProductKey();

        List<OrderProduct> orderProducts = new ArrayList<>();
        List<Product> products = cartService.getCartList();

        order.setOrderSumm(1000.00);
        order.setOrderDeliveryType("dsfsdf");
        order.setOrderDescription("dfdsfsdf");
        orderRepo.save(order);


        productKey.setOrderIdFk(order.getOrderId());

        for (Product p: products) {
            OrderProduct orderProduct = new OrderProduct();

            productKey.setProductIdFk(p.getProductId());
            System.out.println("------------------");
            System.out.println(orderProd.toString());
            orderProduct.setOrder(order);
            orderProduct.setId(productKey);
            orderProduct.setProduct(p);
            orderProduct.setProductQuantity(orderProd.getProductQuantity());
            orderProduct.setProductSize(p.getProductSize());
            orderProduct.setProductSumm(p.getProductPrice());


            orderProducts.add(orderProduct);
            orderProductRepo.saveAll(orderProducts);


            System.out.println(orderProducts.toString());


            order.setOrderProducts(orderProducts);
            summ+=p.getProductPrice()*orderProduct.getProductQuantity();

        }
        order.setOrderSumm(summ);
        orderRepo.save(order);
        products.clear();



    }


    @GetMapping(value = "/admin/orders/{id}")
    public List<Product> getProductsByOrderId(@PathVariable Long id){
         return productRepo.findByOrderProducts_Order_OrderId(id);
    }


    @DeleteMapping(value = "/admin/orders/{id}")
        public void deleteOrder(@PathVariable long id){
            orderRepo.deleteById(id);
    }
}

