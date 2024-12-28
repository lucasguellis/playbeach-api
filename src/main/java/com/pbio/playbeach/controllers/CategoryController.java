package com.pbio.playbeach.controllers;

import com.pbio.playbeach.entities.Category;
import com.pbio.playbeach.entities.TmpUserCategory;
import com.pbio.playbeach.entities.dao.TmpUserCategoryDAO;
import com.pbio.playbeach.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final UserService userService;
    private final TmpUserCategoryDAO tmpUserCategoryDAO;

    public CategoryController(UserService userService, TmpUserCategoryDAO tmpUserCategoryDAO) {
        this.userService = userService;
        this.tmpUserCategoryDAO = tmpUserCategoryDAO;
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<Category>> getUserSubscribedCategories(@PathVariable Long userId) {
        List<TmpUserCategory> userCategories = tmpUserCategoryDAO.findByUserId(userId);

        List<Category> categories = new ArrayList<>();
        userCategories.forEach(userCategory -> categories.add(userCategory.getCategory()));

        return ResponseEntity.ok(categories);

    }
}
