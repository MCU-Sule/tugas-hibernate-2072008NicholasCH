package com.example.pt08_2072008.dao;

import java.util.List;

public interface interfaceDao<T> {
    List<T> getData();
    int addData(T data);
    int upData(T data);
    int delData(T data);
}
