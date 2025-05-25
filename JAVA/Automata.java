/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.automata;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

public class Automata {
    public static String sha256(String data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes("UTF-8"));
        Formatter formatter = new Formatter();
        for (byte b : hash)
            formatter.format("%02x", b);
        return formatter.toString();
    }

    public static void main(String[] args) throws Exception {
        int n = 200, k = 10; //////////////CAMBIAR PARAMETROS AQUI////////////////////////////
        ArrayList<String> partidas = new ArrayList<>();
        ArrayList<String> firmas = new ArrayList<>();
        ArrayList<int[]> cuerpos = new ArrayList<>();

        SecureRandom random = new SecureRandom();
        long startTime = System.nanoTime();

        for (int i = 0; i < n; i++) {
            String partida;
            if (i == 0) {
                partida = sha256(Instant.now().toString());
            } else {
                partida = firmas.get(i - 1);
            }

            int[] cuerpo = new int[k];
            for (int j = 0; j < k; j++)
                cuerpo[j] = random.nextInt(100000) + 1;

            StringBuilder sb = new StringBuilder(partida);
            for (int num : cuerpo)
                sb.append(" ").append(num);

            String firma = sha256(sb.toString());

            partidas.add(partida);
            cuerpos.add(cuerpo);
            firmas.add(firma);
        }

        long endTime = System.nanoTime();
        double tiempo = (endTime - startTime) / 1e9;

        for (int i = 0; i < n; i++) {
            System.out.println("Nodo " + (i + 1) + ":");
            System.out.println("  Partida: " + partidas.get(i));
            System.out.println("  Cuerpo : " + Arrays.toString(cuerpos.get(i)));
            System.out.println("  Firma  : " + firmas.get(i) + "\n");
        }

        System.out.printf("Tiempo de ejecución: %.6f segundos\n", tiempo);
    }
}
