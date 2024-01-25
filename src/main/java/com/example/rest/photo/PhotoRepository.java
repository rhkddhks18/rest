package com.example.rest.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{
    List<Photo> findAllByArticleId(Long boardId);
}
