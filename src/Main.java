import java.util.*;

public class Main {

    public static ArrayList<ArrayList<Integer>> sortBySuites(ArrayList<Integer> deck, Integer selectedKey){
        // bir destedeki kartları renklerine göre diz, sahte okeyin hangi renk ve değer olduğunu bulup yerleştirir
        ArrayList<ArrayList<Integer>> sortedKeys = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> yellowSuite = new ArrayList<Integer>();
        ArrayList<Integer> blueSuite = new ArrayList<Integer>();
        ArrayList<Integer> blackSuite = new ArrayList<Integer>();
        ArrayList<Integer> redSuite = new ArrayList<Integer>();
        sortedKeys.add(yellowSuite);
        sortedKeys.add(blueSuite);
        sortedKeys.add(blackSuite);
        sortedKeys.add(redSuite);
        for(Integer key:deck){

            if(0<=key && key<=12){
              sortedKeys.get(0).add(key);

           }
           else if(13<=key && key<=25){
                sortedKeys.get(1).add(key);

           }
           else if(26<=key && key<=38){
                sortedKeys.get(2).add(key);

           }
           else if(39<=key && key<=51){
                sortedKeys.get(3).add(key);
           }
            else if(key==52){
                if(0<=selectedKey && selectedKey<=12){
                    sortedKeys.get(0).add(selectedKey);

                }
                else if(13<=selectedKey && selectedKey<=25){
                    sortedKeys.get(1).add(selectedKey);

                }
                else if(26<=selectedKey && selectedKey<=38){
                    sortedKeys.get(2).add(selectedKey);

                }
                else if(39<=selectedKey && selectedKey<=51){
                    sortedKeys.get(3).add(selectedKey);
                }
            }

       }
       for(ArrayList<Integer> sortedList : sortedKeys){
           System.out.print(sortedList);

       }

        return sortedKeys;
    }
    public static ArrayList<ArrayList<Integer>> sortBySerialMatches(ArrayList<ArrayList<Integer>> sortedBySuites){
        ArrayList<ArrayList<Integer>> sortedKeys = new ArrayList<ArrayList<Integer>>();
        //aynı renkde olan taşlar için sıralı dizim olacak şekilde listlere parçalara
        int lastKey ;
        for(ArrayList<Integer> suite :sortedBySuites){


            if(suite.size()>0) {
                ArrayList<Integer> sortedPartDeck = new ArrayList<Integer>();
                lastKey = suite.get(0);

                sortedPartDeck.add(lastKey);
                for (int i = 1; i < suite.size(); i++) {

                    if(lastKey+1 == suite.get(i)){
                        sortedPartDeck.add(suite.get(i));
                        lastKey = suite.get(i);
                    }
                    else{
                        sortedKeys.add(sortedPartDeck);
                        sortedPartDeck = new ArrayList<Integer>();
                        sortedPartDeck.add(suite.get(i));
                        lastKey = suite.get(i);
                    }
                }
                sortedKeys.add(sortedPartDeck);
            }

        }

        for(ArrayList<Integer> sortedList:sortedKeys){
            System.out.print(sortedList);
        }

            return sortedKeys;
    }
    public static ArrayList<ArrayList<Integer>> sortByValueMatches(ArrayList<ArrayList<Integer>> sortedBySerial){

        ArrayList<ArrayList<Integer>> sortedFlatKeys = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> flatUnmatchedDeck = new ArrayList<Integer>();
        // per olmuş taşları düzenleme
        for(ArrayList<Integer> partDeck :sortedBySerial){


            if(partDeck.size()<3) {
                flatUnmatchedDeck.addAll(partDeck);
            }

        }
        //farklı renklerin aynı değerlerini sıralamak için ilk önce mod 13e göre düzenlenmesi lazım
        Collections.sort(flatUnmatchedDeck,(Integer i1,Integer i2) -> Integer.compare(i1%13,i2%13));
        //sıralı şekilde düzenlenmiş taşların boşta kalan taşlarını
        // ,farklı renkde aynı değerde olan taşlar için listere dizim işlemi yap
        int lastKey = flatUnmatchedDeck.get(0);
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        tempList.add(lastKey);
        sortedFlatKeys.add(tempList);
        for(int i=1;i<flatUnmatchedDeck.size();i++){
            if(lastKey != flatUnmatchedDeck.get(i) && lastKey%13 == flatUnmatchedDeck.get(i)%13){
                tempList.add(flatUnmatchedDeck.get(i));
                lastKey = flatUnmatchedDeck.get(i);
            }
            else{
                tempList = new ArrayList<Integer>();
                tempList.add(flatUnmatchedDeck.get(i));
                sortedFlatKeys.add(tempList);
                lastKey = flatUnmatchedDeck.get(i);

            }

        }
        // sıralı düzenlenmiş perleri listeye geri ekle
        for(ArrayList<Integer> partDeck :sortedBySerial){


            if(partDeck.size()>=3) {
                sortedFlatKeys.add(partDeck);
            }

        }

        for(ArrayList<Integer> sortedList:sortedFlatKeys){
            System.out.print(sortedList);
        }


        return sortedFlatKeys;
    }
    public static Integer calculateSerialPoints(ArrayList<ArrayList<Integer>> sortedSerialDeck,boolean hasJoker){
        //düz gidilmiş el puanını hesapla
        int totalSerialPoint = 0 ;

        for(ArrayList<Integer> partDeck : sortedSerialDeck){
            if(partDeck.size() <=2){
                totalSerialPoint += partDeck.size();
            }
        }
        // joker taşı herhangi bir ikiliyi üçlü yapabileceği için 2 boş taş puanı silinir
        if (hasJoker){
            totalSerialPoint -= 2;
        }
        return totalSerialPoint;
    }
    public static ArrayList<ArrayList<Integer>> sortByDoubleMatches(ArrayList<ArrayList<Integer>> sortedBySuites){
        ArrayList<ArrayList<Integer>> sortedKeys = new ArrayList<ArrayList<Integer>>();
        //çiftli olacak şekilde taşları listelere ayır
        int lastKey ;
        for(ArrayList<Integer> suite :sortedBySuites){


            if(suite.size()>0) {
                ArrayList<Integer> sortedPartDeck = new ArrayList<Integer>();
                lastKey = suite.get(0);

                sortedPartDeck.add(lastKey);
                for (int i = 1; i < suite.size(); i++) {

                    if(lastKey == suite.get(i)){
                        sortedPartDeck.add(suite.get(i));
                        lastKey = suite.get(i);
                    }
                    else{
                        sortedKeys.add(sortedPartDeck);
                        sortedPartDeck = new ArrayList<Integer>();
                        sortedPartDeck.add(suite.get(i));
                        lastKey = suite.get(i);
                    }
                }
                sortedKeys.add(sortedPartDeck);
            }

        }
        // end order by same suite , serial keys
        for(ArrayList<Integer> sortedList:sortedKeys){
            System.out.print(sortedList);
        }

        return sortedKeys;
    }
    public static Integer calculateDoublePoints(ArrayList<ArrayList<Integer>> sortedDoubleDeck,boolean hasJoker){
        //çifte gidilmiş el puanını hesapla
        int totalSerialPoint = 0 ;

        for(ArrayList<Integer> partDeck : sortedDoubleDeck){
            if(partDeck.size() <=1){
                totalSerialPoint += partDeck.size();
            }
        }
        // joker taşı herhangi bir tekliyi çiftli yapabileceği için 1 boş taş puanı silinir
        if (hasJoker){
            totalSerialPoint -= 1;
        }
        return totalSerialPoint;
    }

    public static void main(String[] args) {


        ArrayList<Integer> allKeys= new ArrayList<Integer>();
        for(int i=0;i<106;i++){
            allKeys.add(i%53);
        }
        Collections.shuffle(allKeys);
        Random rand = new Random();
        int selectedIndex = rand.nextInt(106);
        Integer selectedKey = allKeys.get(selectedIndex) + 1;
        System.out.println(selectedKey);
        ArrayList<ArrayList<Integer>> decks = new ArrayList<ArrayList<Integer>>();
        int selectedDeck = rand.nextInt(4);
        System.out.println(selectedDeck);
        int lastKey = 0;
        // birinde 15 kalanlarında 15 tane olacak şekilde destelere taşları dağıt
        for(int i=0;i<4;i++){
            ArrayList<Integer> deck = new ArrayList<Integer>();
            if(i == selectedDeck) {
                deck.addAll(allKeys.subList(lastKey, lastKey + 15));
                lastKey=lastKey+15;
            }
            else{
                deck.addAll(allKeys.subList(lastKey, lastKey + 14));
                lastKey=lastKey+14;
            }

            decks.add(deck);
        }

        ArrayList<Integer> totalSerialPoints = new ArrayList<Integer>();
        ArrayList<Integer> totalDoublePoints = new ArrayList<Integer>();
        for(int i=0;i<decks.size();i++){
            // düzenleme işlemlerinde kullanılan algoritmanın çalışması için destelerin sıralı olması gerekiyor
            Collections.sort(decks.get(i));

            ArrayList<ArrayList<Integer>> sortedBySuites = sortBySuites(decks.get(i),selectedKey);
            System.out.println("****** renklere göre dizilmiş hali");
            ArrayList<ArrayList<Integer>> sortedBySerial=sortBySerialMatches(sortedBySuites);
            System.out.println("****** sıralı aynı renke göre dizilmiş hali");
            ArrayList<ArrayList<Integer>> sortedByValue=sortByValueMatches(sortedBySerial);
            System.out.println("****** hem sıralı hem aynı renke göre dizilmiş hali");
            int serialPoint = calculateSerialPoints(sortedByValue,decks.get(i).contains(selectedKey));
            System.out.println("****** ıstaka :"+i+"  seri puan : "+serialPoint);
            totalSerialPoints.add(serialPoint);
            ArrayList<ArrayList<Integer>> sortedByDoubles=sortByDoubleMatches(sortedBySuites);
            System.out.println("****** çifte göre dizilmiş hali");
            int doublePoint = calculateDoublePoints(sortedByDoubles,decks.get(i).contains(selectedKey));
            System.out.println("****** ıstaka :"+i+"  çift puan : "+doublePoint);
            totalDoublePoints.add(serialPoint);


        }
        ArrayList<Integer> totalFinalPoints = new ArrayList<Integer>();
        for(int i=0;i<totalSerialPoints.size();i++){

            if(totalSerialPoints.get(i)>=totalDoublePoints.get(i)){
                totalFinalPoints.add( totalDoublePoints.get(i));
            }
            else{
                totalFinalPoints.add( totalSerialPoints.get(i));
            }
        }
        System.out.println(totalFinalPoints);
        int finalPoint = Collections.min(totalFinalPoints);
        int finalDeck = totalFinalPoints.indexOf(finalPoint);
        System.out.println("*****Kazanan ıstaka :" + decks.get(finalDeck)) ;




    }
}
