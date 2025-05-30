package com.codeid.eshopay.service.implementation;

import com.codeid.eshopay.model.entity.oe.Cart;
import com.codeid.eshopay.model.entity.oe.CartItem;
import com.codeid.eshopay.model.entity.oe.CartItemId;
import com.codeid.eshopay.model.entity.oe.Product;
import com.codeid.eshopay.model.entity.person.User;
import com.codeid.eshopay.model.request.CreateCartRequest;
import com.codeid.eshopay.model.request.CreateCheckoutRequest;
import com.codeid.eshopay.model.request.UpdateCartRequest;
import com.codeid.eshopay.model.response.CartItemResponse;
import com.codeid.eshopay.model.response.CartResponse;
import com.codeid.eshopay.repository.CartItemRepository;
import com.codeid.eshopay.repository.CartRepository;
import com.codeid.eshopay.repository.ProductRepository;
import com.codeid.eshopay.repository.UserRepository;
import com.codeid.eshopay.service.CartService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CartResponse> findAll(Pageable pageable) {
        Page<Cart> carts = cartRepository.findAll(pageable);

        return carts.getContent().stream()
                .map(CartServiceImpl::mapToCartResponse)
                .toList();
    }

    @Override
    public CartResponse findById(Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id " + id));

        return mapToCartResponse(cart);
    }

    @Override
    public CartResponse findByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for user " + userId));

        return mapToCartResponse(cart);
    }

    @Transactional
    @Override
    public CartResponse save(CreateCartRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + request.getUserId()));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.saveAndFlush(newCart);
                });

        List<CartItem> cartItems = request.getItems().stream()
                .map(item -> {
                    Product product = productRepository.findById(item.getProductId())
                            .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + item.getProductId()));

                    CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                            .orElseGet(() -> {
                                CartItemId id = new CartItemId();
                                id.setCartId(cart.getCartId());
                                id.setProductId(product.getProductId());

                                BigDecimal discount = product.getUnitPrice()
                                        .multiply(item.getDiscount())
                                        .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);

                                CartItem newCartItem = new CartItem();
                                newCartItem.setId(id);
                                newCartItem.setUnitPrice(product.getUnitPrice());
                                newCartItem.setQuantity(0);
                                newCartItem.setDiscount(discount);
                                newCartItem.setCart(cart);
                                newCartItem.setProduct(product);

                                return newCartItem;
                            });

                    int quantity = cartItem.getQuantity() + item.getQuantity();

                    cartItem.setQuantity(quantity);

                    return cartItem;
                })
                .toList();

        cartItemRepository.saveAllAndFlush(cartItems);

        entityManager.refresh(cart);

        return mapToCartResponse(cart);
    }

    @Transactional
    @Override
    public CartResponse update(UpdateCartRequest request) {
        Cart cart = cartRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id " + request.getId()));

        if (!request.getMerge()) {
            cartItemRepository.deleteAllByCart(cart);
        }

        List<CartItem> cartItems = request.getItems().stream()
                .map(item -> {
                    Product product = productRepository.findById(item.getProductId())
                            .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + item.getProductId()));

                    CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                            .orElseGet(() -> {
                                CartItemId id = new CartItemId();
                                id.setCartId(cart.getCartId());
                                id.setProductId(product.getProductId());

                                BigDecimal discount = product.getUnitPrice()
                                        .multiply(item.getDiscount())
                                        .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);

                                CartItem newCartItem = new CartItem();
                                newCartItem.setId(id);
                                newCartItem.setUnitPrice(product.getUnitPrice());
                                newCartItem.setQuantity(0);
                                newCartItem.setDiscount(discount);
                                newCartItem.setCart(cart);
                                newCartItem.setProduct(product);

                                return newCartItem;
                            });

                    int quantity = cartItem.getQuantity() + item.getQuantity();

                    cartItem.setQuantity(quantity);

                    return cartItem;
                })
                .toList();

        cartItemRepository.saveAll(cartItems);

        return mapToCartResponse(cart);
    }

    @Override
    public void delete(Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id " + id));

        cartRepository.delete(cart);
    }

    @Transactional
    @Override
    public CartResponse checkout(CreateCheckoutRequest request) {
        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id " + request.getCartId()));

        cartItemRepository.unselectAllByCart(cart);

        List<CartItem> cartItems = request.getProductIds().stream()
                .map(productId -> {
                    Product product = productRepository.findById(productId)
                            .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));

                    CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                            .orElseThrow(() -> new EntityNotFoundException("Cart Item not found for product id " + productId));
                    cartItem.setSelected(true);

                    return cartItem;
                })
                .toList();

        cartItemRepository.saveAll(cartItems);

        return mapToCartResponse(cart);
    }

    protected static CartResponse mapToCartResponse(Cart cart) {
        List<CartItemResponse> items = cart.getCartItems().stream()
                .map(CartItemServiceImpl::mapToCartItemResponse)
                .toList();

        return CartResponse.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUser().getUserId())
                .items(items)
                .createdDate(cart.getCreatedDate())
                .modifiedDate(cart.getModifiedDate())
                .build();
    }
}
