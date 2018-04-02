package session;



import config.MyConfiguration;
import config.MyMapperRegistory;
import executor.Executor;

/**
 * @author eason.li
 *
 */
public class MySqlsession {
    private MyConfiguration configuration;
    private Executor executor;
    
    public MySqlsession(MyConfiguration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }
    
    public <T> T getMapper(Class<T> clazz) {
        return configuration.getMapper(clazz,this);
    }
    
    public <T> T selectOne(MyMapperRegistory.MapperData mapperData, Object parameter) {
        return executor.query(mapperData,parameter);
    }
    
    public MyConfiguration getConfiguration() {
           return configuration;
    }
}
