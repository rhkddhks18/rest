//package com.example.rest.member;
//
//import com.example.rest.article.Article;
//import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@NoArgsConstructor
//@Entity
//@Table(name = "member")
//public class Member {
//    //필드
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "member_id", unique = true, nullable = false)
//    private Long id;
//
//    @Column(length = 15, nullable = false)
//    private String username;
//
//    @Column(length = 100, nullable = false)
//    private String password;
//
//    @Column(length = 50, nullable = false)
//    private String email;
//
//    @Column(length = 20, nullable = false)
//    private String contact;
//
//    @OneToMany(mappedBy = "member", cascade = CascadeType.MERGE, orphanRemoval = true)
//    private List<Article> articleList = new ArrayList<>();
//
//    //빌더
//    @Builder
//    public Member(String username, String password, String email, String contact) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.contact = contact;
//    }
//}
