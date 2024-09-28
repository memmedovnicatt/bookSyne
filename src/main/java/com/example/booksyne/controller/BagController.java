package com.example.booksyne.controller;

import com.example.booksyne.dao.entity.Bag;
import com.example.booksyne.model.dto.request.BagRequestDto;
import com.example.booksyne.model.dto.response.BagInfoResponse;
import com.example.booksyne.model.dto.response.BagResponse;
import com.example.booksyne.model.exception.child.AuthorNotFoundException;
import com.example.booksyne.model.exception.child.BagNotFoundException;
import com.example.booksyne.servis.BagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bags")
@RequiredArgsConstructor
public class BagController {
    private final BagService bagService;

    @GetMapping("/{id}")
    public ResponseEntity<BagInfoResponse> getById(@PathVariable Integer id) {
        BagInfoResponse bagResponse=bagService.getById(id);
        return ResponseEntity.ok(bagResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<BagResponse> getByName(@RequestParam String name) {
        BagResponse bagResponse=bagService.getByName(name);
        return ResponseEntity.ok(bagResponse);
    }
 
    @GetMapping
    public ResponseEntity<Page<BagResponse>> getAll(Pageable pageable) {
        Page<BagResponse> bagInfoPage = bagService.getAll(pageable);
        return ResponseEntity.ok(bagInfoPage);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<BagResponse> add(@RequestBody BagRequestDto bagRequestDto) {
        BagResponse newBag=bagService.add(bagRequestDto);
        return ResponseEntity.ok(newBag);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bagService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody BagRequestDto bagRequestDto,@PathVariable Integer id) {
         bagService.update(bagRequestDto,id);
         return ResponseEntity.ok().build();
    }
    @ExceptionHandler(BagNotFoundException.class)
    public ResponseEntity<String> handleAuthorNotFoundException(BagNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}