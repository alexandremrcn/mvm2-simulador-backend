package mvm2services.services;

import java.text.DecimalFormat;

public class ExpectativaRectoSAC extends ExpectativaDeRecebimento {
	
	public boolean calcularFluxo()
	{

		super.numParcela.clear();
		super.vlSdoRemanescente.clear();
		super.vlParcela.clear();
		super.vlPrincipal.clear();
		super.vlJuros.clear();
		double vlPresente = 0.00;

		boolean resultValue = super.validarDadosComuns();
		
		if (resultValue)
			resultValue = validarDadosSAC();
		
		if (resultValue)
		{

			if (super.getIdTaxaFator() == 'T')
			{
				super.calcularFatoresHP();
			}

			if (super.getVlPresente() == 0)
			{
				vlPresente = this.calcularVlPresente();
				super.setVlPresente(vlPresente);
			}

			this.calcularFluxoSdDevedorSAC();
		}
		
		return resultValue;
	}
	
	boolean validarDadosSAC()
	{
		
		if (super.getVlPresente() <= 0 && super.getVl1aParcela() <= 0)
		{
			idRetorno = 1;
			msgRetorno = "Cálculo da Expectativa de Recebimento SAC - Valor presente e valor da primeira parcelas não informados!";
			return false;
		}
		
		return true;
	}

	public double calcularVlPresente() {
		double vlPresente = 0.00;
		double vl1Parcela = getVl1aParcela();
		int qtdeParcelas = getQtdeParcelas();
		double taxames = super.getFatorTaxaGAP().get(0)-1;
		vlPresente =  vl1Parcela / (taxames + (1.0 / qtdeParcelas));
		return vlPresente;
	}

	public void calcularFluxoSdDevedorSAC()
	{
		double vlJuros = 0.00;
		double vlParcela = 0.00;
		double vlPrincipal = (super.getVlPresente() / super.getQtdeParcelas());
		double vlSdoRemanescente = super.getVlPresente();
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("0.00");


		int x = 1;
		for (int i = 0; i < super.getQtdeParcelas(); i++)
		{

			vlJuros = ((vlSdoRemanescente * super.getFatorTaxaGAP().get(i)) - vlSdoRemanescente);
			vlParcela = vlPrincipal + vlJuros;
			vlSdoRemanescente = (vlSdoRemanescente * super.getFatorTaxaGAP().get(i)) - vlParcela;
			vlSdoRemanescente = Math.abs(vlSdoRemanescente);


			super.numParcela.add(x);
			super.vlParcela.add(NumberConverter.convertToDouble(df.format(vlParcela)));
			super.vlSdoRemanescente.add(NumberConverter.convertToDouble(df.format(vlSdoRemanescente)));
			super.vlPrincipal.add(NumberConverter.convertToDouble(df.format(vlPrincipal)));
			super.vlJuros.add(NumberConverter.convertToDouble(df.format(vlJuros)));
			x++;
		}
	}

}
