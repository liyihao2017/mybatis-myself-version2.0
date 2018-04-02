/**
 * The contents of this file are copyright (c) 2017 by medavis GmbH, Karlsruhe, Germany
 */
package test;

import config.MyConfiguration;
import entity.Test;
import executor.SimpleExecutor;
import mapper.TestMapper;
import session.MySqlsession;

/**
 * @author eason.li
 *
 */
public class Entry {
    public static void main(String[] args) {
        
        MyConfiguration configuration = new MyConfiguration();
        MySqlsession session = new MySqlsession(configuration
                , new SimpleExecutor(configuration));
        
        TestMapper mapper = session.getMapper(TestMapper.class);
        Test test = mapper.selectByPrimaryKey(1);
        System.out.println(test.getId()+ "--------------" + test.getName());
    }
}
