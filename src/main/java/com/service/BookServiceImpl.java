package com.service;

import com.baseService.BaseServiceImpl;
import com.mapper.BaseMapperImpl;
import com.mapper.IBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl extends BaseServiceImpl implements IBookService {
    @Autowired
    private ChainFactory chainFactory;
    @Autowired
    private IBaseMapper baseMapper;

    public List<String> selectBookMyself() {
        return new ArrayList<String>();
    }
    public int selectBookSupper(){
        /*
        这个方法是从BaseServiceImpl继承来的，
         */
        return this.getBooksFromDatabase();
    }
    public String getString(){
        String s = baseMapper.selectBookWithCondition(1, "hello test");
        if(s.length()>1){
            return s;
        }else
            return "hello";
    }
    public String testChainCall(){
        return chainFactory.getBookService().getString();
    }
    /*
    方法内部新建对象。
     */
    public String getStringNothing(){
        //方法内部创建的对象无法用mockito时行模拟，可以用PowerMockito进行模拟，请百度powerMockito.whenNew()接口
        BaseMapperImpl baseMapperTemp = new BaseMapperImpl();
        if(baseMapperTemp.selectBook()==32||"world".equals(baseMapperTemp.selectBookWithCondition(12,"hello"))){
            System.out.println("这段代码永远不会被执行");
        }
        return baseMapper.selectBookWithCondition(1, "hello test");
    }
}
