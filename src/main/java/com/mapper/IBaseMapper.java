package com.mapper;

public interface IBaseMapper extends Mapper {
    public void insertBook();
    public int selectBook();
    public String selectBookWithCondition(int i,String s);
}
