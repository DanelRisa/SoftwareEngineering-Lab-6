package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 200)
    private String userName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Courses course;

    @Column(name = "commentary", length = 200)
    private String commentary;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "handled")
    private boolean handled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "request_operators",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "operator_id")
    )
    private List<Operators> operators;
}