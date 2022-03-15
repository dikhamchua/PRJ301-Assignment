/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1610.dao;

import com.se1610.mapper.RowMapper;
import java.util.List;

/**
 *
 * @author PHAM KHAC VINH
 */
public interface GenericDAO<T> {
    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters );
    
    void update(String sql, Object... parameters);
    
    int insert(String sql, Object... parameters);
}
