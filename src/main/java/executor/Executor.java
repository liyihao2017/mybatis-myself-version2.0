package executor;

import config.MyMapperRegistory;

/**
 * @author eason.li
 *
 */
public interface Executor {
    <T> T query(MyMapperRegistory.MapperData mapperData, Object parameter);
}
