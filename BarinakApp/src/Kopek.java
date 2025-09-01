// Hayvan sınıfından türedi
public class Kopek extends Hayvan {

    // Yeni köpek eklerken
    public Kopek(String isim, int yas) {
        super(isim, "Köpek", yas); // Bu kurucu var: Hayvan(String isim, String tur, int yas)
    }

    // Dosyadan okurken
    public Kopek(String id, String isim, int yas, boolean sahiplendiMi) {
        super(id, isim, "Köpek", yas, sahiplendiMi);
    }

    // Polymorphism: Köpeğe özel ses çıkarma
    @Override
    public void sesCikar() {
        System.out.println(getIsim() + ": Hav Hav!");
    }
}
