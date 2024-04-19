package com.chuong.app.common.paging;

import java.util.List;

public interface TPage<T> {
    List<T> getContent();

    int getPage();

    int getSize();

    String getTSort();

    long getTotalElements();

    int getTotalPages();

    int getNumberOfElements();
}

