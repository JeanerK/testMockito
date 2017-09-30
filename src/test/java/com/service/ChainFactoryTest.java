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
        //匹配参数进行同一个方法根据参数不同返回不同的值,第一个参数我们用参数匹配器any()，即程序执行时，接收到什么参数我都认。
        //第二个参数因为我们需要根据参数的不同控制不同的返回值。
        // any()匹配器不能和实际参数混用所以我们用eq("hello")把实际参数参数包起来.
        when(hashOperations.get(any(), eq("hello"))).thenReturn("hello");
        when(hashOperations.get(any(), eq("world"))).thenReturn("world");
        String s = chainFactory.chainCall();
        Assert.assertNotEquals(s,"hello world");


        String sSeq = chainFactory.chainCall();
        Assert.assertNotEquals(sSeq,"hello world");
    }
    @Test
    public void testChainCallHelloSeq() throws Exception {
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        //第二种方式解决一个方法在一次调用执行两次返回不同值，这种方式是通过方法的调用顺序来控件一个方法两次调用时返回不两只的值
        //第一次执行返回“hello"，第二次执行返回“world”
        when(hashOperations.get(any(), any())).thenReturn("hello").thenReturn("world");

        String sSeq = chainFactory.chainCall();
        Assert.assertNotEquals(sSeq,"hello world");
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
