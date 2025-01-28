package strategy;

import java.util.List;

// Buy on second low after opening price and sell if day price crosses the first opening price
public class OpeningPriceWithTwoReversalStrategy implements Strategy {

    private List<Integer> priceAction;

    @Override
    public int getBuyDay(int day) {
        int firstLow=0;
        for(int i=1 ; i< priceAction.size() ; i++){
            if(priceAction.get(i) < priceAction.get(i-1) && firstLow == 0){
                firstLow = priceAction.get(i);
            }
            if(priceAction.get(i) < firstLow){
                System.out.println("Bought silver on day:" + i + " price:" + priceAction.get(i));
                return i;
            }
        }
        return 0;
    }

    @Override
    public int getSellDay(int day){
        if(priceAction.get(0) < priceAction.get(day)){
            System.out.println("Sold silver with profit on day:" + day + " price:" + priceAction.get(day));
            return day;
        }
        return 0;
    }

    public void setPriceAction(List<Integer> priceAction) {
        this.priceAction = priceAction;
    }
}
