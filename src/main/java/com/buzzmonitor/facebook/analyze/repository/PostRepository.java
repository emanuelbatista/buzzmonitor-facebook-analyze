
package com.buzzmonitor.facebook.analyze.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.buzzmonitor.facebook.analyze.dto.response.PostCountForDateDTO;
import com.buzzmonitor.facebook.analyze.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    @Query("SELECT p FROM Post p where p.createdTime BETWEEN :since AND :until")
    public List<Post> findAllBySinceDataAndUntilData(@Param("since") OffsetDateTime since,
            @Param("until") OffsetDateTime until);

    @Query("SELECT new com.buzzmonitor.facebook.analyze.dto.response.PostCountForDateDTO(year(p.createdTime), month(p.createdTime), day(p.createdTime), COUNT(p)) "
            + "FROM Post p WHERE p.createdTime BETWEEN :since AND :until GROUP BY year(p.createdTime), month(p.createdTime), day(p.createdTime) "
            + "ORDER BY year(p.createdTime), month(p.createdTime), day(p.createdTime)")
    public List<PostCountForDateDTO> countPostsByDate(@Param("since") OffsetDateTime since,
            @Param("until") OffsetDateTime until);
}
