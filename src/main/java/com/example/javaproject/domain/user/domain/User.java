package com.example.javaproject.domain.user.domain;

import com.example.javaproject.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "tbl_user")
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(50)")
    private String nickname;

    @Column(columnDefinition = "VARCHAR(60)")
    private String password;

}
