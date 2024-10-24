package PaisFitxategia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class FitxategiOperazioak.
 */
public class FitxategiOperazioak {

	/** Pais Kodea. */
	static String[] Kodea = { "31", "376", "90", "261", "685", "213", "291", "595", "30", "964" };

	/** Pais Estatua. */
	static String[] Estatua = { "Holanda", "Andorra", "Turkia", "Madagascar", "Samoa Occidental", "Argelia", "Eritrea",
			"Paraguay", "Grecia", "Irak" };

	/** Pais Bizi esperantza. */
	static int[] BiziEsperantza = { 78, 0, 67, 52, 68, 70, 0, 68, 78, 66 };

	/** Pais Data sortu. */
	static LocalDate[] DataSortu = { LocalDate.of(1581, 7, 26), LocalDate.of(1993, 3, 14), LocalDate.of(1923, 10, 29),
			LocalDate.of(1960, 6, 26), LocalDate.of(1962, 1, 1), LocalDate.of(1962, 7, 5), LocalDate.of(1993, 5, 24),
			LocalDate.of(1825, 8, 25), LocalDate.of(1830, 2, 3), LocalDate.of(1958, 7, 14) };

	/** Pais Poblazioa. */
	static double[] Poblazioa = { 15460000, 64000, 61058000, 13651000, 165000, 27959000, 3400000, 4828000, 10467000,
			20097000 };

	/** Pais Kapitala. */
	static String[] Kapitala = { "Amsterdam", "Andorra La Vieja", "Ankara", "Antananarivo", "Apia", "Argel", "Asmara",
			"Asuncion", "Atenas", "Bagdad" };

	/**
	 * Fitxategia bete.
	 *
	 * @param fitxategia , gure fitxategia
	 */
	public static void fitxategiaBete(File fitxategia) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategia))) {
			// Lehenengo lerroa idatzi
			writer.write("PAISES 1.0");
			writer.newLine();

			// Idatzi datuak
			for (int i = 0; i < Kodea.length; i++) {
				writer.write(Kodea[i] + ";" + Estatua[i] + ";" + BiziEsperantza[i] + ";" + DataSortu[i] + ";"
						+ Poblazioa[i] + ";" + Kapitala[i]);
				writer.newLine();
			}
			System.out.println("Fitxategia datuekin bete da.");
		} catch (IOException e) {
			System.out.println("Errorea fitxategia idaztean: " + e.getMessage());
		}
	}

	/**
	 * Fitxategia hutsik utzi.
	 *
	 * @param fitxategia , gure fitxategia
	 * @param sc         , eskanerra
	 */
	public static void fitxategiaHutsikUtzi(File fitxategia, Scanner sc) {
		System.out.println("Hutsik utzi nahi duzu fitxategia? (bai/ez)");
		String confirmation = sc.nextLine();
		if (confirmation.equalsIgnoreCase("bai")) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategia))) {
				writer.write("PAISES 1.0");
				writer.newLine(); // Lehen lerroa mantendu
				System.out.println("Fitxategia hutsik utzi da.");
			} catch (IOException e) {
				System.out.println("Errorea fitxategia hutsik uztean: " + e.getMessage());
			}
		} else {
			System.out.println("Ez da fitxategia hutsik utzi.");
		}
	}

	/**
	 * Bilatu erregistro bat.
	 *
	 * @param fitxategia, gure fitxategia
	 * @param sc,         gure eskanerra
	 */
	public static void bilatuErregistroBat(File fitxategia, Scanner sc) {
		System.out.println("Sartu kodea erregistroa bat bilatzeko:");
		String kodeaBilatu = sc.nextLine();
		boolean found = false;

		try (BufferedReader reader = new BufferedReader(new FileReader(fitxategia))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith(kodeaBilatu + ";")) {
					System.out.println("Aurkitutako erregistroa: " + line);
					found = true;
					break;
				}
			}
			if (!found) {
				System.out.println("Ez da aurkitu erregistro bat kodearekin: " + kodeaBilatu);
			}
		} catch (IOException e) {
			System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
		}
	}

	/**
	 * Erregistroak enumeratu.
	 *
	 * @param fitxategia , gure fitxategia
	 * @param sc,        eskanerra
	 */
	public static void ErregistroakEnumeratu(File fitxategia, Scanner sc) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fitxategia))) {
			String line;
			System.out.println("Erregistroak (populazioaren arabera):");
			while ((line = reader.readLine()) != null) {
				if (!line.startsWith("PAISES")) { // Salto egin lehen lerroa PAISES 1.0
					String[] parts = line.split(";");
					System.out.println(parts[0] + ": " + parts[1] + " (Populazioa: " + parts[4] + ")");
				}
			}
		} catch (IOException e) {
			System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
		}
	}

	/**
	 * Erregistro berri bat gehitu.
	 *
	 * @param fitxategia, gure fitxategia
	 * @param sc          , eskanerra
	 */
	public static void ErregistroBerriBatGehitu(File fitxategia, Scanner sc) {
		System.out.println("Sartu erregistro berriaren kodea:");
		String kodea = sc.nextLine();
		System.out.println("Sartu erregistro berriaren izena:");
		String izena = sc.nextLine();
		System.out.println("Sartu erregistro berriaren bizi esperantza:");
		int biziEsperantza = sc.nextInt();
		System.out.println("Sartu erregistro berriaren sortze-data (YYYY-MM-DD formatua):");
		String dataSortuString = sc.next();
		LocalDate dataSortu = LocalDate.parse(dataSortuString);
		System.out.println("Sartu erregistro berriaren populazioa:");
		double populazioa = sc.nextDouble();
		sc.nextLine();
		System.out.println("Sartu erregistro berriaren kapitulua:");
		String kapitulua = sc.nextLine();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategia, true))) {
			writer.write(
					kodea + ";" + izena + ";" + biziEsperantza + ";" + dataSortu + ";" + populazioa + ";" + kapitulua);
			writer.newLine();
			System.out.println("Erregistro berri bat gehitu da.");
		} catch (IOException e) {
			System.out.println("Errorea erregistro berri bat gehitzean: " + e.getMessage());
		}
	}

	/**
	 * Ezabatu erregistro bat.
	 *
	 * @param fitxategia, gure fitxategia
	 * @param sc          , eskanerra
	 */
	public static void ezabatuErregistroBat(File fitxategia, Scanner sc) {
		System.out.println("Sartu ezabatu nahi duzun Erregistroa (kodea): ");
		String kodeaEzabatu = sc.nextLine();

		File fitxategiAuxiliar = new File(fitxategia.getParent(), "auxiliar_" + fitxategia.getName());

		try (BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
				BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategiAuxiliar))) {

			String line;
			boolean aurkituta = false;

			while ((line = reader.readLine()) != null) {
				// Konprobatu sartutako kodea
				if (line.startsWith(kodeaEzabatu + ";")) {
					System.out.println("Aurkitutako erregistroa: " + line);
					System.out.println("Ziur ezabatu nahi duzula? (bai/ez)");
					String confirmation = sc.nextLine();

					if (confirmation.equalsIgnoreCase("bai")) {
						aurkituta = true; // Erregistroa aurkitu bada markatu
						System.out.println("Erregistroa ezabatuko da.");
					} else {

						writer.write(line);
						writer.newLine();
					}
				} else {

					writer.write(line);
					writer.newLine();
				}
			}

			if (!aurkituta) {
				System.out.println("Ez da aurkitu erregistroa kodearekin: " + kodeaEzabatu);
			}
		} catch (IOException e) {
			System.out.println("Errorea fitxategia irakurtzean edo idaztean: " + e.getMessage());
			return;
		}

		if (fitxategia.delete()) {
			System.out.println("Fitxategia jadanik ezabatuta.");
		} else {
			System.out.println("Errorea fitxategia ezabatzean.");
			return;
		}

		if (fitxategiAuxiliar.renameTo(fitxategia)) {
			System.out.println("Fitxategia berriz izendatua: " + fitxategia.getName());
		} else {
			System.out.println("Errorea fitxategia berriz izendatzean.");
		}
	}

	/**
	 * Kopiatu fitxategia.
	 *
	 * @param fitxategia , gure fitxategia
	 * @param sc         , eskanerra
	 */
	public static void kopiatuFitxategia(File fitxategia, Scanner sc) {

		if (!fitxategia.exists()) {
			System.out.println("Errorea: Fitxategia ez da aurkitu: " + fitxategia.getAbsolutePath());
			return;
		}

		System.out.println("Sartu kopia gorde nahi duzun direktorioaren helbidea:");
		String direktorioString = sc.nextLine();
		File direktorio = new File(direktorioString);

		// Konrpobatu direktorioa
		if (!direktorio.isDirectory()) {
			System.out.println("Errorea: Ez da aurkitu direktorioa: " + direktorioString);
			return;
		}

		System.out.println("Sartu kopia gorde nahi duzun fitxategiaren izena : ");
		String izenaKopia = sc.nextLine() + ".txt";

		// konprobatu izena ez dagoela hutsik
		if (izenaKopia.trim().isEmpty()) {
			System.out.println("Errorea: Fitxategiaren izena hutsa da.");
			return;
		}

		Path fitxategiDestinokoa = Paths.get(direktorio.getAbsolutePath(), izenaKopia);

		// Konprobatu izne berdinarekin ez dagoela fitxategirik
		if (Files.exists(fitxategiDestinokoa)) {
			System.out
			.println("Errorea: Fitxategi honen berdina dago jadanik: " + fitxategiDestinokoa.toAbsolutePath());
			return;
		}

		// Fitxategia kopiatu
		try {
			Files.copy(fitxategia.toPath(), fitxategiDestinokoa);
			System.out.println("Fitxategia kopiatuta: " + fitxategiDestinokoa.toAbsolutePath());
		} catch (IOException e) {
			System.out.println("Errorea fitxategia kopiatzean: " + e.getMessage());
		}
	}

	/**
	 * Kontatu fitxategiko lerroak.
	 *
	 * @param fitxategia gure fitxategia
	 */
	public static void kontatuFitxategikoLerroak(File fitxategia) {
		int lineCount = 0;
		boolean isFirstLine = true; // Lehen lerroa ez du kontuan hartu behar

		try (BufferedReader reader = new BufferedReader(new FileReader(fitxategia))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				lineCount++; // Kontatu lerroa PAISES 1.0 gabe
			}
			System.out.println("Fitxategiak " + lineCount + " lerro ditu (PAISES 1.0 ezik).");
		} catch (IOException e) {
			System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
		}
	}

	/**
	 * Editatu errregistro bat.
	 *
	 * @param fitxategia gure fitxategia
	 * @param sc         , eskanerra
	 */
	public static void editatuErregistroBat(File fitxategia, Scanner sc) {
		System.out.println("Sartu editatu nahi duzun erregistroa (kodea):");
		String iritzia = sc.nextLine();
		File fitxategiBerria = new File(fitxategia.getParent(), "temp.txt");

		try (BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
				BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategiBerria))) {
			String line;
			boolean found = false;
			while ((line = reader.readLine()) != null) {
				if (line.contains(iritzia)) {
					found = true;
					System.out.println("Aurkitutako erregistroa: " + line);
					System.out.println(
							"Sartu erregistroan eguneratu nahi dituzun datuak (kodea; estatua; bizi-esperantza; data; populazioa; kapitala):");
					String newIritzi = sc.nextLine();
					writer.write(newIritzi);
					writer.newLine();
					System.out.println("Erregistroa eguneratu da.");
				} else {
					writer.write(line);
					writer.newLine();
				}
			}
			if (!found) {
				System.out.println("Ez da aurkitu erregistroa: " + iritzia);
			}
		} catch (IOException e) {
			System.out.println("Errorea Erregistroa editatzean: " + e.getMessage());
		}

		if (!fitxategia.delete()) {
			System.out.println("Errorea fitxategia ezabatzean.");
			return;
		}

		if (!fitxategiBerria.renameTo(fitxategia)) {
			System.out.println("Errorea fitxategia berriz izendatzean.");
		}
	}
}
