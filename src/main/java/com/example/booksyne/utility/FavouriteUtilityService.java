package com.example.booksyne.utility;

import com.example.booksyne.dao.entity.Favourite;
import com.example.booksyne.dao.entity.User;
import com.example.booksyne.dao.repository.FavouriteRepository;
import com.example.booksyne.dao.repository.UserRepository;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.exception.child.AlreadyExistsException;
import com.example.booksyne.model.exception.child.NotFoundException;
import com.example.booksyne.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavouriteUtilityService {
    private final FavouriteRepository favouriteRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public void checkAndAddToFavourite(Integer productId, FavouriteRequestDto favouriteRequestDto, HttpServletRequest httpServletRequest) {
        Integer userId = jwtService.getUserId(jwtService.resolveClaims(httpServletRequest));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        Optional<Favourite> existingFavourite = favouriteRepository
                .findByUserIdAndProductIdAndProductType(userId, productId,favouriteRequestDto.getProductType());
        if (existingFavourite.isPresent()) {
            throw new AlreadyExistsException("This product already exists in favourites");
        }
    }
}