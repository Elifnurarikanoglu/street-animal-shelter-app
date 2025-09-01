import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BarinakService service = new BarinakService();

        while (true) {
            // Menü
            System.out.println("\n--- BARINAK MENÜ ---");
            System.out.println("1- Hayvan Ekle");
            System.out.println("2- Listele");
            System.out.println("3- Hayvan Sil");
            System.out.println("4- Hayvan Ara");
            System.out.println("5- Hayvan Sahiplendir");
            System.out.println("6- Çıkış");
            System.out.print("Seçiminiz: ");

            int secim;
            try { secim = Integer.parseInt(scanner.nextLine()); }
            catch (NumberFormatException e) { System.out.println("Geçerli bir sayı giriniz!"); continue; }

            switch (secim) {
                case 1:
                    // İsim kontrolü
                    String isim;
                    while (true) {
                        System.out.print("Hayvanın ismi: ");
                        isim = scanner.nextLine();
                        if (isim.matches("[a-zA-ZğüşöçıİĞÜŞÖÇ ]+")) break;
                        else System.out.println("Geçerli bir isim giriniz!");
                    }

                    // Yaş kontrolü
                    int yas;
                    while (true) {
                        System.out.print("Hayvanın yaşı: ");
                        try { yas = Integer.parseInt(scanner.nextLine()); if (yas >=0) break; }
                        catch (NumberFormatException e) { System.out.println("Geçerli bir sayı giriniz!"); }
                    }

                    // Tür seçimi
                    String tur;
                    while (true) {
                        System.out.print("Hayvan türü (Köpek/Kedi): ");
                        tur = scanner.nextLine();
                        if (tur.equalsIgnoreCase("Köpek") || tur.equalsIgnoreCase("Kedi")) break;
                        else System.out.println("Sadece Köpek veya Kedi yazınız!");
                    }

                    // Alt sınıf nesnesi oluşturma
                    Hayvan yeni;
                    if (tur.equalsIgnoreCase("Köpek")) yeni = new Kopek(isim, yas);
                    else yeni = new Kedi(isim, yas);

                    service.hayvanEkle(yeni);
                    break;

                case 2:
                    service.listele();
                    break;

                case 3:
                    System.out.print("Silmek istediğiniz hayvanın ismi: ");
                    String silIsim = scanner.nextLine();
                    service.hayvanSil(silIsim);
                    break;

                case 4:
                    System.out.print("Aramak istediğiniz isim veya tür: ");
                    String kelime = scanner.nextLine();
                    service.hayvanAra(kelime);
                    break;

                case 5:
                    System.out.print("Sahiplendirmek istediğiniz hayvanın ismi: ");
                    String sahiplendirmeIsim = scanner.nextLine();
                    service.sahiplendir(sahiplendirmeIsim);
                    break;

                case 6:
                    System.out.println("Çıkılıyor...");
                    return;

                default:
                    System.out.println("Geçersiz seçim! 1-6 arası bir değer giriniz.");
            }
        }
    }
}
