package mvm2services.services;

import java.util.ArrayList;

public class TaxaInternaDeRetorno {

	
	/* C�lculo da Taxa Interna de Retorno - M�todo Exponencial e M�todo Linear
	*/
	
	// Atributos de Entrada
	
	private double vlFinanciado;
	private int qtdeParcelas;
	private int baseTaxa;
	private ArrayList<Integer> qtdeDias = new ArrayList<Integer>();
	private ArrayList<Double>  vlFuturo = new ArrayList<Double>();
	
	// Atributo de Sa�da
	
	double taxaInternaDeRetorno;

	// Tratamento de erro

	boolean resultValue;
	int idRetorno;
	String msgRetorno;
	
	
	public TaxaInternaDeRetorno(double vlFinanciado, int qtdeParcelas, int baseTaxa, ArrayList<Integer> qtdeDias,
			ArrayList<Double> vlFuturo, double taxaInternaDeRetorno) {
		super();
		this.vlFinanciado = vlFinanciado;
		this.qtdeParcelas = qtdeParcelas;
		this.baseTaxa = baseTaxa;
		this.qtdeDias = qtdeDias;
		this.vlFuturo = vlFuturo;
		this.taxaInternaDeRetorno = taxaInternaDeRetorno;
	}


	public TaxaInternaDeRetorno()
	{
		
	}
	

	public boolean isResultValue() {
		return resultValue;
	}


	public int getIdRetorno() {
		return idRetorno;
	}


	public String getMsgRetorno() {
		return msgRetorno;
	}

	public void setMsgRetorno(String msgRetorno) {
		this.msgRetorno = msgRetorno;
	}


	public double getVlFinanciado() {
		return vlFinanciado;
	}


	public void setVlFinanciado(double vlFinanciado) {
		this.vlFinanciado = vlFinanciado;
	}


	public int getQtdeParcelas() {
		return qtdeParcelas;
	}


	public void setQtdeParcelas(int qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}


	public int getBaseTaxa() {
		return baseTaxa;
	}

	public void setBaseTaxa(int baseTaxa) {
		this.baseTaxa = baseTaxa;
	}


	public ArrayList<Integer> getQtdeDias() {
		return qtdeDias;
	}

	public void setQtdeDias(ArrayList<Integer> qtdeDias) {
		this.qtdeDias = qtdeDias;
	}


	public ArrayList<Double> getVlFuturo() {
		return vlFuturo;
	}


	public void setVlFuturo(ArrayList<Double> vlFuturo) {
		this.vlFuturo = vlFuturo;
	}


	public double getTaxaInternaDeRetorno() {
		return taxaInternaDeRetorno;
	}


	boolean validarDadosEntrada()
	{
		// Valida��o do Valor Financiado
		
		if (vlFinanciado <= 0)
		{
			idRetorno = 1;
			msgRetorno = "C�lculo da Taxa Interna de Retorno - Valor Financiado Inv�lido " + vlFinanciado; 
			return false;
		}
		
		if ( qtdeParcelas <= 0 )
		{
			idRetorno = 2;
			msgRetorno = "C�lculo da Taxa Interna de Retorno - Quantidade de Parcelas Inv�lido " + qtdeParcelas;
			return false;
		}
		
		if (baseTaxa != 252 && baseTaxa != 360)
		{
			idRetorno = 3;
			msgRetorno = "C�lculo da Taxa Interna de Retorno - Base da Taxa Inv�lida (252/360) " + baseTaxa; 
			return false;
		}
		
		for (int i = 0; i < qtdeParcelas; i++)
		{
			if (qtdeDias.get(i) <= 0 )
			{
				idRetorno = 4;
				msgRetorno = "C�lculo da Taxa Interna de Retorno - Qtde de Dias Inv�lido, ocorr�ncia " + i
							 + ", qtde de dias = " + qtdeDias.get(i);
				return false;
			}
			
			if (vlFuturo.get(i) <= 0)
			{
				idRetorno = 5;
				msgRetorno = "C�lculo da Taxa Interna de Retorno - Valor Futuro Inv�lido, ocorr�ncia " + i
							+ ", Vl Futuro = " + vlFuturo.get(i);
				return false;
			}
		}
		
		return true;
	}
	
	public double calcTaxaInternaDeRetorno()
	{
		return taxaInternaDeRetorno;
	}
}
