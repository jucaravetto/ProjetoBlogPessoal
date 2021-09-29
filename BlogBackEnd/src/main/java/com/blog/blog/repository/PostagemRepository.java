package com.blog.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blog.model.PostagemModel;

@Repository
public interface PostagemRepository extends JpaRepository <PostagemModel,Long> {

	List<PostagemModel>findAllByTituloContainingIgnoreCase (String titulo);
	
}
