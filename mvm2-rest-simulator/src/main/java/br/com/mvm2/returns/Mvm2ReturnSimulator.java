package br.com.mvm2.returns;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Mvm2ReturnSimulator {
	
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
	
	private ArrayList<Integer> numParcela = new ArrayList<Integer>();
	private ArrayList<String> dtVencimento = new ArrayList<String>();
	private ArrayList<Integer> qtdeDiasCorr = new ArrayList<Integer>();
	private ArrayList<Integer> qtdeDiasUteis = new ArrayList<Integer>();
	private ArrayList<String> idCobraJuros = new ArrayList<String>();
	private ArrayList<String> idCobraPrincipal = new ArrayList<String>();
	
	private ArrayList<String> vlParcela = new ArrayList<String>();
	private ArrayList<String> vlJuros = new ArrayList<String>();
	private ArrayList<String> vlPrincipal = new ArrayList<String>();
	private ArrayList<String> vlSdoRemanescente = new ArrayList<String>();

	public Mvm2ReturnSimulator() {

	}

	
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


	public ArrayList<Integer> getNumParcela() {
		return numParcela;
	}


	public void setNumParcela(ArrayList<Integer> numParcela) {
		this.numParcela = numParcela;
	}


	public ArrayList<String> getDtVencimento() {
		return dtVencimento;
	}


	public void setDtVencimento(ArrayList<String> dtVencimento) {
		this.dtVencimento = dtVencimento;
	}


	public ArrayList<Integer> getQtdeDiasCorr() {
		return qtdeDiasCorr;
	}


	public void setQtdeDiasCorr(ArrayList<Integer> qtdeDiasCorr) {
		this.qtdeDiasCorr = qtdeDiasCorr;
	}


	public ArrayList<Integer> getQtdeDiasUteis() {
		return qtdeDiasUteis;
	}


	public void setQtdeDiasUteis(ArrayList<Integer> qtdeDiasUteis) {
		this.qtdeDiasUteis = qtdeDiasUteis;
	}


	public ArrayList<String> getIdCobraJuros() {
		return idCobraJuros;
	}


	public void setIdCobraJuros(ArrayList<String> idCobraJuros) {
		this.idCobraJuros = idCobraJuros;
	}


	public ArrayList<String> getIdCobraPrincipal() {
		return idCobraPrincipal;
	}


	public void setIdCobraPrincipal(ArrayList<String> idCobraPrincipal) {
		this.idCobraPrincipal = idCobraPrincipal;
	}


	public ArrayList<String> getVlParcela() {
		return vlParcela;
	}


	public void setVlParcela(ArrayList<String> vlParcela) {
		this.vlParcela = vlParcela;
	}


	public ArrayList<String> getVlJuros() {
		return vlJuros;
	}


	public void setVlJuros(ArrayList<String> vlJuros) {
		this.vlJuros = vlJuros;
	}


	public ArrayList<String> getVlPrincipal() {
		return vlPrincipal;
	}


	public void setVlPrincipal(ArrayList<String> vlPrincipal) {
		this.vlPrincipal = vlPrincipal;
	}


	public ArrayList<String> getVlSdoRemanescente() {
		return vlSdoRemanescente;
	}


	public void setVlSdoRemanescente(ArrayList<String> vlSdoRemanescente) {
		this.vlSdoRemanescente = vlSdoRemanescente;
	}


	public void ConverterResultArray(ArrayList<Double> dfvlParcela, 
			 						 ArrayList <Double> dfvlJuros, ArrayList <Double> dfvlPrincipal,
			 						 ArrayList <Double> dfvlSdoRemanescente) {
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("0.00");
		
		for (int i = 0; i < this.qtdeParcelas; i++) {
			this.vlParcela.add(df.format(dfvlParcela.get(i)));
			this.vlJuros.add(df.format(dfvlJuros.get(i)));
			this.vlPrincipal.add(df.format(dfvlPrincipal.get(i)));
			this.vlSdoRemanescente.add(df.format(dfvlSdoRemanescente.get(i)));
		}
	}
}
