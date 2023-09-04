package com.tc.brewery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tc.brewery.entity.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

//    @Query("SELECT c FROM Cart c JOIN FETCH c.cartItems WHERE c.user.id = :userId")
//    List<Cart> findCartsByUserId(@Param("userId") Long userId);
//    Cart findTopByUserIdOrderByTimestampDesc(Long userId);
    List<Cart> findCartsByUserIdOrderByTimestampDesc(Long userId);
    List<Cart> findAllByUserIdAndStatus(Long userId, String status);

}
