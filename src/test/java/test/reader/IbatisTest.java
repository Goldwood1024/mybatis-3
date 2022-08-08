package test.reader;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import test.reader.model.Demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangwei
 * @since 2022/8/8
 */
public class IbatisTest {
  public Configuration getConfiguration() {
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUser("root");
    dataSource.setPassword("Root123.");
    Environment environment = new Environment("1", new JdbcTransactionFactory(), dataSource);
    Configuration configuration = new Configuration();
    configuration.setDefaultExecutorType(ExecutorType.SIMPLE);
    configuration.setEnvironment(environment);
    configuration.setDefaultFetchSize(50);
    return configuration;
  }

  @Test
  public void testQuery() {
    String id = "testQuery";
    Configuration configuration = getConfiguration();

    ResultMap resultMap = new ResultMap.Builder(configuration, id, Demo.class, new ArrayList<>()).build();

    SqlSource sqlSource = new StaticSqlSource(configuration, "select * from local_mock.demo");
    MappedStatement statement = new MappedStatement
      .Builder(configuration, id, sqlSource, SqlCommandType.SELECT)
      .useCache(false)
      .resultMaps(Lists.newArrayList(resultMap))
      .build();
    configuration.addMappedStatement(statement);

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    SqlSession sqlSession = sqlSessionFactory.openSession();

    List<Demo> list = sqlSession.selectList(id);
    System.out.println(list);
    sqlSession.close();
  }
}
