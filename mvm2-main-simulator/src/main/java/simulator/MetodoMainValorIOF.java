package simulator;

import java.text.DecimalFormat;

import java.time.LocalDate;
import java.util.ArrayList;

import mvm2services.services.Datas;
import mvm2services.services.ExpectativaDeRecebimento;
import mvm2services.services.ExpectativaRectoPRICE;
import mvm2services.services.ValorIOF;

public class MetodoMainValorIOF {

	public static void main(String[] args) {
		
		double vlPresente = 0.00;
		char idTaxaFator = 'T';
		double taxa = 7.3;
		int baseTaxa = 360;
		char periodicidadeTaxa = 'A';
		char periodicidadeVcto = 'M';
		ArrayList<Double> fatorTaxa = new ArrayList<Double>();
		ArrayList<Double> fatorTaxaGAP = new ArrayList<Double>();
		char tipoJuros = 'S';
		int qtdeParcelas = 2;
		int cdSistemaAmortizacao = 2;
		double vl1aParcela = 5000.00;
		ArrayList<String> idCobrancaJuros = new ArrayList<String>();
		ArrayList<String> idCobrancaPrincipal = new ArrayList<String>();
		
		Datas datas = new Datas();
		
		datas.setDiaBaseVcto(10);
		
		LocalDate localDate = LocalDate.now();
		String dtCorrente = String.format("%04d",localDate.getYear()) + 
							String.format("%02d",localDate.getMonthValue()) +
							String.format("%02d",localDate.getDayOfMonth());
		datas.setDtReferencia(dtCorrente);
		
		datas.setIdDiaUtil('N');
		datas.setPeriodicidadeVcto(periodicidadeVcto);
		datas.setQtdeDiasCarencia(0);
		datas.setQtdeParcelas(qtdeParcelas);
		
		boolean validaDatas = datas.calcularLinhaDoTempo();
		ArrayList<Integer> qtdeDiasVcto = datas.getQtdeDiasCorridos();
		ArrayList<Integer> qtdeDiasGAP = new ArrayList<Integer>();
		ArrayList<Double> vlParcelaIn = new ArrayList<Double>();
		
		ExpectativaDeRecebimento expRecebimento = new ExpectativaRectoPRICE();
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
		
		ValorIOF valorIOF = new ValorIOF();
		
		valorIOF.setDiasCorridos(datas.getQtdeDiasCorridos());
		valorIOF.setIofEmbutido(false);
		valorIOF.setTipoNegociacaoIOF('1');
		valorIOF.setVlAliquota(3.00);
		valorIOF.setVlAliquotaAdicional(0.38);
		valorIOF.setVlPresente(expRecebimento.getVlPresente());
		valorIOF.setVlPrincipal(expRecebimento.vlPrincipal);
		
		double vlIOF = valorIOF.calcularIOF();
		
		
		if (valorIOF.getTipoNegociacaoIOF() == '1')
			vlPresente = expRecebimento.getVlPresente() + vlIOF;
		
		expRecebimento.setVlPresente(vlPresente);
		
		calcularFluxo = expRecebimento.calcularFluxo();
		
		
		if (calcularFluxo == true)
		{

			DecimalFormat dcInteiro = new DecimalFormat("000000");
			DecimalFormat dcValor = new DecimalFormat("00000000.00");
			DecimalFormat dcFator = new DecimalFormat("000.00000000");

			System.out.println("Valor Presente = " + dcValor.format(expRecebimento.getVlPresente()));
			System.out.println("Valor do IOF   = " + dcValor.format(vlIOF));

			System.out.println("Parcela   DD Corr Vencimento   Fator             "
					            + "Fator GAP          Parcela         "
					            + "Juros           Principal       Saldo");

			
			for (int i = 0; i < qtdeParcelas; i++)
			{

				   System.out.println(dcInteiro.format(i+1) +  "    "+ 
						   dcInteiro.format(expRecebimento.getQtdeDiasVcto().get(i)) + "  " +
						   datas.getDtVencimento().get(i).substring(6,8) + "/" +
						   datas.getDtVencimento().get(i).substring(4,6) + "/" +
						   datas.getDtVencimento().get(i).substring(0,4) + "   " +
						   dcFator.format(expRecebimento.getFatorTaxa().get(i)) + "      "            +
						   dcFator.format(expRecebimento.getFatorTaxaGAP().get(i)) + "       "        +
						   dcValor.format(expRecebimento.getVlParcela().get(i)) 		+"     "      +
						   dcValor.format(expRecebimento.getVlJuros().get(i))   		+"     "      +
						   dcValor.format(expRecebimento.getVlPrincipal().get(i)) 		+"     "      +
						   dcValor.format(expRecebimento.getVlSdoRemanescente().get(i)));
			}
		}
		else
		{
			System.out.println(expRecebimento.idRetorno);
			System.out.println(expRecebimento.msgRetorno);
		}

	}

}
