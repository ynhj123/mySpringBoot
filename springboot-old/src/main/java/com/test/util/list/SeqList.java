package com.test.util.list;

import org.apache.poi.ss.formula.functions.T;

/**
 * @className:
 * @Description:
 * @auther:ynhj
 * @date:10:29 2018-11-13
 * @version: ver 1.0
 */
public  interface SeqList <T> {
    Integer length();
    T get(Integer i);
    Integer locate(T data);
    void insert(Integer position,T data);
    void delete(T data);
}
