package com.tc.brewery.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String foodName;
    private String category;
    private String imageUrl;
    private BigDecimal foodPrice;
    private String description;
    private String calories;
    @OneToMany(mappedBy = "food",cascade= CascadeType.ALL)
    @JsonIgnoreProperties("food")
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "food",cascade= CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Rating> ratings = new ArrayList<>();
    @Column(precision = 3, scale = 1)
    private BigDecimal averageRating;

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public Food() {
    }

    public Food(int id, String foodName, String category, String imageUrl, BigDecimal foodPrice, String description, String calories, List<CartItem> cartItems, List<Rating> ratings, BigDecimal averageRating) {
        this.id = id;
        this.foodName = foodName;
        this.category = category;
        this.imageUrl = imageUrl;
        this.foodPrice = foodPrice;
        this.description = description;
        this.calories = calories;
        this.cartItems = cartItems;
        this.ratings = ratings;
        this.averageRating = averageRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    @JsonGetter("ratings")
    public List<Map<String, Object>> getRatingsInfo() {

        List<Map<String, Object>> ratingsInfo = new ArrayList<>();
        for (Rating rating : ratings) {
            Map<String, Object> info = new HashMap<>();
            info.put("id", rating.getId());
            info.put("rating", rating.getRating());
            info.put("review", rating.getReview());
            info.put("user", getUserInfo(rating.getUser()));
            ratingsInfo.add(info);
        }
        return ratingsInfo;
    }

    private Map<String, Object> getUserInfo(User user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("firstName", user.getFirstName());
        userInfo.put("lastName", user.getLastName());
        return userInfo;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", category='" + category + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", foodPrice=" + foodPrice +
                ", description='" + description + '\'' +
                ", calories='" + calories + '\'' +
                ", cartItems=" + cartItems +
                ", ratings=" + ratings +
                ", averageRating=" + averageRating +
                '}';
    }
}

