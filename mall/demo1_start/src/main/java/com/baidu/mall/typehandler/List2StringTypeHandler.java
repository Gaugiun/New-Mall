package com.baidu.mall.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MappedTypes(List.class)
public class List2StringTypeHandler implements TypeHandler<List> {
    /**
     * 向数据库传值 转换为String类型
     * @param preparedStatement
     * @param i
     * @param list
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List list, JdbcType jdbcType) throws SQLException {
//存入的过程
        //使用json 是方便转换过程
        ObjectMapper objectMapper = new ObjectMapper();
        //这是存入过程
        String s = null;
        try {
            s = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        preparedStatement.setString(i,s);
    }

    /**
     * 从数据库中取值 转换为List类型
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public List<String> getResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        return transfer(string);
    }

    @Override
    public List<String> getResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        return transfer(string);
    }

    @Override
    public List<String> getResult(CallableStatement callableStatement, int i) throws SQLException {
        String result = callableStatement.getString(i);
        return transfer(result);
    }
    private List<String> transfer(String string) {
        if (string.length() > 2) {
            List<String> list = new ArrayList(Arrays.asList(string.split(",")));
            return list;
        }else {
            List<String>  list = new ArrayList<>();
            list.add("");
            return list;
        }
    }
}
