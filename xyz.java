import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class xyz {

    static class record {
        int stockNumber;
        double price;
        boolean done = false;
//        boolean promo = false;

        public record() {
            this.stockNumber = 0;
            this.price = 0;
        }

        public record(int stockNum, double salePrice) {
            this.stockNumber = stockNum;
            this.price = salePrice;
//            this.promo = promo;
        }

        public void set(int num){
            this.stockNumber = num;
        }

        public void setDone(){
            this.done = true;
        }



//        public double checkPromo(double discount) {
//            double newPrice = 0;
//            if(promo = true){
//                 newPrice = discount * price;
//            }
//
//            return  newPrice;
//        }
    }

    static class sell {
        int stcokSellNum;
        double rate = 1.0;
        boolean promo = false;
        boolean done = false;

        public sell(int num, boolean promo, double rate){
            this.stcokSellNum = num;
            this.promo = promo;
            this.rate = rate;
        }

        public void set(int num){
            this.stcokSellNum = num;
        }

        public void setDone(){
            this.done = true;
        }

    }

    public static void main(String[] args) throws IOException {

        List<record> recordList = new ArrayList<>();
        List<sell> sellList = new ArrayList<>();

        int inStock;
        int sellNum;
        String priceTag = "";
        String discount = "";
        String priceInDouble = "";

        FileReader fr;
        fr = new FileReader("src/data");

        Scanner sc = new Scanner(fr);

        while(sc.hasNext()) {
            String chr = sc.next();
//            System.out.println(chr);

            if (chr.equals("R")) {
                inStock = sc.nextInt();
                priceTag = sc.next();
                //System.out.println(inStock + " " + priceTag);

                //convert $ price into double
                //System.out.println("Price Convert: ");
                for (int i = 0; i < priceTag.length(); i++) {
                    if (i != 0) {
                        priceInDouble += priceTag.charAt(i);
                    }
                }

                //System.out.println("New Price: " + priceInDouble);

                double price = Double.parseDouble(priceInDouble);

                record r = new record(inStock, price);
                recordList.add(r);

                //reset $Price for next price input
                priceInDouble = "";


            } else if (chr.equals("S")) {
                sellNum = sc.nextInt();
                //System.out.println("Sell " + sellNum);
                sc.nextLine();

                sell s = new sell(sellNum, false, 1.0);
                sellList.add(s);

            } else if (chr.equals("P")) {

                discount = sc.next();
                //System.out.println(discount);
                sc.nextLine();
                String rate = "";
                for (int i = 0; i < 2; i++) {
                    rate += discount.charAt(i);
                }
                //System.out.println("Discount: " + rate);

                for (int i = 0; i < 2; i++) {
                    chr = sc.next();
                    sellNum = sc.nextInt();
                    //System.out.println("Sell " + sellNum);
                    sc.nextLine();
                    double rateInDouble = Double.parseDouble(rate);
                    sell s = new sell(sellNum, true, rateInDouble * 0.01);
                    sellList.add(s);
                }
            }

            //System.out.println();
        }

//        //Done with all data read in.
//        //Print out both list
//        for(record r : recordList){
//            System.out.print(r.price + " ");
//            System.out.println(r.stockNumber);
//        }
//
//        for(sell s : sellList){
//            System.out.print(s.stcokSellNum + " ");
//            System.out.print(s.promo + " ");
//            System.out.println(s.rate);
//        }
//
        //Calculation Part of R and S with P

        //Loop through Sell list
        for(sell s : sellList){

            //Loop through record list using current sell object
            for(record r : recordList) {

                if (r.done != true && s.done != true) {
                    //if sell object sellNum is larger than record list in stock num
                    //Means this record object will be empty out by sell object

                    System.out.println("S: " + s.stcokSellNum);
                    System.out.println("R: " + r.stockNumber);

                    if (s.stcokSellNum > r.stockNumber) {

                        //left over in sell object for next record
                        int leftover = s.stcokSellNum - r.stockNumber;
                        System.out.println("Leftover: " + leftover);
                        System.out.println("Remainder of " + r.stockNumber + " Widgets not available");

                        //put leftover num as new sell num in the object
                        s.set(leftover);

                        //calculate price - first check if promo or not
                        if (s.promo) {
                            //With promo
                            double price = r.stockNumber * r.price * s.rate;
                            System.out.println("Price: " + price);
                        } else {
                            //without promo
                            double price = r.stockNumber * r.price;
                            System.out.println("Price: " + price);
                        }

                        //When done with this record object, delete it
                        r.setDone();
                    }
                    //if sell object less than record
                    else {
                        //left over for record stock number
                        int leftover = r.stockNumber - s.stcokSellNum;

                        //Check promo
                        if (s.promo) {
                            //With promo
                            double price = s.stcokSellNum * r.price * s.rate ;
                            System.out.println("Price: " + price);
                        } else {
                            //without promo
                            double price = s.stcokSellNum * r.price;
                            System.out.println("Price: " + price);
                        }
                        r.set(leftover);
                        s.setDone();
                    }

                }
            }
        }

    }
}
