package com.example.SimplestCRUDExample.controller;

import com.example.SimplestCRUDExample.model.Book;
import com.example.SimplestCRUDExample.repo.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        book1 = new Book(1L, "The Hobbit", "J.R.R. Tolkien");
        book2 = new Book(2L, "1984", "George Orwell");
    }

    @Test
    void testGetAllBooks_Success() throws Exception {
        List<Book> books = new ArrayList<>(Arrays.asList(book1, book2));

        Mockito.when(bookRepository.findAll()).thenReturn(books);

        mockMvc.perform(get("/api/getAllBooks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("The Hobbit"));
    }

    @Test
    void testGetAllBooks_Empty() throws Exception {
        Mockito.when(bookRepository.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/getAllBooks"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetBookById_Found() throws Exception {
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));

        mockMvc.perform(get("/api/getBookById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Hobbit"));
    }

    @Test
    void testGetBookById_NotFound() throws Exception {
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/getBookById/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddBook_Success() throws Exception {
        Mockito.when(bookRepository.save(any(Book.class))).thenReturn(book1);

        mockMvc.perform(post("/api/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("The Hobbit"));
    }

    @Test
    void testUpdateBook_Success() throws Exception {
        Book updatedDetails = new Book(1L, "New Title", "New Author");
        
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        Mockito.when(bookRepository.save(any(Book.class))).thenReturn(updatedDetails);

        mockMvc.perform(post("/api/updateBook/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDetails)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Title"));
    }

    @Test
    void testUpdateBook_NotFound() throws Exception {
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/updateBook/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book1)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteBookById_Success() throws Exception {
        Mockito.doNothing().when(bookRepository).deleteById(1L);

        mockMvc.perform(delete("/api/deleteBookById/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAllBooks_Success() throws Exception {
        Mockito.doNothing().when(bookRepository).deleteAll();

        mockMvc.perform(delete("/api/deleteAllBooks"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetAllBooks_InternalServerError() throws Exception {
        Mockito.when(bookRepository.findAll()).thenThrow(new RuntimeException("Database down"));

        mockMvc.perform(get("/api/getAllBooks"))
                .andExpect(status().isInternalServerError());
    }
}
