package com.codeid.eshopay.service.implementation;

import com.codeid.eshopay.model.entity.oe.*;
import com.codeid.eshopay.model.request.CreateCartItemRequest;
import com.codeid.eshopay.model.request.UpdateCartItemRequest;
import com.codeid.eshopay.model.response.CartItemResponse;
import com.codeid.eshopay.repository.CartItemRepository;
import com.codeid.eshopay.repository.CartRepository;
import com.codeid.eshopay.repository.ProductRepository;
import com.codeid.eshopay.service.CartItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<CartItemResponse> findSelectedByCartId(Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id " + cartId));

        List<CartItem> cartItems = cartItemRepository.findByCartAndSelectedTrue(cart);

        return cartItems.stream()
                .map(CartItemServiceImpl::mapToCartItemResponse)
                .toList();
    }

    @Transactional
    @Override
    public CartItemResponse save(CreateCartItemRequest request) {
        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id " + request.getCartId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + request.getProductId()));

        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseGet(() -> {
                    CartItemId id = new CartItemId();
                    id.setCartId(cart.getCartId());
                    id.setProductId(product.getProductId());

                    BigDecimal discount = product.getUnitPrice()
                            .multiply(request.getDiscount())
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

        int quantity = cartItem.getQuantity() + request.getQuantity();

        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        return mapToCartItemResponse(cartItem);
    }

    @Transactional
    @Override
    public CartItemResponse update(UpdateCartItemRequest request) {
        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id " + request.getCartId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + request.getProductId()));

        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseThrow(() -> new EntityNotFoundException("Cart Item not found for product id " + request.getProductId()));

        int quantity = cartItem.getQuantity() + request.getQuantity();

        BigDecimal discount = product.getUnitPrice()
                .multiply(request.getDiscount())
                .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);

        cartItem.setUnitPrice(product.getUnitPrice());
        cartItem.setQuantity(quantity);
        cartItem.setDiscount(discount);
        cartItemRepository.save(cartItem);

        return mapToCartItemResponse(cartItem);
    }

    @Override
    public void delete(Integer cartId, Short productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id " + cartId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));

        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseThrow(() -> new EntityNotFoundException("Cart Item not found with product id " + productId));

        cartItemRepository.delete(cartItem);
    }

    protected static CartItemResponse mapToCartItemResponse(CartItem cartItem) {
        BigDecimal discountPercentage = cartItem.getDiscount()
                .divide(cartItem.getUnitPrice(), 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100));

        List<String> productImageUrls = cartItem.getProduct().getProductImages().stream()
                .map(ProductImage::getFileName)
                .toList();

        return CartItemResponse.builder()
                .cartId(cartItem.getCart().getCartId())
                .productId(cartItem.getProduct().getProductId())
                .productName(cartItem.getProduct().getProductName())
                .productPictureUrl(cartItem.getProduct().getPicture())
                .productImageUrls(productImageUrls)
                .supplierName(cartItem.getProduct().getSupplier().getCompanyName())
                .unitPrice(cartItem.getUnitPrice())
                .quantity(cartItem.getQuantity())
                .discount(cartItem.getDiscount())
                .discountPercentage(discountPercentage + "%")
                .selected(cartItem.getSelected())
                .createdDate(cartItem.getCreatedDate())
                .modifiedDate(cartItem.getModifiedDate())
                .build();
    }
}
