import strategy.OpeningPriceWithTwoReversalStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * The idea behind is to create some strategy to buy or sell silver and then pass that to execution.
     *
     */
    public static void main(String[] args) {

        OpeningPriceWithTwoReversalStrategy openingPriceWithTwoReversalStrategy = new OpeningPriceWithTwoReversalStrategy();

        MockedApi mockedApi = new MockedApi();
        int buyDay = 0;
        List<Integer> priceOnDayList = new ArrayList<>();

        // adding opening price to list, it is because nothing will be bought/sold on opening day
        priceOnDayList.add(mockedApi.getPriceOnDay(0));

        // getting the buy day based on strategy
        buyDay = setBuyCall(mockedApi, priceOnDayList, openingPriceWithTwoReversalStrategy, buyDay);

        // getting the sell day based on strategy
        setSellCall(buyDay, mockedApi, priceOnDayList, openingPriceWithTwoReversalStrategy);

    }

    private static void setSellCall(int buyDay, MockedApi mockedApi, List<Integer> priceOnDayList, OpeningPriceWithTwoReversalStrategy openingPriceWithTwoReversalStrategy) {
        int sellDay;
        for(int i = buyDay; i < mockedApi.getNumberOfDays(); i++){

            priceOnDayList.add(mockedApi.getPriceOnDay(i));
            openingPriceWithTwoReversalStrategy.setPriceAction(priceOnDayList);

            sellDay = openingPriceWithTwoReversalStrategy.getSellDay(i);

            // if sell day is not 0, then it means sell strategy is met
            if(sellDay != 0){
                break;
            }

            // If strategy didn't satisfy sell call,then sell on the last day.
            if(i == mockedApi.getNumberOfDays() - 1){
                System.out.println("Sold silver with loss on last day:" + i + " price:" + mockedApi.getPriceOnDay(i));
            }
        }
    }

    private static int setBuyCall(MockedApi mockedApi, List<Integer> priceOnDayList, OpeningPriceWithTwoReversalStrategy openingPriceWithTwoReversalStrategy, int buyDay) {
        for(int i = 1; i < mockedApi.getNumberOfDays(); i++){
            priceOnDayList.add(mockedApi.getPriceOnDay(i));
            openingPriceWithTwoReversalStrategy.setPriceAction(priceOnDayList);
            buyDay = openingPriceWithTwoReversalStrategy.getBuyDay(i);
            if(buyDay != 0){
               break;
            }
        }
        return buyDay;
    }

}