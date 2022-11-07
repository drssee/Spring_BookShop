package com.example.common.paging;

public interface PageSetable {
    void setPage(int page);
    void setSize(int size);
    int getPage();
    int getSize();
}
