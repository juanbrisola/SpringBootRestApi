package com.juanbrisola.data.model;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ingrediente")
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 45)
    @Column(nullable = false, length = 45)
    private String descricao;

    @NotNull
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    @Column(nullable = false, columnDefinition = "DECIMAL(4,2) DEFAULT 0.00")
    private BigDecimal valor;

    @OneToMany(mappedBy = "ingrediente")
    private List<LancheIngrediente> lancheIngredienteList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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

        Ingrediente that = (Ingrediente) o;

        if (!id.equals(that.id)) return false;
        if (!descricao.equals(that.descricao)) return false;
        if (!valor.equals(that.valor)) return false;
        return lancheIngredienteList != null ? lancheIngredienteList.equals(that.lancheIngredienteList) : that.lancheIngredienteList == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + descricao.hashCode();
        result = 31 * result + valor.hashCode();
        result = 31 * result + (lancheIngredienteList != null ? lancheIngredienteList.hashCode() : 0);
        return result;
    }
}
