package com.tc.brewery.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String imageUrl;

    private int abv;
    private int ibu;
    private String category;
    private String brewersTips;
    private String ingredientName;
    private String foodPairing;
    private String tagline;
    @OneToMany(mappedBy = "beer",cascade= CascadeType.ALL)
    @JsonIgnore
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "beer",cascade=CascadeType.ALL)
    @JsonIgnoreProperties("beer")
    private List<Pricing> pricings = new ArrayList<>();

    @OneToMany(mappedBy = "beer",cascade=CascadeType.ALL)
    @JsonIgnoreProperties("beer")
    private List<CartItem> cartItems = new ArrayList<>();

    @Column(precision = 3, scale = 1)
    private BigDecimal averageRating;

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public Beer() {
    }

    public Beer(int id, String name, String description, String imageUrl, int abv, int ibu, String category, String brewersTips, String ingredientName, String foodPairing, String tagline, List<Rating> ratings, List<Pricing> pricings, List<CartItem> cartItems, BigDecimal averageRating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.abv = abv;
        this.ibu = ibu;
        this.category = category;
        this.brewersTips = brewersTips;
        this.ingredientName = ingredientName;
        this.foodPairing = foodPairing;
        this.tagline = tagline;
        this.ratings = ratings;
        this.pricings = pricings;
        this.cartItems = cartItems;
        this.averageRating = averageRating;
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

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Pricing> getPricings() {
        return pricings;
    }

    public void setPricings(List<Pricing> pricings) {
        this.pricings = pricings;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getAbv() {
        return abv;
    }

    public void setAbv(int abv) {
        this.abv = abv;
    }

    public int getIbu() {
        return ibu;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    private Map<String, Object> getUserInfo(User user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("firstName", user.getFirstName()); // Include first name
        userInfo.put("lastName", user.getLastName());   // Include last name
        return userInfo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    public void setBrewersTips(String brewersTips) {
        this.brewersTips = brewersTips;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getFoodPairing() {
        return foodPairing;
    }

    public void setFoodPairing(String foodPairing) {
        this.foodPairing = foodPairing;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", abv=" + abv +
                ", ibu=" + ibu +
                ", category='" + category + '\'' +
                ", brewersTips='" + brewersTips + '\'' +
                ", ingredientName='" + ingredientName + '\'' +
                ", foodPairing='" + foodPairing + '\'' +
                ", tagline='" + tagline + '\'' +
                ", ratings=" + ratings +
                ", pricings=" + pricings +
                ", cartItems=" + cartItems +
                ", averageRating=" + averageRating +
                '}';
    }
}
