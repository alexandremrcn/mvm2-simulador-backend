package mvm2services.services;

public class TaxaEquivalente {
	

	// Atributos de Entrada
	
	private double taxaInformada;
	private char periodicidadeInformada;
	private int baseTaxa;
	private char periodicidadeDesejada;

	
	// Atributo de Sa�da
	private double taxaDesejada;
	
	// Tratamento de erro;
	
	boolean resultValue;
	int idRetorno;
	String msgRetorno = new String();

	
	
	public TaxaEquivalente(double taxaInformada, char periodicidadeInformada, int baseTaxa, char periodicidadeDesejada,
			double taxaDesejada, boolean resultValue, int idRetorno, String msgRetorno) {
		super();
		this.taxaInformada = taxaInformada;
		this.periodicidadeInformada = periodicidadeInformada;
		this.baseTaxa = baseTaxa;
		this.periodicidadeDesejada = periodicidadeDesejada;
		this.taxaDesejada = taxaDesejada;
		this.resultValue = resultValue;
		this.idRetorno = idRetorno;
		this.msgRetorno = msgRetorno;
	}
	public TaxaEquivalente()
	{
		
	}
	public double getTaxaInformada() {
		return taxaInformada;
	}
	public void setTaxaInformada(double taxaInformada) {
		this.taxaInformada = taxaInformada;
	}
	public char getPeriodicidadeInformada() {
		return periodicidadeInformada;
	}
	public void setPeriodicidadeInformada(char periodicidadeInformada) {
		this.periodicidadeInformada = periodicidadeInformada;
	}
	public int getBaseTaxa() {
		return baseTaxa;
	}
	public void setBaseTaxa(int baseTaxa) {
		this.baseTaxa = baseTaxa;
	}
	public char getPeriodicidadeDesejada() {
		return periodicidadeDesejada;
	}
	public void setPeriodicidadeDesejada(char periodicidadeDesejada) {
		this.periodicidadeDesejada = periodicidadeDesejada;
	}
	public double getTaxaDesejada() {
		return taxaDesejada;
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

	boolean validarDadosEntrada()
	{
		
/*		private double taxaInformada;
		private char periodicidadeInformada;
		private int baseTaxa;
		private char periodicidadeDesejada;
*/		
		if (periodicidadeInformada != 'A' && periodicidadeInformada != 'M' && periodicidadeInformada != 'D')
		{
			idRetorno = 1;
			msgRetorno = "C�lculo da Taxa Equivalente - Periodicidade (A/M/D) da Taxa Inv�lida  - " 
			+ periodicidadeInformada;
			return false;
		}
		
		if (baseTaxa != 360 && baseTaxa != 252)
		{
			idRetorno = 2;
			msgRetorno = "C�lculo da Taxa Equivalente - Base (360/252) da Taxa Inv�lida  - " 
			+ baseTaxa;
			return false;
		}
		
		if (periodicidadeDesejada != 'A' && periodicidadeDesejada != 'M' && periodicidadeDesejada != 'D')
		{
			idRetorno = 1;
			msgRetorno = "C�lculo da Taxa Equivalente - Periodicidade desejada (A/M/D) da Taxa Inv�lida  - " 
			+ periodicidadeDesejada;
			return false;
		}
		
		return true;
	}
	
	public double calcularTaxaEquivalente()
	{
		return taxaDesejada;
	}

}
