package mvm2services.models;

import java.util.List;

public class SimulatorResult {

    public int cdResult;
    public String msgResult;

    public String dtReferencia;
    public double pcTaxaJuros;
    public char periodicidadeTaxa;
    public int baseTaxaJuros;
    public int qtdeParcelas;
    public String vlLiberado;
    public String vlFinanciado;
    public String vlIof;
    public int sistemaAmortizacao;
    public char periodicidadeVcto;
    public char tpJuros;
    public String vl1aParela;

    public List<Integer> numParcela;
    public List<String> dtVencimento;
    public List<Integer> qtdeDiasCorr;
    public List<Integer> qtdeDiasUteis;
    public List<String> idCobraJuros;
    public List<String> idCobraPrincipal;

    public List<String> vlParcela;
    public List<String> vlJuros;
    public List<String> vlPrincipal;
    public List<String> vlSdoRemanescente;
}
