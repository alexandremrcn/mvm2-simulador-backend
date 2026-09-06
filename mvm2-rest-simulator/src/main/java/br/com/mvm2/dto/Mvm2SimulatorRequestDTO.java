package br.com.mvm2.dto;

import java.util.List;

public class Mvm2SimulatorRequestDTO {

    private String vlSolicitado;
    private String vl1aParcela;
    private String idTaxaFator;
    private String taxa;
    private String periodicidadeTaxa;
    private String baseTaxa;
    private String periodicidadeVcto;
    private String idDiaUtil;
    private String tipoJuros;
    private String qtdeParcelas;
    private String qtdeDiasCarencia;
    private String diaBaseVencimento;
    private String cdSistemaAmortizacao;
    private String tpNegociacaoIof;
    private String idIofEmbutido;
    private String pcAliquotaIOF;
    private String pcAliquotaAdicional;

    private List<String> idCobrJuros;
    private List<String> idCobrPrinc;
    private List<String> vlParcelaIn;
    private List<String> idTipoParcelaIn;

    public String getVlSolicitado() {
        return vlSolicitado;
    }

    public void setVlSolicitado(String vlSolicitado) {
        this.vlSolicitado = vlSolicitado;
    }

    public String getVl1aParcela() {
        return vl1aParcela;
    }

    public void setVl1aParcela(String vl1aParcela) {
        this.vl1aParcela = vl1aParcela;
    }

    public String getIdTaxaFator() {
        return idTaxaFator;
    }

    public void setIdTaxaFator(String idTaxaFator) {
        this.idTaxaFator = idTaxaFator;
    }

    public String getTaxa() {
        return taxa;
    }

    public void setTaxa(String taxa) {
        this.taxa = taxa;
    }

    public String getPeriodicidadeTaxa() {
        return periodicidadeTaxa;
    }

    public void setPeriodicidadeTaxa(String periodicidadeTaxa) {
        this.periodicidadeTaxa = periodicidadeTaxa;
    }

    public String getBaseTaxa() {
        return baseTaxa;
    }

    public void setBaseTaxa(String baseTaxa) {
        this.baseTaxa = baseTaxa;
    }

    public String getPeriodicidadeVcto() {
        return periodicidadeVcto;
    }

    public void setPeriodicidadeVcto(String periodicidadeVcto) {
        this.periodicidadeVcto = periodicidadeVcto;
    }

    public String getIdDiaUtil() {
        return idDiaUtil;
    }

    public void setIdDiaUtil(String idDiaUtil) {
        this.idDiaUtil = idDiaUtil;
    }

    public String getTipoJuros() {
        return tipoJuros;
    }

    public void setTipoJuros(String tipoJuros) {
        this.tipoJuros = tipoJuros;
    }

    public String getQtdeParcelas() {
        return qtdeParcelas;
    }

    public void setQtdeParcelas(String qtdeParcelas) {
        this.qtdeParcelas = qtdeParcelas;
    }

    public String getQtdeDiasCarencia() {
        return qtdeDiasCarencia;
    }

    public void setQtdeDiasCarencia(String qtdeDiasCarencia) {
        this.qtdeDiasCarencia = qtdeDiasCarencia;
    }

    public String getDiaBaseVencimento() {
        return diaBaseVencimento;
    }

    public void setDiaBaseVencimento(String diaBaseVencimento) {
        this.diaBaseVencimento = diaBaseVencimento;
    }

    public String getCdSistemaAmortizacao() {
        return cdSistemaAmortizacao;
    }

    public void setCdSistemaAmortizacao(String cdSistemaAmortizacao) {
        this.cdSistemaAmortizacao = cdSistemaAmortizacao;
    }

    public String getTpNegociacaoIof() {
        return tpNegociacaoIof;
    }

    public void setTpNegociacaoIof(String tpNegociacaoIof) {
        this.tpNegociacaoIof = tpNegociacaoIof;
    }

    public String getIdIofEmbutido() {
        return idIofEmbutido;
    }

    public void setIdIofEmbutido(String idIofEmbutido) {
        this.idIofEmbutido = idIofEmbutido;
    }

    public String getPcAliquotaIOF() {
        return pcAliquotaIOF;
    }

    public void setPcAliquotaIOF(String pcAliquotaIOF) {
        this.pcAliquotaIOF = pcAliquotaIOF;
    }

    public String getPcAliquotaAdicional() {
        return pcAliquotaAdicional;
    }

    public void setPcAliquotaAdicional(String pcAliquotaAdicional) {
        this.pcAliquotaAdicional = pcAliquotaAdicional;
    }

    public List<String> getIdCobrJuros() {
        return idCobrJuros;
    }

    public void setIdCobrJuros(List<String> idCobrJuros) {
        this.idCobrJuros = idCobrJuros;
    }

    public List<String> getIdCobrPrinc() {
        return idCobrPrinc;
    }

    public void setIdCobrPrinc(List<String> idCobrPrinc) {
        this.idCobrPrinc = idCobrPrinc;
    }

    public List<String> getVlParcelaIn() {
        return vlParcelaIn;
    }
    public List<String> getIdTipoParcelaIn() {
        return idTipoParcelaIn;
    }

    public void setVlParcelaIn(List<String> vlParcelaIn) {
        this.vlParcelaIn = vlParcelaIn;
    }
    public void setIdTipoParcelaIn(List<String> idTipoParcelaIn) {
        this.idTipoParcelaIn = idTipoParcelaIn;
    }
}
