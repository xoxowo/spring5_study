package chap07;

public class ImpeCalculator implements Calculator {

	@Override // 인터페이스 구현 
	public long factorial(long num) {
		long result = 1;
		for (long i = 1; i <= num; i++) {
			result *= i;
		}
		return result;
	}

}