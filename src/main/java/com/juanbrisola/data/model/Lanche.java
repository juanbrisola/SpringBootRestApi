package com.juanbrisola.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "lanche")
public class Lanche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @OneToMany(mappedBy = "lanche")
    private List<LancheIngrediente> lancheIngredienteList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<LancheIngrediente> getLancheIngredienteList() {
        return lancheIngredienteList;
    }

    public void setLancheIngredienteList(List<LancheIngrediente> lancheIngredienteList) {
        this.lancheIngredienteList = lancheIngredienteList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lanche lanche = (Lanche) o;

        if (!id.equals(lanche.id)) return false;
        if (!nome.equals(lanche.nome)) return false;
        return lancheIngredienteList != null ? lancheIngredienteList.equals(lanche.lancheIngredienteList) : lanche.lancheIngredienteList == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + (lancheIngredienteList != null ? lancheIngredienteList.hashCode() : 0);
        return result;
    }
}
