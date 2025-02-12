package com.example.booksyne.controller;

import com.example.booksyne.dao.repository.PublisherRepository;
import com.example.booksyne.model.dto.request.PublisherRequestDto;
import com.example.booksyne.model.dto.request.PublisherUpdateDto;
import com.example.booksyne.model.dto.response.PublisherResponse;
import com.example.booksyne.model.dto.response.PublisherResponseDetail;
import com.example.booksyne.model.dto.response.PublisherResponseName;
import com.example.booksyne.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
@Slf4j
public class PublisherController {

    private final PublisherService publisherService;

    @Operation(summary = "Adding a new Publisher")
    @PostMapping
    public ResponseEntity<PublisherResponse> add(@RequestBody PublisherRequestDto publisherRequestDto) {
        PublisherResponse publisherResponse = publisherService.add(publisherRequestDto);
        return ResponseEntity.ok(publisherResponse);
    }

    @Operation(summary = "Retrieve a list of publisher names")
    @GetMapping()
    public ResponseEntity<List<PublisherResponseName>> getListName() {
        List<PublisherResponseName> PublisherResponseName = publisherService.getPublisherNames();
        return ResponseEntity.ok(PublisherResponseName);
    }

    @Operation(summary = "Remove publisher data by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        publisherService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Retrieve detailed information on a specific publisher")
    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDetail> getById(@PathVariable Integer id) {
        PublisherResponseDetail publisherResponseDetail = publisherService.getById(id);
        return ResponseEntity.ok(publisherResponseDetail);
    }

    @Operation(summary = "Update an existing publisher")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, PublisherUpdateDto publisherUpdateDto) {
        publisherService.update(id, publisherUpdateDto);
        return ResponseEntity.ok().build();
    }
}