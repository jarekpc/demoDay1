package com.example.demoDay1.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select * from book b where b.p_id =:id", nativeQuery = true)
    List<Book> findBooksByAuthor(@Param("id") Long id);

    @Query(value = "select * from book b where b.title like %:title%", nativeQuery = true)
    List<Book> findBooksByTitle(@Param("title") String title);
}
