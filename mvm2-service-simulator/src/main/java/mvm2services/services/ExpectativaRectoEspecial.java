package mvm2services.services;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ExpectativaRectoEspecial extends ExpectativaDeRecebimento {
	
	public boolean calcularFluxo()
	{

		super.vlSdoRemanescente.clear();
		super.numParcela.clear();
		super.vlParcela.clear();
		super.vlPrincipal.clear();
		super.vlJuros.clear();
		
		boolean resultValue = super.validarDadosComuns();
		
		if (resultValue == true)
		{
			if (super.getIdTaxaFator() == 'T')
			{
				super.calcularFatores();
			}
			resultValue = validarDadosEspecial();
		}
		
		if (resultValue == true)
			resultValue = calcularFluxoSdDevedorEspecial();
		
		return resultValue;
	}
	
	boolean validarDadosEspecial() 
	{

		boolean existeParcelaZero = false;
		
		if (super.getVlPresente() <= 0.00)
		{
			idRetorno = 12;
			msgRetorno = "Cálculo da Expectativa de Recebimento - Valor Presente não informado";
			return false;
		}
		
		if (super.getTipoJuros() != 'S')
		{
			idRetorno = 13;
			msgRetorno = "Cálculo da Expectativa de Recebimento - Tipo de Juros diferente de 'S'";
			return false;
		}
		
		for (int i = 0; i < super.getQtdeParcelas(); i++)
		{
			
			
			if (super.getIdCobrancaJuros().isEmpty())
			{
				idRetorno = 14;
				msgRetorno = "Cálculo da Expectativa de Recebimento - indicador de cobrança de Juros (S/N) vazio";
				return false;
			}
				
			if (super.getIdCobrancaJuros().get(i).charAt(0) != 'S' && super.getIdCobrancaJuros().get(i).charAt(0) != 'N')
			{
				idRetorno = 15;
				msgRetorno = "Cálculo da Expectativa de Recebimento - indicador de cobrança de Juros (S/N) inválido -> "
				+ super.getIdCobrancaJuros().get(i).charAt(0)  + " ref. parcela " + i++;
				return false;
			}
				
			if (super.getIdCobrancaPrincipal().isEmpty())
			{
				idRetorno = 16;
				msgRetorno = "Cálculo da Expectativa de Recebimento - indicador de cobrança de Principal (S/N) vazio";
				return false;
			}

			if (super.getIdCobrancaPrincipal().get(i).charAt(0) != 'S' && super.getIdCobrancaPrincipal().get(i).charAt(0) != 'N')
			{
				idRetorno = 17;
				msgRetorno = "Cálculo da Expectativa de Recebimento - indicador de cobrança de Principal (S/N) inválido"
				+ super.getIdCobrancaPrincipal().get(i).charAt(0) + " ref. parcela " + i++;
				return false;
			}
			
			if (super.getIdCobrancaJuros().get(i).charAt(0) == 'N'  && super.getIdCobrancaPrincipal().get(i).charAt(0) == 'S')
			{
				idRetorno = 18;
				msgRetorno = "Cálculo da Expectativa de Recebimento - Para cobrar principal, precisa cobrar tamb�m os juros";
				return false;
			}

			if (! super.getVlParcelaIn().isEmpty())
			{
				if (super.getIdCobrancaPrincipal().get(i).charAt(0) == 'N' && super.getVlParcelaIn().get(i) > 0.00)
				{
					DecimalFormat dcValor = new DecimalFormat("00000000.00");
					idRetorno = 20;
					msgRetorno = "Cálculo Especial - não informar valor para parcela de Juros, Parcela " 
							+ (i+1) + ", valor informado = " + dcValor.format(super.getVlParcelaIn().get(i));
					return false;
				}

				if (super.getIdTipoParcelaIn().get(i).charAt(0) != 'B' && super.getIdTipoParcelaIn().get(i).charAt(0) != 'A')
				{
					DecimalFormat dcValor = new DecimalFormat("00000000.00");
					idRetorno = 21;
					msgRetorno = "Cálculo Especial - tipo da parcela informada não identificado --> (B ou A), Parcela "
							+ (i+1);
					return false;
				}
			}
			
			if (super.getVlParcelaIn().isEmpty())
				existeParcelaZero = true;
			else if (super.getVlParcelaIn().get(i).equals(null) || super.getVlParcelaIn().get(i).equals(0.00) )
			{
				existeParcelaZero = true;
			}
		
		}
		
		if (super.getVlPresente() <= 0 && existeParcelaZero )
		{
			idRetorno = 22;
			msgRetorno = "Cálculo da Expectativa de Recebimento - Valor Presente não informado";
			return false;
		}
		else
		if (super.getVlPresente() > 0 && existeParcelaZero == false)
		{
			idRetorno = 23;
			msgRetorno = "Cálculo da Expectativa de Recebimento - Deixar uma ou mais parcela(s) sem preenchimento";
			return false;
		}
		
		
		// Validar se o somat�rio dos Valores Presentes das parcelas � maior do que o valor Presente informado
		
		double vlPresenteParcelas = 0.00;
		
		if (! super.getVlParcelaIn().isEmpty())
			vlPresenteParcelas = calcularVlPresenteParcela();
		
		if (vlPresenteParcelas >= super.getVlPresente())
		{
			idRetorno = 23;
			msgRetorno = "Cálculo da Expectativa de Recebimento - Valor Presente maior do que o Valor da soma de principal das parcelas";
			return false;
		}
		
		return true;
	}
	

	public double calcularVlPresenteParcela()
	{
		
		ArrayList<Double> fatorEspecial = new ArrayList<Double>();

		fatorEspecial = calcularFatorEspecial();
		
		double vlPresenteParcela = 0.00;
		
		for (int i = 0; i < super.getQtdeParcelas(); i++)
		{
			if ( super.getVlParcelaIn().get(i) > 0.00)
				vlPresenteParcela = vlPresenteParcela + (super.getVlParcelaIn().get(i) / fatorEspecial.get(i)); 
		}
		
		return vlPresenteParcela;
	}

	
	public boolean calcularFluxoSdDevedorEspecial()
	{
		ArrayList<Double> fatorEspecial = new ArrayList<Double>();

		double vlPrincipal = 0.00;
		double vlJuros = 0.00;
		double vlSdoRemanescente = super.getVlPresente();
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("0.00");

		
		if (super.getVlParcelaIn().isEmpty())
		{
 			ArrayList<Double> initVlParcela = new ArrayList<Double>();
 			
 			for (int i = 0; i < super.getQtdeParcelas(); i++)
 				initVlParcela.add(0.00);
 			
 			super.setVlParcelaIn(initVlParcela);
		}

		fatorEspecial = calcularFatorEspecial();
		
		double vlParcelaRef = calcularVlParcelaEspecial(fatorEspecial);
		
		double vlParcela = 0;
		
		int x = 1;
		for (int i = 0; i < super.getQtdeParcelas(); i++)
		{
			
			if (super.getVlParcelaIn().get(i) > 0.00)
				vlParcela = super.getVlParcelaIn().get(i);
			else if (super.getIdCobrancaPrincipal().get(i).charAt(0) == 'N')
				vlParcela = ((vlSdoRemanescente * super.getFatorTaxaGAP().get(i)) - vlSdoRemanescente);
			else
				vlParcela = vlParcelaRef;

			if (i == (super.getQtdeParcelas() -1))
			{
				vlPrincipal = vlSdoRemanescente;
				vlJuros = (vlParcela - vlPrincipal);
				vlSdoRemanescente = (vlSdoRemanescente * super.getFatorTaxaGAP().get(i)) - vlParcela;
				vlSdoRemanescente = Math.abs(vlSdoRemanescente);
			}
			else
			{
				vlJuros = ((vlSdoRemanescente * super.getFatorTaxaGAP().get(i)) - vlSdoRemanescente);

				if (super.getIdCobrancaPrincipal().get(i).charAt(0) == 'N')
					vlPrincipal = 0;
				else
				{
					vlPrincipal = (vlParcela - vlJuros);
					vlSdoRemanescente = (vlSdoRemanescente * super.getFatorTaxaGAP().get(i)) - vlParcela;
				}
			}

			if (vlJuros <= 0.00 || vlPrincipal < 0.00)
			{
				idRetorno = 24;
				msgRetorno = "Valor da Parcela menor do que o Valor dos Juros, favor alterar - Parcela "
				+ (i+1);
				return false;
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

		return true;
	}

	public ArrayList<Double> calcularFatorEspecial()
	{
		
		ArrayList<Double> fatorEspecial = new ArrayList<Double>();
		
		double fatorBase = 0;

		for (int x = 0; x < super.getQtdeParcelas(); x++)
		{
			
			if (x == 0)
			{
				fatorBase = 1;
				fatorEspecial.add(super.getFatorTaxa().get(x));
			}
			else if (super.getIdCobrancaPrincipal().get(x).charAt(0) == 'N')
			{
				fatorBase = super.getFatorTaxa().get(x);
				fatorEspecial.add(super.getFatorTaxa().get(x));
			}
			else
			{
				fatorEspecial.add(super.getFatorTaxa().get(x) / fatorBase);
			}
		}
		
		return fatorEspecial;
		
	}

	public double calcularVlParcelaEspecial(ArrayList<Double> fatorEspecial)
	{
		
		double vlParcela = 0.0000;
		double fatorParcela = 0.000;
		double vlPresenteParcela = 0.00;
		double vlParcelaRef = 0.00;
		
		for (int i = 0; i < super.getQtdeParcelas(); i++)
		{
			
			if (super.getVlParcelaIn().get(i).equals(null) || super.getVlParcelaIn().get(i).equals(0.00))
				vlParcelaRef = 0.00;
			else
				vlParcelaRef = super.getVlParcelaIn().get(i);
			
				
			if (super.getIdCobrancaPrincipal().get(i).charAt(0) == 'S' && vlParcelaRef == 0.00)
				fatorParcela = fatorParcela + (1 / fatorEspecial.get(i));
			
			if (vlParcelaRef > 0.00)
				vlPresenteParcela = vlPresenteParcela + (vlParcelaRef / fatorEspecial.get(i)); 
			
		}
		
		if (super.getVlPresente() > vlPresenteParcela)
			vlParcela = (super.getVlPresente() - vlPresenteParcela ) / fatorParcela;
		else
			vlParcela = 0.00;

		return vlParcela;
	}
}
