package com.topi.repository;

import com.topi.model.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Interface created to be a Basic Repository for the entire system.
 *
 * @since 2021-02-23
 */

@NoRepositoryBean
public interface BasicRepository<T extends BasicEntity>
        extends JpaRepository<T, Long>, PagingAndSortingRepository<T, Long>, QuerydslPredicateExecutor<T> {
}
