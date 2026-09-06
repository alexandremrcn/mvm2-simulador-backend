package simulator;

import java.time.LocalDate;

import mvm2services.services.Datas;

public class MetodoMainData {

	public static void main(String[] args) {
		
		
		/* Teste de valida��o dos campos de entrada */
		
		Datas dtSMX = new Datas();
		dtSMX.setDiaBaseVcto(25);
		
		LocalDate localdtReferencia = LocalDate.now();
		String dtReferencia = String.format("%04d",localdtReferencia.getYear()) + 
				String.format("%02d",localdtReferencia.getMonthValue()) +
				String.format("%02d",localdtReferencia.getDayOfMonth());
		
		
		dtSMX.setDtReferencia(dtReferencia);
		
		
		dtSMX.setIdDiaUtil('S');
		dtSMX.setQtdeDiasCarencia(70);
		dtSMX.setPeriodicidadeVcto('D');
		dtSMX.setQtdeParcelas(1);
		
		/* Teste de valida��o do Primeiro Vencimento*/

		boolean valida1Vcto = dtSMX.validarDtVtc1ap("20230817");
		
		if (valida1Vcto == false)
		{
			System.out.println("C�digo de Retorno..: "+dtSMX.getIdRetorno());
			System.out.println("Mensagem de Retorno..: "+ dtSMX.getMsgRetorno());
		}
		else
		{
			System.out.println("Valida��o do primeiro vencimento - Campos validados com sucesso!!");
		}
		
		/* Valida fluxo de vencimentos */ 
		
		String dtVcto[] = new String[dtSMX.getQtdeParcelas()];
		
		String dtBase = "20230717";
		
		for (int x = 0; x<dtSMX.getQtdeParcelas(); x++)
		{
			if (Integer.parseInt(dtBase.substring(4,6)) > 11)
			{
				dtBase = 	String.format("%04d",Integer.parseInt(dtBase.substring(0,4))+1)  +
							"01" + 
							dtBase.substring(6,8);
			}
			else
			{
				dtBase = 	dtBase.substring(0,4)+
							String.format("%02d",Integer.parseInt(dtBase.substring(4,6))+01)  +
							dtBase.substring(6,8);
			}	
				
			dtVcto[x] = dtBase;
			
		}
		
		boolean validaFluxo = dtSMX.validaFluxoVencimentos(dtVcto);
		
		if (validaFluxo == false)
		{
			System.out.println("C�digo de Retorno..: "+dtSMX.getIdRetorno());
			System.out.println("Mensagem de Retorno..: "+ dtSMX.getMsgRetorno());
		}
		else
		{
			System.out.println("Valida��o do fluxo de vencimentos - Campos validados com sucesso!!");
		}
		
		
		/* Teste do c�lculo do Primeiro vencimento*/
		
		String dataPrimeiroVcto = dtSMX.calcularDtVtc1ap();
		
		if (dtSMX.isResultValue() == false)
		{
			System.out.println("C�digo de Retorno..: "+ dtSMX.getIdRetorno());
			System.out.println("Mensagem de Retorno..: "+ dtSMX.getMsgRetorno());
		}
		else
		{
			System.out.println("C�lculo do primeiro vencimento = " + dataPrimeiroVcto);
		}
		
		/* Teste do c�lculo  - Quantidade de Dias Corridos */

		String dataDe = "20000101";
		String dataAte = "20700101";
		
		int qtdeDiasCorridos = dtSMX.calcularDiasCorridos(dataDe, dataAte);
		
		if (dtSMX.isResultValue() == false)
		{
			System.out.println("C�digo de Retorno..: "+dtSMX.getIdRetorno());
			System.out.println("Mensagem de Retorno..: "+ dtSMX.getMsgRetorno());
		}
		else
		{
			System.out.println("Dias corridos entre "+ dataDe +" e "+ dataAte + " � "+qtdeDiasCorridos);
		}
		
		/* Teste do c�lculo  - Quantidade de Dias �teis */

		int qtdeDiasUteis = dtSMX.calcularDiasUteis(dataDe, dataAte);
		
		if (dtSMX.isResultValue() == false)
		{
			System.out.println("C�digo de Retorno..: "+dtSMX.getIdRetorno());
			System.out.println("Mensagem de Retorno..: "+ dtSMX.getMsgRetorno());
		}
		else
		{
			System.out.println("Dias �teis entre "+ dataDe +" e "+ dataAte + " � "+qtdeDiasUteis);
		}
		
		/* Teste do c�lculo  - Calcular pr�ximo vencimento, a partir de data de vcto atual -  */
		
		String dataBase = "20190619";
		String dataPrxVcto = dtSMX.calcularPrxVcto(dataBase);

		if (dtSMX.isResultValue() == false)
		{
			System.out.println("C�digo de Retorno..: "+dtSMX.getIdRetorno());
			System.out.println("Mensagem de Retorno..: "+ dtSMX.getMsgRetorno());
		}
		else
		{
			System.out.println("C�lculo do Pr�ximo Vencimento = "+ dataPrxVcto);
		}
		
		
		/* Teste do c�lculo  - Calcular Linha do Tempo -  */
		/* dtVencimento[];
		 * qtdeDiasCorridos[];
		 * qtdeDiasUteis[];
		 */
		
		boolean resultLinhadoTempo = dtSMX.calcularLinhaDoTempo();
		
		if (resultLinhadoTempo == true)
		{
			System.out.println("Parcela Vencimento     Dias Corridos     Dias �teis  ");
			System.out.println("0000    "
			                +  dtSMX.getDtReferencia().substring(6, 8)+"/"
							+  dtSMX.getDtReferencia().substring(4, 6)+"/"
							+  dtSMX.getDtReferencia().substring(0, 4)+"     "
							+  "00000"+"             "+"00000");
			
			for (int i = 0; i < dtSMX.getQtdeParcelas(); i++)
			{
				System.out.println(String.format("%04d",i+1)+"    "
						+  dtSMX.getDtVencimento().get(i).substring(6, 8)+"/"
						+  dtSMX.getDtVencimento().get(i).substring(4, 6)+"/"
						+  dtSMX.getDtVencimento().get(i).substring(0, 4)+"     "
						+  String.format("%05d", dtSMX.getQtdeDiasCorridos().get(i))
						+ "             "
						+ String.format("%05d", dtSMX.getQtdeDiasUteis().get(i)));
			}
			
		} 
		else
		{
			System.out.println("C�digo de Retorno..: "+ dtSMX.getIdRetorno());
			System.out.println("Mensagem de Retorno..: "+ dtSMX.getMsgRetorno());
		}
	}
}
