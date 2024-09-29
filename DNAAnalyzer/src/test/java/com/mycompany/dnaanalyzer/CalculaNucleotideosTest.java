/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.dnaanalyzer;

import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
/**
 * criação de testes do metado calcula nucleotideos.
 * @author ednei soares e emilha de souza
 */
public class CalculaNucleotideosTest {

    private static Path validFile;
    private static Path invalidFile;
    private static Path tooManyErrorsFile;
    private static Path errorFile;
    private static Path arquivoExemplo;

    @BeforeAll
    public static void init() {
        // Definição dos caminhos para os arquivos criados
        validFile = Paths.get("src/test/resources/validSequence.txt");
        invalidFile = Paths.get("src/test/resources/invalidSequence.txt");
        tooManyErrorsFile = Paths.get("src/test/resources/tooManyErrorsFile.txt");
        errorFile = Paths.get("src/test/resources/nonexistentFile.txt");
        arquivoExemplo = Paths.get("src/test/resources/arquivoExemplo.txt");
    }

    @AfterAll
    public static void cleanup() throws IOException {
        // Não é necessário excluir os arquivos criados manualmente no diretório src/test/resources
        // Apenas remover arquivos temporários criados dinamicamente, se houver
    }
    
    @Test
    @DisplayName("Teste de exemplo")
    public void testExemplo() throws IOException {
        int[] result = CalculaNucleotideos.calculaNucleotideos(arquivoExemplo.toString());
        assertArrayEquals(new int[]{13, 13, 9, 8, 0}, result);
    }
    
    @Test
    @DisplayName("Testa sequência válida")
    public void testValidSequence() throws IOException {
        int[] result = CalculaNucleotideos.calculaNucleotideos(validFile.toString());
        assertArrayEquals(new int[]{4, 2, 2, 2, 0}, result);
    }

    @Test
    @DisplayName("Testa sequência com um erro")
    public void testInvalidSequenceWithOneError() throws IOException {
        int[] result = CalculaNucleotideos.calculaNucleotideos(invalidFile.toString());
        assertArrayEquals(new int[]{3, 2, 2, 2, 1}, result);
    }

    @Test
    @DisplayName("Testa sequência inválida com mais de 10% de erros")
    public void testSequenceWithTooManyErrors() throws IOException {
        assertNull(CalculaNucleotideos.calculaNucleotideos(tooManyErrorsFile.toString()));
    }

    @Test
    @DisplayName("Testa arquivo inexistente")
    public void testFileNotFound() {
        assertThrows(IOException.class, () -> CalculaNucleotideos.calculaNucleotideos(errorFile.toString()));
    }
}