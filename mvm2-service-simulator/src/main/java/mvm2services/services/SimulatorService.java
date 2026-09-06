package mvm2services.services;

import mvm2services.services.Datas;
import mvm2services.services.ExpectativaDeRecebimento;
import mvm2services.services.ExpectativaRectoEspecial;
import mvm2services.services.ExpectativaRectoPRICE;
import mvm2services.services.ExpectativaRectoPRICEHP;
import mvm2services.services.ExpectativaRectoSAC;
import mvm2services.services.NumberConverter;
import mvm2services.services.ValorIOF;

import mvm2services.models.SimulatorInput;
import mvm2services.models.SimulatorResult;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimulatorService {

    public SimulatorResult simulate(SimulatorInput input) throws Exception {

        double dbVlSolicitado = NumberConverter.convertToDouble(input.vlSolicitado);
        double vlPresente = dbVlSolicitado;

        ArrayList<Double> fatorTaxa = new ArrayList<>();
        ArrayList<Double> fatorTaxaGAP = new ArrayList<>();
        ArrayList<Integer> qtdeDiasGAP = new ArrayList<>();

        ArrayList<Double> dbVlParcelaIn = new ArrayList<>();
        if (input.vlParcelaIn != null) {
            for (String v : input.vlParcelaIn) {
                dbVlParcelaIn.add(NumberConverter.convertToDouble(v));
            }
        }

        ArrayList<String> strIdCobrJuros = new ArrayList<>(input.idCobrJuros);
        ArrayList<String> strIdCobrPrincipal = new ArrayList<>(input.idCobrPrinc);

        Datas datas = new Datas();

        LocalDate localDate = LocalDate.now();
        String dtCorrente = String.format("%04d", localDate.getYear()) +
                String.format("%02d", localDate.getMonthValue()) +
                String.format("%02d", localDate.getDayOfMonth());

        datas.setDtReferencia(dtCorrente);

        if (Integer.parseInt(input.diaBaseVencimento) == 0) {
            datas.setDiaBaseVcto(localDate.getDayOfMonth());
        } else {
            datas.setDiaBaseVcto(Integer.parseInt(input.diaBaseVencimento));
        }

        datas.setIdDiaUtil(input.idDiaUtil.charAt(0));
        datas.setPeriodicidadeVcto(input.periodicidadeVcto.charAt(0));
        datas.setQtdeDiasCarencia(Integer.parseInt(input.qtdeDiasCarencia));
        datas.setQtdeParcelas(Integer.parseInt(input.qtdeParcelas));

        boolean validaDatas = datas.calcularLinhaDoTempo();
        if (!validaDatas) {
            throw new RuntimeException(datas.getMsgRetorno());
        }

        ArrayList<Integer> qtdeDiasVcto = datas.getQtdeDiasCorridos();

        ExpectativaDeRecebimento expRecebimento;

        int amort = Integer.parseInt(input.cdSistemaAmortizacao);
        if (amort == 1) {
            expRecebimento = new ExpectativaRectoPRICEHP();
        } else if (amort == 2) {
            expRecebimento = new ExpectativaRectoPRICE();
        } else if (amort == 3) {
            expRecebimento = new ExpectativaRectoSAC();
        } else {
            expRecebimento = new ExpectativaRectoEspecial();
        }

        expRecebimento.setBaseTaxa(Integer.parseInt(input.baseTaxa));
        expRecebimento.setCdSistemaAmortizacao(amort);
        expRecebimento.setFatorTaxa(fatorTaxa);
        expRecebimento.setFatorTaxaGAP(fatorTaxaGAP);
        expRecebimento.setIdTaxaFator(input.idTaxaFator.charAt(0));
        expRecebimento.setPeriodicidadeTaxa(input.periodicidadeTaxa.charAt(0));
        expRecebimento.setPeriodicidadeVcto(input.periodicidadeVcto.charAt(0));
        expRecebimento.setQtdeDiasVcto(qtdeDiasVcto);
        expRecebimento.setQtdeDiasGAP(qtdeDiasGAP);
        expRecebimento.setQtdeParcelas(Integer.parseInt(input.qtdeParcelas));
        expRecebimento.setTaxa(NumberConverter.convertToDouble(input.taxa));
        expRecebimento.setTipoJuros(input.tipoJuros.charAt(0));
        expRecebimento.setVl1aParcela(NumberConverter.convertToDouble(input.vl1aParcela));
        expRecebimento.setVlParcelaIn(dbVlParcelaIn);
        expRecebimento.setVlPresente(vlPresente);
        expRecebimento.setIdCobrancaJuros(strIdCobrJuros);
        expRecebimento.setIdCobrancaPrincipal(strIdCobrPrincipal);

        boolean calcularFluxo = expRecebimento.calcularFluxo();

        double vlIOF = 0.00;

        if (calcularFluxo) {
            ValorIOF valorIOF = new ValorIOF();

            valorIOF.setDiasCorridos(datas.getQtdeDiasCorridos());
            valorIOF.setIofEmbutido(input.idIofEmbutido.charAt(0) == 'S');
            valorIOF.setTipoNegociacaoIOF(input.tpNegociacaoIof.charAt(0));
            valorIOF.setVlAliquota(NumberConverter.convertToDouble(input.pcAliquotaIOF));
            valorIOF.setVlAliquotaAdicional(NumberConverter.convertToDouble(input.pcAliquotaAdicional));
            valorIOF.setVlPresente(expRecebimento.getVlPresente());
            valorIOF.setVlPrincipal(expRecebimento.getVlPrincipal());

            vlIOF = valorIOF.calcularIOF();

            if (valorIOF.getIdRetorno() != 0) {
                throw new RuntimeException(valorIOF.getMsgRetorno());
            }

            if ((dbVlSolicitado == 0.00) || (valorIOF.getTipoNegociacaoIOF() != '1')) {
                vlPresente = expRecebimento.getVlPresente();
                dbVlSolicitado = vlPresente - vlIOF;
            } else {
                vlPresente = expRecebimento.getVlPresente() + vlIOF;
                expRecebimento.setVlPresente(vlPresente);
                calcularFluxo = expRecebimento.calcularFluxo();
            }
        }

        DecimalFormat df = new DecimalFormat("0.00");

        SimulatorResult result = new SimulatorResult();

        result.cdResult = expRecebimento.getIdRetorno();
        result.msgResult = expRecebimento.getMsgRetorno();
        result.dtReferencia = datas.getDtReferencia();
        result.pcTaxaJuros = expRecebimento.getTaxa();
        result.periodicidadeTaxa = expRecebimento.getPeriodicidadeTaxa();
        result.baseTaxaJuros = expRecebimento.getBaseTaxa();
        result.qtdeParcelas = expRecebimento.getQtdeParcelas();
        result.vlFinanciado = df.format(expRecebimento.getVlPresente());
        result.vlLiberado = df.format(dbVlSolicitado);
        result.vlIof = df.format(vlIOF);
        result.sistemaAmortizacao = expRecebimento.getCdSistemaAmortizacao();
        result.periodicidadeVcto = expRecebimento.getPeriodicidadeVcto();
        result.tpJuros = expRecebimento.getTipoJuros();
        result.vl1aParela = df.format(expRecebimento.getVl1aParcela());

        result.dtVencimento = datas.getDtVencimento();
        result.qtdeDiasCorr = datas.getQtdeDiasCorridos();
        result.qtdeDiasUteis = datas.getQtdeDiasUteis();
        result.numParcela = expRecebimento.getNumParcela();
        result.idCobraJuros = strIdCobrJuros;
        result.idCobraPrincipal = strIdCobrPrincipal;

        result.vlParcela = formatList(expRecebimento.getVlParcela(), df);
        result.vlJuros = formatList(expRecebimento.getVlJuros(), df);
        result.vlPrincipal = formatList(expRecebimento.getVlPrincipal(), df);
        result.vlSdoRemanescente = formatList(expRecebimento.getVlSdoRemanescente(), df);

        return result;
    }

    private List<String> formatList(List<Double> values, DecimalFormat df) {
        List<String> formatted = new ArrayList<>();
        for (Double v : values) {
            formatted.add(df.format(v));
        }
        return formatted;
    }
}
