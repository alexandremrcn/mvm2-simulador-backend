package simulator;

import java.text.DecimalFormat;

import mvm2services.services.ValorFuturo;
import mvm2services.services.ValorFuturoJrComposto;
import mvm2services.services.ValorFuturoJrSimples;

public class MetodoMainValorFuturo {

	public static void main(String[] args) {
		
		double vlTeste = 2970.2970297;
		double taxa_ano = 12.000;
		int periodo = 30; // quantidade de dias
		char periodicidade = 'A'; // Periodicidade da Taxa
		
		
// Valida��o da Classe ValorFuturo, ValorFuturoJrComposto, ValorFuturoJrSimples

// ValorFuturoJrComposto
		
		ValorFuturo vlrFuturoComposto = new ValorFuturoJrComposto();
		
		vlrFuturoComposto.setVlPresente(vlTeste);
		vlrFuturoComposto.setTaxa(taxa_ano);
		vlrFuturoComposto.setPeriodo(periodo);
		vlrFuturoComposto.setPeriodicidade(periodicidade);
		
		double vlrResultadoComposto = vlrFuturoComposto.calcularValorFuturo();
		
		if (vlrFuturoComposto.isResultValue() == true)
		{
			DecimalFormat myFormatter = new DecimalFormat("###,###,###,###.######");
			String output = myFormatter.format(vlrResultadoComposto);
			System.out.println(output); 			
			
		}
		else
		{
			System.out.println(vlrFuturoComposto.getIdRetorno());
			System.out.println(vlrFuturoComposto.getMsgRetorno());
		}

// ValorFuturoJrSimples
		
		ValorFuturo vlrFuturoSimples = new ValorFuturoJrSimples();
		
		vlrFuturoSimples.setVlPresente(vlTeste);
		vlrFuturoSimples.setTaxa(taxa_ano);
		vlrFuturoSimples.setPeriodo(periodo);
		vlrFuturoSimples.setPeriodicidade(periodicidade);
		
		double vlrResultadoSimples = vlrFuturoSimples.calcularValorFuturo();
		
		if (vlrFuturoSimples.isResultValue() == true)
		{
			DecimalFormat myFormatter = new DecimalFormat("###,###,###,###.######");
			String output = myFormatter.format(vlrResultadoSimples);
			System.out.println(output); 			
		}
		else
		{
			System.out.println(vlrFuturoSimples.getIdRetorno());
			System.out.println(vlrFuturoSimples.getMsgRetorno());
		}
	}
}
