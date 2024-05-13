package com.sgm.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgm.spring.model.TutorialEntity;

public interface TutorialRepository extends JpaRepository<TutorialEntity, Long> {
	List<TutorialEntity> findByPublished(boolean published);
	List<TutorialEntity> findByTitleContaining(String title);
}
