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

/**
 * The Class PaisFitxategiak.
 * Programa honek fitxategi-izen bat eta direktorio bat eskatzen dizkio erabiltzaileari. 
 * Gero, egiaztatu fitxategia direktorio horretan dagoen, eta, bestela, sortu.
 */
public class PaisFitxategiak {

    static String Kodea[] = {"31", "376", "90", "261", "685", "213", "291", "595", "30", "964"};
    static String Estatua[] = {"Holanda", "Andorra", "Turkia", "Madagascar", "Samoa Occidental",
            "Argelia", "Eritrea", "Paraguay", "Grecia", "Irak"};
    static int BiziEsperantza[] = {78, 0, 67, 52, 68, 70, 0, 68, 78, 66};
    static LocalDate DataSortu[] = {LocalDate.of(1581, 7, 26), LocalDate.of(1993, 3, 14),
            LocalDate.of(1923, 10, 29), LocalDate.of(1960, 6, 26), LocalDate.of(1962, 1, 1),
            LocalDate.of(1962, 7, 5), LocalDate.of(1993, 5, 24), LocalDate.of(1825, 8, 25),
            LocalDate.of(1830, 2, 3), LocalDate.of(1958, 7, 14)};
    static double Poblazioa[] = {15460000, 64000, 61058000, 13651000, 165000,
            27959000, 3400000, 4828000, 10467000, 20097000};
    static String Kapitala[] = {"Amsterdam", "Andorra La Vieja", "Ankara", "Antananarivo",
            "Apia", "Argel", "Asmara", "Asuncion", "Atenas", "Bagdad"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Fitxategiaren izena eskatu
        System.out.println("Sartu lan egingo duzun fitxategiaren izena:");
        String fitxategiIzenaString = sc.nextLine() + ".txt"; // Añade la extensión .txt

        // Eskatu direktorioa
        String helbideOsoaString = EskatuDirektorioa(sc);

        // Konprobatu fitxategia
        File fitxategia = KonprobatuEdoSortuFitxategia(helbideOsoaString, fitxategiIzenaString);

        // Menú de opciones
        int option;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Fitxategia bete datuekin.");
            System.out.println("2. Fitxategia hutsik utzi.");
            System.out.println("3. Bilatu eta erakutsi fitxategiko iritzi bat bere kodearekin.");
            System.out.println("4. Iritziak enumeratu (populazioaren arabera).");
            System.out.println("5. Iritzi berri bat gehitu.");
            System.out.println("6. Iritzi bat ezabatu.");
            System.out.println("7. Kopiatu fitxategia.");
            System.out.print("Aukeratu aukera (1-7): ");
            option = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    fitxategiaBete(fitxategia);
                    break;
                case 2:
                    fitxategiaHutsikUtzi(fitxategia, sc);
                    break;
                case 3:
                    bilatuIritziBat(fitxategia, sc);
                    break;
                case 4:
                    iritziakEnumeratu(fitxategia, sc);
                    break;
                case 5:
                    iritziBerriBatGehitu(fitxategia, sc);
                    break;
                case 6:
                    ezabatuIritziBat(fitxategia, sc);
                    break;
                case 7:
                   kopiatuFitxategia(fitxategia, sc);
                    break;
                case 8:
                    System.out.println("Irten da.");
                    break;
                default:
                    System.out.println("Aukera ezegokia. Saiatu berriro.");
            }
        } while (option != 9);
        sc.close();
    }

    public static String EskatuDirektorioa(Scanner sc) {
        String helbideOsoaString;
        File dirFile;

        // Eskatu direktorioa direktorio egoi bat sartu harte
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

    public static File KonprobatuEdoSortuFitxategia(String helbideOsoa, String fitxategiIzena) {
        File dirFile = new File(helbideOsoa);
        File fitxatefiosoaFile = new File(dirFile, fitxategiIzena);

        // Konprobatu fitxategia
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
        return fitxatefiosoaFile;
    }

    public static void fitxategiaBete(File fitxategia) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategia))) {
            // Lehenengo lerroa idatzi
            writer.write("PAISES 1.0");
            writer.newLine();

            // Idatzi datuak
            for (int i = 0; i < Kodea.length; i++) {
                writer.write(Kodea[i] + ";" + Estatua[i] + ";" + BiziEsperantza[i] + ";" + DataSortu[i] + ";" + Poblazioa[i] + ";" + Kapitala[i]);
                writer.newLine();
            }
            System.out.println("Fitxategia datuekin bete da.");
        } catch (IOException e) {
            System.out.println("Errorea fitxategia idaztean: " + e.getMessage());
        }
    }

    public static void fitxategiaHutsikUtzi(File fitxategia, Scanner sc) {
        System.out.println("Hutsik utzi nahi duzu fitxategia? (bai/ez)");
        String confirmation = sc.nextLine();
        if (confirmation.equalsIgnoreCase("bai")) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategia))) {
                writer.write("PAISES 1.0");
                writer.newLine(); // Asegúrate de que la primera línea se mantenga
                System.out.println("Fitxategia hutsik utzi da.");
            } catch (IOException e) {
                System.out.println("Errorea fitxategia hutsik uztean: " + e.getMessage());
            }
        } else {
            System.out.println("Ez da fitxategia hutsik utzi.");
        }
    }

    public static void bilatuIritziBat(File fitxategia, Scanner sc) {
        System.out.println("Sartu kodea iritzi bat bilatzeko:");
        String kodeaBilatu = sc.nextLine();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(fitxategia))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(kodeaBilatu + ";")) {
                    System.out.println("Aurkitutako iritzia: " + line);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Ez da aurkitu iritzi bat kodearekin: " + kodeaBilatu);
            }
        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }

    public static void iritziakEnumeratu(File fitxategia, Scanner sc) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fitxategia))) {
            String line;
            System.out.println("Iritziak (populazioaren arabera):");
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("PAISES")) { // Skip the header line
                    String[] parts = line.split(";");
                    System.out.println(parts[0] + ": " + parts[1] + " (Populazioa: " + parts[4] + ")");
                }
            }
        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }

    public static void iritziBerriBatGehitu(File fitxategia, Scanner sc) {
        System.out.println("Sartu iritzi berriaren kodea:");
        String kodea = sc.nextLine();
        System.out.println("Sartu iritzi berriaren izena:");
        String izena = sc.nextLine();
        System.out.println("Sartu iritzi berriaren bizi esperantza:");
        int biziEsperantza = sc.nextInt();
        System.out.println("Sartu iritzi berriaren sortze-data (YYYY-MM-DD formatua):");
        String dataSortuString = sc.next();
        LocalDate dataSortu = LocalDate.parse(dataSortuString);
        System.out.println("Sartu iritzi berriaren populazioa:");
        double populazioa = sc.nextDouble();
        sc.nextLine(); // Consume the newline character
        System.out.println("Sartu iritzi berriaren kapitulua:");
        String kapitulua = sc.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategia, true))) {
            writer.write(kodea + ";" + izena + ";" + biziEsperantza + ";" + dataSortu + ";" + populazioa + ";" + kapitulua);
            writer.newLine();
            System.out.println("Iritzi berri bat gehitu da.");
        } catch (IOException e) {
            System.out.println("Errorea iritzi berri bat gehitzean: " + e.getMessage());
        }
    }

    public static void ezabatuIritziBat(File fitxategia, Scanner sc) {
        System.out.println("Sartu ezabatu nahi duzun iritzia (kodea): ");
        String kodeaEzabatu = sc.nextLine();

        File fitxategiAuxiliar = new File(fitxategia.getParent(), "auxiliar_" + fitxategia.getName());
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
             BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategiAuxiliar))) {
            
            String line;
            boolean encontrado = false;

            while ((line = reader.readLine()) != null) {
                // Verifica si la línea contiene el código que queremos eliminar
                if (line.startsWith(kodeaEzabatu + ";")) {
                    System.out.println("Aurkitutako iritzia: " + line);
                    System.out.println("Ziur ezabatu nahi duzula? (bai/ez)");
                    String confirmation = sc.nextLine();
                    
                    if (confirmation.equalsIgnoreCase("bai")) {
                        encontrado = true; // Marcamos que encontramos el registro y se eliminará
                        System.out.println("Iritzia ezabatuko da.");
                    } else {
                        // Si no confirmamos, escribimos la línea en el archivo auxiliar
                        writer.write(line);
                        writer.newLine();
                    }
                } else {
                    // Si no es el registro que queremos eliminar, lo copiamos al auxiliar
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!encontrado) {
                System.out.println("Ez da aurkitu iritzia kodearekin: " + kodeaEzabatu);
            }
        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean edo idaztean: " + e.getMessage());
            return;
        }

        // Borramos el archivo original
        if (fitxategia.delete()) {
            System.out.println("Fitxategia jadanik ezabatuta.");
        } else {
            System.out.println("Errorea fitxategia ezabatzean.");
            return;
        }

        // Renombramos el archivo auxiliar
        if (fitxategiAuxiliar.renameTo(fitxategia)) {
            System.out.println("Fitxategia berriz izendatua: " + fitxategia.getName());
        } else {
            System.out.println("Errorea fitxategia berriz izendatzean.");
        }
    }
    public static void kopiatuFitxategia(File fitxategia, Scanner sc) {
        // Verificar si el archivo de origen existe
        if (!fitxategia.exists()) {
            System.out.println("Errorea: Fitxategia ez da aurkitu: " + fitxategia.getAbsolutePath());
            return;
        }

        System.out.println("Sartu kopia gorde nahi duzun direktorioaren helbidea:");
        String direktorioString = sc.nextLine();
        File direktorio = new File(direktorioString);

        // Verificar que el directorio existe
        if (!direktorio.isDirectory()) {
            System.out.println("Errorea: Ez da aurkitu direktorioa: " + direktorioString);
            return;
        }

        System.out.println("Sartu kopia gorde nahi duzun fitxategiaren izena : ");
        String izenaKopia = sc.nextLine() + ".txt";

        // Verificar que el nombre no esté vacío
        if (izenaKopia.trim().isEmpty()) {
            System.out.println("Errorea: Fitxategiaren izena hutsa da.");
            return;
        }

        Path fitxategiDestinokoa = Paths.get(direktorio.getAbsolutePath(), izenaKopia);

        // Verificar que no existe un archivo con el mismo nombre en la ubicación de destino
        if (Files.exists(fitxategiDestinokoa)) {
            System.out.println("Errorea: Fitxategi honekin berdina dago jadanik: " + fitxategiDestinokoa.toAbsolutePath());
            return;
        }

        // Crear la copia del archivo
        try {
            Files.copy(fitxategia.toPath(), fitxategiDestinokoa);
            System.out.println("Fitxategia kopiatuta: " + fitxategiDestinokoa.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Errorea fitxategia kopiatzean: " + e.getMessage());
        }
    }
}
