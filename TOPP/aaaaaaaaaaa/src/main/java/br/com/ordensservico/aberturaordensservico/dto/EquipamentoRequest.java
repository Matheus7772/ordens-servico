package br.com.ordensservico.aberturaordensservico.dto;

public class EquipamentoRequest {

private String nome;
private String numeroPatrimonio;
private Integer setorId;

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public String getNumeroPatrimonio() {
    return numeroPatrimonio;
}

public void setNumeroPatrimonio(String numeroPatrimonio) {
    this.numeroPatrimonio = numeroPatrimonio;
}

public Integer getSetorId() {
    return setorId;
}

public void setSetorId(Integer setorId) {
    this.setorId = setorId;
}


}
