
import java.util.LinkedList;
import java.util.Scanner;
/**
 *  Name:
 *  Class Group:
 */
public class CA3_Question6
{

    /*
    Will repeatedly ask the user to enter the commands in the format
    buy qty price
    or
    sell qty price
    sell qty price
    sell qty price
    or
    quit
     */

    static LinkedList<Block> blocks = new LinkedList<>();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String command="";
            do {
            System.out.print(">");
            command = in.next();
            if(command.equalsIgnoreCase("buy"))
            {
                int qty = in.nextInt();
                double price = in.nextDouble();
                blocks.add(new Block(qty, price));
            }
            else if(command.equals("sell"))
            {
                int qty = in.nextInt();
                double price = in.nextDouble();


            }
        }while(!command.equalsIgnoreCase("quit"));
    }

    public static void calculateTotalGain(Block sale) {
        double total = 0;

        while(sale.qty > 0 && !blocks.isEmpty()) {
            if (sale.qty > blocks.peek().qty) { // The qty of sale is greater than amount of blocks
                total += calculateBlockProfit(blocks.poll()); // Calculate profit and remove block
            } else {
                total += calculateBlockProfit(blocks.peek()); // Calculate profit and keep block
                blocks.peek().qty -= sale.qty; // 
            }
        }
    }

    public static double calculateBlockProfit(Block stock) {
        // First calculate total worth of block, then multiply by 1.25 for it's profit when selling
        double worth = stock.qty * stock.price;

        return worth * 1.25;
    }
}

class Block {
    public int qty;
    public double price;

    Block(int qty, double price) {
        this.qty = qty;
        this.price = price;
    }
}