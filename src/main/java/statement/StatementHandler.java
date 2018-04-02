package statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.MyConfiguration;
import config.MyMapperRegistory;
import result.ResultHandler;
import session.MySqlsession;

/**
 * @author eason.li
 *
 */
public class StatementHandler {
    private final MyConfiguration configuration;
    private final ResultHandler resultHandler;
    
    public StatementHandler(MyConfiguration configuration) {
        this.configuration = configuration;
        resultHandler = new ResultHandler(configuration);
    }
    
    public <T> T query(MyMapperRegistory.MapperData mapperData, Object parameter) {
        try {
            Connection conn = getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(String.format(mapperData.getSql(), Integer.parseInt(String.valueOf(parameter))));
            preparedStatement.execute();
            return resultHandler.handle(preparedStatement, mapperData);
            
        }  catch(Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    private Connection getConn() {
        Connection connection = null;
        try {
            //1.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获得连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "920905");
        } catch(Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
}
