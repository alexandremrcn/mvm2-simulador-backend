package mvm2services.services;

import java.text.DecimalFormat;

public class ValorPresenteJrComposto extends ValorPresente {
	
	public double calcularValorPresente()
	{

		resultValue = super.validarDadosEntrada();

		if (resultValue == true)
		{

			double vlFuturo = super.getVlFuturo();
			double taxa = super.getTaxa();
			int periodo = super.getPeriodo();
			char periodicidade = super.getPeriodicidade();
			
			DecimalFormat df = new DecimalFormat();
			df.applyPattern("0.00");


			/* Cálculo do Valor Presente - Método exponencial*/
			
			double 	p1 = 0.000000000, 
					p2 = 0.000000000,
					p3 = 0.000000000,
					p4 = 0.000000000,
					p5 = 0.000000000;
					
			p1 = taxa / (double) 100;							// Calcular Taxa / 100
			p2 = (double) 1 + p1;								// Calcular fator da taxa
			
			if (periodicidade == 'A')
				p3 = ( (double) 1 / (double) 360);              // Calcular a fração de 360 dias em relação a um ano
			else if (periodicidade == 'M')
				p3 = ( (double) 1 / (double) 12);               // Calcular a fração de 1 mês em relação a um ano
			else if (periodicidade == 'D')
				p3 = (double) 1;                             	// A periodicidade informada � igual � periodicidade da taxa
			
			p4 = Math.pow(p2, p3);								// Calcular fator equivalente a 1 período
			p5 = Math.pow(p4, periodo);							// Calcular fator para a quantidad de períodos informada
			double vlPresente = ( vlFuturo / p5); 				// Calcular o Valor Presente
			
			return NumberConverter.convertToDouble(df.format(vlPresente));
		} 
		else
		{
			return 0.00;
		}
	}
}
