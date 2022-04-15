package Sample;

import java.util.Random;

public class RandomPick {
	
	int totalAmount=JavaFrame_sub.totalAmount;
	
	public int RandomPick() {
Random ran =new Random();
int n=ran.nextInt(totalAmount-1)+1;

System.out.println(n);

	return n;
	
	}
}
