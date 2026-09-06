package simulator;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import mvm2services.services.Datas;
import mvm2services.services.ExpectativaDeRecebimento;
import mvm2services.services.ExpectativaRectoEspecial;


public class MetodoMainExpRectoEspecial {

	public static void main(String[] args) {

		double vlPresente = 100000.00;
		int qtdeParcelas = 10;


		// Data da simulação
		Datas datas = new Datas();

		datas.setDiaBaseVcto(29);
		LocalDate localDate = LocalDate.now();
		String dtCorrente = String.format("%04d",localDate.getYear()) + 
							String.format("%02d",localDate.getMonthValue()) +
							String.format("%02d",localDate.getDayOfMonth());
		datas.setDtReferencia(dtCorrente);

		datas.setDtVct1ap("20230629");
		char idTaxaFator = 'T';
		double taxa = 10.00;
		int baseTaxa = 360;
		char periodicidadeTaxa = 'A';
		char periodicidadeVcto = 'M';
		ArrayList<Double> fatorTaxa = new ArrayList<Double>();
		ArrayList<Double> fatorTaxaGAP = new ArrayList<Double>();
		char tipoJuros = 'S';
		int cdSistemaAmortizacao = 2;
		double vl1aParcela = 0.00;
		ArrayList<String> idCobrancaJuros = new ArrayList<String>();
		ArrayList<String> idCobrancaPrincipal = new ArrayList<String>();
		

		datas.setIdDiaUtil('N');
		datas.setPeriodicidadeVcto(periodicidadeVcto);
		//datas.setQtdeDiasCarencia(90);
		datas.setQtdeParcelas(qtdeParcelas);
		
		
		boolean validaDatas = datas.calcularLinhaDoTempo();
		ArrayList<Integer> qtdeDiasVcto = datas.getQtdeDiasCorridos();
		ArrayList<Integer> qtdeDiasGAP = new ArrayList<Integer>();
		
		ArrayList<String> idCobrJuros = new ArrayList<String>();
		ArrayList<String> idCobrPrinc = new ArrayList<String>();
		ArrayList<Double> vlParcelaIn = new ArrayList<Double>();
		
		
		for (int i = 0; i < qtdeParcelas; i++)
		{
			if (i < 7)
				idCobrPrinc.add("N");
			else
				idCobrPrinc.add("S");
			
			idCobrJuros.add("S");
			
/*			if (i <= 99)
				vlParcelaIn.add(0.00);
			else if (i == 11)
				vlParcelaIn.add(15000.00);
			else
				vlParcelaIn.add(0.00);
*/			
		}
		
		ExpectativaDeRecebimento expRecebimento = new ExpectativaRectoEspecial();
		expRecebimento.setBaseTaxa(baseTaxa);
		expRecebimento.setCdSistemaAmortizacao(cdSistemaAmortizacao);
		expRecebimento.setFatorTaxa(fatorTaxa);
		expRecebimento.setFatorTaxaGAP(fatorTaxaGAP);
		expRecebimento.setIdCobrancaJuros(idCobrancaJuros);
		expRecebimento.setIdTaxaFator(idTaxaFator);
		expRecebimento.setPeriodicidadeTaxa(periodicidadeTaxa);
		expRecebimento.setPeriodicidadeVcto(periodicidadeVcto);
		expRecebimento.setQtdeDiasVcto(qtdeDiasVcto);
		expRecebimento.setQtdeDiasGAP(qtdeDiasGAP);
		expRecebimento.setQtdeParcelas(qtdeParcelas);
		expRecebimento.setTaxa(taxa);
		expRecebimento.setTipoJuros(tipoJuros);
		expRecebimento.setVl1aParcela(vl1aParcela);
		expRecebimento.setVlPresente(vlPresente);

		

		
//		expRecebimento.setVlParcelaIn(vlParcelaIn);
		expRecebimento.setIdCobrancaJuros(idCobrJuros);
		expRecebimento.setIdCobrancaPrincipal(idCobrPrinc);
		
		boolean calcularFluxo = expRecebimento.calcularFluxo();
		
		if (calcularFluxo == true)
		{

			DecimalFormat dcInteiro = new DecimalFormat("000000");
			DecimalFormat dcValor = new DecimalFormat("00000000.00");
			DecimalFormat dcFator = new DecimalFormat("000.00000000");

			System.out.println("Valor Presente = " + dcValor.format(expRecebimento.getVlPresente()));
			System.out.println("Taxa de Juros = " + dcValor.format(expRecebimento.getTaxa()));
			System.out.println("Sistema de Amortiza��o = " + expRecebimento.getCdSistemaAmortizacao());

			System.out.println("N�      Dias Corridos    Dt Vencimento   Fator             Fator GAP          Parcela         "
					            + "Juros           Principal       Saldo Remanescente");
			for (int i = 0; i < qtdeParcelas; i++)
			{
				
				System.out.println(dcInteiro.format(expRecebimento.getNumParcela().get(i)) + "    "+ 
						   dcInteiro.format(expRecebimento.getQtdeDiasVcto().get(i)) + "            " +
						   datas.getDtVencimento().get(i)                               +"     "      +
						   dcFator.format(expRecebimento.getFatorTaxa().get(i)) + "      "            +
						   dcFator.format(expRecebimento.getFatorTaxaGAP().get(i)) + "       "        +
						   dcValor.format(expRecebimento.getVlParcela().get(i)) 		+"     "      +
						   dcValor.format(expRecebimento.getVlJuros().get(i))   		+"     "      +
						   dcValor.format(expRecebimento.getVlPrincipal().get(i)) 		+"     "      +
						   dcValor.format(expRecebimento.getVlSdoRemanescente().get(i)));
			}
		}
	}
}
