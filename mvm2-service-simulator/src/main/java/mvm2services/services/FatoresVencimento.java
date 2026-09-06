package mvm2services.services;

import java.util.ArrayList;

public class FatoresVencimento extends Fatores {
	
	public ArrayList<Double> calcularFatores()
	{

		resultValue = super.validarDadosEntrada();
		ArrayList<Double> fatorTaxa = new ArrayList<Double>();
		
		if (resultValue == true)
		{
			ArrayList<Integer> qtdeDias = super.getQtdeDias();
			int qtdeParcelas = super.getQtdeParcelas();
			double fatorTaxaDia = super.calcularTaxaDia(super.getTaxaAno(), super.getBaseTaxa());

			for (int x = 0; x < qtdeParcelas; x++)
			{
				// Fator do per�odo at� o vencimento
				fatorTaxa.add(Math.pow(fatorTaxaDia, qtdeDias.get(x)));  
			}
			return fatorTaxa;
		}
		else
		{
			return fatorTaxa;
		}
	}
}
