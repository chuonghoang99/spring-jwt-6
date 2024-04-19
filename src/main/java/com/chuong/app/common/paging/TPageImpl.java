package com.chuong.app.common.paging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class TPageImpl<T> implements TPage<T>, Page<T> {
    private final Page<T> delegate;

    public TPageImpl(Page<T> delegate) {
        this.delegate = delegate;
    }

    @JsonProperty(
            index = 0
    )
    public List<T> getContent() {
        return this.delegate.getContent();
    }

    @JsonProperty(
            index = 1
    )
    public int getPage() {
        return this.delegate.getNumber();
    }

    @JsonProperty(
            index = 2
    )
    public int getSize() {
        return this.delegate.getSize();
    }

    @JsonProperty(
            value = "sort",
            index = 3
    )
    public String getTSort() {
        return this.delegate.getSort().toString();
    }

    @JsonProperty(
            index = 4
    )
    public long getTotalElements() {
        return this.delegate.getTotalElements();
    }

    @JsonProperty(
            index = 5
    )
    public int getTotalPages() {
        return this.delegate.getTotalPages();
    }

    @JsonProperty(
            index = 6
    )
    public int getNumberOfElements() {
        return this.delegate.getNumberOfElements();
    }

    @JsonIgnore
    public Sort getSort() {
        return this.delegate.getSort();
    }

    @JsonIgnore
    public boolean hasContent() {
        return this.delegate.hasContent();
    }

    @JsonIgnore
    public Pageable getPageable() {
        return this.delegate.getPageable();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @JsonIgnore
    public int getNumber() {
        return this.delegate.getNumber();
    }

    @JsonIgnore
    public boolean isFirst() {
        return this.delegate.isFirst();
    }

    @JsonIgnore
    public boolean isLast() {
        return this.delegate.isLast();
    }

    @JsonIgnore
    public boolean hasNext() {
        return this.delegate.hasNext();
    }

    @JsonIgnore
    public boolean hasPrevious() {
        return this.delegate.hasPrevious();
    }

    @JsonIgnore
    public Pageable nextPageable() {
        return this.delegate.nextPageable();
    }

    @JsonIgnore
    public Pageable previousPageable() {
        return this.delegate.previousPageable();
    }

    @JsonIgnore
    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        return this.delegate.map(converter);
    }

    @JsonIgnore
    public Iterator<T> iterator() {
        return this.delegate.iterator();
    }
}