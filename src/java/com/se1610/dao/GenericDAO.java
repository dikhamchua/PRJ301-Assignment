/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1610.dao;

import com.se1610.dto.Parameter;
import com.se1610.mapper.RowMapper;
import java.util.List;

/**
 *
 * @author PHAM KHAC VINH
 */
public interface GenericDAO<T> {
    <T> List<T> query(String sql, RowMapper<T> rowMapper, Parameter... parameters );
    
    void update(String sql, Parameter... parameters);
    
    int insert(String sql, Parameter... parameters);
    
    
}
