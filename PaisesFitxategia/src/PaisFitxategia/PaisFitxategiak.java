package PaisFitxategia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Class PaisFitxategiak.
 * Programa honek fitxategi-izen bat eta direktorio bat eskatzen dizkio erabiltzaileari. 
 * Gero, egiaztatu fitxategia direktorio horretan dagoen, eta, bestela, sortu.
 */
public class PaisFitxategiak {

	/**
	 * The main method.
	 * 
	 * 
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Fitxategiaren izena eskatu
		System.out.println("Sartu lan egingo duzun fitxategiaren izena:");
		String fitxategiIzenaString = sc.nextLine() + ".txt"; // Añade la extensión .txt

		// Eskatu direktorioa
		String helbideOsoaString = EskatuDirektorioa(sc);

		// konproabtu fitxategia
		KonprobatuEdoSortuFitxategia(helbideOsoaString, fitxategiIzenaString);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Eskatu direktorioa.
	 * Metodo honek lan egin nahi duen direktorio osoa eskatzen dio erabiltzaileari. 
	 * Direktorioa existitzen ez bada, eskatu berriro baliozko bat sartu arte.
	 * @param sc gure eskanerra.
	 * @return the string , gure fitxategiaren ruta osoa.
	 */
	public static String EskatuDirektorioa(Scanner sc) {
		String helbideOsoaString;
		File dirFile;

		//Eskatu direktorioa direktorio egoi bat sartu harte
		do {
			System.out.println("Sartu fitxategiaren helbide osoa:");
			helbideOsoaString = sc.nextLine();
			dirFile = new File(helbideOsoaString);

			// Konprobatu ea ruta ondo dagoen
			if (!dirFile.isDirectory()) {
				System.out.println("Ez da aurkitu direktorioa: " + helbideOsoaString);
			}
		} while (!dirFile.isDirectory()); 

		return helbideOsoaString;
	}

	/**
	 * Konprobatu edo sortu fitxategia.
	 * 
	 * Metodo honek emandako izena duen fitxategi bat zehaztutako direktorioan lehendik dagoen egiaztatzen du.
	 * Ez badago, fitxategia sortzen saiatuko gara. 
	 * Fitxategia ezin bada sortu, IOException motako salbuespen bat abiarazten da.
	 * @param helbideOsoa   Gure fitxategiaren ruta osoa.
	 * @param fitxategiIzena gure fitxategiaren izena.
	 */
	public static void KonprobatuEdoSortuFitxategia(String helbideOsoa, String fitxategiIzena) {

        File dirFile = new File(helbideOsoa);
        File fitxatefiosoaFile = new File(dirFile, fitxategiIzena);

        // Konprobatu fitxategia
        if (fitxatefiosoaFile.exists() && fitxatefiosoaFile.isFile()) {
            System.out.println("Fitxategia existitzen da.");
            // Irakurri lehenengo lerroa
            try (BufferedReader reader = new BufferedReader(new FileReader(fitxatefiosoaFile))) {
                String firstLine = reader.readLine();
                if ("PAISES 1.0".equals(firstLine)) {
                    System.out.println("Lehenengo lerroa zuzena da: " + firstLine);
                } else {
                    System.out.println("Lehenengo lerroa ez da zuzena: " + firstLine);
                    // Lehenengo lerroa gaizki badago, fitxategia berridatzi
                    System.out.println("Lehenengo lerroa zuzentzen...");
                    BerridatziFitxategia(fitxatefiosoaFile);
                }
            } catch (IOException e) {
                System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
            }
        } else {
            System.out.println("Fitxategia ez dago, sortzen saiatuko da...");
            // Fitxategia sortu
            BerridatziFitxategia(fitxatefiosoaFile);
        }
    }
	 /**
     * Metodo honek fitxategia berridazten du lehenengo lerroa "PAISES 1.0" izateko.
     * @param fitxategia Gure fitxategia.
     */
    public static void BerridatziFitxategia(File fitxategia) {
        try {
            // Fitxategia sortu edo berridatzi
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategia))) {
                writer.write("PAISES 1.0");
                writer.newLine();
                System.out.println("Fitxategia berridatzi da eta lehen lerroa zuzen jarri da: PAISES 1.0");
            }
        } catch (IOException e) {
            System.out.println("Errorea fitxategia sortzean edo berridaztean: " + e.getMessage());
        }
    }
}
