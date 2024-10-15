package PaisFitxategia;

import java.io.BufferedWriter;
import java.io.File;
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

		//Konprobatu fitxategia
		if (fitxatefiosoaFile.exists() && fitxatefiosoaFile.isFile()) {
			System.out.println("Fitxategia existitzen da.");
		} else {
			System.out.println("Fitxategia ez dago, sortzen saiatuko da...");

			// Sortu fitxategia existitzen ez bada
			try {
				if (fitxatefiosoaFile.createNewFile()) {
					System.out.println("Fitxategia sortu da: " + fitxatefiosoaFile.getAbsolutePath());


					try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxatefiosoaFile))) {
						writer.write("PAISES 1.0");
						writer.newLine(); 
						System.out.println("Testua idatzi da fitxategian.");
					} catch (IOException e) {
						System.out.println("Errorea fitxategian testua idaztean: " + e.getMessage());
					}
				} else {
					System.out.println("Ezin izan da fitxategia sortu.");
				}
			} catch (IOException e) {
				System.out.println("Errorea fitxategia sortzean: " + e.getMessage());
			}
		}
	}
}
