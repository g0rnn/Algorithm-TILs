import java.util.*;
import java.io.*;

public class Main {
	static int n, k;
	
	static class ConveyorBelt{
		Square[] belt;
		int missing = 0;
		
		public ConveyorBelt(int n) {
			belt = new Square[2*n];
		}
		
		boolean isCarry() {
			return belt[0].hp > 0 && !belt[0].robot;
		}
		
		void put() {
			if (isCarry()) {
				belt[0].robot = true;
				belt[0].hp--;
				if (belt[0].hp == 0) missing++;
			}
		}
		
		void lay() {
			belt[n-1].robot = false;
		}
		
		void moveRobots() {
			for (int i = n-1; i > 0; i--) {
				if (belt[i].robot || belt[i].hp <= 0 || !belt[i-1].robot) continue;
				
				belt[i-1].robot = false;
				belt[i].robot = true;
				belt[i].hp--;
				if (belt[i].hp == 0) missing++;
			}
			lay();
		}
		
		void moveBelt() {
			Square tmp = belt[2*n-1];
			for (int i = 2*n-1; i > 0; i--) belt[i] = belt[i-1];
			belt[0] = tmp;
			lay();
		}
		
		int getMissingSquareCount() {
			return missing;
		}
		
		public String toString() {return "ConveyorBelt={missing="+missing+", "+Arrays.toString(belt);}
	}
	
	static class Square {
		int hp;
		boolean robot = false;
		public Square(int h) {hp=h;}
		public String toString() {return "square={robot="+robot+", hp="+hp+"}";}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		ConveyorBelt belt = new ConveyorBelt(n);
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2*n; i++) {
			int a = Integer.parseInt(st.nextToken());
			belt.belt[i] = new Square(a);
			if (a == 0) belt.missing++;
		}
		
		int round = 0;
		while (belt.missing < k) {
			round++;
			belt.lay();
			belt.moveBelt();
			belt.moveRobots();
			belt.put();
//			System.out.println(belt.toString());
		}
		System.out.println(round);
	}
}
