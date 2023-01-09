package test;

import com.pw.entity.Blog;
import com.pw.entity.BlogRecord;
import com.pw.service.BlogLabelService;
import com.pw.service.BlogRecordService;
import com.pw.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //启动Spring容器
@ContextConfiguration(locations = {"classpath:applicationContext_mapper.xml",
        "classpath:applicationContext_service.xml"})
@ComponentScan(basePackages = {"com.pw.service"})
public class BlogRecordTest {
    @Autowired
    BlogRecordService blogRecordService;
    @Test
    public void testGetBlogRecord() {
        List<BlogRecord> list = blogRecordService.getBlogRecord("6",0);
        list.forEach(blog -> {
            System.out.println("用户博客-->"+blog);
        });
    }

    @Test
    public void testCreateBlogRecord(){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月和小时的格式为两个大写字母
        java.util.Date date = new Date();//获得当前时间
        String dateTime = df.format(date);//将当前时间转换成特定格式的时间字符串，这样便可以插入到数据库中
        BlogRecord blogRecord = new BlogRecord( "1","maven编辑器研究23123","修改","",dateTime,"6",null);
        int result = blogRecordService.createBlogRecord(blogRecord);
        System.out.println("添加博客-->"+result);
    }

    @Test
    public void testDeleteBlogById(){
        int result = blogRecordService.deleteBlogRecord("");
        System.out.println("删除博客结果-->"+result);
    }

}

