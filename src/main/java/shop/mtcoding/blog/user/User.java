package shop.mtcoding.blog.user;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;

@Data //getter setter tostring
@Entity
@Table(name = "user_tb") //테이블 명 정하는것
public class User {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
    private int id;

    @Column(unique = true)
    private String username;

    @Column(length = 60, nullable = false) // 제약조건을 어노테이션으로 걸어준다
    private String password;
    private String email;


    @CreationTimestamp
    private LocalDateTime createdAt;
}
// h2를 안쓰는데 User를 쓰면 불편해짐