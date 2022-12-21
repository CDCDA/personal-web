package test;


import com.pw.entity.User;
import com.pw.mapper.UserMapper;
import com.pw.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //启动Spring容器
@ContextConfiguration(locations = {"classpath:applicationContext_mapper.xml",
        "classpath:applicationContext_service.xml"})
public class MyTest {

    @Autowired
    UserService userService;

    @Test
    public void testSelectUserPage(){
        List<User> list = userService.selectUserPage(null,null,2);
        list.forEach(user->{
            System.out.println(user);
        });
    }
    @Test
    public void testDeleteUserById(){
        int list = userService.deleteUserById(2);
        System.out.println(list);
    }

    @Test
    public void testLogin(){
        int list = userService.login("Tom","1");
        System.out.println(list);
    }
}
