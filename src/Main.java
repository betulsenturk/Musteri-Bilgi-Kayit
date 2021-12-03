// Dosya okumak, arraylistin ozelliklerini kullanmak ve klavyeden veri almak icin gerekli olanlari import ediyoruz
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
//----------------------------------------------------------------------------------------------------------------------

// Ana islemlerimizin gerceklesecegi class
public class Main
{
    // Musterileri tutacak olan listemizi tanimliyoruz
    private DoublyLinkedList customers;

    // Kullaniciya sunulacak olan seceneklerin atamalarini yapiyoruz
    private static final int FILE_ADD = 1;      // Listeye dosyadan veri ekleme
    private static final int KEYBOARD_ADD = 2;  // Listeye klavyeden veri ekleme
    private static final int PRINT_TARGET = 3;  // Klavyeden adsoyadi alinan musterinin bilgilerini yazdirma
    private static final int DELETE_TARGET = 4; // Klavyeden adsoyadi alinan musteriyi listeden silme
    private static final int PRINT_ATOZ = 5;    // Listeyi Adan Zye yazdirma
    private static final int PRINT_ZTOA = 6;    // Listeyi Zden Aya yazdirma
    private static final int QUIT = 7;          // Cikis

    // Musterileri tutacak olan listemizi olusturuyoruz ve tanimladigimiz customersa atiyoruz
    Main(){
        customers = new DoublyLinkedList();
    }
//----------------------------------------------------------------------------------------------------------------------

    // Dosyadan verileri okuyup listeye eklemek icin metot
    public void readFile()
    {
        // Dosyayi acmak icin gerekli islemleri yapiyoruz
        Scanner fileIn = null;
        try
        {
            fileIn = new Scanner(new FileInputStream("customer.txt"));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Dosya bulunamadi.");
            System.exit(0);
        }

        // Dosyada okunacak yeni satir oldugu surece dongu devam edecek
        while (fileIn.hasNextLine())
        {
            // Musterinin bilgilerini tum satiri okuyarak aliyoruz
            String customerInfo = fileIn.nextLine();

            // Bilgileri virgullerinden ayiriyoruz
            String[] ayrikInfo = customerInfo.split(",");

            // Ayrilan bilgilerin sifirinci indexini adsoyad degiskenine atiyoruz
            String adSoyad = ayrikInfo[0];
            // Ayrilan bilgilerin birinci indexini adres degiskenine atiyoruz
            String adres = ayrikInfo[1];

            // Telefon numaralarini tutmak icin arraylist olusturuyoruz
            ArrayList<String> telNolar = new ArrayList<String>();

            // Ayrilan bilgilerin ikinci indexinden sonuna kadar teker teker okuyoruz
            for (int i = 2; i<ayrikInfo.length; i++)
            {
                // Bulundugumuz indexteki telefon numarasini aliyoruz
                String telNo = ayrikInfo[i];
                // Telefon numaralari dizisine ekliyoruz
                telNolar.add(telNo);
            }

            // Aldigimiz bilgilerle yeni bir musteri olusturup customer degiskenine atiyoruz
            CustomerInfo customer = new CustomerInfo(adSoyad, adres, telNolar);
            // Yeni musteriyi customers listesine sirali olarak ekliyoruz
            customers.insertInOrder(customer);
        }
        System.out.println("***Dosyadan ekleme islemi basarili.***");
        System.out.println();
        // Dosyadaki veriler bitince dosyayi kapatiyoruz
        fileIn.close();
    }
//----------------------------------------------------------------------------------------------------------------------

    // Klavyeden musteri bilgilerini alip listeye ekleyen metot
    public void readKeyboard()
    {
        // Klavyeden veri alabilmek icin Scanneri kullaniyoruz
        Scanner keyboard = new Scanner(System.in);

        // Musterinin adsoyadinin girilmesini bekliyoruz ve okuyup adsoyad degiskenine atiyoruz
        System.out.println("Musterinin ad-soyadi(Ilk harflerini buyuk yaziniz):");
        String adSoyad = keyboard.nextLine();

        // Musterinin adresinin girilmesini bekliyoruz ve okuyup adres degiskenine atiyoruz
        System.out.println("Musterinin adresi(Ilk harfi buyuk yaziniz):");
        String adres = keyboard.nextLine();

        // Musterinin telefon numaralarinin girilmesini bekliyoruz ve okuyup keyboardTelnolar degiskenine atiyoruz
        System.out.println("Musterinin telefon numaralari(Birden fazla numara var ise virgul(,) ile ayiriniz):");
        String keyboardTelnolar = keyboard.nextLine();

        // Telefon numaralarini tutmak icin arraylist olusturuyoruz
        ArrayList<String> telNolar = new ArrayList<String>();
        // Tek seferde girilen telefon numaralarini virgullerden ayiriyoruz
        String[] ayrikTelnolar = keyboardTelnolar.split(",");
        // Her bir telefon numarasini tek tek arrayliste ekliyoruz
        for (String telNo : ayrikTelnolar)
            telNolar.add(telNo);

        // Aldigimiz bilgilerle yeni bir musteri olusturup customer degiskenine atiyoruz
        CustomerInfo customer = new CustomerInfo(adSoyad, adres, telNolar);
        // Yeni musteriyi customers listesine sirali olarak ekliyoruz
        customers.insertInOrder(customer);

        System.out.println("***Listeye ekleme basarili.***");
        System.out.println();
    }
//----------------------------------------------------------------------------------------------------------------------

    // Klavyeden adsoyadi alinan musterinin bilgilerini yazdiran metot
    public void print_target()
    {
        // Klavyeden veri alabilmek icin Scanneri kullaniyoruz
        Scanner keyboard = new Scanner(System.in);

        // Bilgileri istenen musterinin adsoyadinin girilmesini bekliyoruz ve okuyup target degiskenine atiyoruz
        System.out.println("Bilgilerini gormek istediginiz musterinin ad-soyadi(Ilk harflerini buyuk yaziniz):");
        String target = keyboard.nextLine();

        // printTarget metodunu kullaniyoruz
        customers.printTarget(target);
        System.out.println();
    }
//----------------------------------------------------------------------------------------------------------------------

    // Klavyeden adsoyadi alinan musteriyi listeden silen metot
    public void delete_target()
    {
        // Klavyeden veri alabilmek icin Scanneri kullaniyoruz
        Scanner keyboard = new Scanner(System.in);

        // Listeden silinmek istenen musterinin adsoyadinin girilmesini bekliyoruz ve okuyup target degiskenine atiyoruz
        System.out.println("Listeden silmek istediginiz musterinin ad-soyadi(Ilk harflerini buyuk yaziniz):");
        String target = keyboard.nextLine();

        // removeTarget metodunu kullaniyoruz
        customers.removeTarget(target);
        System.out.println();
    }
//----------------------------------------------------------------------------------------------------------------------

    // Listeyi Adan Zye yazdiran metot
    public void print_AtoZ()
    {
        // printAtoZ metodunu kullaniyoruz
        customers.printAtoZ();
        System.out.println();
    }
//----------------------------------------------------------------------------------------------------------------------

    // Zden Aya yazdiran metot
    public void print_ZtoA()
    {
        // prinZtoA metodunu kullaniyoruz
        customers.printZtoA();
        System.out.println();
    }
//----------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args)
    {
        // Musteriyi olusturuyoruz
        Main customer = new Main();

        // Klavyeden veri alabilmek icin Scanneri kullaniyoruz
        Scanner keyboard = new Scanner(System.in);

        // choicea baslangic olarak FILE_ADD i atiyoruz
        int choice = FILE_ADD;

        // choice QUIT olmadigi surece dongu devam edecek
        while (choice != QUIT)
        {
            // Kullaniciya sunulan secenekler
            System.out.println("Choose from the following:");
            System.out.println("1) Add costumers from txt");
            System.out.println("2) Add new costumer from keyboard");
            System.out.println("3) Search customers by keyboard and display");
            System.out.println("4) Search customers by keyboard and delete");
            System.out.println("5) List all customers A to Z");
            System.out.println("6) List all customers Z to A");
            System.out.println("7) Quit");

            // choicei klavyeden aliyoruz
            choice = keyboard.nextInt();

            switch (choice)
            {
                case FILE_ADD:                // choice 1 ise
                    customer.readFile();      // readFile metodunu kullaniyoruz
                    break;

                case KEYBOARD_ADD:            // choice 2 ise
                    customer.readKeyboard();  // readKeyboard metodunu kullaniyoruz
                    break;

                case PRINT_TARGET:            // choice 3 ise
                    customer.print_target();  // print_target metodunu kullaniyoruz
                    break;

                case DELETE_TARGET:           // choice 3 ise
                    customer.delete_target(); // delete_target metodunu kullaniyoruz
                    break;

                case PRINT_ATOZ:              // choice 4 ise
                    customer.print_AtoZ();    // print_AtoZ metodunu kullaniyoruz
                    break;

                case PRINT_ZTOA:              // choice 5 ise
                    customer.print_ZtoA();    // print_ZtoA metodunu kullaniyoruz
                    break;
            }
        }
    }
}
//----------------------------------------------------------------------------------------------------------------------