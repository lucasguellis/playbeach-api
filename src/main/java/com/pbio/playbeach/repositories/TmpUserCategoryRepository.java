package com.pbio.playbeach.repositories;

import com.pbio.playbeach.entities.Place;
import com.pbio.playbeach.entities.TmpUserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TmpUserCategoryRepository extends JpaRepository<TmpUserCategory, Long> {
    Optional<TmpUserCategory> findByUserIdAndCategoryId(Long userId, Long categoryId);
    List<TmpUserCategory> findByUserId(Long userId);
}
