package simulator;

import java.text.DecimalFormat;

import java.time.LocalDate;
import java.util.ArrayList;

import mvm2services.services.Datas;
import mvm2services.services.ExpectativaDeRecebimento;
import mvm2services.services.ExpectativaRectoEspecial;
import mvm2services.services.ValorIOF;

public class MetodoMainValorIOFEspecial {

	public static void main(String[] args) {
		
		double vlSolicitado = 0;
		double vlPresente = vlSolicitado;
		int qtdeParcelas = 36;
		double taxa = 1.5;
		char periodicidadeTaxa = 'M';
		int carencia = 0;
		String dataPrimeraParcela = new String();
		dataPrimeraParcela = "20230529";
		int diaBaseVencimento = 5;
		
		int baseTaxa = 360;
		char periodicidadeVcto = 'M';
		ArrayList<Double> fatorTaxa = new ArrayList<Double>();
		ArrayList<Double> fatorTaxaGAP = new ArrayList<Double>();
		char tipoJuros = 'S';
		char idDiasUteis = 'N';
		int cdSistemaAmortizacao = 2;
		double vl1aParcela = 10000.00;

		char idTaxaFator = 'T';
		ArrayList<String> idCobrancaJuros = new ArrayList<String>();
		ArrayList<String> idCobrancaPrincipal = new ArrayList<String>();
		

		Datas datas = new Datas();
		
		datas.setDiaBaseVcto(diaBaseVencimento);
		
		LocalDate localDate = LocalDate.now();
		String dtCorrente = String.format("%04d",localDate.getYear()) + 
							String.format("%02d",localDate.getMonthValue()) +
							String.format("%02d",localDate.getDayOfMonth());
		datas.setDtReferencia(dtCorrente);
		
		datas.setIdDiaUtil(idDiasUteis);
		datas.setPeriodicidadeVcto(periodicidadeVcto);
		datas.setQtdeParcelas(qtdeParcelas);
		datas.setDtVct1ap(dataPrimeraParcela);
		datas.setQtdeDiasCarencia(carencia);
		
		boolean validaDatas = datas.calcularLinhaDoTempo();
		
		ArrayList<Integer> qtdeDiasVcto = datas.getQtdeDiasCorridos();
		ArrayList<Integer> qtdeDiasGAP = new ArrayList<Integer>();
		ArrayList<Double> vlParcelaIn = new ArrayList<Double>();

		ArrayList<String> idCobrJuros = new ArrayList<String>();
		ArrayList<String> idCobrPrinc = new ArrayList<String>();

		for (int i = 0; i < qtdeParcelas; i++)
		{
			if (i < (6) )
				idCobrPrinc.add("N");
			else
				idCobrPrinc.add("S");
			
			idCobrJuros.add("S");
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
		expRecebimento.setVlParcelaIn(vlParcelaIn);
		expRecebimento.setVlPresente(vlPresente);
		expRecebimento.setIdCobrancaJuros(idCobrJuros);
		expRecebimento.setIdCobrancaPrincipal(idCobrPrinc);

		
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

			System.out.println("Valor Solicitado = " + dcValor.format(vlSolicitado));
			System.out.println("Valor Financiado = " + dcValor.format(expRecebimento.getVlPresente()));
			System.out.println("Valor do IOF     = " + dcValor.format(vlIOF));

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
