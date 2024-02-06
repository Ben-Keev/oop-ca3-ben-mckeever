import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
/**
 *  Name:
 *  Class Group:
 */
public class CA3_Question7
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

    static HashMap<String, LinkedList<Block>> blockMap = new HashMap<>();
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        String command="";
            do {
            System.out.print(">");
            command = in.next();
            if(command.equalsIgnoreCase("buy"))
            {
                LinkedList<Block> blockQueue = new LinkedList<>();
                System.out.print("Enter Company: ");
                String company = in.next();

                System.out.print("Enter Quantity of Purchase: ");
                int qty = in.nextInt();

                System.out.print("Enter Price of Purchase: ");
                double price = in.nextDouble();

                if(!blockMap.containsKey(company)) { // Company not added yet
                    blockQueue.add(new Block(qty, price));
                    blockMap.put(company, blockQueue); // Add company and new block
                } else {
                    blockMap.get(company).add(new Block(qty, price));
                }
            }
            else if(command.equals("sell"))
            {   
                System.out.print("Enter Company: ");
                String company = in.next();
                calculateTotalGain(sellBlock(), company);
            }
        }while(!command.equalsIgnoreCase("quit"));
    }

    public static Block sellBlock() {
        System.out.print("Enter Quantity of sale: ");
        int qty = in.nextInt();
        System.out.print("Enter price of sale: ");
        double price = in.nextDouble();
        return new Block(qty, price);
    }

    public static Block sellBlock(int qty) { // Where quantity is already defined in leftover blocks
        System.out.print("Enter Price of sale (" + qty + " left): ");
        double price = in.nextDouble();
        return new Block(qty, price);
    }

    public static void calculateTotalGain(Block sale, String company) {
        double total = 0; // The total gain at sale of the block
        double original = 0; // The total cost at purchase of the block.
        Block polled;
        int toRemove; // Removing quantity from block and sales.

        if(!blockMap.containsKey(company)) {
            System.out.println("Company " + company + " does not exist.");
            return;
        }

        LinkedList<Block> blocks = blockMap.get(company);

        while(sale.qty > 0 && !blocks.isEmpty()) { 
            if (sale.qty > blocks.peek().qty && blocks.size() > 1) { // The qty of sale is greater than amount of blocks AND there are still more blocks in the queue AND the sale belongs to the right company
                polled = blocks.poll();
                original += calculateBlockWorth(polled);
                total += calculateBlockWorth(polled, sale.price); // Calculate profit at sale price and remove block
                sale.qty -= polled.qty;
                sellBlock(sale.qty); // Prompt another input
            } else {
                original += calculateBlockWorth(blocks.peek());
                total += calculateBlockWorth(blocks.peek(), sale.qty, sale.price); // Calculate profit and keep block
                toRemove = sale.qty;
                sale.qty -= blocks.peek().qty;
                blocks.peek().qty -= toRemove;
            }
        }

        System.out.println("The total gain is: " + (total - original));
    }

    public static double calculateBlockWorth(Block block) { // Calculate worth of block at bought price
        double worth = block.qty * block.price;

        return worth;
    }

    public static double calculateBlockWorth(Block block, double salePrice) { // Assumes every block's quantity will be sold
        // First calculate total worth of block.
        double worth = block.qty * salePrice;

        return worth;
    }

    public static double calculateBlockWorth(Block block, int saleQuantity, double salePrice) { // The quantity may be less or greater than amount available
        double worth = 0;
        
        if(saleQuantity > block.qty) { // Sell all the remaining blocks, excluding those that don't exist
            worth = block.qty * salePrice;
        } else if (block.qty > saleQuantity) { // Sell a portion of the remaining blocks
            worth = block.qty * salePrice;
        }

        return worth;
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