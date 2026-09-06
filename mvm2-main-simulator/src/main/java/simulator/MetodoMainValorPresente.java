package simulator;

import java.text.DecimalFormat;

import mvm2services.services.ValorPresente;
import mvm2services.services.ValorPresenteJrComposto;
import mvm2services.services.ValorPresenteJrSimples;

public class MetodoMainValorPresente {

	public static void main(String[] args) {
		
		double vlTeste = 3000.00;
		double taxa_ano = 12;
		int periodo = 30;
		char periodicidade = 'A';
		
		
// Valida��o da Classe ValorPresente, ValorPresenteJrComposto, ValorPresenteJrSimples

// ValorPresenteJrComposto
		
		ValorPresente vlrPresenteComposto = new ValorPresenteJrComposto();
		
		vlrPresenteComposto.setVlFuturo(vlTeste);
		vlrPresenteComposto.setTaxa(taxa_ano);
		vlrPresenteComposto.setPeriodo(periodo);
		vlrPresenteComposto.setPeriodicidade(periodicidade);
		
		double vlrResultadoComposto = vlrPresenteComposto.calcularValorPresente();
		
		if (vlrPresenteComposto.isResultValue() == true)
		{
			DecimalFormat myFormatter = new DecimalFormat("###,###,###,###.######");
			String output = myFormatter.format(vlrResultadoComposto);
			System.out.println(output); 			
			
		}
		else
		{
			System.out.println(vlrPresenteComposto.getIdRetorno());
			System.out.println(vlrPresenteComposto.getMsgRetorno());
		}

// ValorPresenteJrSimples
		
		ValorPresente vlrPresenteSimples = new ValorPresenteJrSimples();
		
		vlrPresenteSimples.setVlFuturo(vlTeste);
		vlrPresenteSimples.setTaxa(taxa_ano);
		vlrPresenteSimples.setPeriodo(periodo);
		vlrPresenteSimples.setPeriodicidade(periodicidade);
		
		double vlrResultadoSimples = vlrPresenteSimples.calcularValorPresente();
		
		if (vlrPresenteSimples.isResultValue() == true)
		{
			DecimalFormat myFormatter = new DecimalFormat("###,###,###,###.######");
			String output = myFormatter.format(vlrResultadoSimples);
			System.out.println(output); 			
		}
		else
		{
			System.out.println(vlrPresenteSimples.getIdRetorno());
			System.out.println(vlrPresenteSimples.getMsgRetorno());
		}
	}
}
