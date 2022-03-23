/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1610.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.module.sitemesh.tapestry.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PHAM KHAC VINH
 */
public class Utility {

    private String value;

    /**
     * this function is use to convert the "value" to the model
     *
     * @param <T>
     * @param tClass
     * @return
     */
    public <T> T toModel(Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(value, tClass);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    /**
     * constructor
     *
     * @param valueString
     */
    public Utility(String valueString) {
        this.value = valueString;
    }

    /**
     * this function use to convert value from json to string
     *
     * @param reader
     * @return null or the value was converted
     */
    public static Utility Of(BufferedReader reader) {
        try {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            return new Utility(sb.toString());
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
