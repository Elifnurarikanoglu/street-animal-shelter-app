// Hayvan sınıfından türedi
public class Kedi extends Hayvan {

    // Yeni kedi eklerken
    public Kedi(String isim, int yas) {
        super(isim, "Kedi", yas);
    }

    // Dosyadan okurken
    public Kedi(String id, String isim, int yas, boolean sahiplendiMi) {
        super(id, isim, "Kedi", yas, sahiplendiMi);
    }

    // Polymorphism: Kediye özel ses çıkarma
    @Override
    public void sesCikar() {
        System.out.println(getIsim() + ": Miyav!");
    }
}
