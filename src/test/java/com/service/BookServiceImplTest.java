package com.service;

import com.mapper.IBaseMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

/**
 * BookServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>¾ÅÔÂ 19, 2017</pre>
 */
public class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private ChainFactory chainFactory;
    @Mock
    private IBaseMapper baseMapper;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: selectBookMyself()
     */
    @Test
    public void testSelectBookMyself() throws Exception {
        bookService.selectBookMyself();
    }

    /**
     * Method: selectBookSupper()
     */
    @Test
    public void testSelectBookSupper() throws Exception {

        bookService.selectBookSupper();
    }

    /**
     * Method: getString()
     */
    @Test
    public void testGetString() throws Exception {
        when(baseMapper.selectBookWithCondition(1, "hello test")).thenReturn("");
        String s = bookService.getString();
        Assert.assertEquals(5,s.length());

        when(baseMapper.selectBookWithCondition(1, "hello test")).thenReturn("helloWorld!");
        String s1 = bookService.getString();
        Assert.assertEquals(11,s1.length());
    }

    /**
     * Method: getStringNothing()
     */
    @Test
    public void testGetStringNothing() throws Exception {
        bookService.getStringNothing();
    }

    /**
     * Method: testChainCall()
     */
    @Test
    public void testTestChainCall() throws Exception {
        bookService.testChainCall();
    }


} 
