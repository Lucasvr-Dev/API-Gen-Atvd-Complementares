package com.pi.apigenatvdcomplementares.models;

import java.util.ArrayList;
import java.util.List;

import com.pi.apigenatvdcomplementares.enums.PerfilUsuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario extends Auditable {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    // Perfil do usuário (SUPER_ADMIN, COORDENADOR, ALUNO)
    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private PerfilUsuario perfil;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Aluno aluno;

    /**
     * Um usuário pode ter vários registros de ação.
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroAcao> registrosAcao = new ArrayList<>();

    /**
     * Um coordenador pode avaliar várias submissões.
     */
    @OneToMany(mappedBy = "coordenadorAvaliador")
    private List<Submissao> submissoesAvaliadas = new ArrayList<>();

    /**
     * Um coordenador pode estar associado a vários cursos.
     */
    @OneToMany(mappedBy = "coordenador")
    private List<CoordenadorCurso> coordenacoes = new ArrayList<>();

}
