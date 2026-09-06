package simulator;

import mvm2services.services.Datas;

public class MainDatas {

	public static void main(String[] args) {

		String periodicidadeVcto = "A";
		String idDiaUtil = "N";
		
		Datas datas = new Datas();
		datas.setDiaBaseVcto(26);
		datas.setIdDiaUtil(idDiaUtil.charAt(0));
		datas.setDtReferencia("20260226");
		datas.setQtdeDiasCarencia(0);
		datas.setPeriodicidadeVcto(periodicidadeVcto.charAt(0));
		datas.setQtdeParcelas(10);
		
		boolean validaData = datas.calcularLinhaDoTempo();
		
		if (validaData)
		{
			for (int i = 0; i < datas.getQtdeParcelas(); i++)				
				System.out.println(datas.getDtVencimento().get(i));
		}
	}
}
