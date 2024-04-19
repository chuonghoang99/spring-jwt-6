package com.chuong.app.common.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.chuong.app.common.paging.TPage;

import java.util.List;

public class PageResponse<T> implements TPage<T> {
    private List<T> content;
    private int page;
    private int size;
    private String sort;
    private long totalElements;
    private int totalPages;
    private int numberOfElements;

    public PageResponse() {
    }

    @JsonProperty(
            index = 0
    )
    public List<T> getContent() {
        return this.content;
    }

    public void setContent(final List<T> content) {
        this.content = content;
    }

    @JsonProperty(
            index = 1
    )
    public int getPage() {
        return this.page;
    }

    public void setPage(final int page) {
        this.page = page;
    }

    @JsonProperty(
            index = 2
    )
    public int getSize() {
        return this.size;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    @JsonProperty(
            value = "sort",
            index = 3
    )
    public String getTSort() {
        return this.sort;
    }

    @JsonProperty(
            index = 4
    )
    public long getTotalElements() {
        return this.totalElements;
    }

    public void setTotalElements(final long totalElements) {
        this.totalElements = totalElements;
    }

    @JsonProperty(
            index = 5
    )
    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }

    @JsonProperty(
            index = 6
    )
    public int getNumberOfElements() {
        return this.numberOfElements;
    }

    public void setNumberOfElements(final int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void setSort(final String sort) {
        this.sort = sort;
    }
}
