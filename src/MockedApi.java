import java.util.ArrayList;

public class MockedApi {
    ArrayList<Integer> priceAction = new ArrayList<Integer>() {
        {
            add(9);
            add(6);
            add(5);
            add(6);
            add(10);
            add(5);
            add(2);
            add(8);
            add(10);
        }
    };

    public int getNumberOfDays(){
        return priceAction.size();
    }
    public int getPriceOnDay(int day){
        return priceAction.get(day);
    }
}
