package com.example.booksyne.service.impl;
import com.example.booksyne.dao.entity.*;
import com.example.booksyne.dao.repository.BasketItemRepository;
import com.example.booksyne.dao.repository.BasketRepository;
import com.example.booksyne.dao.repository.CardRepository;
import com.example.booksyne.dao.repository.PaymentRepository;
import com.example.booksyne.model.dto.request.PaymentDto;
import com.example.booksyne.model.enums.PaymentStatus;
import com.example.booksyne.model.enums.ProductType;
import com.example.booksyne.model.exception.child.NotFoundException;
import com.example.booksyne.service.JwtService;
import com.example.booksyne.service.PaymentService;
import com.example.booksyne.utility.StockServiceManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;


@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final BasketRepository basketRepository;
    private final BasketItemRepository basketItemRepository;
    private final PaymentRepository paymentRepository;
    private final JwtService jwtService;
    private final StockServiceManager stockServiceManager;
    private final CardRepository cardRepository;

    @Override
    @Transactional
    public void pay(PaymentDto paymentDto, HttpServletRequest httpServletRequest) {
        log.info("createPayment method was started");
        Integer userId = jwtService.getUserId(jwtService.resolveClaims(httpServletRequest));
        Basket basket = basketRepository
                .findById(paymentDto.getBasketId())
                .orElseThrow(() -> new NotFoundException("This basket not found"));
        log.info("Basket found");

        for (BasketItem item : basket.getBasketItems()) {
            if (ProductType.BAG.equals(item.getProductType())) {
                stockServiceManager.checkAndDecreaseStock(ProductType.BAG, item.getProductId(), item.getQuantity());
            } else if (ProductType.BOOK.equals(item.getProductType())) {
                stockServiceManager.checkAndDecreaseStock(ProductType.BOOK, item.getProductId(), item.getQuantity());
            } else if (ProductType.GIFT.equals(item.getProductType())) {
                stockServiceManager.checkAndDecreaseStock(ProductType.GIFT, item.getProductId(), item.getQuantity());
            } else if (ProductType.MAGAZINE.equals(item.getProductType())) {
                stockServiceManager.checkAndDecreaseStock(ProductType.MAGAZINE, item.getProductId(), item.getQuantity());
            }
        }
        Card card = cardRepository.
                findById(paymentDto.getCardId()).orElseThrow(() -> new NotFoundException("Card not found"));
        log.info("Card was found");
        Payment payment = new Payment();
        payment.setPaymentStatus(PaymentStatus.PAID);
        payment.setCard(card);
        payment.setPaymentDate(LocalDate.now());
        payment.setUser(basket.getUser());
        Double basketItemPrice = basketItemRepository.findTotalAmountByBasketId(paymentDto.getBasketId());
        payment.setAmount(basketItemPrice);
        payment.setCurrency("$");
        payment.setBasket(basket);
        paymentRepository.save(payment);
        log.info("payment saved successfully");
    }
}