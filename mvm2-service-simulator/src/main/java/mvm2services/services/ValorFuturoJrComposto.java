package mvm2services.services;

public class ValorFuturoJrComposto extends ValorFuturo {
	
	public double calcularValorFuturo()
	{

		resultValue = super.validarDadosEntrada();

		if (resultValue == true)
		{
			double vlPresente = super.getVlPresente();
			double taxa = super.getTaxa();
			int periodo = super.getPeriodo();
			char periodicidade = super.getPeriodicidade();

			/* C�lculo do Valor Futuro - M�todo exponencial*/
			
			double 	p1 = 0.000000000, 
					p2 = 0.000000000,
					p3 = 0.000000000,
					p4 = 0.000000000,
					p5 = 0.000000000;
					
			p1 = taxa / (double) 100;							// Calcular Taxa / 100
			p2 = (double) 1 + p1;								// Calcular fator da taxa
			
			if (periodicidade == 'A')
				p3 = ( (double) 1 / (double) 360);              // Calcular a fra��o de 360 dias em rela��o a um ano
			else if (periodicidade == 'M')
				p3 = ( (double) 1 / (double) 12);               // Calcular a fra��o de 1 m�s em rela��o a um ano
			else if (periodicidade == 'D')
				p3 = (double) 1;                             	// A periodicidade informada � igual � periodicidade da taxa
			
			p4 = Math.pow(p2, p3);								// Calcular fator equivalente a 1 per�odo
			p5 = Math.pow(p4, periodo);							// Calcular fator para a quantidade de per�odos informada
			double vlFuturo = ( vlPresente * p5); 				// Calcular o Valor Futuro
			
			return vlFuturo;
		} 
		else
		{
			return 0;
		}
	}
}
