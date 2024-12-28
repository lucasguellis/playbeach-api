package com.pbio.playbeach.entities.dao;

import com.pbio.playbeach.entities.TmpUserCategory;
import com.pbio.playbeach.repositories.TmpUserCategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TmpUserCategoryDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    TmpUserCategoryRepository tmpUserCategoryRepository;

    public Optional<TmpUserCategory> findByUserIdAndCategoryId(Long userId, Long categoryId) {
        return tmpUserCategoryRepository.findByUserIdAndCategoryId(userId, categoryId);
    };

    public List<TmpUserCategory> findByUserId(Long userId) {
        return tmpUserCategoryRepository.findByUserId(userId);
    };

    @Transactional
    public void insert(TmpUserCategory tmpUserCategory) {
        this.entityManager.persist(tmpUserCategory);
    }

    @Transactional
    public void delete(TmpUserCategory tmpUserCategory) {
        this.entityManager.remove(tmpUserCategory);
    }

}
