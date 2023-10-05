/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Aluno
 */
public class Produto {
    
    private int idProduto;
    private String descricaoProduto;
    private int quantidadeProduto;
    private double precoUnitarioProduto;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public double getPrecoUnitarioProduto() {
        return precoUnitarioProduto;
    }

    public void setPrecoUnitarioProduto(double precoUnitarioProduto) {
        this.precoUnitarioProduto = precoUnitarioProduto;
    }
            
}
