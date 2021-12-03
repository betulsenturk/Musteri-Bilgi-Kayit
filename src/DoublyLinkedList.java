// Cift yonlu liste classı
public class DoublyLinkedList
{
//----------------------------------------------------------------------------------------------------------------------

    // Node inner class olarak kullanildi
    private class Node
    {
        private CustomerInfo data;   // Musteri bilgilerinin tutulacagi dugum
        private Node previous;
        private Node next;

        // Parametresiz constructor
        public Node()
        {
            data = null;
            previous = null;
            next = null;
        }

        // Baslangic olarak yalnizca musterinin bilgilerini alan constructor
        public Node(CustomerInfo data)
        {
            this.data = data;
            previous = null;
            next = null;
        }

        // Tanimladigimiz tum degiskenleri parametre olarak alan constructor
        public Node(CustomerInfo data, Node previous, Node next)
        {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
        // Nodedaki dataya ulasmamizi saglayan get metodu
        public CustomerInfo getData()
        {
            return data;
        }
    }
//----------------------------------------------------------------------------------------------------------------------

    // Listemizin head ve taili
    private Node head;
    private Node tail;

    // Baslangic olarak head ve taila null atadik
    public DoublyLinkedList()
    {
        head = tail = null;
    }
//----------------------------------------------------------------------------------------------------------------------

    // Soyada gore alfabetik sirali olacak sekilde listeye ekleme yapan metot
    public void insertInOrder(CustomerInfo insertData)
    {
        // Listemiz bos ise,
        if (isEmpty())
            head = tail = new Node(insertData);   // Head ve tail ilk gelen musteriyi gosteriyor

        else   // Liste bos degil ise,
        {
            // Headdeki musterinin adsoyadini ayiriyoruz
            String[] headadsoyad = head.getData().getAdSoyad().split(" ");
            // Listenin sonundaki eleman ile soyadi cekiyoruz
            String headsoyad = headadsoyad[headadsoyad.length-1];

            // Taildaki musterinin adsoyadini ayiriyoruz
            String[] tailadsoyad = tail.getData().getAdSoyad().split(" ");
            // Listenin sonundaki eleman ile soyadi cekiyoruz
            String tailsoyad = tailadsoyad[tailadsoyad.length-1];

            // Eklenecek olan musterinin adsoyadini ayiriyoruz
            String[] insertadsoyad = insertData.getAdSoyad().split(" ");
            // Listenin sonundaki eleman ile soyadi cekiyoruz
            String insertsoyad= insertadsoyad[insertadsoyad.length-1];

            // Headdeki musterinin ve eklenecek musterinin soyadlari ayniysa ve eklenecek musterinin adi alfabede once geliyorsa,
            if (headsoyad.equals(insertsoyad) && head.getData().getAdSoyad().compareTo(insertData.getAdSoyad()) > 0)
            {
                // Eski headin previousu eklenecek musteriyi gosterecek sekilde ve
                // Eklenecek musteri yeni head olacak sekilde ayarliyoruz
                head = head.previous = new Node(insertData, null, head);
                // Yeni musteri artik head oldugu icin previousu nullu gosterecek, nexti de eski headi gosterecek
            }
            //**********************************************************************************************************

            // Eklenecek musterinin soyadi, headdeki musterinin soyadina gore alfabede once geliyorsa,
            else if (headsoyad.compareTo(insertsoyad) > 0)
            {
                // Eski headin previousu eklenecek musteriyi gosterecek sekilde ve
                // Eklenecek musteri yeni head olacak sekilde ayarliyoruz
                head = head.previous = new Node(insertData, null, head);
                // Yeni musteri artik head oldugu icin previousu nullu gosterecek, nexti de eski headi gosterecek
            }
            //**********************************************************************************************************

            // Taildaki musterinin ve eklenecek musterinin soyadlari ayniysa ve eklenecek musterinin adi alfabede sonra geliyorsa,
            else if (tailsoyad.equals(insertsoyad) && tail.getData().getAdSoyad().compareTo(insertData.getAdSoyad()) < 0)
            {
                // Eski tailin nexti eklenecek musteriyi gosterecek sekilde ve
                // Eklenecek musteri yeni tail olacak sekilde ayarliyoruz
                tail = tail.next = new Node(insertData, tail, null);
                // Yeni musteri artik tail oldugu icin previousu eski taili gosterecek, nexti de nullu gosterecek
            }
            //**********************************************************************************************************

            // Eklenecek musterinin soyadi, taildaki musterinin soyadina gore alfabede sonra geliyorsa,
            else if (tailsoyad.compareTo(insertsoyad) < 0)
                {
                    // Eski tailin nexti eklenecek musteriyi gosterecek sekilde ve
                    // Eklenecek musteri yeni tail olacak sekilde ayarliyoruz
                    tail = tail.next = new Node(insertData, tail, null);
                    // Yeni musteri artik tail oldugu icin previousu eski taili gosterecek, nexti de nullu gosterecek
                }
            //**********************************************************************************************************

            else   // Eklenecek musteri headden onceye veya taildan sonraya eklenmeyecekse,
            {
                // Headden baslayip uygun yeri bulabilmek icin headi previous, headin nextini de current yaptik
                Node current = head.next;
                Node previous = head;

                // Currenttaki musterinin adsoyadini ayiriyoruz
                String[] currentadsoyad = current.getData().getAdSoyad().split(" ");
                // Listenin sonundaki eleman ile soyadi cekiyoruz
                String currentsoyad = currentadsoyad[currentadsoyad.length-1];

                // current nulla esit olmadigi ve currentin soyadi eklenecek musterinin soyadindan once geldigi surece ya da
                // current nulla esit olmadigi ve currentin soyadi ile eklenecek musterinin soyadi ayniyken ve currentin adi eklencek musterinin adindan once geldigi surece,
                // donguye devam ediyoruz
                while ((current != null && (currentsoyad).compareTo(insertsoyad) < 0)||
                        (current != null && currentsoyad.equals(insertsoyad) && current.getData().getAdSoyad().compareTo(insertData.getAdSoyad()) < 0))
                {
                    // previousi yeni current olarak degistiriyoruz
                    previous = current;
                    // currenti de bir yana kaydiriyoruz
                    current = current.next;
                    // dongude tekrar karsilastirabilmek icin currentin soyadini guncelliyoruz
                    currentadsoyad = current.getData().getAdSoyad().split(" ");
                    currentsoyad = currentadsoyad[currentadsoyad.length-1];
                }
                // Yeni gelen musteriyi previous ile currentin arasina ekliyoruz
                previous.next = current.previous = new Node(insertData, previous, current);
                // Araya ekledigimiz icin yeni musterinin previousi previousi gosterecek, nexti de currenti gosterecek
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    // Verilen adsoyada gore listeden musteriyi bulmayi saglayan metot
    private Node find(String target)
    {
        // Listeyi dolasabilmek icin position degiskeni olusturuyoruz ve bastan baslayacagi icin de headi atiyoruz
        Node position = head;
        // Bulunan pozisyondaki musteri ile kullanicidan aldigimiz musteriyi karsilastirabilmek icin degisken olusturduk
        String itemAtPosition;

        // Position null olmadigi surece yani liste bitene kadar,
        while (position != null)
        {
            // Olusturdugumuz degiskene bulundugumuz pozisyondaki musterinin adsoyad bilgisini atiyoruz
            itemAtPosition = position.getData().getAdSoyad();

            // Adsoyadlar eslesirse,
            if (itemAtPosition.equals(target))
                // Verilen adsoyadin bulundugu pozisyonu donduruyoruz
                return position;
            // Adsoyadlar eslesmezse positioni bir sonraki musteriye kaydiriyoruz
            position = position.next;
        }
        // Verilen adsoyad listede yoksa null donduruyoruz
        return null;
    }
//----------------------------------------------------------------------------------------------------------------------

    // Verilen adsoyada gore musterinin bilgilerini yazdiran metot
    public void printTarget(String target)
    {
        // Bilgilerin bulundugu pozisyonu tutmak icin bir degisken olusturuyoruz ve find metodu ile verilen adsoyada gore musterinin adresini atiyoruz
        Node info = find(target);

        // Verilen adsoyad listede yok ise, bilgilendirme mesaji yazdiriyoruz
        if (info == null)
            System.out.println("Aradiginiz kisi listede bulunmamaktadir.");
        // Verilen adsoyad listede var ise, musterinin bilgilerini yazdiriyoruz
        else
        System.out.println(info.getData());
    }
//----------------------------------------------------------------------------------------------------------------------

    // Verilen adsoyada gore musteriyi listeden silen metot
    public void removeTarget(String target)
    {
        // Bilgilerin bulundugu pozisyonu tutmak icin bir degisken olusturuyoruz ve find metodu ile verilen adsoyada gore musterinin adresini atiyoruz
        Node info = find(target);

        // Verilen adsoyad listede yok ise, bilgilendirme mesaji yazdiriyoruz
        if (info == null)
            System.out.println("Aradiginiz kisi listede bulunmamaktadir.");
        // Verilen adsoyad listede var ise,
        else
        {
            // infonun previousu null ise info headdir
            if(info.previous == null)
            {
                // yeni headi infonun nextini gosterecek sekilde bagliyoruz boylece infoya erisim kalmayacak
                head = info.next;
                // previous baglantisini da koparmak icin yeni headin previousini null yapiyoruz
                head.previous = null;
            }
            //**********************************************************************************************************

            // infonun nexti null ise info taildir
            else if (info.next == null)
            {
                // yeni taili infonun previousi olarak guncelliyoruz
                tail = info.previous;
                // yeni tailin nextini de null olarak degistirerek eski tail ile baglantisini kopariyoruz
                tail.next = null;
            }
            //**********************************************************************************************************

            // info head veya tailda degilse,
            else
            {
                // infodan onceki musterinin next baglantisini, infodan sonraki musteriyi gosterecek sekilde degistiriyoruz
                info.previous.next = info.next;
                // infodan sonraki musterinin previous baglantisini da infodan onceki musteriyi gosterecek sekilde degistiyoruz
                info.next.previous = info.previous;
            }
            System.out.println("***Silme islemi basarili.***");
        }
    }
//----------------------------------------------------------------------------------------------------------------------

    // Listeyi Adan Zye sirali olarak yazdiran metot
    public void printAtoZ()
    {
        // Listeye eklemeleri sirali yaptigimiz icin listeyi bastan sona dolasmamiz yeterlidir
        // Bastan baslamak icin current degiskenine head atiyoruz
        Node current = head;

        // Listedeki tum elemanlar bitene kadar dongu devam eder
        while (current != null)
        {
            // Currenttaki musterinin bilgilerini yazdiriyoruz
            System.out.println(current.getData());
            // Currenti bir yana kaydiriyoruz
            current = current.next;
        }
    }
//----------------------------------------------------------------------------------------------------------------------

    // Listeyi Zden Aya sirali olarak yazdiran metot
    public void printZtoA()
    {
        // Listeye eklemeleri sirali yaptigimiz icin listeyi sondan basa dolasmamiz yeterlidir
        // Sondan baslamak icin current degiskenine tail atiyoruz
        Node current = tail;

        // Listedeki tum elemanlar bitene kadar dongu devam eder
        while (current != null)
        {
            // Currenttaki musterinin bilgilerini yazdiriyoruz
            System.out.println(current.getData());
            // Currenti bir geriye kaydiriyoruz
            current = current.previous;
        }
    }
//----------------------------------------------------------------------------------------------------------------------

    // Listenin bos olup olmadigini kontrol eden metot
    public boolean isEmpty( )
    {
        return (head == null);
    }
}
//----------------------------------------------------------------------------------------------------------------------