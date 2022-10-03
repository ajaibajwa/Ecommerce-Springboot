package com.ajay.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ajay.DTO.CartDTO;
import com.ajay.DTO.OrderDTO;
import com.ajay.model.Cart;
import com.ajay.model.Order;
import com.ajay.model.OrderItem;
import com.ajay.model.Product;
import com.ajay.model.User;
import com.ajay.repo.CartRepository;
import com.ajay.repo.OrderItemRepository;

@Service
public class CartService {

	@Autowired
	CartRepository repo;

	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@Autowired
	EmailService emailService;

	public List<Cart> getAllCarts() {
		return repo.findAll();
	}

	public List<Cart> getCartByUser(String userEmail) {
		/*
		 * MyUserDetails user = (MyUserDetails)
		 * SecurityContextHolder.getContext().getAuthentication().getPrincipal(); User u
		 * = user.getUser();
		 */

		// return repo.findByUser(u);
		return repo.findByUseremail(userEmail);
	}

	public String addToCart(CartDTO cartDto, String useremail) {
		Cart cart = new Cart();

		Product product = productService.getproductById(cartDto.getProductid()).get();
		if (cartDto.getQuantity() > 0) {
			if (product.getStockavailable() >= cartDto.getQuantity()) {
				//cart.setUserid(userService.getCurrentUser().get().getUserid());
				cart.setUseremail(useremail);
				// cart.setUser(userService.getUserById(currentUserId).get());
				cart.setProduct(product);
				cart.setQuantity(cartDto.getQuantity());
				repo.save(cart);
				return cart.getProduct().getProductname() + " added to cart. Quantity - " + cart.getQuantity();
			} else {
				return "Not enough stock available";
			}
		} else {
			return "Please enter a valid quantity.";
		}

	}

	public String updateQuantity(CartDTO cartDto, String userEmail) {
		try {
			if (cartDto.getProductid() != 0) {
				Product product = productService.getproductById(cartDto.getProductid()).get();
				Cart cart = repo.findByProductAndUseremail(product, userEmail);
				if (cartDto.getQuantity() > 0) {
					// List<Cart> cartItems =
					// repo.findByUserid(userService.getCurrentUser().get().getUserid());
					if (product.getStockavailable() >= cartDto.getQuantity()) {
						cart.setQuantity(cartDto.getQuantity());

						repo.save(cart);
						return "Quantity changed to " + cart.getQuantity();
					} else {
						return "Not enough stock available";
					}

				} else if (cartDto.getQuantity() == 0) {
					repo.delete(cart);
					return "Product deleted from cart.";
				} else {
					return "Please enter a valid quantity.";
				}
			} else {
				return "Entered product not found in the cart.";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "Entered product not found in the cart.";
		}
	}

	double totalAmount;
	String orderDetailsString = "Order Details->\n";

	public String Checkout(OrderDTO orderDto, String userEmail) {
		List<Cart> cartItems = getCartByUser(userEmail);
		System.out.println(userEmail);
		// double totalAmount;
	
		cartItems.forEach(c -> {
			totalAmount += c.getProduct().getPrice() * c.getQuantity();
		});

		Order order = new Order();
		order.setTotalamount(totalAmount);
		order.setOrderdate(LocalDate.now());
		order.setStatus("Order_Placed");
		order.setUseremail(userEmail);
		//order.setUser(userService.getCurrentUser().get());
		order.setShippingaddress(orderDto.getShippingaddress());
		order.setBillingaddress(orderDto.getBillingaddress());
		
		int orderid = orderService.repo.save(order).getOrderid();
		System.out.println("orderId is "+orderid);
		cartItems.forEach(c->{

			productService.updateProductStockById(c.getProduct().getProductid(),
					c.getProduct().getStockavailable() - c.getQuantity());
			orderDetailsString = orderDetailsString.concat(
					"Product Name: " + c.getProduct().getProductname() + "\nQuantity:" + c.getQuantity() + "\n");
		
			
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(c.getProduct());
			orderItem.setQuantity(c.getQuantity());
			//orderItem.setUserid(c.getUserid());
			orderItem.setUseremail(c.getUseremail());
			orderItem.setOrderid(orderid);
			orderItemRepository.save(orderItem);
			
			repo.delete(c);
		});

		orderDetailsString = orderDetailsString.concat("Total Amount: " + totalAmount);
		//emailService.sendCheckoutEmail(userService.getCurrentUser().get().getEmail(),
		//		userService.getCurrentUser().get().getFirstname(), "Ajaydeep1717@gmail.com", orderDetailsString);
		//totalAmount = 0;
		return "Order placed successfully.";
	}

}
