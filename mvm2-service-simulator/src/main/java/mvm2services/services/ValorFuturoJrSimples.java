package mvm2services.services;

import java.text.DecimalFormat;

public class ValorFuturoJrSimples extends ValorFuturo {

	public double calcularValorFuturo()
	{
		
		resultValue = super.validarDadosEntrada();

		if (resultValue == true)
		{
			double vlPresente = super.getVlPresente();
			double taxa = super.getTaxa();
			int periodo = super.getPeriodo();
			char periodicidade = super.getPeriodicidade();
			
			DecimalFormat df = new DecimalFormat();
			df.applyPattern("0.00");

			/* Cálculo do Valor Futuro - Método - Juros Simples */
			
			double 	p1 = 0.000000000,
					p2 = 0.000000000,
					p3 = 0.000000000,
					p4 = 0.000000000,
					p5 = 0.000000000;
			
			p1 = (taxa / (double) 100);					// Calcular Taxa / 100
			
			if (periodicidade == 'A')
				p2 = (double) 360; 						// A Taxa ao ano possui 360 dias
			else if (periodicidade == 'M')
				p2 = (double) 12;						// A Taxa ao ano possui 12 meses 
			else if (periodicidade == 'D')
				p2 = (double) 1;						// A periodicidade informada � igual � periodicidade da taxa
			
			p3 = p1 / p2;								// Calcular taxa Equivalente a 1 per�odo;
			p4 = p3 * periodo; 							// Calcular 1 per�odo da taxa;
			p5 = p4 + (double) 1;						// Calcular fator da Taxa;
			
			double vlFuturo = (vlPresente * p5);        // Calcular Valor Futuro
			return NumberConverter.convertToDouble(df.format(vlFuturo));
		} 
		else
		{
			return 0.00;
		}
	}
}
