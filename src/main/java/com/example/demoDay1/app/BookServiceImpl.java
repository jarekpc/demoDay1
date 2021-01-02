package com.example.demoDay1.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void deleteBook(long id) {
        String sql = "DELETE FROM book where id=" + id;
        jdbcTemplate.update(sql);
    }

    @Override
    public void addBookToAuthor(Book b, Person p) {
        Long bookId = 0L;
        String maxB = "Select max(id) from Book b";
        bookId = jdbcTemplate.query(maxB, new ResultSetExtractor<Long>() {
            @Override
            public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
                long maxid;
                rs.next();
                maxid = rs.getLong(1);
                return maxid;
            }
        });
        bookId = bookId + 1;
        String sql = "INSERT INTO "
                + "`book`(`id`, `description`, `path`, `price`, `publishdate`, `title`, `p_id`,`bfile`)" + " VALUES ("
                + bookId + "," + "'" + b.getDescription() + "'," + "'" + b.getPath() + "'," + b.getPrice() + "," + "'"
                + b.getPublishDate() + "'," + "'" + b.getTitle() + "'," + p.getId() + "," + b.getBfiles() + ")";

        jdbcTemplate.update(sql);
    }

    @Override
    public void updateBook(Book b) {
        String sql = "UPDATE `book` set " + "title='" + b.getTitle() + "', description = '" + b.getDescription()
                + "', publishdate='" + b.getPublishDate() + "', price=" + b.getPrice() + " where book.id = "
                + b.getId();

        jdbcTemplate.update(sql);
    }
}
