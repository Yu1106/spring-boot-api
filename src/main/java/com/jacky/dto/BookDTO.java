package com.jacky.dto;

import com.jacky.domain.Book;
import com.jacky.util.CustomBeanUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String author;
    @NotNull
    private Integer status;
    @Length(max = 20)
    private String description;

    public BookDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 轉換傳輸對象
     *
     * @param book
     */
    public void convertToBook(Book book) {
        new BookConvert().convert(this, book);
    }

    public Book convertToBook() {
        return new BookConvert().convert(this);
    }

    private class BookConvert implements Convert<BookDTO, Book> {

        @Override
        public Book convert(BookDTO bookDTO, Book book) {
            String[] nullPropertyNames = CustomBeanUtils.getNullPropertyNames(bookDTO);
            BeanUtils.copyProperties(bookDTO, book, nullPropertyNames);
            return book;
        }

        @Override
        public Book convert(BookDTO bookDTO) {
            Book book = new Book();
            BeanUtils.copyProperties(bookDTO, book);
            return book;
        }
    }
}
