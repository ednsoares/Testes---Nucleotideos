/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.dnaanalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * criação do metado calcula nucleotides para ser utilizados os teste.
 * @author ednei soares e emilha de souza
 */
public class CalculaNucleotideos {

    public static int[] calculaNucleotideos(String filePath) throws IOException {
        // Lê o conteúdo do arquivo
        String sequence = Files.readString(Paths.get(filePath)).toUpperCase();

        int[] counts = new int[5];  // [A, C, G, T, Erros]

        // Percorre a sequência e conta os nucleotídeos e erros
        int sequenceLength = sequence.length();
        for (char nucleotide : sequence.toCharArray()) {
            switch (nucleotide) {
                case 'A' -> counts[0]++;
                case 'C' -> counts[1]++;
                case 'G' -> counts[2]++;
                case 'T' -> counts[3]++;
                default -> counts[4]++;  // Qualquer caractere inválido é contado como erro
            }
        }

        // Se mais de 10% dos caracteres forem inválidos, retorne null
        if ((double) counts[4] / sequenceLength > 0.1) {
            return null;
        }

        return counts;
    }
}