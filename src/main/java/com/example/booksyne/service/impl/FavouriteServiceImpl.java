package com.example.booksyne.service.impl;

import com.example.booksyne.dao.entity.*;
import com.example.booksyne.dao.repository.*;
import com.example.booksyne.model.dto.request.DeleteFavouriteRequest;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.dto.response.FavouriteDetailDto;
import com.example.booksyne.model.enums.ProductType;
import com.example.booksyne.model.exception.child.*;
import com.example.booksyne.service.*;
import com.example.booksyne.utility.ReflectionServiceInvoker;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavouriteServiceImpl implements FavouriteService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ReflectionServiceInvoker reflectionServiceInvoker;
    private final ProductTypeEntityRepository productTypeEntityRepository;
    private final FavouriteRepository favouriteRepository;
    private final BagRepository bagRepository;
    private final BookRepository bookRepository;
    private final GiftRepository giftRepository;
    private final MagazineRepository magazineRepository;

    @Override
    @Transactional
    public void add(Integer productId, FavouriteRequestDto favouriteRequestDto, HttpServletRequest httpServletRequest) {
        Integer userId = jwtService.getUserId(jwtService.resolveClaims(httpServletRequest));
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        log.info("User found");
        ProductTypeEntity productType = productTypeEntityRepository
                .findByProductType(favouriteRequestDto.getProductType())
                .orElseThrow(() -> new NotFoundException(favouriteRequestDto
                        .getProductType() + " type is not available in stock"));
        log.info("Product type {} found for the request.", favouriteRequestDto.getProductType());
        log.info("{} type was found ", favouriteRequestDto.getProductType());

        Class<?>[] parameterTypes = {User.class, Integer.class, FavouriteRequestDto.class, HttpServletRequest.class};
        Object[] parameters = {user, productId, favouriteRequestDto, httpServletRequest};

        try {
            reflectionServiceInvoker.invokeService(productType.getServiceName(),
                    productType.getMethodName(),
                    parameterTypes,
                    parameters);
            log.info("Successfully add to favourites");
        } catch (Exception e) {
            log.error("Error while adding to favourites", e);
            throw new RuntimeException("Failed to add to favourites", e);
        }
        log.info("Method done");
    }

    @Transactional
    @Override
    public String delete(DeleteFavouriteRequest deleteFavouriteRequest, HttpServletRequest httpServletRequest) {
        Integer userId = jwtService.getUserId(jwtService.resolveClaims(httpServletRequest));
        log.info("Delete method was started with {} productId and for this userId:{}", deleteFavouriteRequest.getProductId(), userId);
        favouriteRepository.deleteByUserIdAndProductIdAndProductType(userId, deleteFavouriteRequest.getProductId(), deleteFavouriteRequest.getProductType());
        return "Deleted successfully from favourites";
    }

    @Override
    public List<FavouriteDetailDto> getFavouriteDetails(HttpServletRequest httpServletRequest) {
        Integer userId = jwtService.getUserId(jwtService.resolveClaims(httpServletRequest));
        log.info("Get all method was started");
        List<Favourite> favourites = favouriteRepository.findByUserId(userId);
        List<FavouriteDetailDto> favouriteDetails = new ArrayList<>();
        if (favourites.isEmpty()) {
            throw new NotFoundException("Favorites are empty");
        }
        for (Favourite favourite : favourites) {
            FavouriteDetailDto favouriteDetailDto = new FavouriteDetailDto();
            favouriteDetailDto.setProductId(favourite.getProductId());
            favouriteDetailDto.setProductType(favourite.getProductType());
            boolean isProcessed = false;
            if (favourite.getProductType() == ProductType.BAG) {
                Bag bag = bagRepository.findById(favourite.getProductId())
                        .orElseThrow(() -> new NotFoundException("This bag not found with id:" + favourite.getProductId()));
                favouriteDetailDto.setCurrency(bag.getCurrency());
                favouriteDetailDto.setPrice(bag.getPrice());
                favouriteDetailDto.setType(bag.getType());
                isProcessed = true;
            }
            if (favourite.getProductType() == ProductType.BOOK) {
                Book book = bookRepository.findById(favourite.getProductId())
                        .orElseThrow(() -> new NotFoundException("This book not found with id:" + favourite.getProductId()));
                favouriteDetailDto.setCurrency(book.getCurrency());
                favouriteDetailDto.setPrice(book.getPrice());
                favouriteDetailDto.setType(book.getName());
                isProcessed = true;
            }
            if (favourite.getProductType() == ProductType.GIFT) {
                Gift gift = giftRepository.findById(favourite.getProductId())
                        .orElseThrow(() -> new NotFoundException("This gift not found with id:" + favourite.getProductId()));
                favouriteDetailDto.setCurrency(gift.getCurrency());
                favouriteDetailDto.setPrice(gift.getPrice());
                favouriteDetailDto.setType(gift.getName());
                isProcessed = true;
            }
            if (favourite.getProductType() == ProductType.MAGAZINE) {
                Magazine magazine = magazineRepository.findById(favourite.getProductId())
                        .orElseThrow(() -> new NotFoundException("This magazine not found with id:" + favourite.getProductId()));
                favouriteDetailDto.setCurrency(magazine.getCurrency());
                favouriteDetailDto.setPrice(magazine.getPrice());
                favouriteDetailDto.setType(magazine.getEditionName());
                isProcessed = true;
            }
            if (isProcessed) {
                favouriteDetails.add(favouriteDetailDto);
            }
        }
        return favouriteDetails;
    }
}