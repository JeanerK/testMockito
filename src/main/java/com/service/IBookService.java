package com.service;

import com.baseService.IBaseService;

import java.util.List;

public interface IBookService extends IBaseService {
    public List<String> selectBookMyself();
}
