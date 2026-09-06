package mvm2services.services;

public class TaxaEquivalenteJrSimples extends TaxaEquivalente {

	
	public double calcularTaxaEquivalente()
	{
		
		resultValue = super.validarDadosEntrada();
		double taxaDesejada = 0;
		
		if (resultValue == true)
		{
			
			double periodT = 0.000000000;
			double periodD = 0.000000000;
			
			switch (super.getPeriodicidadeInformada()) {
				case 'A': 
					periodT = 1;
					break;
				case 'M': 
					periodT = 12;
					break;
				default:
					if (super.getBaseTaxa() == 360)
						periodT = 360;
					else
						periodT = 252;
					break;
			}
			

			switch (super.getPeriodicidadeDesejada()) {
			case 'A': 
				periodD = 1;
				break;
			case 'M': 
				periodD = 12;
				break;
			default:
				if (super.getBaseTaxa() == 360)
					periodD = 360;
				else
					periodD = 252;
				break;
			}

			double p1 = 0.000000000;
			double p2 = 0.000000000;
			double p3 = 0.000000000;
			
			p1 = super.getTaxaInformada();
			p2 = (periodT / periodD);
			p3 = p1 * p2;
			
			taxaDesejada = p3;
				
		}
		
		return taxaDesejada;

	}
}
