package com.example.booksyne.servis.impl;

import com.example.booksyne.dao.entity.Bag;
import com.example.booksyne.dao.repository.BagRepository;
import com.example.booksyne.mapper.BagMapper;
import com.example.booksyne.model.dto.request.BagRequestDto;
import com.example.booksyne.model.dto.response.BagResponse;
import com.example.booksyne.servis.BagService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BagServiceImplTest {
    @InjectMocks
    BagServiceImpl bagService;

    @Mock
    BagRepository bagRepository;

    @Mock
    private BagMapper bagMapper;




    private Bag bag;
    private  BagResponse bagResponse;


    @BeforeEach
    void setUp() {

        bag = new Bag();
        bag.setId(1);
        bag.setType("Handbag");
        bag.setPrice(12.0);

        bagResponse=new BagResponse();
        bagResponse.setId(1);
        bagResponse.setType("Handbag");
        bagResponse.setPrice(12.0);




    }

    @AfterEach
    void tearDown() {
        bag=null;
    }

    @Test
    void givenIdThenReturnThenOk() {
        //arrange
        when(bagRepository.findById(anyInt())).thenReturn(Optional.of(bag));
        when(bagMapper.toBagResponse(bag)).thenReturn(bagResponse);

        //act
        BagResponse bagRes=bagService.getById(1);

        //assert
        assertThat(bagRes.getType()).isEqualTo("Handbag");

        //verify
        verify(bagRepository,times(1)).findById(1);
        verify(bagMapper,times(1)).toBagResponse(bag);

    }
}