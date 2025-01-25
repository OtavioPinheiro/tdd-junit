package br.com.alura.tdd.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.tdd.modelo.Funcionario;

public class BonusServiceTest {
    private BonusService service;
    private Funcionario funcionario;

    @BeforeEach
    public void setUp() {
        this.service = new BonusService();
        this.funcionario = new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("1000"));
    }

    @Test
    void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto() {
        // BigDecimal bonus = service.calcularBonus(funcionario);
        assertThrows(IllegalArgumentException.class, () -> service.calcularBonus(funcionario));
    }

    @Test
    void mensagemDeveriaSerFuncionarioComSalarioMuitoAltoNaoPodeReceberBonus() {
        try {
            service.calcularBonus(funcionario);
            fail("Não lançou exceção");
        } catch (Exception e) {
            assertEquals("Funcionário com salário maior que R$10.000,00 não pode receber bônus", e.getMessage());
        }
    }

    @Test
    void bonusDeveriaSer10PorCentoDoSalario() {
        BigDecimal bonus = service.calcularBonus(funcionario);

        assertEquals(new BigDecimal("250.00"), bonus);
    }

    @Test
    void bonusDeveriaSer10PorCentoParaSalarioDeExatamente10000() {
        BigDecimal bonus = service.calcularBonus(funcionario);

        assertEquals(new BigDecimal("1000.00"), bonus);
    }
}
