package com.mapper;


public interface Mapper<T> {
    public void insertBook();
    public int selectBook();
    public String selectBookWithCondition(int i,String s);
}
