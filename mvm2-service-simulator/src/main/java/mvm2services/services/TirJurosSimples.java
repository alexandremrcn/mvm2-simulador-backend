package mvm2services.services;

public class TirJurosSimples extends TaxaInternaDeRetorno {
	
	public double calcTaxaInternaDeRetorno()
	{
		
		resultValue = super.validarDadosEntrada();
		
		if (resultValue == true)
		{

			double vlFator002 = 0.000000000;
			double vlFator004 = 0.000000000;
			double vlFator005 = 0.000000000;
			double vlFator006 = 0.000000000;
			double vlFator007 = 0.000000000;
			double vlFator009 = 0.000000000;
			double vlFator010 = 0.000000000;
			double vlFator012 = 0.000000000;
			double vlFator013 = 0.000000000;
			double vlFator014 = 0.000000000;
			double vlFator015 = 0.000000000;
			double vlFator016 = 0.000000000;
			double vlFator017 = 0.000000000;
			double vlFator018 = 0.000000000;
			double vlFbase006 = 0.000000000;
			double vlDiferenca  = 0.000000000;

			double taxaInicialMes = 1.00000000; 						// Chute Inicial = 1% ao m�s
			double taxaInicialDia = taxaInicialMes / 30;
			
			double taxaInternaDia = taxaInicialDia;
			
			ValorPresente vlPresente = new ValorPresenteJrSimples();
			vlPresente.setPeriodicidade('D');

			// C�lculo do Valor Presente
			
			vlPresente.setTaxa(taxaInternaDia);
			
			vlFator002 = taxaInternaDia / 100;
			
			vlFator005 = 0;
			for (int i = 0; i < super.getQtdeParcelas(); i++)
			{
				vlPresente.setPeriodo(super.getQtdeDias().get(i));    
				vlPresente.setVlFuturo(super.getVlFuturo().get(i));
				vlFator004 = vlPresente.calcularValorPresente();
				vlFator005 = vlFator005 + vlFator004;
			}
			
			vlFator006 = super.getVlFinanciado() - vlFator005;
			vlDiferenca = vlFator006;
			if (vlDiferenca < 0)
				vlDiferenca = vlDiferenca * -1;
			
			vlFbase006 = vlFator006;  // Valor Base para compara��o - c�lculo do Fator013

			if (vlFator006 != 0.000000000)
			{

				// C�lculo do Delta
				
				vlFator007 = vlFator006 / super.getVlFinanciado(); 
				vlFator009 = 1 + (vlFator007 / 100);
				
				// C�lculo da Nova Taxa da Opera��o
				
				vlFator010 = (1 + vlFator002 ) / vlFator009;
				vlFator012 = vlFator010 - 1;
				vlFator012 = vlFator012 * 100;
				
				taxaInternaDia = vlFator012;
				
				// C�lculo do Valor Presente

				vlPresente.setTaxa(taxaInternaDia);
				vlFator002 = (taxaInternaDia / 100 ) + 1;
				
				vlFator005 = 0;
				for (int i = 0; i < super.getQtdeParcelas(); i++)
				{
					vlPresente.setPeriodo(super.getQtdeDias().get(i));    
					vlPresente.setVlFuturo(super.getVlFuturo().get(i));
					vlFator004 = vlPresente.calcularValorPresente();
					
					vlFator005 = vlFator005 + vlFator004;
				}
				
				vlFator006 = super.getVlFinanciado() - vlFator005;
				vlDiferenca = vlFator006;
				if (vlDiferenca < 0)
					vlDiferenca = vlDiferenca * -1;
				
				int iteracoes = 0;
				while (vlDiferenca > 0.00000001 && iteracoes < 1000)
				{
					vlFator013 = vlFbase006;
					vlFator013 = vlFator013 * -1;
					
					vlFator014 = vlFator006 - vlFbase006;
					
					if (vlFator014 == 0)
						vlFator014 = 1;
					
					vlFator015 = vlFator013 / vlFator014;
					vlFator016 = taxaInternaDia - taxaInicialDia;
					vlFator017 = vlFator015 * vlFator016;
					vlFator018 = vlFator017 + taxaInicialDia;
					taxaInternaDia = vlFator018;
					
					// C�lculo do Valor Presente

					vlPresente.setTaxa(taxaInternaDia);
					
					vlFator005 = 0;
					for (int i = 0; i < super.getQtdeParcelas(); i++)
					{
						vlPresente.setPeriodo(super.getQtdeDias().get(i));    
						vlPresente.setVlFuturo(super.getVlFuturo().get(i));
						vlFator004 = vlPresente.calcularValorPresente();
						vlFator005 = vlFator005 + vlFator004;
					}
					
					vlFator006 = super.getVlFinanciado() - vlFator005;
					vlDiferenca = vlFator006;
					if (vlDiferenca < 0)
						vlDiferenca = vlDiferenca * -1;
					
					iteracoes++;
				}
			}
			
			// Gerar Taxa Ano na Base 252 ou 360;
			
			taxaInternaDeRetorno = taxaInternaDia;
			taxaInternaDeRetorno = taxaInternaDeRetorno * super.getBaseTaxa();
			
		}
		
		return taxaInternaDeRetorno;
		
	}
}
