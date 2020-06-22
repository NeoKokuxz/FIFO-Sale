import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

class LinkedListXyz {
    public static void main(String[] args) throws IOException {
        LinkedList<Record> records = new LinkedList<>();
        LinkedList<Sell> sells = new LinkedList<>();

        FileReader fr = new FileReader("src/data");

        Scanner sc = new Scanner(fr);

        int inStock;
        int sellNum;
        String priceTag = "";

        while(sc.hasNext()){
            String symbol = sc.next();

            //Promo Sell
            if(symbol.equals("P")){

                //Read in discount rate in %
                String percentage = sc.next();
                String percentageString = "";
                sc.nextLine();

                //Convert % into real number
                for(int i = 0; i < 2; i++){
                    percentageString += percentage.charAt(i);
                }

                //Parse percentage string into rate, ex 30% into 0.3
                double rate = Double.parseDouble(percentageString) * 0.01;

                //Next 2 sales with discount
                for(int i = 0; i < 2 ; i++){
                    //Read in sell info
                    symbol = sc.next();
                    sellNum = sc.nextInt();
                    sc.nextLine();
                    Sell sell = new Sell(sellNum, true, rate);
                    sells.add(sell);
                }

            }
            //Record
            else if(symbol.equals("R")) {

                String priceInDouble = "";

                //Read in stock number and it's unit price
                inStock = sc.nextInt();
                priceTag = sc.next();

                //Parse $ in the string
                for(int i = 0; i < priceTag.length(); i++){
                    if(i > 0){
                        priceInDouble += priceTag.charAt(i);
                    }
                }

                //Parse String into double for price
                double price = Double.parseDouble(priceInDouble);

                //Store into record object and added to records linked list
                Record record = new Record(inStock, price);
                records.add(record);
            }
            //Regular Sell
            else {
                sellNum = sc.nextInt();
                sc.nextLine();

                //Store into sell object and added to sells linked list
                Sell sell = new Sell(sellNum, false, 1.0);
                sells.add(sell);
            }
        }

        //loop through linked list
//        for(Record r : records){
//            System.out.println(r.stockNumber);
//        }
//
//        System.out.println();
//
//        for(Sell s : sells){
//            System.out.println(s.stcokSellNum);
//        }

        //30% Mark up in price
        for(Sell s : sells){
            //get first sell number in the first position of the linked list
            int currentSell = s.stcokSellNum ;
            //System.out.println("Current Sell: " + currentSell);

            //Current in stock number
            int currentHold = records.peek().stockNumber;
            //System.out.println("Current hold: " + currentHold);

            double price;



            while(currentSell >= currentHold && records.peek() != null){
                //Means all stock in first position record sold out
                System.out.println(currentHold + " Widgets sold");

                //No promotion on current sell
                if(s.promo != true ){

                    price = currentHold * records.peek().price * 1.3;
                    System.out.println(currentHold + " at " + records.peek().price + " Sales: $ " + String.format( "%.1f" ,price ));
                } else {
                    //With promotion
                    price = currentHold * records.peek().price * 1.3 * (1 - s.rate) ;
                    System.out.println(currentHold + " at " + records.peek().price + " Sales: $ " + String.format( "%.1f" ,price ));
                }

                int leftover = currentSell - currentHold;

                //Sold out first record, pop from linked list.
                records.pop();
                //Update current hold stock
                currentSell = leftover;

                if(!records.isEmpty()) {
                    currentHold = records.peek().stockNumber;

                }

            }

            if(currentSell < currentHold && currentSell != 0) {

                System.out.println(currentSell + " Widgets sold");

                if(s.promo != true){
                    //No promotion
                    price = currentSell * records.peek().price * 1.3;
                    System.out.println(currentSell + " at " + records.peek().price + " Sales: $ " + String.format( "%.1f" ,price ));

                } else {
                    //With promotion
                    price = currentSell * records.peek().price * 1.3 * ( 1 - s.rate);
                    System.out.println(currentSell + " at " + records.peek().price + " Sales: $ " + String.format( "%.1f" ,price ));
                }

                int leftover = currentHold - currentSell;
                //Update remaining stock number on record
                records.peek().set(leftover);
//                System.out.println("Remaining stock after sell " + records.peek().stockNumber);

            }

            if(records.isEmpty()){
                if(currentHold < currentSell){
                    System.out.println("Remainder of " + currentSell + " Widgets not available");
                }
            }
        }


    }
}