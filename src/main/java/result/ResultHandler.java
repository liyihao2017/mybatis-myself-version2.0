package result;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import config.MyConfiguration;
import config.MyMapperRegistory;
import session.MySqlsession;

/**
 * @author eason.li
 *
 */
public class ResultHandler {
    private final MyConfiguration configuration;
    
    public ResultHandler(MyConfiguration configuration) {
        this.configuration = configuration;
    }
    
    public <T> T handle(PreparedStatement statement, MyMapperRegistory.MapperData mapperData) {
        try {
            Object resultObj = new DefaultObjectFactory().create(mapperData.getType());
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                int i = 0;
                for (Field field : resultObj.getClass().getDeclaredFields()) {
                    setValue(resultObj, field, rs, i);
                }
            }
            
            return (T)resultObj;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void setValue(Object resultObj, Field field, ResultSet rs, int i) {
        try {
            Method method = resultObj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
            method.invoke(resultObj, getResult(field,rs));
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private Object getResult(Field field, ResultSet rs) {
        try {
            Class type = field.getType();
            if (type == Integer.class) {
                return rs.getInt(field.getName());
            }
            
            if (type == String.class) {
                return rs.getString(field.getName());
            }
        } catch(Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null; 
    }
    
    private String upperCapital(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }
    
}
