package com.itheima.mapper.impl;

import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.SoundbankResource;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserMapperTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws Exception {
        // 创建SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 加载SqlMapConfig.xml配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlsessionFactory
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    //以接口创建动态代理对象的方式执行查询
    @Test
    public void testQueryByUserId(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 从sqlSession中获取Mapper接口的代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //使用mapper代理对象执行查询
        User user = mapper.queryUserById(28);
        System.out.println(user);
        sqlSession.close();
    }


    @Test
    public void testQueryUserByUsername() {
        // 获取sqlSession，和spring整合后由spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();

        // 从sqlSession中获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.queryUserByUsername("张");
        System.out.println(users);
        sqlSession.close();

    }



    @Test
    public void testSaveUser() {
        // 获取sqlSession，和spring整合后由spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();

        // 从sqlSession中获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("刘备");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("蜀国");

        userMapper.saveUser(user);

        sqlSession.commit();
        sqlSession.close();
        //此时的user会返回插入后的user,因为配置了selectKey标签
        System.out.println(user);
    }


}
