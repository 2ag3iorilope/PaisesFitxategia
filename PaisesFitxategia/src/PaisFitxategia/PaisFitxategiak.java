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
        File fitxategiFile = new File(helbideOsoaString, fitxategiIzenaString);
        int aukera;
        do {
            aukera = erakutsiMenua(sc);
            kudeatuAukera(aukera, fitxategiFile, sc);
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
        File fitxategiaFile = new File(dirFile, fitxategiIzena);

        if (fitxategiaFile.exists() && fitxategiaFile.isFile()) {
            System.out.println("Fitxategia existitzen da.");
            try (BufferedReader reader = new BufferedReader(new FileReader(fitxategiaFile))) {
                String firstLine = reader.readLine();
                if ("PAISES 1.0".equals(firstLine)) {
                    System.out.println("Lehenengo lerroa zuzena da: " + firstLine);
                } else {
                    System.out.println("Lehenengo lerroa ez da zuzena: " + firstLine);
                    BerridatziFitxategia(fitxategiaFile);
                }
            } catch (IOException e) {
                System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
            }
        } else {
            System.out.println("Fitxategia ez dago, sortzen saiatuko da...");
            BerridatziFitxategia(fitxategiaFile);
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
        System.out.println("2. Utzi fitxategia hutsik (eska iezaiozu baieztapena)");
        System.out.println("3. Bilatu eta erakutsi fitxategiko iritzia bere kodearekin");
        System.out.println("4. Iragazi eta erakutsi fitxategiko iritzien lista");
        System.out.println("5. Gehitu iritzi bat fitxategira");
        System.out.println("6. Fitxategia berrizendatu");
        System.out.println("7. Fitxategia ezabatu");
        System.out.println("8. Fitxategi berri bat sortu");
        System.out.println("9. Irten");

        System.out.print("Aukeratu zenbakia: ");
        return sc.nextInt();
    }

    public static void kudeatuAukera(int aukera, File fitxategia, Scanner sc) {
        sc.nextLine(); // Limpiar buffer del scanner
        switch (aukera) {
            case 1 -> idatziTaulaFitxategian(fitxategia);
            case 2 -> utziFitxategiaHutsik(fitxategia, sc);
            case 3 -> bilatuFitxategian(fitxategia, sc);
            case 4 -> iragaziFitxategian(fitxategia, sc);
            case 5 -> gehituIritziBat(fitxategia, sc); // Implementación de la quinta opción
            case 6 -> berrizendatuFitxategia(fitxategia, sc);
            case 7 -> ezabatuFitxategia(fitxategia);
            case 8 -> sortuFitxategiBerria(sc);
            case 9 -> System.out.println("Agur!");
            default -> System.out.println("Aukera ez da balioduna.");
        }
    }

    public static void idatziTaulaFitxategian(File fitxategia) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategia))) {
            // Escribe la primera línea "PAISES 1.0"
            writer.write("PAISES 1.0");
            writer.newLine();
            // Escribe los datos de los arrays en formato CSV
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

    // Segunda opción del menú: Dejar el archivo en blanco con confirmación
    public static void utziFitxategiaHutsik(File fitxategia, Scanner sc) {
        System.out.println("Ziur zaude fitxategia hutsik utzi nahi duzula? (bai/ez): ");
        String erantzuna = sc.nextLine().toLowerCase();

        if ("bai".equals(erantzuna)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategia))) {
                writer.write("PAISES 1.0");
                writer.newLine(); // Deja el archivo en blanco y escribe la primera línea
                System.out.println("Fitxategia hutsik utzi da eta lehen lerroa jarri da: PAISES 1.0");
            } catch (IOException e) {
                System.out.println("Errorea fitxategia hutsik uztean: " + e.getMessage());
            }
        } else {
            System.out.println("Fitxategia ez da hutsik utzi.");
        }
    }

    // Tercera opción del menú: Buscar y mostrar un registro con su código
    public static void bilatuFitxategian(File fitxategia, Scanner sc) {
        System.out.println("Sartu kodea (adibidez, 31) bilatzeko:");
        String kodeaBilatu = sc.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(fitxategia))) {
            String line;
            boolean found = false;
            // Leer el archivo línea por línea
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(kodeaBilatu + ";")) {
                    System.out.println("Aurkitutako iritzia: " + line);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Ez da aurkitu iritzia kode honekin: " + kodeaBilatu);
            }
        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }

    // Cuarta opción del menú: Filtrar y mostrar registros
    public static void iragaziFitxategian(File fitxategia, Scanner sc) {
        System.out.println("Sartu gutxieneko populazioa iragazteko:");
        int gutxienekoPopulazioa = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer

        try (BufferedReader reader = new BufferedReader(new FileReader(fitxategia))) {
            String line;
            boolean found = false;
            System.out.println("Fitxategiko iritzien lista:");
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("PAISES 1.0")) { // Ignorar la primera línea
                    String[] parts = line.split(";");
                    if (parts.length > 4) { // Verifica que la línea tenga suficientes campos
                        double populazioa = Double.parseDouble(parts[4]);
                        if (populazioa > gutxienekoPopulazioa) {
                            System.out.println(line);
                            found = true;
                        }
                    }
                }
            }
            if (!found) {
                System.out.println("Ez da aurkitu iritzirik populazio minimo honekin: " + gutxienekoPopulazioa);
            }
        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
        }
    }

    // Quinta opción del menú: Añadir un registro al final del archivo
    public static void gehituIritziBat(File fitxategia, Scanner sc) {
        System.out.println("Sartu kodea (adibidez, 31):");
        String kodea = sc.nextLine();
        
        // Comprobar si el código ya existe
        if (codigoExistente(fitxategia, kodea)) {
            System.out.println("Kodea jada existitzen da. Mesedez, beste kode bat aukeratu.");
            return;
        }

        System.out.println("Sartu estatua:");
        String estatua = sc.nextLine();
        System.out.println("Sartu bizitzen esperantza:");
        int biziEsperantza = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer
        System.out.println("Sartu dataren irailak (YYYY-MM-DD formatua):");
        String dataSortuStr = sc.nextLine();
        LocalDate dataSortu = LocalDate.parse(dataSortuStr);
        System.out.println("Sartu populazioa:");
        double populazioa = sc.nextDouble();
        sc.nextLine(); // Limpiar el buffer
        System.out.println("Sartu kapitala:");
        String kapitala = sc.nextLine();

        // Añadir registro al archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxategia, true))) {
            writer.write(kodea + ";" + estatua + ";" + biziEsperantza + ";" + dataSortu + ";" + populazioa + ";" + kapitala);
            writer.newLine();
            System.out.println("Iritzi bat gehitu da fitxategira: " + kodea);
        } catch (IOException e) {
            System.out.println("Errorea fitxategira iritzi bat gehitzean: " + e.getMessage());
        }
    }

    // Comprobar si el código ya existe en el archivo
    private static boolean codigoExistente(File fitxategia, String kodea) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fitxategia))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(kodea + ";")) {
                    return true; // El código ya existe
                }
            }
        } catch (IOException e) {
            System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
        }
        return false; // El código no existe
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
