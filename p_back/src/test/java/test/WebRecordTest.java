package test;

import com.pw.entity.WebRecord;
import com.pw.service.WebRecordService;
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
public class WebRecordTest {
    @Autowired
    WebRecordService webRecordService;
    @Test
    public void testGetWebRecord() {
        List<WebRecord> list = webRecordService.getWebRecord(0,10);
        list.forEach(blog -> {
            System.out.println("网站修改记录-->"+blog);
        });
    }

    @Test
    public void testCreateWebRecord(){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月和小时的格式为两个大写字母
        java.util.Date date = new Date();//获得当前时间
        String dateTime = df.format(date);//将当前时间转换成特定格式的时间字符串，这样便可以插入到数据库中
        WebRecord webRecord = new WebRecord( null,"修改",dateTime,"SSAAAAA");
        int result = webRecordService.createWebRecord(webRecord);
        System.out.println("添加网站修改记录-->"+result);
    }

    @Test
    public void testDeleteWebRecord(){
        int result = webRecordService.deleteWebRecord("2");
        System.out.println("删除网站修改记录结果-->"+result);
    }

    @Test
    public void testUpdateWebRecord(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月和小时的格式为两个大写字母
        java.util.Date date = new Date();//获得当前时间
        String dateTime = df.format(date);//将当前时间转换成特定格式的时间字符串，这样便可以插入到数据库中
        WebRecord webRecord = new WebRecord( "1","新增111111",dateTime,"SSSSS11111");
        int result = webRecordService.updateWebRecord(webRecord);
        System.out.println("修改网站修改记录结果-->"+result);
    }

}

