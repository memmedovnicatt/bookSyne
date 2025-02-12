package com.example.booksyne.utility;

import com.example.booksyne.dao.repository.BagRepository;
import com.example.booksyne.dao.repository.BookRepository;
import com.example.booksyne.dao.repository.GiftRepository;
import com.example.booksyne.dao.repository.MagazineRepository;
import com.example.booksyne.model.enums.ProductType;
import com.example.booksyne.model.exception.child.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceManager {
    private final BagRepository bagRepository;
    private final BookRepository bookRepository;
    private final GiftRepository giftRepository;
    private final MagazineRepository magazineRepository;

    public void checkAndDecreaseStock(ProductType productType, Integer productId, int requestedQuantity) {
        Integer stock = null;
        switch (productType) {
            case BAG:
                stock = bagRepository.findStockByBagId(productId)
                        .orElseThrow(() -> new NotFoundException("Bag not found"));
                decreaseStock(bagRepository, productId, stock, requestedQuantity);
                break;
            case BOOK:
                stock = bookRepository.findStockByBookId(productId)
                        .orElseThrow(() -> new NotFoundException("Book not found"));
                decreaseStock(bookRepository, productId, stock, requestedQuantity);
                break;
            case GIFT:
                stock = giftRepository.findStockByGiftId(productId)
                        .orElseThrow(() -> new NotFoundException("Gift not found"));
                decreaseStock(giftRepository, productId, stock, requestedQuantity);
                break;
            case MAGAZINE:
                stock = magazineRepository.findStockByMagazineId(productId)
                        .orElseThrow(() -> new NotFoundException("Magazine not found"));
                decreaseStock(magazineRepository, productId, stock, requestedQuantity);
                break;
            default:
                throw new NotFoundException("Unknown product type: " + productType);
        }
        if (stock < requestedQuantity) {
            throw new NotFoundException("Insufficient stock for productType: " + productType + ", productId: " + productId);
        }
    }

    private void decreaseStock(Object repository, Integer productId, Integer stock, int requestedQuantity) {
        if (stock < requestedQuantity) {
            throw new NotFoundException("Insufficient stock for productId: " + productId);
        }
        if (repository instanceof BagRepository) {
            bagRepository.decreaseStock(productId, requestedQuantity);
        } else if (repository instanceof BookRepository) {
            bookRepository.decreaseStock(productId, requestedQuantity);
        } else if (repository instanceof GiftRepository) {
            giftRepository.decreaseStock(productId, requestedQuantity);
        } else if (repository instanceof MagazineRepository) {
            magazineRepository.decreaseStock(productId, requestedQuantity);
        }
    }
}
