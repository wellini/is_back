package com.nonfallable.taskKnight.rest.dto;

import java.util.List;

public class PageDTO<T> {

    private List<T> data;

    private PageableDTO page;

    public PageDTO() {
    }

    public PageDTO(List<T> data, int size, int number, long totalElements, long totalPages) {
        this.data = data;
        this.page = new PageableDTO().setSize(size)
                .setNumber(number)
                .setTotalElements(totalElements)
                .setTotalPages(totalPages);
    }

    class PageableDTO {

        private int number;
        private int size;
        private long totalElements;
        private long totalPages;

        public int getNumber() {
            return number;
        }

        public PageableDTO setNumber(int number) {
            this.number = number;
            return this;
        }

        public int getSize() {
            return size;
        }

        public PageableDTO setSize(int size) {
            this.size = size;
            return this;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public PageableDTO setTotalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public long getTotalPages() {
            return totalPages;
        }

        public PageableDTO setTotalPages(long totalPages) {
            this.totalPages = totalPages;
            return this;
        }
    }

    public List<T> getData() {
        return data;
    }

    public PageDTO<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    public PageableDTO getPage() {
        return page;
    }

    public PageDTO<T> setPage(PageableDTO page) {
        this.page = page;
        return this;
    }
}
