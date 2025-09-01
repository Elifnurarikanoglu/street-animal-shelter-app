import java.util.List;

// Servis sınıfı: iş mantığını yönetir
public class BarinakService {
    private DosyaDepo depo = new DosyaDepo();

    // Hayvan ekleme
    public void hayvanEkle(Hayvan hayvan) {
        depo.yaz(hayvan);
        System.out.println("Hayvan başarıyla eklendi!");
    }

    // Listeleme
    public void listele() {
        List<Hayvan> hayvanlar = depo.oku();
        if (hayvanlar.isEmpty()) System.out.println("Henüz hiç hayvan eklenmemiş!");
        else {
            System.out.println("\n--- Barınaktaki Hayvanlar ---");
            for (Hayvan h : hayvanlar) {
                System.out.println(h);   // toString() çağrılır
                h.sesCikar();            // türüne özel ses çıkarma
            }
        }
    }

    // Hayvan silme
    public void hayvanSil(String isim) { depo.sil(isim); }

    // Hayvan arama
    public void hayvanAra(String kelime) {
        List<Hayvan> sonuc = depo.ara(kelime);
        if (sonuc.isEmpty()) System.out.println(kelime + " için sonuç bulunamadı.");
        else {
            System.out.println("\nArama Sonuçları:");
            for (Hayvan h : sonuc) System.out.println(h);
        }
    }

    // Sahiplendirme
    public void sahiplendir(String isim) {
        depo.sahiplendir(isim); // işlemi DosyaDepo’ya devrettik
    }
}
