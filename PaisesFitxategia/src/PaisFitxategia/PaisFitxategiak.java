package PaisFitxategia;

import java.io.File;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class PaisFitxategiak.
 */
public class PaisFitxategiak {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Fitxategiaren izena eskatu
		System.out.println("Sartu lan egingo duzun fitxategiaren izena:");
		String fitxategiIzenaString = sc.nextLine() + ".txt";

		// Eskatu direktorioa
		String helbideOsoaString = DirektorioaUtils.EskatuDirektorioa(sc);

		// Konprobatu fitxategia
		File fitxategia = DirektorioaUtils.KonprobatuEdoSortuFitxategia(helbideOsoaString, fitxategiIzenaString);

		// Aukera menua
		int option;
		do {
			System.out.println("\n=============================");
			System.out.println("           MENUA            ");
			System.out.println("=============================");
			System.out.println("1. 🗂️ Fitxategia bete datuekin");
			System.out.println("2. 🗑️ Fitxategia hutsik utzi");
			System.out.println("3. 🔍 Bilatu eta erakutsi erregistroa");
			System.out.println("4. 📊 Erregistroak enumeratu");
			System.out.println("5. ➕ Erregistro berri bat gehitu");
			System.out.println("6. ❌ Erregistro bat ezabatu");
			System.out.println("7. 📁 Kopiatu fitxategia");
			System.out.println("8. 📜 Kontatu fitxategiko lerroak");
			System.out.println("9. ✏️ Erregistro bat editatu");
			System.out.println("10. Irten");
			System.out.println("=============================");
			System.out.print("Aukeratu aukera (1-10): ");

			option = sc.nextInt();
			sc.nextLine();

			switch (option) {
			case 1:
				FitxategiOperazioak.fitxategiaBete(fitxategia);
				break;
			case 2:
				FitxategiOperazioak.fitxategiaHutsikUtzi(fitxategia, sc);
				break;
			case 3:
				FitxategiOperazioak.bilatuErregistroBat(fitxategia, sc);
				break;
			case 4:
				FitxategiOperazioak.ErregistroakEnumeratu(fitxategia, sc);
				break;
			case 5:
				FitxategiOperazioak.ErregistroBerriBatGehitu(fitxategia, sc);
				break;
			case 6:
				FitxategiOperazioak.ezabatuErregistroBat(fitxategia, sc);
				break;
			case 7:
				FitxategiOperazioak.kopiatuFitxategia(fitxategia, sc);
				break;
			case 8:
				FitxategiOperazioak.kontatuFitxategikoLerroak(fitxategia);
				break;
			case 9:
				FitxategiOperazioak.editatuErregistroBat(fitxategia, sc);
				break;
			case 10:

				System.out.println("Irteten...");
				break;
			default:
				System.out.println("Aukera ezegokia. Saiatu berriro.");
			}
		} while (option != 10);
		sc.close();
	}
}
