package mvm2services.services;

import java.util.ArrayList;

public class Fatores {
	
/* Atributos de entrada */
	
	private double taxaAno;
	private int baseTaxa;
	private int qtdeParcelas;
	private ArrayList<Integer> qtdeDias = new ArrayList<Integer>();
			
	
/* Atributo de Sa�da */
	
	private ArrayList<Double> fatorTaxa = new ArrayList<Double>();
	
/* Valida��o dos dados de entrada */
	
	boolean resultValue;
	private int idRetorno = 0;
	private String msgRetorno = "";


	public Fatores(double taxaAno, int baseTaxa, int qtdeParcelas, ArrayList<Integer> qtdeDias,
			ArrayList<Double> fatorTaxa, boolean resultValue, int idRetorno, String msgRetorno) {
		super();
		this.taxaAno = taxaAno;
		this.baseTaxa = baseTaxa;
		this.qtdeParcelas = qtdeParcelas;
		this.qtdeDias = qtdeDias;
		this.fatorTaxa = fatorTaxa;
		this.resultValue = resultValue;
		this.idRetorno = idRetorno;
		this.msgRetorno = msgRetorno;
	}

	public Fatores()
	{
		
	}

	public double getTaxaAno() {
		return taxaAno;
	}

	public void setTaxaAno(double taxaAno) {
		this.taxaAno = taxaAno;
	}

	public int getBaseTaxa() {
		return baseTaxa;
	}

	public void setBaseTaxa(int baseTaxa) {
		this.baseTaxa = baseTaxa;
	}

	public int getQtdeParcelas() {
		return qtdeParcelas;
	}

	public void setQtdeParcelas(int qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}


	public ArrayList<Integer> getQtdeDias() {
		return qtdeDias;
	}

	public void setQtdeDias(ArrayList<Integer> qtdeDias) {
		this.qtdeDias = qtdeDias;
	}


	public ArrayList<Double> getFatorTaxa() {
		return fatorTaxa;
	}

	public int getIdRetorno() {
		return idRetorno;
	}

	public String getMsgRetorno() {
		return msgRetorno;
	}

	public boolean isResultValue() {
		return resultValue;
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
		
		if (baseTaxa != 360 && baseTaxa != 252)
		{
			idRetorno = 11;
			msgRetorno = "C�lculo de fatores - Base da Taxa inv�lida (360 ou 252)";
			return false;
		}

		return true;
	}
	
	protected double calcularTaxaDia( double taxaAno, int baseTaxa)
	{
		
		double fatorTaxaDia;
		double p1, p2, p3, p4;
		
		p1 = taxaAno / 100;                 	// Taxa Ano / 100
		p2 = p1 + (double) 1;					// Fator da Taxa Ano

		if (baseTaxa == 360) {
			p3 = (double) 1 / (double) (360);	// C�lculo de 1 dia de 360
		} 
		else
		{
			p3 = (double) 1 / (double) (252);	// C�lculo de 1 dia de 252
		}
		
		p4 = Math.pow(p2, p3);					// C�lculo do Fator Dia na Base 360
		fatorTaxaDia = p4;
		
		return fatorTaxaDia;
				
	}
	
	
	public  ArrayList<Double> calcularFatores()
	{
		return fatorTaxa;
	}
}
