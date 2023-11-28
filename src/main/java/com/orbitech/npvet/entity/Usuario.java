package com.orbitech.npvet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario", schema = "public")
public class Usuario {
    @Id
    @Getter
    @Setter
    private String id;

    @Column(length = 100, name = "nome")
    private String nome;

    @Column(unique = true, length = 11, name = "cpf")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(unique = true, nullable = false, length = 30, name = "username")
    private String username;

    @OneToMany(mappedBy = "veterinario")
    @JsonIgnoreProperties("veterinario")
    private List<Anamnese> anamneses = new ArrayList<>();

    @OneToMany
    private  List<Consulta> consultas = new ArrayList<>();

    @Getter @Setter
    private LocalDateTime createdAt;

    @Getter @Setter
    private LocalDateTime updatedAt;

    @Getter @Setter
    private LocalDateTime deletedAt;

    public void roleStringSet(String role){
        this.role = Role.valueOf(role);
    }
    public String roleStringGet() {
        return this.role.toString();
    }

    public void delete(){
        this.deletedAt = LocalDateTime.now();
    }
    public void activate(){
        this.deletedAt = null;
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    private void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
