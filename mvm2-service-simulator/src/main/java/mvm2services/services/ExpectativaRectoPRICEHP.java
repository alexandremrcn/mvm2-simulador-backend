package mvm2services.services;

import java.text.DecimalFormat;

public class ExpectativaRectoPRICEHP extends ExpectativaDeRecebimento {
	
	public boolean calcularFluxo()
	{

		super.numParcela.clear();
		super.vlSdoRemanescente.clear();
		super.vlParcela.clear();
		super.vlPrincipal.clear();
		super.vlJuros.clear();

		boolean resultValue = super.validarDadosComuns();
		
		if (resultValue == true)
			resultValue = validarDadosPRICEHP();
		
		if (resultValue == true)
		{

			if (super.getIdTaxaFator() == 'T')
			{
				super.calcularFatoresHP();
			}
			
 			double vlPresente = 0.000000;
			
			if (super.getVlPresente() == 0)
			{
				vlPresente = calcularInvertidoSdPrice();
				super.setVlPresente(vlPresente);
			}
			else
				resultValue = this.calcularFluxoSdDevedorPrice();
		}
		
		return resultValue;
	}
	
	boolean validarDadosPRICEHP() 
	{
		if (super.getTipoJuros() != 'S')
		{
			idRetorno = 1;
			msgRetorno = "C�lculo da Expectativa de Recebimento PRICE HP - Tipo de Juros Inv�lido";
			return false;
			
		}

		
		if (super.getVlPresente() <= 0 && super.getVl1aParcela() <= 0)
		{
			idRetorno = 2;
			msgRetorno = "C�lculo da Expectativa de Recebimento PRICE HP - Valor Presente e Valor da parcela n�o informados";
			return false;
		}
		
		return true;
	}
	
	public double calcularInvertidoSdPrice()
	{
		double vlParcela = super.getVl1aParcela();
		double vlSdoRemanescente = 0.00;
		double vlPrincipal = 0.00;
		double vlJuros = 0.00;
		double vlPresente = 0.00;
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("0.00");

		
		int ipos;
		
		int vetorParcela = super.getQtdeParcelas() -1;

		double vetorvlParcela[] = new double[super.getQtdeParcelas()];
		double vetorvlSdoRemanescente[] = new double[super.getQtdeParcelas()];
		double vetorvlPrincipal[] = new double[super.getQtdeParcelas()];
		double vetorvlJuros[] = new double[super.getQtdeParcelas()];
		
		for (int i = vetorParcela; i >= 0; i--)
		{
			ipos = i+1;
			
			if (i == vetorParcela )
				vlSdoRemanescente = 0.00;
			else 
				vlSdoRemanescente = (vlSdoRemanescente + vlParcela) / super.getFatorTaxaGAP().get(ipos);
			
			vlPrincipal = ((vlSdoRemanescente + vlParcela) / super.getFatorTaxaGAP().get(i)) - vlSdoRemanescente;
			vlJuros = vlParcela - vlPrincipal;

			vetorvlParcela[i] = vlParcela;
			vetorvlSdoRemanescente[i] = vlSdoRemanescente;
			vetorvlPrincipal[i] = vlPrincipal;
			vetorvlJuros[i] = vlJuros;
			
		}
		
		vlPresente = (vlSdoRemanescente + vlParcela) / super.getFatorTaxaGAP().get(0);

		int x = 1;
		for (int i = 0; i < super.getQtdeParcelas(); i++)
		{
			super.numParcela.add(x);
			super.vlParcela.add(NumberConverter.convertToDouble(df.format(vetorvlParcela[i])));
			super.vlSdoRemanescente.add(NumberConverter.convertToDouble(df.format(vetorvlSdoRemanescente[i])));
			super.vlPrincipal.add(NumberConverter.convertToDouble(df.format(vetorvlPrincipal[i])));
			super.vlJuros.add(NumberConverter.convertToDouble(df.format(vetorvlJuros[i])));
			x++;
		}

		return vlPresente;
	}

	public double calcularVlParcelaPrice()
	{
		double vlParcela = 0.0000;
		double fatorParcela = 0.000;
		
		for (int i = 0; i < super.getQtdeParcelas(); i++)
			fatorParcela = fatorParcela + (1 / super.getFatorTaxa().get(i));

		vlParcela = super.getVlPresente() / fatorParcela; 
		return vlParcela;
		
	}
	
	
	public boolean calcularFluxoSdDevedorPrice()
	{
		double vlPrincipal = 0.00;
		double vlJuros = 0.00;
		double vlParcela = calcularVlParcelaPrice();
		double vlSdoRemanescente = super.getVlPresente();
		boolean resultvalue = true;
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("0.00");


		int x = 1;
		for (int i = 0; i < super.getQtdeParcelas(); i++)
		{

			if (i == (super.getQtdeParcelas() -1))
			{
				vlPrincipal = vlSdoRemanescente;
				vlJuros = (vlParcela - vlPrincipal);
			}
			else
			{
				vlJuros = ((vlSdoRemanescente * super.getFatorTaxaGAP().get(i)) - vlSdoRemanescente);
				vlPrincipal = (vlParcela - vlJuros);
			}

			vlSdoRemanescente = (vlSdoRemanescente * super.getFatorTaxaGAP().get(i)) - vlParcela;
			vlSdoRemanescente = Math.abs(vlSdoRemanescente);
			
			if (vlJuros <= 0.00 || vlPrincipal < 0.00)
			{
				idRetorno = 24;
				msgRetorno = "Valor da Parcela menor do que o Valor dos Juros, favor alterar - Parcela "
				+ (i+1);
				i = super.getQtdeParcelas();
				resultvalue = false;
			}
			else
			{
				super.numParcela.add(x);
				super.vlSdoRemanescente.add(NumberConverter.convertToDouble(df.format(vlSdoRemanescente)));
				super.vlParcela.add(NumberConverter.convertToDouble(df.format(vlParcela)));
				super.vlPrincipal.add(NumberConverter.convertToDouble(df.format(vlPrincipal)));
				super.vlJuros.add(NumberConverter.convertToDouble(df.format(vlJuros)));
				x++;
			}
		}
		return resultvalue;
	}

}
