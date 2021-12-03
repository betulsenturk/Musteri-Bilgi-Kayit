// Kodumuzda kullanmak icin ArrayListi import ediyoruz
import java.util.ArrayList;

// Musteri bilgilerini tanimlayan class
public class CustomerInfo
{
    private String adSoyad;           // Musterinin adini ve soyadini tutan degisken
    private String adres;             // Musterinin adresini tutan degisken
    private ArrayList<String> telNo;  // Musterinin telefon numaralarini tutan degisken

    // Musterinin bilgilerini parametre olarak alan constructor
    public CustomerInfo(String adSoyad, String adres, ArrayList<String> telNo)
    {
        setAdSoyad(adSoyad);
        setAdres(adres);
        setTelNo(telNo);
    }

    // get metodları
    public String getAdSoyad(){
        return adSoyad;
    }

    public String getAdres(){
        return adres;
    }

    public ArrayList<String> getTelNo(){
        return telNo;
    }

    // set metodları
    public void setAdSoyad(String adSoyad){
        this.adSoyad = adSoyad;
    }

    public void setAdres(String adres){
        this.adres = adres;
    }

    public void setTelNo(ArrayList<String> telNo){
        this.telNo = telNo;
    }

    // toString metodu
    public String toString()
    {
        return String.format("Ad-Soyad: %-15s Adres: %-15s Telefon numarasi: %-5s", getAdSoyad(), getAdres(),
                getTelNo().toString().replace("[", "").replace("]", ""));
    }

}
