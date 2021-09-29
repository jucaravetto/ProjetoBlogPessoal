package com.blog.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blog.model.TemaModel;

@Repository
public interface TemaRepository extends JpaRepository <TemaModel,Long> {

}
