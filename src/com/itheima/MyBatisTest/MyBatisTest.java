package com.itheima.MyBatisTest;

import com.itheima.domain.User;
import jdk.internal.org.objectweb.asm.tree.analysis.SourceInterpreter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.SoundbankResource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


public class MyBatisTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream inputStream = Resources.getResourceAsStream("SqlMapCOnfig.xml");
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

    }

    @Test
    public void testQueryUserById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("queryUserById", 10);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void testQueryUsernameLike() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> list = sqlSession.selectList("queryUsernameLike", "%王%");
        System.out.println(list);
        sqlSession.close();

    }

    //在配置文件中写好%
    @Test
    public void testQueryUsernameLike02() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> list = sqlSession.selectList("queryUsernameLike02", "明");
        System.out.println(list);
        sqlSession.close();

    }

    //添加用户insert语句测试
    @Test
    public void testSaveUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("小红帽2");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("北京紫禁城");
        sqlSession.insert("saveUser", user);

        System.out.println(user);
        //注意一定要提交事务,否则没有数据
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testUpdate(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(28);
        user.setUsername("关羽");
        user.setSex("1");
        user.setBirthday(new Date());
        user.setAddress("蜀国");

        sqlSession.update("updateByUserId",user);
        sqlSession.commit();
        System.out.println(user);
        sqlSession.close();

    }


    @Test
    public void testDelete(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("deleteByUser",26);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("搞定.......");

    }

}
