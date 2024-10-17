package PaisFitxategia;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class PaisFitxategiak {

    static String Kodea[] = { "31", "376", "90", "261", "685", "213", "291", "595", "30", "964" };
    static String Estatua[] = { "Holanda", "Andorra", "Turquia", "Madagascar", "Samoa Occidental", "Argelia", "Eritrea", "Paraguay", "Grecia", "Irak" };
    static int BiziEsperantza[] = { 78, 0, 67, 52, 68, 70, 0, 68, 78, 66 };
    static LocalDate DataSortu[] = { LocalDate.of(1581, 7, 26), LocalDate.of(1993, 3, 14), LocalDate.of(1923, 10, 29),
            LocalDate.of(1960, 6, 26), LocalDate.of(1962, 1, 1), LocalDate.of(1962, 7, 5), LocalDate.of(1993, 5, 24),
            LocalDate.of(1825, 8, 25), LocalDate.of(1830, 2, 3), LocalDate.of(1958, 7, 14) };
    static double Poblazioa[] = { 15460000, 64000, 61058000, 13651000, 165000, 27959000, 3400000, 4828000, 10467000,
            20097000 };
    static String Kapitala[] = { "Amsterdam", "Andorra La Vieja", "Ankara", "Antananarivo", "Apia", "Argel", "Asmara",
            "Asuncion", "Atenas", "Bagdad" };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Fitxategiaren izena eskatu
        System.out.println("Sartu lan egingo duzun fitxategiaren izena:");
        String fitxategiIzenaString = sc.nextLine() + ".txt"; // Añade la extensión .txt

        // Eskatu direktorioa
        String helbideOsoaString = EskatuDirektorioa(sc);

        // Konprobatu edo sortu fitxategia
        KonprobatuEdoSortuFitxategia(helbideOsoaString, fitxategiIzenaString);

        // Menua erakutsi
        File fitxatefiosoaFile = new File(helbideOsoaString, fitxategiIzenaString);
        int aukera;
        do {
            aukera = erakutsiMenua(sc);
            kudeatuAukera(aukera, fitxatefiosoaFile, sc);
        } while (aukera != 9); // Salir del menú cuando elija la opción 9
    }

    public static String EskatuDirektorioa(Scanner sc) {
        String helbideOsoaString;
        File dirFile;

        do {
            System.out.println("Sartu fitxategiaren helbide osoa:");
            helbideOsoaString = sc.nextLine();
            dirFile = new File(helbideOsoaString);

            if (!dirFile.isDirectory()) {
                System.out.println("Ez da aurkitu direktorioa: " + helbideOsoaString);
            }
        } while (!dirFile.isDirectory());

        return helbideOsoaString;
    }

    public static void KonprobatuEdoSortuFitxategia(String helbideOsoa, String fitxategiIzena) {
        File dirFile = new File(helbideOsoa);
        File fitxatefiosoaFile = new File(dirFile, fitxategiIzena);

        if (fitxatefiosoaFile.exists() && fitxatefiosoaFile.isFile()) {
            System.out.println("Fitxategia existitzen da.");
            try (BufferedReader reader = new BufferedReader(new FileReader(fitxatefiosoaFile))) {
                String firstLine = reader.readLine();
                if ("PAISES 1.0".equals(firstLine)) {
                    System.out.println("Lehenengo lerroa zuzena da: " + firstLine);
                } else {
                    System.out.println("Lehenengo lerroa ez da zuzena: " + firstLine);
                    BerridatziFitxategia(fitxatefiosoaFile);
                }
            } catch (IOException e) {
                System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
            }
        } else {
            System.out.println("Fitxategia ez dago, sortzen saiatuko da...");
            BerridatziFitxategia(fitxatefiosoaFile);
        }
    }

    public static void BerridatziFitxategia(File fitxategia) {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategia))) {
                writer.write("PAISES 1.0");
                writer.newLine();
                System.out.println("Fitxategia berridatzi da eta lehen lerroa zuzen jarri da: PAISES 1.0");
            }
        } catch (IOException e) {
            System.out.println("Errorea fitxategia sortzean edo berridaztean: " + e.getMessage());
        }
    }

    public static int erakutsiMenua(Scanner sc) {
        System.out.println("\nMenu:");
        System.out.println("1. Fitxategia berridatzi taulako datuekin");
        System.out.println("2. Gehitu edukia fitxategian");
        System.out.println("3. Erakutsi fitxategiaren ruta");
        System.out.println("4. Fitxategia berrizendatu");
        System.out.println("5. Fitxategia ezabatu");
        System.out.println("6. Fitxategi berria sortu");
        System.out.println("7. Erakutsi fitxategiaren tamaina");
        System.out.println("8. Irakurri fitxategia");
        System.out.println("9. Irten");

        System.out.print("Aukeratu zenbakia: ");
        return sc.nextInt();
    }

    public static void kudeatuAukera(int aukera, File fitxategia, Scanner sc) {
        sc.nextLine(); // Limpiar buffer del scanner
        switch (aukera) {
            case 1 -> idatziTaulaFitxategian(fitxategia);
            case 2 -> gehituFitxategian(fitxategia, sc);
            case 3 -> System.out.println("Fitxategiaren ruta: " + fitxategia.getAbsolutePath());
            case 4 -> berrizendatuFitxategia(fitxategia, sc);
            case 5 -> ezabatuFitxategia(fitxategia);
            case 6 -> sortuFitxategiBerria(sc);
            case 7 -> System.out.println("Fitxategiaren tamaina: " + fitxategia.length() + " bytes");
            case 8 -> irakurriFitxategia(fitxategia);
            case 9 -> System.out.println("Agur!");
            default -> System.out.println("Aukera ez da balioduna.");
        }
    }

    public static void idatziTaulaFitxategian(File fitxategia) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategia))) {
            writer.write("PAISES 1.0");
            writer.newLine();
            for (int i = 0; i < Kodea.length; i++) {
                writer.write(Kodea[i] + ";" + Estatua[i] + ";" + BiziEsperantza[i] + ";" + DataSortu[i] + ";"
                        + Poblazioa[i] + ";" + Kapitala[i]);
                writer.newLine();
            }
            System.out.println("Fitxategia berridatzi da taulako datuekin.");
        } catch (IOException e) {
            System.out.println("Errorea fitxategia idaztean: " + e.getMessage());
        }
    }

    public static void gehituFitxategian(File fitxategia, Scanner sc) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategia, true))) {
            System.out.println("Sartu fitxategian gehituko duzun testua:");
            String text = sc.nextLine();
            writer.write(text);
            writer.newLine();
            System.out.println("Testua gehitu da.");
        } catch (IOException e) {
            System.out.println("Errorea testua gehitzean: " + e.getMessage());
        }
    }

    public static void berrizendatuFitxategia(File fitxategia, Scanner sc) {
        System.out.println("Sartu fitxategi berriaren izena (ezin da hutsik egon):");
        String izenBerria = sc.nextLine();
        if (!izenBerria.isEmpty()) {
            File berria = new File(fitxategia.getParent(), izenBerria + ".txt");
            if (fitxategia.renameTo(berria)) {
                System.out.println("Fitxategia berrizendatu da: " + berria.getAbsolutePath());
            } else {
                System.out.println("Fitxategia ez da berrizendatu.");
            }
        } else {
            System.out.println("Izen berria hutsik dago.");
        }
    }

    public static void ezabatuFitxategia(File fitxategia) {
        if (fitxategia.delete()) {
            System.out.println("Fitxategia ezabatu da.");
        } else {
            System.out.println("Fitxategia ezin izan da ezabatu.");
        }
    }

    public static void sortuFitxategiBerria(Scanner sc) {
        System.out.println("Sartu fitxategi berriaren izena (hutsik egon gabe):");
        String izenBerria = sc.nextLine();
        if (!izenBerria.isEmpty()) {
            File berria = new File(izenBerria + ".txt");
            try {
                if (berria.createNewFile()) {
                    System.out.println("Fitxategi berria sortu da: " + berria.getAbsolutePath());
                } else {
                    System.out.println("Fitxategia ez da sortu.");
                }
            } catch (IOException e) {
                System.out.println("Errorea fitxategia sortzean: " + e.getMessage());
            }
        } else {
            System.out.println("Izen berria hutsik dago.");
        }
    }

    public static void irakurriFitxategia(File fitxategia) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fitxategia))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }
}
