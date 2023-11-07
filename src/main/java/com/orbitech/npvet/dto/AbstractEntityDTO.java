package com.orbitech.npvet.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AbstractEntityDTO {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public void delete(){
        this.deletedAt = LocalDateTime.now();
    }
    public void activate(){
        this.deletedAt = null;
    }

}