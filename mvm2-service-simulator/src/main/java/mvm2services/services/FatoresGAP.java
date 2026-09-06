package mvm2services.services;

import java.util.ArrayList;

public class FatoresGAP extends Fatores {

	public ArrayList<Double> calcularFatores()
	{
		
		resultValue = super.validarDadosEntrada();
		ArrayList<Double> fatorTaxa = new ArrayList<Double>();

		if (resultValue == true)
		{
			double fatorTaxaDia = super.calcularTaxaDia(super.getTaxaAno(), super.getBaseTaxa());
			ArrayList<Integer> qtdeDias = super.getQtdeDias();
			int qtdeParcelas = super.getQtdeParcelas();
			

			for (int x = 0; x < qtdeParcelas; x++)
			{
				if (x == 0)
				{
					// Fator do primeiro vencimento
					fatorTaxa.add(Math.pow(fatorTaxaDia, (int) qtdeDias.get(x)));
				}
				else
				{
					// Fator do GAP (vencimento atual - vencimento anterior)
					fatorTaxa.add(Math.pow(fatorTaxaDia, (int) qtdeDias.get(x)) /    
									Math.pow(fatorTaxaDia, (int) qtdeDias.get(x-1)));
				}
			}
			return fatorTaxa;
		}
		else
		{
			return fatorTaxa;
		}
	}
}
