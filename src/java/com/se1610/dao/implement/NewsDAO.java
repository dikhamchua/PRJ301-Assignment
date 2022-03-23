package com.se1610.dao.implement;

import com.se1610.model.Category;
import com.se1610.model.News;
import java.util.List;
import com.se1610.dao.INewsDAO;
import com.se1610.mapper.NewMapper;

public class NewsDAO extends AbstractDAO<News> implements INewsDAO {

    /**
     * this function use to find a list of news by categoryID
     *
     * @param categoryID
     * @return a list of news
     */
    @Override
    public List<News> findByCategoryID(int categoryID) {
        String sql = "select * from news\n"
                + "where category_id = ?";
        return query(sql, new NewMapper(), categoryID);
    }

    /**
     * this function use to
     *
     * @return
     */
    @Override
    public List<Category> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * insert a news into database
     *
     * @param news
     * @return
     */
    @Override
    public int save(News news) {

        String sql = "insert into [news](title, content,category_id)\n"
                + "values(? ,? ,?)";
        return insert(sql, news.getTitle(), news.getContent(), news.getCategoryID());
    }

    @Override
    public News findOne(int id) {
        String sql = "select * from news\n"
                + "where id = ?";
        List<News> news = query(sql, new NewMapper(), id);
        return news.isEmpty() ? null : news.get(0);
    }

    @Override
    public void update(News updateNews) {

        String sql = "UPDATE news SET title = ?, thumbnail = ?, "
                + "short_description = ?, content = ?, category_id = ?,"
                + "create_date = ?, created_by = ? WHERE id = ?";
        update(sql, updateNews.getTitle(),
                updateNews.getThumbnail(),
                updateNews.getShortDescription(),
                updateNews.getContent(),
                updateNews.getCategoryID(),
                updateNews.getCreateDate(),
                updateNews.getCreateBy(),
                updateNews.getId());

    }

}
