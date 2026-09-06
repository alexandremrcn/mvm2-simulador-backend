package mvm2services.services;

public class ValorPresente {

/* Calcular o Valor Presente, a partir de
 * - Valor Futuro,
 * - Taxa ao Ano
 * - Per�odo - O Per�odo deve estar de acordo com a capitaliza��o do Fator da Taxa
 * - Periodicidade
 * - D - Di�ria;
 * - M - Mensal
 * - A - Anual.
*/

	enum TipoJuros 
	{ 	JUROS_COMPOSTO,
		JUROS_SIMPLES
	};
		

/* Atributos de Entrada */
	
	private double vlFuturo = 0.000000;
	private double taxa = 0.000000000;
	private int periodo;
	private char periodicidade;

/* Resultado */

	private double vlPresente = 0.000000;

/* Valida��o */
	boolean resultValue;
	private int idRetorno = 0;
	private String msgRetorno = "";
	
	
	public ValorPresente(double vlFuturo, double taxa, int periodo, char periodicidade) {
		super();
		this.vlFuturo = vlFuturo;
		this.taxa = taxa;
		this.periodo = periodo;
		this.periodicidade = periodicidade;
	}
	
	public ValorPresente()
	{
		
	}

	public double getVlFuturo() {
		return vlFuturo;
	}

	public void setVlFuturo(double vlFuturo) {
		this.vlFuturo = vlFuturo;
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public char getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(char periodicidade) {
		this.periodicidade = periodicidade;
	}

	public double getVlPresente() {
		return vlPresente;
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

		if (vlFuturo  < 0)
		{
			idRetorno = 1;
			msgRetorno = "C�lculo do Valor Presente - Valor Futuro informado menor do que 0";
			return false;
		}
		else if (taxa == 0 )
		{
			idRetorno = 2;
			msgRetorno = "C�lculo do Valor Presente - Fator da Taxa informada inv�lida";
			return false;
		}
		else if (periodo <= 0)
		{	
			idRetorno = 3;
			msgRetorno = "C�lculo do Valor Presente - periodo informado inv�lido";
			return false;
		} 
		else if (periodicidade != 'A' && periodicidade != 'M' && periodicidade != 'D')
		{
			idRetorno = 4;
			msgRetorno = "C�lculo do Valor Presente - perdiodicidade inv�lida";
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public double calcularValorPresente()
	{
		return vlPresente;
	}
}
