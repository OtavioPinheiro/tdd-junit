# Java e TDD: Testes unitários com JUnit

Curso da Alura ensinando a utilizar o JUnit para escrever e executar testes unitários utilizando a linguagem Java com gerenciador de pacotes Maven.

# Sumário

1. [TDD](#tdd)
2. [O que são Testes Unitários?](#o-que-são-testes-unitários)
3. [JUnit](#junit)
4. [Fixtures no JUnit](#fixtures-no-junit)

# TDD

**TDD**, ou Desenvolvimento Orientado a Testes (em inglês, *Test-Driven Development*), é uma prática de desenvolvimento de software que inverte a ordem tradicional de codificação. Em vez de escrever o código primeiro e depois os testes, o **TDD** prega que você deve escrever os testes ***antes*** do código da funcionalidade. Isso pode parecer estranho no início, mas traz inúmeros benefícios para a qualidade e a manutenibilidade do software.

## O Ciclo Red-Green-Refactor:

O **TDD** se baseia em um ciclo iterativo curto, conhecido como **_"Red, Green, Refactor"_** (Vermelho, Verde, Refatorar):

1.  **Red (Vermelho):**
    * Nesta fase, você escreve um pequeno teste automatizado para uma funcionalidade que *ainda não existe*. Este teste deve descrever um aspecto específico do comportamento esperado do código.
    * Como o código da funcionalidade não foi implementado, o teste irá *falhar* (daí o "vermelho"). Este é o comportamento esperado. **O objetivo aqui é ter um teste que defina exatamente o que o código deve fazer.**

2.  **Green (Verde):**
    * Agora, você escreve o código *mínimo necessário* para fazer o teste passar (ficar "verde"). A preocupação aqui não é com a perfeição do código, mas sim em satisfazer o teste que você escreveu anteriormente.
    * É importante focar em **escrever apenas o código necessário para o teste atual**. Evite adicionar funcionalidades extras ou antecipar necessidades futuras neste momento.

3.  **Refactor (Refatorar):**
    * Com o teste passando, você tem a garantia de que a funcionalidade básica está funcionando corretamente. Agora é hora de **melhorar o código**.
    * Refatorar significa melhorar a estrutura interna do código sem alterar seu comportamento externo. Isso inclui **remover duplicações, melhorar a legibilidade, otimizar o desempenho, etc**.
    * Graças aos testes que você escreveu, você pode refatorar com segurança, sabendo que se introduzir algum erro, os testes irão falhar e você poderá corrigi-lo imediatamente.

**Exemplo Prático (Conceitual):**

Imagine que você precisa criar uma função que calcula a área de um retângulo. Em **TDD**, você faria o seguinte:

1.  **Red:** Escreve um teste que verifica se a área de um retângulo com base 5 e altura 10 é igual a 50. O teste falha porque a função ainda não existe.

2.  **Green:** Escreve a função:

    ```java
    public int calcularAreaRetangulo(int base, int altura) {
        return base * altura;
    }
    ```

    O teste agora passa.

3.  **Refactor:** Você pode adicionar tratamento para entradas inválidas (ex: base ou altura negativas) ou melhorar a nomeação de variáveis, sem medo de quebrar a funcionalidade, pois o teste garante a correção.

**Benefícios do TDD:**

*   **Código de alta qualidade:** O **TDD** leva à criação de código mais limpo, modular, coeso e com baixo acoplamento, facilitando a manutenção e a reutilização.
*   **Menos bugs:** A escrita de testes antes do código ajuda a prevenir erros, pois você pensa nos casos de uso e possíveis problemas antes mesmo de implementar a solução.
*   **Maior confiança em mudanças:** Com uma suíte de testes robusta, você pode refatorar e modificar o código com segurança, sabendo que os testes irão detectar qualquer regressão (quebra de funcionalidade existente).
*   **Documentação viva:** Os testes servem como uma forma de documentação executável, mostrando como o código deve se comportar em diferentes cenários.
*   **Design aprimorado:** O **TDD** força você a pensar no design da sua solução antes da implementação, levando a um design mais limpo e bem estruturado.
*   **Feedback rápido:** O ciclo **_Red-Green-Refactor_** proporciona feedback imediato sobre a correção do código, permitindo a identificação e correção de erros de forma mais ágil.

**Desvantagens do TDD:**

*   **Curva de aprendizado:** Exige um certo tempo para aprender e se adaptar à metodologia.
*   **Esforço inicial:** Pode parecer que demanda mais tempo no início, pois você precisa escrever os testes antes do código. No entanto, a longo prazo, o tempo economizado com a redução de bugs e a facilidade de manutenção compensa esse investimento inicial.

[Sumário](#sumário)

# O que são Testes Unitários?

Testes unitários focam em testar partes isoladas do código, como métodos, funções ou classes. O objetivo é verificar se cada unidade executa sua função corretamente, independentemente de outras partes do sistema. Isso ajuda a identificar erros cedo no processo de desenvolvimento, facilitando a correção e reduzindo o custo de manutenção a longo prazo.

[Sumário](#sumário)

# JUnit

**JUnit** é um _framework_ popular para testes unitários em Java. Ele fornece uma estrutura para escrever e executar testes automatizados, garantindo que pequenas partes isoladas do seu código (unidades) funcionem corretamente. É uma ferramenta fundamental para o Desenvolvimento Orientado a Testes (**TDD**) e para manter a qualidade do código em projetos Java.

## Como o JUnit Funciona?

O **JUnit** oferece uma estrutura para definir e executar testes. Os testes são escritos em classes chamadas *classes de teste*, que geralmente seguem a convenção de nomenclatura `NomeDaClasseTest`. Dentro dessas classes, você define métodos de teste, anotados com `@Test`, que contêm asserções (_assertions_). As asserções comparam o resultado real da execução do código com o resultado esperado.

**Exemplo Básico:**

Suponha que você tenha uma classe `Calculadora` com um método `somar()`:

```java
public class Calculadora {
    public int somar(int a, int b) {
        return a + b;
    }
}
```

Um teste unitário usando **JUnit** para esse método seria:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    @Test
    void testSomarNumerosPositivos() {
        Calculadora calc = new Calculadora();
        int resultado = calc.somar(2, 3);
        assertEquals(5, resultado);
    }

    @Test
    void testSomarNumeroComZero() {
        Calculadora calc = new Calculadora();
        int resultado = calc.somar(5, 0);
        assertEquals(5, resultado);
    }

    @Test
    void testSomarNumerosNegativos() {
        Calculadora calc = new Calculadora();
        int resultado = calc.somar(-2, -3);
        assertEquals(-5, resultado);
    }

    @Test
    void testSomarComNumerosGrandes() {
        Calculadora calc = new Calculadora();
        int resultado = calc.somar(Integer.MAX_VALUE, 1);
        assertEquals(Integer.MIN_VALUE, resultado); // Overflow intencional para teste
    }
}
```

**Explicação do Exemplo:**

*   `@Test`: Anotação que marca um método como um método de teste.
*   `assertEquals(esperado, resultado)`: Uma asserção que verifica se o valor `resultado` é igual ao valor `esperado`. Existem diversas outras asserções no **JUnit**.
*   `Calculadora calc = new Calculadora();`: Cria uma instância da classe que será testada.
*   `int resultado = calc.somar(2, 3);`: Executa o método a ser testado.

**Executando Testes com JUnit:**

**JUnit** pode ser executado de várias maneiras:

*   **IDEs (Integrated Development Environments):** A maioria das IDEs Java, como Eclipse, IntelliJ IDEA e NetBeans, possui suporte integrado para executar testes **JUnit**.
*   **Linha de comando:** Usando o **JUnit** Launcher.
*   **Ferramentas de build:** Como Maven e Gradle, que automatizam a execução de testes durante o processo de _build_.

**Principais Recursos do JUnit:**

*   **Asserções:** Um conjunto rico de asserções para verificar diferentes condições (igualdade, desigualdade, verdadeiro, falso, exceções, etc.).
*   **Fixtures:** Mecanismos para configurar o ambiente de teste antes de cada teste (`@BeforeEach` ou `@Before`) e limpar após cada teste (`@AfterEach` ou `@After`).
*   **Suítes de Testes:** Permitem agrupar vários testes em uma única execução.
*   **Parametrização de Testes:** Permite executar o mesmo teste com diferentes conjuntos de dados usando `@ParameterizedTest` e `@ValueSource`/`@MethodSource`.
*   **Testes com Timeout:** Permitem definir um tempo máximo para a execução de um teste.
*   **Extensões:** JUnit 5 introduziu um sistema de extensões que permite adicionar funcionalidades extras ao framework.

**Benefícios do Uso do JUnit:**

*   **Melhora a qualidade do código:** Ajuda a identificar erros precocemente e garante que o código funcione conforme o esperado.
*   **Facilita a refatoração:** Permite refatorar o código com segurança, sabendo que os testes irão detectar qualquer regressão.
*   **Documentação viva:** Os testes servem como uma forma de documentação executável, mostrando como o código deve se comportar.
*   **Suporte ao TDD:** É uma ferramenta essencial para praticar o Desenvolvimento Orientado a Testes.
*   **Integração contínua:** Facilita a integração com ferramentas de CI/CD, automatizando a execução dos testes em cada commit.

**JUnit** é uma ferramenta poderosa e essencial para desenvolvedores Java que buscam produzir código de alta qualidade. Dominar o **JUnit** e a prática de testes unitários é um investimento valioso para qualquer projeto Java.

[Sumário](#sumário)


# Fixtures no JUnit

Em JUnit, *Fixtures* são um mecanismo para configurar o ambiente de teste *antes* da execução dos testes e limpá-lo *depois*. Eles garantem que cada teste seja executado em um estado conhecido e consistente, evitando interferências entre os testes e tornando-os mais confiáveis e repetíveis.

**Por que usar Fixtures?**

Imagine que você está testando uma classe que interage com um banco de dados. Você não quer que cada teste insira e remova dados diretamente no banco de dados, pois isso pode levar a problemas de dependência entre os testes e dificultar a análise dos resultados. Com Fixtures, você pode configurar o estado inicial do banco de dados antes de cada teste e limpá-lo após a execução, garantindo que cada teste comece com um banco de dados limpo e previsível.

**Anotações e Métodos para Fixtures no JUnit 5 (Jupiter):**

JUnit 5 (Jupiter) introduziu novas anotações para Fixtures, tornando o código mais legível e flexível. As principais são:

*   `@BeforeEach`: Anotado em um método que será executado *antes* de cada método de teste (`@Test`). É usado para configurar o ambiente de teste para cada teste individualmente.
*   `@AfterEach`: Anotado em um método que será executado *após* a execução de cada método de teste (`@Test`). É usado para limpar o ambiente de teste e desfazer as alterações feitas durante o teste.
*   `@BeforeAll`: Anotado em um método *estático* que será executado *uma única vez, antes de todos* os métodos de teste da classe. É usado para realizar configurações que são comuns a todos os testes e que não precisam ser repetidas antes de cada um.
*   `@AfterAll`: Anotado em um método *estático* que será executado *uma única vez, após a execução de todos* os métodos de teste da classe. É usado para limpar recursos que foram alocados durante a configuração global.

**Exemplo Prático com JUnit 5:**

```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class ListaDeComprasTest {

    private List<String> listaDeCompras;

    @BeforeEach // Executa antes de CADA teste
    void setUp() {
        System.out.println("Configurando a lista de compras...");
        listaDeCompras = new ArrayList<>();
        listaDeCompras.add("Maçã");
        listaDeCompras.add("Banana");
    }

    @AfterEach // Executa depois de CADA teste
    void tearDown() {
        System.out.println("Limpando a lista de compras...");
        listaDeCompras.clear();
    }

    @BeforeAll // Executa UMA VEZ antes de TODOS os testes
    static void setUpAll() {
        System.out.println("Configurando ambiente de testes...");
        // Ex: Conectar a um banco de dados de teste
    }

    @AfterAll // Executa UMA VEZ depois de TODOS os testes
    static void tearDownAll() {
        System.out.println("Limpando ambiente de testes...");
        // Ex: Desconectar do banco de dados de teste
    }

    @Test
    void testAdicionarItem() {
        listaDeCompras.add("Laranja");
        assertEquals(3, listaDeCompras.size());
        assertTrue(listaDeCompras.contains("Laranja"));
        System.out.println("Testando adicionar item...");
    }

    @Test
    void testRemoverItem() {
        listaDeCompras.remove("Banana");
        assertEquals(1, listaDeCompras.size());
        assertFalse(listaDeCompras.contains("Banana"));
        System.out.println("Testando remover item...");
    }
}
```

**Explicação do Exemplo:**

*   `@BeforeEach`: O método `setUp()` é executado antes de cada teste (`testAdicionarItem` e `testRemoverItem`). Ele cria uma nova lista de compras e adiciona dois itens a ela, garantindo que cada teste comece com a mesma lista inicial.
*   `@AfterEach`: O método `tearDown()` é executado após cada teste. Ele limpa a lista de compras, evitando que as alterações feitas por um teste afetem outros testes.
*   `@BeforeAll` e `@AfterAll`: Os métodos `setUpAll()` e `tearDownAll()` são executados apenas uma vez, antes do início de todos os testes e após o término de todos os testes, respectivamente. Eles são úteis para configurações globais, como conexões com banco de dados ou inicialização de recursos pesados.

**Diferenças entre JUnit 4 e JUnit 5:**

Em JUnit 4, as anotações equivalentes eram:

*   `@Before`: Equivalente a `@BeforeEach`.
*   `@After`: Equivalente a `@AfterEach`.
*   `@BeforeClass`: Equivalente a `@BeforeAll`.
*   `@AfterClass`: Equivalente a `@AfterAll`.

**Quando usar `@BeforeAll` e `@AfterAll`?**

Use `@BeforeAll` e `@AfterAll` com cautela. Eles são apropriados para configurações que são realmente globais e custosas de serem repetidas a cada teste. Em geral, é preferível usar `@BeforeEach` e `@AfterEach` para isolar os testes o máximo possível.

**Em resumo:**

Fixtures são uma parte essencial do JUnit, permitindo que você escreva testes mais limpos, confiáveis e independentes. Ao usar as anotações `@BeforeEach`, `@AfterEach`, `@BeforeAll` e `@AfterAll`, você garante que o ambiente de teste esteja sempre em um estado consistente, facilitando a detecção de erros e a manutenção do seu código.

[Sumário](#sumário)
