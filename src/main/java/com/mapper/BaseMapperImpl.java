package com.mapper;

public class BaseMapperImpl implements IBaseMapper {
    public void insertBook() {
    }

    public int selectBook() {
        return 0;
    }

    public String selectBookWithCondition(int i, String s) {
        if(i<10){
            return "number less than 10";
        }
        else return s;
    }
}
