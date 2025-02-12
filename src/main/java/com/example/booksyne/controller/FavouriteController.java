package com.example.booksyne.controller;

import com.example.booksyne.model.dto.request.DeleteFavouriteRequest;
import com.example.booksyne.model.dto.request.FavouriteRequestDto;
import com.example.booksyne.model.dto.response.FavouriteDetailDto;
import com.example.booksyne.service.FavouriteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/favourites")
@RestController
@RequiredArgsConstructor
public class FavouriteController {
    private final FavouriteService favouriteService;

    @Operation(summary = "Add a product to the user's favourites list")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{productId}")
    public ResponseEntity<String> add(@PathVariable Integer productId, @RequestBody FavouriteRequestDto favouriteRequestDto, HttpServletRequest httpServletRequest) {
        favouriteService.add(productId, favouriteRequestDto, httpServletRequest);
        return ResponseEntity.ok("Successfully add to favourite");
    }

    @Operation(summary = "Delete a product from the user's favourites list")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestBody DeleteFavouriteRequest deleteFavouriteRequest, HttpServletRequest httpServletRequest) {
        favouriteService.delete(deleteFavouriteRequest, httpServletRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Retrieve all favourite products for the logged-in user")
    @GetMapping()
    public ResponseEntity<List<FavouriteDetailDto>> getAll(HttpServletRequest httpServletRequest) {
        List<FavouriteDetailDto> favouriteDetailDto = favouriteService.getFavouriteDetails(httpServletRequest);
        return ResponseEntity.ok(favouriteDetailDto);
    }
}