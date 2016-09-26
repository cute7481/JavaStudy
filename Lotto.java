import java.io.*;

class InputString {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	String input() {
		String line = "";

		try {
			line = br.readLine(); //지역(선언초기화), 참조, 변수
		}
		catch (IOException ie) {
			System.out.println("ERROR");
		}

		return line;
	}
}

class RandomNum {
	int m1(int bound) {
		double d = Math.random(); //0.0 <= value < 1.0
		int j = (int)(d * bound); //0 <= value <= 9
		j = j+1; //1 <= value <= 10
		//pln("j :" + j);
		return j;
	}
}

class Lotto extends RandomNum {
	String path = "C:/Users/EE-00/Desktop/work/day3/list.txt";

	static String list[] = new String[15];;
	//static Integer nList[] = new Integer[15];
	static int nList[] = new int[15];

	void pln(String str) {
		System.out.println(str);
	}

	int fromFile(String path) {
		this.path = path;

		BufferedReader br = null;
		RandomNum rn = new RandomNum();
		
		int count = 0;

		try {
			br = new BufferedReader(new FileReader(path));
			String line = null;
			int i = 0;

			while((line = br.readLine()) != null) {
				Lotto.list[i] = line;

				int rnum = rn.m1(count);
				Lotto.nList[i] = rnum;

				pln(line + "     "+ rnum);

				i++;
				count++;
			}
			pln("총 : " + count + "명");
		}
		catch(IOException ie) {

		}
		return count;
	}

	void countDown() {
		pln("COUNT DOWN START");
		pln("3");
		try {
			Thread.sleep(1000);
			pln("2");
			Thread.sleep(1000);
			pln("1");
			Thread.sleep(1000);
		} catch (InterruptedException e) { }
	}

	public static void main(String[] args) {

		Lotto lt = new Lotto();
		int count = 0;

		//String lList[]  = new String[15];;
		//Integer nlList[];  = new Integer[15];

		//lt.lList[] = new String[15];
		//lt.nlList[] = new Integer[15];

		lt.pln("대박 로또 : 상금 100억");

		InputString istr = new InputString();

		lt.pln("명단 루트를 입력해주세요");
		String uPath = istr.input();
		count = lt.fromFile(uPath);

		lt.pln("오른쪽 숫자가 당신의 숫자입니다");
		lt.pln("추첨을 원하시면 S 또는 s 를 눌러주세요");

		String str = "";
		str = istr.input();

		if (str.equalsIgnoreCase("s")) {

			lt.countDown();

			int num = lt.m1(count);
			lt.pln("당첨 번호 : " + num);
			
			int pCount = 0;

			//lt.pln("당첨 번호 : " + Lotto.nList[].length());
			//lt.pln("당첨 번호 : " + Lotto.nList[1].intValue());
			//int get = 0;

			for(int i = 0; i < Lotto.nList.length; i++) {
				//get = nList[i].intValue();
				if(Lotto.nList[i] == num) {
					lt.pln("당첨자 : " + Lotto.list[i]);
					pCount++;
				}
			}
			lt.pln("당첨자 수 : " + pCount);
		}
	}
}
