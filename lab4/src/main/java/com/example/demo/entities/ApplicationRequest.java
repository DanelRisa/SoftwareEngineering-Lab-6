package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "postgres")
@Getter
@Setter
public class ApplicationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 200)
    private String userName;

    @Column(name = "coursename", length = 200)
    private String courseName;

    @Column(name = "commentary", length = 200)
    private String commentary;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "handled")
    private boolean handled;

}
