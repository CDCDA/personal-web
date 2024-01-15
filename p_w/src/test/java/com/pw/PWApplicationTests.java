package com.pw;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pw.domain.User;
import com.pw.mapper.BlogMapper;
import com.pw.mapper.BlogTagMapper;
import com.pw.mapper.UserMapper;
import com.pw.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class PWApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Autowired
    private BlogMapper blogMapper;


    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        //这里的参数是一个Wrapper,条件构造器，这里我们先不用，将其设为null
        //查询全部用户
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    void insertTest() {
//        User user = new User(null, "jojo");
//        userMapper.insert(user);
    }

    //测试批量查询！
    @Test
    public void testSelectByBatchId() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2));
        users.forEach(System.out::println);
    }

    //按条件查询之一使用 map 操作
    @Test
    public void testSelectByBatchIds() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
//        User user = new User(null, "jo");
////        HashMap<String, Object> map = new HashMap<>();
//        //自定义要查询
////        map.put("name", "jojo");
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "jo");
//        map.put("id","");
//        List<User> users = userMapper.selectList(mapToWrap(map));
//        users.forEach(System.out::println);
    }

    //分页测试
    @Test
    public void testPage() {
        System.out.println("----- selectPage method test ------");
        //分页参数
        Page<User> page = Page.of(1, 2);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
    }

    @Test
    public void testExit() {
//        User user = new User(null, "jo");
//        User ResultUser =  userService.getOne(convertEqWrap(user));
    }

    @Test
    public void Blog() {
//        Blog blog = new Blog();
//        blog.setPageNum(2);
//        blog.setPageSize(2);
//       List<Blog> blogList = blogMapper.list(blog);
    }

    @Test
    public void updateUser() {
        User user = new User();
//        user.setUserId("26f88bbc356a4c408e07b7dcfa126d0d");
        user.setNickName("SDDDD");
        userMapper.updateById(user);
    }
}
