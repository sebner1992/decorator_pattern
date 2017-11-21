package averkova_ebner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        MorseCode.init();

        System.out.println("Morse code:");
        try (BufferedReader br = new BufferedReader(new MorseReader(new FileReader(new File("morse.txt")))))
        {
            System.out.println(br.readLine());
        }
        System.out.println("------------------------------------------");
        System.out.println("ROT13 example:");
        try (BufferedWriter bw = new BufferedWriter(new ROT13Writer(new OutputStreamWriter(System.out))))
        {
            bw.write("test 123 §/? another, !!!");
        }
    }

}
