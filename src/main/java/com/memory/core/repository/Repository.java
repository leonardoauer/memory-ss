package com.memory.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.memory.core.model.Bean;

/**
 * This repository serves as common functions between all repositories. 
 * Write here repository methods that all your repositories will share. 
 * 
 * @author lauer
 */
@NoRepositoryBean
public interface Repository<T extends Bean> extends JpaRepository<T, Long> {
	
}
