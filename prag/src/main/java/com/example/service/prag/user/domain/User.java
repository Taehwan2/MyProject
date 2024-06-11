package com.example.service.prag.user.domain;

import com.example.service.prag.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "user_table")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User extends BaseEntity  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(name = "user_name",nullable = false)
    private  String userName;

    @Column(name = "user_email",nullable = false)
    private String userEmail;

    @Column(name = "user_password",nullable = false)
    private String userPassword;



}
