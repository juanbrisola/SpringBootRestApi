package com.juanbrisola.data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "lanche_ingrediente")
public class LancheIngrediente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "lanche_id")
    private Lanche lanche;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lanche getLanche() {
        return lanche;
    }

    public void setLanche(Lanche lanche) {
        this.lanche = lanche;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LancheIngrediente that = (LancheIngrediente) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (lanche != null ? !lanche.equals(that.lanche) : that.lanche != null) return false;
        return ingrediente != null ? ingrediente.equals(that.ingrediente) : that.ingrediente == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (lanche != null ? lanche.hashCode() : 0);
        result = 31 * result + (ingrediente != null ? ingrediente.hashCode() : 0);
        return result;
    }
}
