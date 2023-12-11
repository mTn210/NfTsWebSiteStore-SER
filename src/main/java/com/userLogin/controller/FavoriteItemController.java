package com.userLogin.controller;

import com.userLogin.model.FavoriteItem;
import com.userLogin.service.FavoriteItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite-items")
public class FavoriteItemController {

    private final FavoriteItemService favoriteItemService;

    @Autowired
    public FavoriteItemController(FavoriteItemService favoriteItemService) {
        this.favoriteItemService = favoriteItemService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(@RequestBody FavoriteItem favoriteItem) {
        favoriteItemService.addFavorite(favoriteItem);
        return ResponseEntity.status(HttpStatus.CREATED).body("Favorite item added successfully");
    }

    @DeleteMapping("/remove/{favoriteItemId}")
    public ResponseEntity<String> removeFavorite(@PathVariable Long favoriteItemId) {
        favoriteItemService.removeFavoriteById(favoriteItemId);
        return ResponseEntity.status(HttpStatus.OK).body("Favorite item removed successfully");
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<FavoriteItem>> getFavorites(@PathVariable Long userId) {
        List<FavoriteItem> favoriteItems = favoriteItemService.getFavorites(userId);
        return ResponseEntity.status(HttpStatus.OK).body(favoriteItems);
    }
}
