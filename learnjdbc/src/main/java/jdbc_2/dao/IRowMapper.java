package jdbc_2.dao;

import java.sql.ResultSet;

public interface IRowMapper {

    /**
     * 结果集映射
     * @param rs
     */
    public Object mapRow(ResultSet rs );

}
