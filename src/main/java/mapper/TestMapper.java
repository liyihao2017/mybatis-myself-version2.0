
package mapper;

import org.apache.ibatis.annotations.Select;

import entity.Test;

/**
 * @author eason.li
 *
 */
public interface TestMapper {
    
    @Select("select * from test.test where id = %d")
    Test selectByPrimaryKey(Integer userId);
}
