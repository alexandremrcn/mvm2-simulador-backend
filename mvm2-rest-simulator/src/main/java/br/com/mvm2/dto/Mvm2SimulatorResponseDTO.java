package br.com.mvm2.dto;

import java.util.List;

public class Mvm2SimulatorResponseDTO {

    private int cdResult;
    private String msgResult;

    private String dtReferencia;
    private double pcTaxaJuros;
    private char periodicidadeTaxa;
    private int baseTaxaJuros;
    private int qtdeParcelas;
    private String vlLiberado;
    private String vlFinanciado;
    private String vlIof;
    private int sistemaAmortizacao;
    private char periodicidadeVcto;
    private char tpJuros;
    private String vl1aParela;

    private List<Integer> numParcela;
    private List<String> dtVencimento;
    private List<Integer> qtdeDiasCorr;
    private List<Integer> qtdeDiasUteis;
    private List<String> idCobraJuros;
    private List<String> idCobraPrincipal;

    private List<String> vlParcela;
    private List<String> vlJuros;
    private List<String> vlPrincipal;
    private List<String> vlSdoRemanescente;

    // getters e setters

    public int getCdResult() {
        return cdResult;
    }

    public void setCdResult(int cdResult) {
        this.cdResult = cdResult;
    }

    public String getMsgResult() {
        return msgResult;
    }

    public void setMsgResult(String msgResult) {
        this.msgResult = msgResult;
    }

    public String getDtReferencia() {
        return dtReferencia;
    }

    public void setDtReferencia(String dtReferencia) {
        this.dtReferencia = dtReferencia;
    }

    public double getPcTaxaJuros() {
        return pcTaxaJuros;
    }

    public void setPcTaxaJuros(double pcTaxaJuros) {
        this.pcTaxaJuros = pcTaxaJuros;
    }

    public char getPeriodicidadeTaxa() {
        return periodicidadeTaxa;
    }

    public void setPeriodicidadeTaxa(char periodicidadeTaxa) {
        this.periodicidadeTaxa = periodicidadeTaxa;
    }

    public int getBaseTaxaJuros() {
        return baseTaxaJuros;
    }

    public void setBaseTaxaJuros(int baseTaxaJuros) {
        this.baseTaxaJuros = baseTaxaJuros;
    }

    public int getQtdeParcelas() {
        return qtdeParcelas;
    }

    public void setQtdeParcelas(int qtdeParcelas) {
        this.qtdeParcelas = qtdeParcelas;
    }

    public String getVlLiberado() {
        return vlLiberado;
    }

    public void setVlLiberado(String vlLiberado) {
        this.vlLiberado = vlLiberado;
    }

    public String getVlFinanciado() {
        return vlFinanciado;
    }

    public void setVlFinanciado(String vlFinanciado) {
        this.vlFinanciado = vlFinanciado;
    }

    public String getVlIof() {
        return vlIof;
    }

    public void setVlIof(String vlIof) {
        this.vlIof = vlIof;
    }

    public int getSistemaAmortizacao() {
        return sistemaAmortizacao;
    }

    public void setSistemaAmortizacao(int sistemaAmortizacao) {
        this.sistemaAmortizacao = sistemaAmortizacao;
    }

    public char getPeriodicidadeVcto() {
        return periodicidadeVcto;
    }

    public void setPeriodicidadeVcto(char periodicidadeVcto) {
        this.periodicidadeVcto = periodicidadeVcto;
    }

    public char getTpJuros() {
        return tpJuros;
    }

    public void setTpJuros(char tpJuros) {
        this.tpJuros = tpJuros;
    }

    public String getVl1aParela() {
        return vl1aParela;
    }

    public void setVl1aParela(String vl1aParela) {
        this.vl1aParela = vl1aParela;
    }

    public List<Integer> getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(List<Integer> numParcela) {
        this.numParcela = numParcela;
    }

    public List<String> getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(List<String> dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public List<Integer> getQtdeDiasCorr() {
        return qtdeDiasCorr;
    }

    public void setQtdeDiasCorr(List<Integer> qtdeDiasCorr) {
        this.qtdeDiasCorr = qtdeDiasCorr;
    }

    public List<Integer> getQtdeDiasUteis() {
        return qtdeDiasUteis;
    }

    public void setQtdeDiasUteis(List<Integer> qtdeDiasUteis) {
        this.qtdeDiasUteis = qtdeDiasUteis;
    }

    public List<String> getIdCobraJuros() {
        return idCobraJuros;
    }

    public void setIdCobraJuros(List<String> idCobraJuros) {
        this.idCobraJuros = idCobraJuros;
    }

    public List<String> getIdCobraPrincipal() {
        return idCobraPrincipal;
    }

    public void setIdCobraPrincipal(List<String> idCobraPrincipal) {
        this.idCobraPrincipal = idCobraPrincipal;
    }

    public List<String> getVlParcela() {
        return vlParcela;
    }

    public void setVlParcela(List<String> vlParcela) {
        this.vlParcela = vlParcela;
    }

    public List<String> getVlJuros() {
        return vlJuros;
    }

    public void setVlJuros(List<String> vlJuros) {
        this.vlJuros = vlJuros;
    }

    public List<String> getVlPrincipal() {
        return vlPrincipal;
    }

    public void setVlPrincipal(List<String> vlPrincipal) {
        this.vlPrincipal = vlPrincipal;
    }

    public List<String> getVlSdoRemanescente() {
        return vlSdoRemanescente;
    }

    public void setVlSdoRemanescente(List<String> vlSdoRemanescente) {
        this.vlSdoRemanescente = vlSdoRemanescente;
    }
}
