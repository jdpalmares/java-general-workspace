import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CashRegister {
    //Use an Arraylist to track the contents of the Cash register
    //Simpler to iterate and can dynamically assign new variables if needed
    //Could add anywhere on the index so can add larger or smaller denominations
    private ArrayList<Integer> CashRegContent = new ArrayList<>();
    //made a class variable so greedy algo and dynamic programming for 
    //retrieving the change can be put in separate methods
    private int[] takenBills;

    private static final int STARTING_DENOMINATOR = 20;

    //Initialize Cash Register values
    private CashRegister() {
        System.out.println("Initialize Cash Register.");
        CashRegContent.add(0); //For TWENTIES
        CashRegContent.add(0); //For TENS
        CashRegContent.add(0); //For FIVES
        CashRegContent.add(0); //For TWOS
        CashRegContent.add(0); //For ONES
        System.out.print("Cash Register current state: ");
        showCashRegState();
    }

    //created separate method to go to the next denomination for maintainability purposes
    private int goToNextDenom (int currentDenomination){
        //divide the denomination by 2 (2.5 will become 2 due to int)
        //this will make iteration easier and short from 20, 10, 5, 2, and 1 
        //must be changed if there are other denominations which does not follow the pattern
        //or if the denominations are sequentially halved
        return currentDenomination /= 2; 
    }

    //Display Cash Register status
    private void showCashRegState() {
        //Use string builder so total can be calculated and displayed first
        //while putting each individual value after 
        StringBuilder cashRegDisp = new StringBuilder();
        int cashRegTotal = 0;
        int cashRegCurDenom = STARTING_DENOMINATOR; 
        for (Integer value : CashRegContent) {
            cashRegDisp.append(value + " ");
            cashRegTotal += (value*cashRegCurDenom);
            cashRegCurDenom = goToNextDenom(cashRegCurDenom);
        }
        cashRegDisp.insert(0, "$"+cashRegTotal+" ");
        System.out.println(cashRegDisp.toString());
    }

    //put denonimation bills in cash register
    private void putCRBills(String[] cashRegCmd) {
        //do not update if there is more than 5 numbers entered
        if(cashRegCmd.length != 6 ){
            System.out.println("Parameters are incorrect.");
            System.out.println("After the 'put' command, input 5 separate denominations in order of");
            System.out.println("#$20 #$10 #$5 #$2 #$1 (Put 0 for empty denominations)");
        } else {
            //start from 1 since index 0 is the commmand
            for (int i = 1; i < 6; i++){
                try{
                    int newVal = CashRegContent.get(i-1) + Integer.parseInt(cashRegCmd[i]);
                    CashRegContent.set(i-1, newVal);
                } catch (NumberFormatException nfe) {
                    //do not update the denomination which does not have an int parameter
                    //but continue the loop to update the whole other parameters
                    System.out.println(cashRegCmd[i] + " is not an integer");
                    System.out.println("Denomination index " + i + " will not be updated");
                    continue;
                }
            }
        }
        showCashRegState();
    }

    //take denonimation bills in cash register
    private void takeCRBills(String[] cashRegCmd) {
        if(cashRegCmd.length != 6 ){
            System.out.println("Parameters are incorrect.");
            System.out.println("After the 'take' command, input 5 separate denominations in order of");
            System.out.println("#$20 #$10 #$5 #$2 #$1 (Put 0 for empty denominations)");
        } else {
             //start from 1 since index 0 is the commmand
             for (int i = 1; i < 6; i++){
                try{
                    int takeVal = Integer.parseInt(cashRegCmd[i]);
                    int curVal = CashRegContent.get(i-1);
                    if(takeVal > curVal){
                        System.out.println("Cannot take the bill since amount taken is greater than current amount");
                        System.out.println("Denomination index " + i + " will not be updated");
                    } else {
                        int newVal = curVal - takeVal;
                        CashRegContent.set(i-1, newVal);
                    }
                } catch (NumberFormatException nfe) {
                    //do not update the denomination which does not have an int parameter
                    //but continue the loop to update the whole other parameters
                    System.out.println(cashRegCmd[i] + " is not an integer");
                    System.out.println("Denomination index " + i + " will not be updated");
                    continue;
                }
            }
        }
        showCashRegState();
    }

    private int getChangeByGreedyAlgo(int tempChange){
        int cashRegCurDenom = STARTING_DENOMINATOR;
        int cashRegPlaceCtr = 0;
        for (Integer value : CashRegContent) {
            if(tempChange == 0){ //if the change is already 0
                break;
            } else if (value == 0) { //if the current denomination value is 0
                cashRegPlaceCtr++;
                cashRegCurDenom = goToNextDenom(cashRegCurDenom);
                continue;
            } else {
                int subTotalDenom = value * cashRegCurDenom;
                //subtract subtotal if denom and instances are less than the change
                if(subTotalDenom <= tempChange){ 
                    tempChange -= subTotalDenom;
                    takenBills[cashRegPlaceCtr] = value;
                    cashRegPlaceCtr++;
                    cashRegCurDenom = goToNextDenom(cashRegCurDenom);
                } else { //optimize on how many instances of the denom will be taken
                    int denomValsForchange = tempChange / cashRegCurDenom;
                    tempChange -= denomValsForchange * cashRegCurDenom;
                    takenBills[cashRegPlaceCtr] = denomValsForchange;
                    cashRegPlaceCtr++;
                    cashRegCurDenom = goToNextDenom(cashRegCurDenom);
                }
            }
        }
        return tempChange;
    }

    private int[][] getChangeByDynaProg(int[][] coinsUsed, int denomLen, int changeValue, int[] tempDenoms){
        Integer[] denomValCpy = new Integer[denomLen]; //reverse order from lowest denom to highest
        
        denomValCpy = Arrays.copyOf(CashRegContent.toArray(), denomLen, Integer[].class);
        int tempCtr=denomLen-1;
        for (Integer value : CashRegContent) {
            denomValCpy[tempCtr] = value;
            tempCtr--;
        }
        for(int i=0; i < denomLen; i++){
            for(int j=0; j <= changeValue; j++){
                if(j==0){
                    coinsUsed[i][j] = 0;
                } else if(i == 0) {
                    if(denomValCpy[i] == 0){
                        coinsUsed[i][j] = 0; //0 since change cannot be made
                    }else {
                        int denomSubTotal = tempDenoms[i]*denomValCpy[i];
                        if(denomSubTotal == j){
                            coinsUsed[i][j] = denomValCpy[i];
                        } else if (denomSubTotal > j) {
                            if(j%tempDenoms[i] == 0){
                                coinsUsed[i][j] = j/tempDenoms[i];
                            }else {
                                //in case change cannot be made exactly on the smallest denomination
                                coinsUsed[i][j] = 0; //0 since change cannot be made
                            }
                        } else {
                            coinsUsed[i][j] = 0; //0 since change cannot be made
                        }
                    }
                } else {
                    if(tempDenoms[i] > j){
                        coinsUsed[i][j] = coinsUsed[i-1][j];
                    } else {
                        if(denomValCpy[i] == 0){
                            coinsUsed[i][j] = coinsUsed[i-1][j];
                        }else {
                            if(coinsUsed[i-1][j] > 0){
                                if(coinsUsed[i-1][j] <= (1 + coinsUsed[i][j-tempDenoms[i]])){
                                    coinsUsed[i][j] = coinsUsed[i-1][j];
                                } else {
                                    int denomSubTotal = tempDenoms[i]*denomValCpy[i];
                                    if(denomSubTotal >= j || (coinsUsed[i-1][j-tempDenoms[i]] != 0)){
                                        coinsUsed[i][j] = 1 + coinsUsed[i][j-tempDenoms[i]];
                                        int tempSubTotal = coinsUsed[i][j] * tempDenoms[i];
                                        if(tempSubTotal < j && coinsUsed[i-1][j] > 0){
                                            coinsUsed[i][j] = coinsUsed[i-1][j];
                                        }
                                    } else {
                                        coinsUsed[i][j] = 0;
                                    }
                                }
                            } else {
                                int denomSubTotal = tempDenoms[i]*denomValCpy[i];
                                if(denomSubTotal >= j || (coinsUsed[i-1][j-tempDenoms[i]] != 0)){
                                    coinsUsed[i][j] = 1 + coinsUsed[i][j-tempDenoms[i]];
                                    int tempSubTotal = coinsUsed[i][j] * tempDenoms[i];
                                    if(tempSubTotal < j && coinsUsed[i-1][j] > 0){
                                        coinsUsed[i][j] = coinsUsed[i-1][j];
                                    }
                                } else {
                                    coinsUsed[i][j] = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
        return coinsUsed;
    }

    //take bills from register based on the amount specified (Give change)
    private void giveCRChange(String[] cashRegCmd) {
        if(cashRegCmd.length != 2 ){
            System.out.println("Parameters are incorrect.");
            System.out.println("After the 'change' command, input 1 value to get the change of");
        } else {
            try{
                int changeValue = Integer.parseInt(cashRegCmd[1]);
                if( changeValue < 1 ){
                    System.out.println("Change value is less than 1. It should be at least 1. Invalid parameter");
                    System.out.println("sorry");
                } else{
                    //Initially solve the problem using greedy algorithm for possible optimization
                    //Greedy Algo isn't always accurate but can deliver optimized and fast results
                    takenBills = new int[]{0, 0, 0, 0, 0};
                    int tempChange = changeValue;
                    tempChange = getChangeByGreedyAlgo(tempChange);
                    if(tempChange > 0) { // If greedy algo does not work, back track first using dynamic programming
                        int denomLen = CashRegContent.size();
                        int[][] coinsUsed = new int[denomLen][changeValue+1];
                        int[] tempDenoms = {1, 2, 5, 10, 20};
                        coinsUsed = getChangeByDynaProg(coinsUsed, denomLen, changeValue, tempDenoms);
                        if (coinsUsed[denomLen-1][changeValue] == 0){
                            System.out.println("There are insufficient funds or no exact change can be made for this amount");
                            System.out.println("Cash register will not be updated and taking the change will be cancelled");
                            System.out.println("sorry");
                        } else {
                            takenBills = new int[]{0, 0, 0, 0, 0};
                            int tempChangeCtr = changeValue;
                            for(int i = denomLen-1; i > 0; i--){
                                if(coinsUsed[i][tempChangeCtr] != coinsUsed[i-1][tempChangeCtr]){
                                    takenBills[4-i]++;
                                    tempChangeCtr = tempChangeCtr - tempDenoms[i];
                                    while(coinsUsed[i][tempChangeCtr] != coinsUsed[i-1][tempChangeCtr]){
                                        takenBills[4-i]++;
                                        tempChangeCtr = tempChangeCtr - tempDenoms[i];
                                    }
                                }
                            }
                            for(int i=0; i < takenBills.length; i++){
                                int curVal = CashRegContent.get(i);
                                int takeVal = takenBills[i];
                                CashRegContent.set(i, curVal - takeVal);
                                System.out.print(takenBills[i] + " ");
                            }
                            System.out.println();
                        }
                    } else {
                        for(int i=0; i < takenBills.length; i++){
                            int curVal = CashRegContent.get(i);
                            int takeVal = takenBills[i];
                            CashRegContent.set(i, curVal - takeVal);
                            System.out.print(takenBills[i] + " ");
                        }
                        System.out.println();
                    }
                }
                
            } catch (NumberFormatException nfe) {
                //will not update the cash register if value is not an integer
                System.out.println("Parameter is incorrect.");
                System.out.println(cashRegCmd[1] + " is not an integer");
            }
        }
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        CashRegister crDeploy = new CashRegister();
        System.out.println("Cash Register Ready, input a command below: ");
        String[] cashRegCmd = scanner.nextLine().split("\\s+");

        //So command will be case insensitive
        String command = cashRegCmd[0].toLowerCase();
        while(!command.equals("quit")){
            if(command.equals("show")){
                crDeploy.showCashRegState();
            } else if(command.equals("put")){
                crDeploy.putCRBills(cashRegCmd);
            } else if(command.equals("take")){
                crDeploy.takeCRBills(cashRegCmd);
            } else if(command.equals("change")){
                crDeploy.giveCRChange(cashRegCmd);
            } else if(command.equals("help")){
                System.out.println("'show' - show current state, including total and each denomination: $Total #$20 #$10 #$5 #$2 #$1");
                System.out.println();
                System.out.println("'put' - put bills in each denomination: #$20 #$10 #$5 #$2 #$1 and show current state");
                System.out.println();
                System.out.println("'take' - take bills in each denomination: #$20 #$10 #$5 #$2 #$1 and show current state");
                System.out.println();
                System.out.println("'change' - show requested change in each denomination: #$20 #$10 #$5 #$2 #$1 and remove money from cash register");
                System.out.println();
            } else {
                System.out.println("Invalid Command or structure. Please try again");
                System.out.println("To see the list of available commmands, type 'help' only.");
                System.out.println();
            }

            System.out.println("Cash Register Ready again, input a command below: ");
            cashRegCmd = scanner.nextLine().split(" ");
            command = cashRegCmd[0].toLowerCase();
        }

        System.out.println("Cash Register Terminated, Bye.");
        scanner.close();
    }
}