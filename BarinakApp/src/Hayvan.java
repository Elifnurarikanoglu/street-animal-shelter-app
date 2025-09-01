import java.util.UUID;

// Üst sınıf: tüm hayvanlar için ortak özellikleri tutar
public class Hayvan {
    private final String id;       // her hayvanın benzersiz kimliği
    private String isim;           // hayvanın ismi
    private String tur;            // hayvanın türü (Köpek/Kedi/Diğer)
    private int yas;               // yaş
    private boolean sahiplendiMi;  // sahiplendirme durumu

    // Yeni hayvan eklerken id otomatik atanır
    public Hayvan(String isim, String tur, int yas) {
        this(UUID.randomUUID().toString(), isim, tur, yas, false);
    }

    // Dosyadan okuma veya özel durumlar için tüm alanlar hazır alınır
    public Hayvan(String id, String isim, String tur, int yas, boolean sahiplendiMi) {
        this.id = id;
        this.isim = isim;
        this.tur = tur;
        this.setYas(yas);
        this.sahiplendiMi = sahiplendiMi;
    }

    // --- Getter/Setter ---
    public String getId() { return id; }
    public String getIsim() { return isim; }
    public void setIsim(String isim) { this.isim = isim; }
    public String getTur() { return tur; }
    public void setTur(String tur) { this.tur = tur; }
    public int getYas() { return yas; }
    public void setYas(int yas) { if (yas < 0) yas = 0; this.yas = yas; }
    public boolean isSahiplendiMi() { return sahiplendiMi; }
    public void setSahiplendiMi(boolean sahiplendiMi) { this.sahiplendiMi = sahiplendiMi; }

    // Ekrana düzgün yazdırma
    @Override
    public String toString() {
        return "İsim: " + isim + " | Tür: " + tur + " | Yaş: " + yas +
                " | Sahiplendi mi: " + (sahiplendiMi ? "Evet" : "Hayır");
    }

    // --- Ses çıkarma metodunu alt sınıflar override eder ---
    public void sesCikar() {
        System.out.println("Bu hayvan sessiz.");
    }

    // --- CSV formatına çevirme ---
    public String toCSV() {
        String temizIsim = (isim == null) ? "" : isim.replace(';', ',');
        String temizTur = (tur == null) ? "" : tur.replace(';', ',');
        return id + ";" + temizIsim + ";" + temizTur + ";" + yas + ";" + sahiplendiMi;
    }

    // --- CSV satırından nesne oluşturma ---
    public static Hayvan fromCSV(String line) {
        if (line == null || line.isBlank()) return null;
        String[] p = line.split(";", -1); // -1 boş alanları korur
        if (p.length < 5) return null;
        String id = p[0];
        String isim = p[1];
        String tur = p[2];
        int yas = 0;
        try { yas = Integer.parseInt(p[3]); } catch (NumberFormatException ignored) {}
        boolean sahiplendi = Boolean.parseBoolean(p[4]);

        // Türüne göre alt sınıf nesnesi oluştur
        if (tur.equalsIgnoreCase("Köpek")) return new Kopek(id, isim, yas, sahiplendi);
        else if (tur.equalsIgnoreCase("Kedi")) return new Kedi(id, isim, yas, sahiplendi);
        else return new Hayvan(id, isim, tur, yas, sahiplendi);
    }
}
