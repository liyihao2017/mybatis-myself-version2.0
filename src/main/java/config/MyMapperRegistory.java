
package config;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import entity.Test;

/**
 * @author eason.li
 *
 */
public class MyMapperRegistory {
    public static final String namespace = "mapper.TestMapper";
    public static final String methodFullName = "selectByPrimaryKey";
    private Map<Object,Object> registory = new HashMap<>();
    
    public MyMapperRegistory() {
        String[] annotation = parseAnnotation();
        registory.put(methodFullName, new MapperData(annotation[0],Test.class));
    }
    
    private String[] parseAnnotation() {
        try {
            Class clazz = Class.forName(namespace);
            for(Method method : clazz.getMethods()) {
                if (methodFullName.equals(method.getName())) {
                    return method.getAnnotation(Select.class).value();
                }
            }
            return null;
            
        } catch(Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public MapperData get(String namespace) {
        return (MapperData)registory.get(namespace);
    }
    
    public class MapperData<T> {
        private String sql;
        private Class<T> type;
        
        public MapperData(String sql, Class<T> type) {
            this.sql = sql;
            this.type = type;
        }

        
        public String getSql() {
            return sql;
        }

        
        public void setSql(String sql) {
            this.sql = sql;
        }

        
        public Class<T> getType() {
            return type;
        }

        
        public void setType(Class<T> type) {
            this.type = type;
        }
        
        
    }
}
