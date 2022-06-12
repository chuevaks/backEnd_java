package lesson6;

import lesson6.db.dao.ProductsMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DbConnectionUtils {

    public static SqlSession createConnection() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        return sqlSession;
    }

    public static void deleteProductById(long id) throws IOException {
        var session = createConnection();
        var productsMapper = session.getMapper(ProductsMapper.class);
        productsMapper.deleteByPrimaryKey(id);
        session.commit();
        session.close();
    }
}
