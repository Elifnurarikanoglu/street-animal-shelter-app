import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Dosya işlemlerini yöneten sınıf
public class DosyaDepo {
    private static final String DOSYA_ADI = "hayvanlar.txt";

    // Hayvanı dosyaya ekler
    public void yaz(Hayvan hayvan) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_ADI, true))) {
            writer.write(hayvan.toCSV()); // CSV formatında yaz
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Dosyaya yazma hatası: " + e.getMessage());
        }
    }

    // Dosyadaki tüm hayvanları oku
    public List<Hayvan> oku() {
        List<Hayvan> hayvanlar = new ArrayList<>();
        File file = new File(DOSYA_ADI);
        if (!file.exists()) return hayvanlar; // Dosya yoksa boş liste

        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_ADI))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                Hayvan h = Hayvan.fromCSV(satir); // CSV’den nesne oluştur
                if (h != null) hayvanlar.add(h);
            }
        } catch (IOException e) {
            System.out.println("Dosyadan okuma hatası: " + e.getMessage());
        }
        return hayvanlar;
    }

    // Hayvan silme işlemi
    public void sil(String isim) {
        List<Hayvan> hayvanlar = oku();
        boolean bulundu = false;

        for (Hayvan h : hayvanlar) {
            if (h.getIsim().equalsIgnoreCase(isim)) {
                hayvanlar.remove(h);
                bulundu = true;
                break;
            }
        }

        if (bulundu) {
            dosyayiTemizle(); // önce dosyayı temizle
            for (Hayvan h : hayvanlar) yaz(h); // kalanları tekrar yaz
            System.out.println(isim + " başarıyla silindi!");
        } else {
            System.out.println(isim + " isminde bir hayvan bulunamadı.");
        }
    }

    // Dosyayı tamamen temizler
    private void dosyayiTemizle() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_ADI))) {}
        catch (IOException e) { System.out.println("Dosya temizleme hatası: " + e.getMessage()); }
    }

    // Hayvan arama
    public List<Hayvan> ara(String kelime) {
        List<Hayvan> sonuc = new ArrayList<>();
        for (Hayvan h : oku()) {
            if (h.getIsim().equalsIgnoreCase(kelime) || h.getTur().equalsIgnoreCase(kelime))
                sonuc.add(h);
        }
        return sonuc;
    }

    // Yeni metod: Sahiplendirme işlemi
    public void sahiplendir(String isim) {
        List<Hayvan> hayvanlar = oku(); // mevcut hayvanları oku
        boolean bulundu = false;

        // İsimle arayıp sahiplendiMi alanını güncelle
        for (Hayvan h : hayvanlar) {
            if (h.getIsim().equalsIgnoreCase(isim)) {
                h.setSahiplendiMi(true);
                bulundu = true;
                break;
            }
        }

        if (bulundu) {
            // Dosyayı temizle ve güncel listeyi yaz
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_ADI))) {
                for (Hayvan h : hayvanlar) {
                    writer.write(h.toCSV());
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Dosya güncelleme hatası: " + e.getMessage());
            }
            System.out.println(isim + " başarıyla sahiplendirildi!");
        } else {
            System.out.println(isim + " bulunamadı.");
        }
    }
}
