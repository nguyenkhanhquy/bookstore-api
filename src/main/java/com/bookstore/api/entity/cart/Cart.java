package com.bookstore.api.entity.cart;

import com.bookstore.api.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public double getTotal() {
        double total = 0.0;
        for (CartItem item : cartItems) {
            total += item.getTotal();
        }
        return total;
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }
}
