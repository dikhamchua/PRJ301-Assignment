package com.se1610.service;

import com.se1610.model.News;
import java.util.List;

public interface INewService {
    List<News> findByCategoryID(int categoryID);

}
