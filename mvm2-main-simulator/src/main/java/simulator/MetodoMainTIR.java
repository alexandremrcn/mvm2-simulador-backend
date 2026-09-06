package simulator;

import mvm2services.services.Datas;
import mvm2services.services.TaxaInternaDeRetorno;
import mvm2services.services.TirJurosComposto;
import mvm2services.services.TirJurosSimples;

public class MetodoMainTIR {

	public static void main(String[] args) {
		
		Datas datas = new Datas();
		datas.setDiaBaseVcto(04);
		datas.setDtReferencia("20210902");
		datas.setIdDiaUtil('N');
		datas.setPeriodicidadeVcto('M');
		datas.setQtdeDiasCarencia(0);
		datas.setQtdeParcelas(120);
		datas.calcularDtVtc1ap();
		datas.calcularLinhaDoTempo();
		
		// C�LCULO DA TAXA INTERNA DE RETORNO - JUROS COMPOSTO
		
		TaxaInternaDeRetorno txInternaComposto = new TirJurosComposto();
		txInternaComposto.setBaseTaxa(360);
		txInternaComposto.setQtdeParcelas(datas.getQtdeParcelas());
		txInternaComposto.setVlFinanciado(80000.00);
		
		if (datas.isResultValue() == true)
		{
			txInternaComposto.setQtdeDias(datas.getQtdeDiasCorridos());
			
			for (int i = 0; i < txInternaComposto.getQtdeParcelas(); i++)
			{
				txInternaComposto.getVlFuturo().add(3000.00);
			}
			
			double taxa = txInternaComposto.calcTaxaInternaDeRetorno();
			
			if (txInternaComposto.isResultValue() == true)
			{
				System.out.println(String.format("%08f", taxa));
			}
			else
			{
				System.out.println(txInternaComposto.getIdRetorno());
				System.out.println(txInternaComposto.getMsgRetorno());
			}
			
		}
		else
		{
			System.out.println(datas.getIdRetorno());
			System.out.println(datas.getMsgRetorno());
		}
		
		// C�LCULO DA TAXA INTERNA DE RETORNO - JUROS SIMPLES

		TaxaInternaDeRetorno txInternaSimples = new TirJurosSimples();
		txInternaSimples.setBaseTaxa(360);
		txInternaSimples.setQtdeParcelas(datas.getQtdeParcelas());
		txInternaSimples.setVlFinanciado(txInternaComposto.getVlFinanciado());
		
		if (datas.isResultValue() == true)
		{
			txInternaSimples.setQtdeDias(datas.getQtdeDiasCorridos());
			
			for (int i = 0; i < txInternaSimples.getQtdeParcelas(); i++)
			{
				txInternaSimples.getVlFuturo().add(3000.00);
			}
			
			double taxa = txInternaSimples.calcTaxaInternaDeRetorno();
			
			if (txInternaSimples.isResultValue() == true)
			{
				System.out.println(String.format("%08f", taxa));
			}
			else
			{
				System.out.println(txInternaSimples.getIdRetorno());
				System.out.println(txInternaSimples.getMsgRetorno());
			}
			
		}
		else
		{
			System.out.println(datas.getIdRetorno());
			System.out.println(datas.getMsgRetorno());
		}

	}
}
