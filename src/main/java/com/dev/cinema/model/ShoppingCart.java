package com.dev.cinema.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @Column(name = "cart_id")
    private Long id;

    @OneToMany
    @JoinColumn(name = "tickets_id")
    private List<Ticket> tickets;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cart_id")
    private User user;
}
