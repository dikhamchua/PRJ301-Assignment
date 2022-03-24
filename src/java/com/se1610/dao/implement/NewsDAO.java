package com.se1610.dao.implement;

import com.se1610.model.Category;
import com.se1610.model.News;
import java.util.List;
import com.se1610.dao.INewsDAO;
import com.se1610.dto.Parameter;
import com.se1610.mapper.NewMapper;
import java.sql.Types;

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
        return query(sql, new NewMapper(), new Parameter(categoryID, Types.INTEGER));
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

        //String sql = "insert into [news](title, content,category_id)\n"
          //      + "values(? ,? ,?)";
         StringBuilder sql = new StringBuilder("insert into news(title, content,");
         sql.append(" thumbnail, shortDescription, categoryID, createDate, createBy) ");
         sql.append(" values (?, ?, ?, ?, ?, ?, ? )");
          
        return insert(sql.toString(),
                new Parameter(news.getTitle(), Types.VARCHAR),
                new Parameter(news.getContent(), Types.LONGNVARCHAR),
                new Parameter(news.getThumbnail(), Types.VARCHAR), 
                new Parameter(news.getShortDescription(), Types.LONGNVARCHAR),
                new Parameter(news.getCategoryID(), Types.INTEGER), 
                new Parameter(news.getCreateDate(), Types.DATE), 
                new Parameter(news.getCreateBy(), Types.VARCHAR));
    }

    @Override
    public News findOne(int id) {
        String sql = "select * from news\n"
                + "where id = ?";
        List<News> news = query(sql, new NewMapper(), new Parameter(id, Types.INTEGER));
        return news.isEmpty() ? null : news.get(0);
    }

    @Override
    public void update(News updateNews) {

        String sql = "UPDATE news SET title = ?"
                + ", thumbnail = ?"
                + ", short_description = ?"
                + ", content = ?"
                + ", category_id = ?"
                + ", created_by = ?"
                + " WHERE id = ?";
        update(sql, new Parameter(updateNews.getTitle(), Types.VARCHAR),
                new Parameter(updateNews.getThumbnail(), Types.VARCHAR),
                new Parameter(updateNews.getShortDescription(), Types.LONGNVARCHAR),
                new Parameter(updateNews.getContent(), Types.LONGNVARCHAR),
                new Parameter(updateNews.getCategoryID(), Types.INTEGER),
                new Parameter(updateNews.getCreateBy(), Types.VARCHAR),
                new Parameter(updateNews.getId(), Types.INTEGER));

    }

    @Override
    public void delete(int id) {
        String sql = "delete from News\n"
                + "where id = ?";
        update(sql, new Parameter(id, Types.INTEGER));
    }

}
