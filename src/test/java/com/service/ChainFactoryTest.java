package com.service;

import com.baseService.IBaseService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.mockito.*;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * ChainFactory Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>09/20/2017</pre>
 */
public class ChainFactoryTest {
    @InjectMocks
    private ChainFactory chainFactory;//测试小三步：第一步：@InjectMocks将被测试类注入测试类。

    @Mock
    private IBaseService baseService;
    @Mock
    private RedisTemplate redisTemplate;//测试小三步：第二步：@Mock模拟被测试类的依赖,
    // 通俗来讲，就是把被测试类里面@Autowired注入的bean复制到测试类，将其以@Mock注解。
    //本例中：chainFactory为被测试类，baseService,redisTemplate为被测试类的依赖。
    @Mock
    private HashOperations hashOperations;
    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);//测试小三步：
        // 第三步：初始化这个测试类，在@Before注解的方法里面添加上面这句代码就可以了。
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getBookService()
     */
    @Test
    public void testGetBookService() throws Exception {
    }

    /**
     * Method: chainCall()
     */
    @Test
    public void testChainCallHello() throws Exception {
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        //匹配参数进行同一个方法根据参数不同返回不同的值
        when(hashOperations.get(any(), eq("hello"))).thenReturn("hello");
        when(hashOperations.get(any(), eq("world"))).thenReturn("world");
        String s = chainFactory.chainCall();
        Assert.assertNotEquals(s,"hello world");
    }

    @Test
    public void testChainCallHelloWorldSeq() throws Exception {
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        //根据一次执行过程中方法出现的顺序返回不同的值
        when(hashOperations.get(any(), any())).thenReturn("hello").thenReturn("world");
        String s = chainFactory.chainCall();
        Assert.assertNotEquals(s,"hello world");
    }

} 
