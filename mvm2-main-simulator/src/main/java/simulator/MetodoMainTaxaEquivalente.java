package simulator;

import mvm2services.services.TaxaEquivalente;
import mvm2services.services.TaxaEquivalenteJrComposto;
import mvm2services.services.TaxaEquivalenteJrSimples;

public class MetodoMainTaxaEquivalente {

	public static void main(String[] args) {
		
		// Juros Composto
		
		TaxaEquivalente txEquivalenteJrComposto = new TaxaEquivalenteJrComposto();
		txEquivalenteJrComposto.setTaxaInformada(12);
		txEquivalenteJrComposto.setBaseTaxa(252);
		txEquivalenteJrComposto.setPeriodicidadeInformada('A');
		txEquivalenteJrComposto.setPeriodicidadeDesejada('A');
		double taxaEquivalente = txEquivalenteJrComposto.calcularTaxaEquivalente();
		
		System.out.println(taxaEquivalente);

		// Juros Simples
		
		TaxaEquivalente txEquivalenteJrSimples = new TaxaEquivalenteJrSimples();
		txEquivalenteJrSimples.setTaxaInformada(1);
		txEquivalenteJrSimples.setBaseTaxa(252);
		txEquivalenteJrSimples.setPeriodicidadeInformada('M');
		txEquivalenteJrSimples.setPeriodicidadeDesejada('D');
		taxaEquivalente = txEquivalenteJrSimples.calcularTaxaEquivalente();
		
		System.out.println(taxaEquivalente);

	}

}