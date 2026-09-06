package simulator;

import java.text.DecimalFormat;
import java.util.ArrayList;

import mvm2services.services.Fatores;
import mvm2services.services.FatoresGAP;
import mvm2services.services.FatoresVencimento;

public class MetodoMainFatores {

	public static void main(String[] args) {

		// Utilizando Fatores Vencimento
		
		Fatores ft = new FatoresVencimento();
		ft.setBaseTaxa((int) 252) ;
		ft.setQtdeParcelas((int) 500);
		ft.setTaxaAno((double) 9.769);
		
		// int y[] = new int[ft.getQtdeParcelas()];
		ArrayList<Integer> y = new ArrayList<Integer>();
		
		int z = 30;
		for (int x = 0; x< ft.getQtdeParcelas();x++)
		{
			y.add(z);
			z = z+30;
		}
		
		ft.setQtdeDias(y);

		ArrayList<Double> fatoresVCTO = ft.calcularFatores();
		
		// Utilizando Fatores GAP
		
		Fatores ftG = new FatoresGAP();
		ftG.setBaseTaxa((int) 252) ;
		ftG.setQtdeParcelas((int) 500);
		ftG.setTaxaAno((double) 9.769);
		ftG.setQtdeDias(y);
		
		ArrayList<Double> fatoresGAP = ftG.calcularFatores();
		
		DecimalFormat myFormatterVCTO = new DecimalFormat("000.00000000000000");
		DecimalFormat myFormatterGAP = new DecimalFormat("000.00000000000000");
		String outputVCTO;
		String outputGAP;
		
		if (ft.isResultValue() == true && ftG.isResultValue() == true)
		{
			for (int xpto = 0; xpto < ftG.getQtdeParcelas(); xpto++)
			{
				outputVCTO = myFormatterVCTO.format(fatoresVCTO.get(xpto));
				outputGAP = myFormatterGAP.format(fatoresGAP.get(xpto));
				
				System.out.println(outputVCTO +" "+ outputGAP);
			}
		}
		else
		{
			System.out.println("Resultado da valida��o CalcularFatores " + ft.getIdRetorno() + " " + ft.getMsgRetorno());
			System.out.println("Resultado da valida��o CalcularFatores GAP " + ftG.getIdRetorno() + " " + ftG.getMsgRetorno());
		}
	}
}
