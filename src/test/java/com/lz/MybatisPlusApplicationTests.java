package com.lz;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.lz.entity.Blog;
import com.lz.entity.User;
import com.lz.mapper.BlogMapper;
import com.lz.mapper.UserMapper;
import com.lz.service.BlogService;
import com.lz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
class MybatisPlusApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @Autowired
    BlogService blogService;

    @Test
    void code(){
        AutoGenerator mpg = new AutoGenerator();

        //1.全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("lz");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setServiceName("%sService");
        gc.setIdType(IdType.NONE);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);

        //2.DataSource配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
        dsc.setUsername("root");
        dsc.setPassword("lizhuo21321");

        mpg.setDataSource(dsc);

        //3.包的配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("blog");
        pc.setParent("com.lz");
        pc.setEntity("entity");
        pc.setController("controller");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");

        mpg.setPackageInfo(pc);

        //4.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("blog");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        //逻辑删除
        strategy.setLogicDeleteFieldName("deleted");
        //自动填充配置
        TableFill gmtCreate = new TableFill("gmtCreate", FieldFill.INSERT);
        TableFill gmtModify = new TableFill("gmtModify", FieldFill.INSERT_UPDATE);
        List<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModify);
        strategy.setTableFillList(tableFills);
        //乐观锁
        strategy.setVersionFieldName("version");

        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);//localhost:8080/hello_id_2

        mpg.setStrategy(strategy);

        //exectue
        mpg.execute();

    }

    @Test
    void testService(){
        Blog blog = new Blog();
        blog.setContent("hello");
        blog.setTitle("plmn");
        blogService.save(blog);
    }

    @Autowired
    BlogMapper blogMapper;

    @Test
    void testBlogMapper(){
        Blog blog = new Blog();
        blog.setContent("hello");
        blog.setTitle("plmn");
        blogMapper.insert(blog);
    }

    @Test
    void log(){
        log.info("hello");
        log.debug("debug");
    }

    @Test
    void contextLoads() {
        List<User> users = userService.list();
        Stream<User> stream = users.stream();
        stream.forEach(user -> System.out.println(user));
    }
    @Test
    void insert(){
        User user = new User();
        user.setAge(20);
        user.setEmail("123@qq.com");
        user.setName("plmn");
        userMapper.insert(user);
    }

    @Test
    void update(){
        User user = userMapper.selectById(6l);
        user.setName("lz");
        user.setAge(100);
        userMapper.updateById(user);
    }

    @Test
    void OptLock(){
        User user1 = userMapper.selectById(1l);
        user1.setName("plmn111");

        User user2 = userMapper.selectById(1l);
        user2.setName("plmn222");
        userMapper.updateById(user2);

        userMapper.updateById(user1);

    }

    @Test
    void page(){
        Page<User> userPage = new Page<>(1,5);
        Page<User> pageInfo = userMapper.selectPage(userPage, null);
        pageInfo.getRecords().forEach(System.out::println);
    }

    @Test
    void LogicDelete(){
        userMapper.deleteById(1l);
    }

}

//8