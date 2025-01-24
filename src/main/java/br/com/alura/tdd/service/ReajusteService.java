package br.com.alura.tdd.service;

import java.math.BigDecimal;

import br.com.alura.tdd.modelo.Desempenho;
import br.com.alura.tdd.modelo.Funcionario;

public class ReajusteService {

    public void concederReajuste(Funcionario funcionario, Desempenho desempenho) {
        if (desempenho != null) switch (desempenho) {
            case A_DESEJAR: {
                BigDecimal reajuste = funcionario.getSalario().multiply(new BigDecimal("0.03"));
                funcionario.reajustarSalario(reajuste);
                    break;
                }
            case BOM: {
                BigDecimal reajuste = funcionario.getSalario().multiply(new BigDecimal("0.15"));
                funcionario.reajustarSalario(reajuste);
                    break;
                }
            case OTIMO: {
                BigDecimal reajuste = funcionario.getSalario().multiply(new BigDecimal("0.20"));
                funcionario.reajustarSalario(reajuste);
                    break;
                }
            default:
                break;
        }
    }

}
