package Sample;



public class Moneycount {
	int totalMoney;
	int price;
	int count;
	
	public String moneyCalculate(int count) {
		price= 1500;
		count=10;
		this.price=price;
		this.count=count;
		
		totalMoney=price*count;
		
		return(totalMoney+"¿ø");
	}
	
	
	
	
	
	
}
