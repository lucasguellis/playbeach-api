package com.pbio.playbeach.services;

import com.pbio.playbeach.entities.TmpUserCategory;
import com.pbio.playbeach.entities.Category;
import com.pbio.playbeach.entities.User;
import com.pbio.playbeach.entities.dao.TmpUserCategoryDAO;
import com.pbio.playbeach.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {


    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final TmpUserCategoryDAO tmpUserCategoryDAO;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, UserService userService, TmpUserCategoryDAO tmpUserCategoryDAO) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
        this.tmpUserCategoryDAO = tmpUserCategoryDAO;
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void subscribeUser(String userEmail, Long categoryId) {
        User user = userService.getUserByEmail(userEmail);
        Category category = this.getCategoryById(categoryId);

        if (user != null && category != null) {
            Optional<TmpUserCategory> tmpUserCategoryOptional = tmpUserCategoryDAO.findByUserIdAndCategoryId(user.getId(), categoryId);
            if (tmpUserCategoryOptional.isEmpty()) {
                TmpUserCategory entity = new TmpUserCategory();
                entity.setUser(user);
                entity.setCategory(category);
                tmpUserCategoryDAO.insert(entity);
            }
        }
    }

    public void unsubscribeUser(Long userId, Long categoryId) {
        var entity = tmpUserCategoryDAO.findByUserIdAndCategoryId(userId, categoryId);
        entity.ifPresent(tmpUserCategoryDAO::delete);
    }

}
