package com.st121334.FinalExam2021.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.number.money.MonetaryAmountFormatter;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Company company;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Categories> categories;

    @Column(name="price", precision=10, scale=2)
    private BigDecimal price_;

    @Transient
    private MonetaryAmount price;

    private int stock;

    @Transient
    private int quantity; // To compute how much quantity is ordered

    @Transient
    private List<Integer> quantities;

    @Version
    private int version; // For Optimistic Locking Case

    public List<Integer> getQuantities() {
        return getNumbersInRange(1, getStock());
    }

    public BigDecimal getPrice_() {
        return price
                .getNumber()
                .numberValue(BigDecimal.class)
                .setScale(price
                        .getCurrency()
                        .getDefaultFractionDigits(), RoundingMode.HALF_EVEN
                );
    }

    public List<Integer> getNumbersInRange(int start, int end) {
        List<Integer> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            result.add(i);
        }
        return result;
    }
}
