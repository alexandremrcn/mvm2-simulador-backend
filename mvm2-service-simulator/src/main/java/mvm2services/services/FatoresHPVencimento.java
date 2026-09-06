package mvm2services.services;

import java.util.ArrayList;

public class FatoresHPVencimento extends FatoresHP {
	
	public ArrayList<Double> calcularFatoresHP()
	{

		resultValue = super.validarDadosEntrada();
		ArrayList<Double> fatorTaxa = new ArrayList<Double>();
		char periodicidade = super.getPeriodicidade();
		
		double fTaxaPeriodo = ( super.getTaxaAno() / 100 ) + 1;
		
		double proRata = 1.00;
		
		
		if (periodicidade == 'M')
		{
			proRata = (double) 1 / (double)  12;
		}
		else if (periodicidade == 'T')
		{
			proRata = (double)1 / (double)4;
		}
		else if (periodicidade == 'S')
		{
			proRata = (double) 1 / (double) 2;
		}
		
		fTaxaPeriodo = Math.pow(fTaxaPeriodo, proRata);
		
		if (resultValue == true)
		{
			int qtdeParcelas = super.getQtdeParcelas();

			int index = 1;
			for (int x = 0; x < qtdeParcelas; x++)
			{
				fatorTaxa.add(Math.pow(fTaxaPeriodo,  index));
				index++;
			}
			return fatorTaxa;
		}
		else
		{
			return fatorTaxa;
		}
	}


}
