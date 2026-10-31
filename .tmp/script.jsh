import mvm2services.services.Datas;
Datas d = new Datas();
d.setDiaBaseVcto(0);
d.setIdDiaUtil('S');
d.setDtReferencia("20240901");
d.setQtdeDiasCarencia(0);
d.setPeriodicidadeVcto('M');
d.setQtdeParcelas(1);
System.out.println(d.calcularDtVtc1ap());
