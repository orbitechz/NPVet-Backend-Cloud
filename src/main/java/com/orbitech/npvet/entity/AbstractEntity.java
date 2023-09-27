package com.orbitech.npvet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass // Mapeia para que o banco de dados considere as colunas dessa super-classe
public class AbstractEntity {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "created_at",nullable = true)
    private LocalDateTime createdAt;

    @Getter @Setter
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Getter @Setter
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    private void preUpData(){
        this.updatedAt = LocalDateTime.now();
    }
}
