package simulator;

import java.text.DecimalFormat;

import java.time.LocalDate;
import java.util.ArrayList;

import mvm2services.services.Datas;
import mvm2services.services.ExpectativaDeRecebimento;
import mvm2services.services.ExpectativaRectoPRICEHP;

public class MetodoMainExpRecebimento {

	public static void main(String[] args) {

		double vlPresente = 0.00;
		int qtdeParcelas = 360;
		Datas datas = new Datas();
		
		// Data da simulação - recupera data do dia
		LocalDate localDate = LocalDate.now();
		String dtCorrente = String.format("%04d",localDate.getYear()) + 
				String.format("%02d",localDate.getMonthValue()) +
				String.format("%02d",localDate.getDayOfMonth());
        datas.setDtReferencia(dtCorrente);
		
        char periodicidadeVcto = 'M';

        char idTaxaFator = 'T';
		double taxa = 2.50;
		int baseTaxa = 252;
		char periodicidadeTaxa = 'M';
		ArrayList<Double> fatorTaxa = new ArrayList<Double>();
		ArrayList<Double> fatorTaxaGAP = new ArrayList<Double>();
		char tipoJuros = 'S';
		int cdSistemaAmortizacao = 2;
		double vl1aParcela = 1800.00;
		ArrayList<String> idCobrancaJuros = new ArrayList<String>();
		ArrayList<String> idCobrancaPrincipal = new ArrayList<String>();

        // primeiro vencimento
		//datas.setDtVct1ap("20220820");

		
		datas.setDiaBaseVcto(22);
		datas.setIdDiaUtil('N');
		datas.setPeriodicidadeVcto(periodicidadeVcto);
		datas.setQtdeDiasCarencia(0);
		datas.setQtdeParcelas(qtdeParcelas);
		
		
		boolean validaDatas = datas.calcularLinhaDoTempo();
		ArrayList<Integer> qtdeDiasVcto = datas.getQtdeDiasCorridos();
		ArrayList<Integer> qtdeDiasGAP = new ArrayList<Integer>();
		ArrayList<Double> vlParcelaIn = new ArrayList<Double>();
		
		ExpectativaDeRecebimento expRecebimento = new ExpectativaRectoPRICEHP();
		//ExpectativaRectoPRICEHP expRecebimento = new ExpectativaRectoPRICEHP();
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
		expRecebimento.setVlParcelaIn(vlParcelaIn);
		expRecebimento.setVlPresente(vlPresente);

		boolean calcularFluxo = expRecebimento.calcularFluxo();
		
		if (calcularFluxo == true)
		{

			DecimalFormat dcInteiro = new DecimalFormat("000000");
			DecimalFormat dcValor = new DecimalFormat("00000000.00");
			DecimalFormat dcFator = new DecimalFormat("000.00000000");

			System.out.println("Valor Presente = " + dcValor.format(expRecebimento.getVlPresente()));
			System.out.println("Taxa de Juros = " + dcValor.format(expRecebimento.getTaxa()));
			System.out.println("Sistema de Amortização = " + expRecebimento.getCdSistemaAmortizacao());

			System.out.println("Nº      Dias Corridos    Dt Vencimento   Fator             Fator GAP          Parcela         "
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
