package mvm2services.services;

import java.util.ArrayList;

public class FatoresHP {
	
	
/* Atributos de entrada */
	
	private double taxaAno;
	private char periodicidade;
	private int qtdeParcelas;
			
	
/* Atributo de Sa�da */
	
	private ArrayList<Double> fatorTaxa = new ArrayList<Double>();
	
/* Valida��o dos dados de entrada */
	
	boolean resultValue;
	private int idRetorno = 0;
	private String msgRetorno = "";



	public FatoresHP(double taxaAno, char periodicidade, int qtdeParcelas, ArrayList<Double> fatorTaxa,
			boolean resultValue, int idRetorno, String msgRetorno) {
		super();
		this.taxaAno = taxaAno;
		this.periodicidade = periodicidade;
		this.qtdeParcelas = qtdeParcelas;
		this.fatorTaxa = fatorTaxa;
		this.resultValue = resultValue;
		this.idRetorno = idRetorno;
		this.msgRetorno = msgRetorno;
	}

	public FatoresHP()
	{
		
	}


	public double getTaxaAno() {
		return taxaAno;
	}

	public void setTaxaAno(double taxaAno) {
		this.taxaAno = taxaAno;
	}

	public char getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(char periodicidade) {
		this.periodicidade = periodicidade;
	}

	public int getQtdeParcelas() {
		return qtdeParcelas;
	}

	public void setQtdeParcelas(int qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}

	public ArrayList<Double> getFatorTaxa() {
		return fatorTaxa;
	}

	public void setFatorTaxa(ArrayList<Double> fatorTaxa) {
		this.fatorTaxa = fatorTaxa;
	}

	public boolean isResultValue() {
		return resultValue;
	}

	public void setResultValue(boolean resultValue) {
		this.resultValue = resultValue;
	}

	public int getIdRetorno() {
		return idRetorno;
	}

	public void setIdRetorno(int idRetorno) {
		this.idRetorno = idRetorno;
	}

	public String getMsgRetorno() {
		return msgRetorno;
	}

	public void setMsgRetorno(String msgRetorno) {
		this.msgRetorno = msgRetorno;
	}

	protected boolean validarDadosEntrada() {
		
		// Valida��o da taxa
		
		if (taxaAno <=0)
		{
			idRetorno = 9;
			msgRetorno = "C�lculo de fatores - taxa inv�lida";
			return false;
		}
		
		if (qtdeParcelas <= 0)
		{
			idRetorno = 10;
			msgRetorno = "C�lculo de fatores - Quantidade de Parcelas inv�lida";
			return false;
		}
		
		if (periodicidade != 'M' && periodicidade != 'A' && periodicidade != 'T' && periodicidade != 'S')
		{
			idRetorno = 11;
			msgRetorno = "C�lculo de fatores - Base da Taxa inv�lida (360 ou 252)";
			return false;
		}

		return true;
	}
	
	public  ArrayList<Double> calcularFatoresHP()
	{
		return fatorTaxa;
	}
}
