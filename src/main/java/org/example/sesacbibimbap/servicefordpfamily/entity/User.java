package org.example.sesacbibimbap.servicefordpfamily.entity;


import jakarta.persistence.*;
import lombok.*;
import org.example.sesacbibimbap.servicefordpfamily.vo.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "nickname", length = 50)
    private String nickname;

//    @Column(name = "phone_number", length = 100)
//    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20)
    private Role role;

//    @Column(name = "provider", length = 20)
//    private String provider;

    @CreatedDate
    @Column(name = "created_at", nullable = false, length = 20)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", length = 20)
    private LocalDateTime updatedAt;

    @PrePersist
    public void setId() {
        if (this.createdAt == null)
            this.createdAt = LocalDateTime.now();
    }

}
