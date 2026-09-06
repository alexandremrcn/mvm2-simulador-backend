package mvm2services.services;

public class ValorFuturo {
	
	/* Calcular o Valor Futuro, a partir de
	 * - Valor Presente,
	 * - Taxa ao Ano
	 * - Per�odo - O Período deve estar de acordo com a capitalização do Fator da Taxa
	 * - Periodicidade
	 * - D - Diária;
	 * - M - Mensal
	 * - A - Anual.
	*/

	/* Atributos de Entrada */
		
		private double vlPresente = 0.000000;
		private double taxa = 0.000000000;
		private int periodo;
		private char periodicidade;

	/* Resultado */

		private double vlFuturo = 0.000000;

	/* Valida*/
		boolean resultValue;
		private int idRetorno = 0;
		private String msgRetorno = "";
		
		public ValorFuturo()
		{
			
		}
		
		public ValorFuturo(double vlPresente, double taxa, int periodo, char periodicidade) {
			super();
			this.vlPresente = vlPresente;
			this.taxa = taxa;
			this.periodo = periodo;
			this.periodicidade = periodicidade;
		}
		
		public double getVlPresente() {
			return vlPresente;
		}

		public void setVlPresente(double vlPresente) {
			this.vlPresente = vlPresente;
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

		public double getVlFuturo() {
			return vlFuturo;
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

			if (vlPresente  < 0)
			{
				idRetorno = 5;
				msgRetorno = "Cálculo do Valor Futuro - Valor Presente informado menor do que 0";
				return false;
			}
			else if (taxa <= 0 )
			{
				idRetorno = 6;
				msgRetorno = "Cálculo do Valor Futuro - Fator da Taxa informada inválida";
				return false;
			}
			else if (periodo <= 0)
			{	
				idRetorno = 7;
				msgRetorno = "Cálculo do Valor Futuro - periodo informado inválido";
				return false;
			} 
			else if (periodicidade != 'A' && periodicidade != 'M' && periodicidade != 'D')
			{
				idRetorno = 8;
				msgRetorno = "Cálculo do Valor Futuro - perdiodicidade inválida";
				return false;
			}
			else
			{
				return true;
			}
		}
		
		public double calcularValorFuturo()
		{
			return vlFuturo;
		}
}
