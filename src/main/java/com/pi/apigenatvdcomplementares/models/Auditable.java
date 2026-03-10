package com.pi.apigenatvdcomplementares.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Auditable {
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @PrePersist // Executa automaticamente antes de salvar um novo registro.
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        criadoEm = now;
        atualizadoEm = now;
    }

    @PreUpdate // Executa automaticamente antes de atualizar um registro existente.
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }
}
