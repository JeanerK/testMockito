package com.baseService;

import com.mapper.IBaseMapper;
import com.mapper.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseServiceImpl implements IBaseService {
    @Resource
    public Mapper mapper;

    public int getBooksFromDatabase() {
        return mapper.selectBook();
    }

    public void insertBookToDataBase() {
        mapper.insertBook();
    }
}
