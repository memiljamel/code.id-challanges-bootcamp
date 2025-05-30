package com.codeid.eshopay.service.implementation;

import com.codeid.eshopay.model.entity.fintech.Bank;
import com.codeid.eshopay.model.entity.hr.Location;
import com.codeid.eshopay.model.entity.oe.*;
import com.codeid.eshopay.model.entity.person.User;
import com.codeid.eshopay.model.enumeration.PaymentType;
import com.codeid.eshopay.model.request.CreateOrderRequest;
import com.codeid.eshopay.model.response.OrderDetailResponse;
import com.codeid.eshopay.model.response.OrderResponse;
import com.codeid.eshopay.repository.*;
import com.codeid.eshopay.service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ShipperRepository shipperRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public OrderResponse findById(Short id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));

        return mapToOrderResponse(order);
    }

    @Transactional
    @Override
    public OrderResponse save(CreateOrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + request.getUserId()));

        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new EntityNotFoundException("Location not found with id " + request.getLocationId()));

        Shipper shipper = shipperRepository.findById(request.getShipperId())
                .orElseThrow(() -> new EntityNotFoundException("Shipper not found with id " + request.getShipperId()));

        Bank bank = bankRepository.findById(request.getBankCode())
                .orElseThrow(() -> new EntityNotFoundException("Bank not found with id " + request.getBankCode()));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for user id " + request.getUserId()));

        List<CartItem> cartItems = cartItemRepository.findByCartAndSelectedTrue(cart);
        if (cartItems.isEmpty()) {
            throw new EntityNotFoundException("No checkout items found in cart for user id " + request.getUserId());
        }

        BigDecimal totalAmount = cartItems.stream()
                .map(cartItem -> cartItem.getUnitPrice()
                        .multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDiscount = cartItems.stream()
                .map(cartItem -> cartItem.getDiscount()
                        .multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setFreight(35000F);
        order.setShipName(shipper.getCompanyName());
        order.setTotalAmount(totalAmount);
        order.setTotalDiscount(totalDiscount);
        order.setPaymentType(PaymentType.TRANSFER);
        order.setUser(user);
        order.setShipper(shipper);
        order.setLocation(location);
        order.setBank(bank);
        orderRepository.saveAndFlush(order);

        List<OrderDetail> orderDetails = cartItems.stream()
                .map(cartItem -> {
                    OrderDetailId id = new OrderDetailId();
                    id.setOrderId(order.getOrderId());
                    id.setProductId(cartItem.getProduct().getProductId());

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setId(id);
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cartItem.getProduct());
                    orderDetail.setUnitPrice(cartItem.getUnitPrice());
                    orderDetail.setQuantity(cartItem.getQuantity());
                    orderDetail.setDiscount(cartItem.getDiscount());

                    return orderDetail;
                })
                .toList();

        orderDetailRepository.saveAllAndFlush(orderDetails);

        cartItemRepository.deleteAll(cartItems);

        entityManager.refresh(order);

        return mapToOrderResponse(order);
    }

    @Override
    public void delete(Short id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));

        orderRepository.delete(order);
    }

    protected static OrderResponse mapToOrderResponse(Order order) {
        List<OrderDetailResponse> details = order.getOrderDetails().stream()
                .map(OrderServiceImpl::mapToOrderDetailResponse)
                .toList();

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderNo(order.getOrderNo())
                .orderDate(order.getOrderDate())
                .requiredDate(order.getRequiredDate())
                .shippedDate(order.getShippedDate())
                .freight(order.getFreight())
                .shipName(order.getShipName())
                .totalAmount(order.getTotalAmount())
                .totalDiscount(order.getTotalDiscount())
                .paymentType(order.getPaymentType())
                .cardNo(order.getCardNo())
                .transacNo(order.getTransacNo())
                .transacDate(order.getTransacDate())
                .refNo(order.getRefNo())
                .details(details)
                .userId(order.getUser().getUserId())
                .locationId(order.getLocation().getLocationId())
                .bankCode(order.getBank().getBankCode())
                .shipperId(order.getShipper().getShipperId())
                .employeeId(null)
                .createdDate(order.getCreatedDate())
                .modifiedDate(order.getModifiedDate())
                .build();
    }

    protected static OrderDetailResponse mapToOrderDetailResponse(OrderDetail orderDetail) {
        return OrderDetailResponse.builder()
                .orderId(orderDetail.getOrder().getOrderId())
                .productId(orderDetail.getProduct().getProductId())
                .unitPrice(orderDetail.getUnitPrice())
                .quantity(orderDetail.getQuantity())
                .discount(orderDetail.getDiscount())
                .createdDate(orderDetail.getCreatedDate())
                .modifiedDate(orderDetail.getModifiedDate())
                .build();
    }
}
