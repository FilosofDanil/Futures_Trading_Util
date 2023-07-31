package com.example.database_service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "alerts")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Alerts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private Users user;
    @Column(name = "ticker")
    private String ticker;
    @Column(name = "price")
    private Double price;
    @Column(name = "current_price")
    private Double current_price;
    @Column(name = "alert_date")
    private Date alert_date;
    @Column(name = "cross_date")
    private Date cross_date;
    @Column(name = "crossed")
    private Boolean crossed;

}
