/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1610.mapper;

import java.sql.ResultSet;

/**
 *
 * @author PHAM KHAC VINH
 */
public interface RowMapper<T> {
    T mapRow(ResultSet resultSet);
    
}
