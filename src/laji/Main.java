	import java.util.Scanner;
	
	public class Main {
	
		public static void main(String[] args) {
			int ba = 0;
			int ma = 0;
			double qwe = 0;
			Scanner input = new Scanner(System.in);
			int asd = input.nextInt();
			input.close();
			if (asd == 0)
				System.out.print("ling");
			if (asd < 0)
			{
				System.out.print("fu ");
				asd = (-1)*asd;
			}
			ma = asd;
			while(ma > 0)
			{
				ma /= 10;
				ba++;
			}
			while(ba > 0)
			{
				qwe = Math.pow(10, --ba);
				int n;
				n = asd / (int)qwe;
				asd = asd % (int)(qwe);
				
				switch (n) {
				case 0:
					System.out.print("ling");
					break;
				case 1:
					System.out.print("yi");
					break;
				case 2:
					System.out.print("er");
					break;
				case 3:
					System.out.print("san");
					break;
				case 4:
					System.out.print("si");
					break;
				case 5:
					System.out.print("wu");
					break;
				case 6:
					System.out.print("liu");
					break;
				case 7:
					System.out.print("qi");
					break;
				case 8:
					System.out.print("ba");
					break;
				case 9:
					System.out.print("jiu");
					break;
				}
				if (ba > 0)
					System.out.print(" ");
			}
		}
	
	}
