package test;

import com.pw.entity.Blog;
import com.pw.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class) //启动Spring容器
@ContextConfiguration(locations = {"classpath:applicationContext_mapper.xml",
        "classpath:applicationContext_service.xml"})
public class BlogTest {
    @Autowired
    BlogService blogService;

    @Test
    public void testGetBlogByUserId() {
        List<Blog> list = blogService.getBlogByUserId(6, 0);
        list.forEach(blog -> {
            System.out.println("用户博客-->"+blog);
        });
    }

    @Test
    public void testGetBlogRowCount() {
        int result = blogService.getBlogRowCount(6);
        System.out.println("用户博客数-->"+result);
    }

    @Test
    public void testGetBlogByBlogId() {
        Blog blog = blogService.getBlogByBlogId(1);
        System.out.println("指定博客-->"+blog);
    }

    @Test
    public void testCreateBlog(){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月和小时的格式为两个大写字母
        java.util.Date date = new Date();//获得当前时间
        String dateTime = df.format(date);//将当前时间转换成特定格式的时间字符串，这样便可以插入到数据库中
        Blog blog = new Blog( 6,"maven编辑器研究","asdasd",null,dateTime,dateTime,"前端/vue","vue/js/css");
        int result = blogService.createBlog(blog);
        System.out.println("添加博客-->"+result);
    }

    @Test
    public void testDeleteBlogById(){
        int result = blogService.deleteBlogById(3);
        System.out.println("删除博客结果-->"+result);
    }
}
