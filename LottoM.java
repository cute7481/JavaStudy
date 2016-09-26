import java.io.*;
import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//import java.util.TreeSet;

class LottoM {
	int count;

	String path = "list.txt";
	BufferedReader brKey = new BufferedReader(new InputStreamReader(System.in));
	FileReader fr;
	BufferedReader brFile;

	ArrayList list = new ArrayList();
	ArrayList nList = new ArrayList();
	ArrayList<Integer> wList = new ArrayList<Integer>();

    LottoM() {
		isExist(path); //(1) ���� ��ġ �Է� 
		checkTotal(); //(2) �� �л� �� 
		getList();
		findWinner2();
	}

	void pln(String str){
		System.out.println(str);
	}

	void isExist(String path) {
		int count = 0;
		try {
			fr = new FileReader(path);
		} catch(IOException ie){
			pln("����. �ٽ� �Է����ּ���.");
			isExist(path);
		}
	}

	int checkTotal() {
        brFile = new BufferedReader(fr);
		String line = "";
		try {
			while((line = brFile.readLine()) != null){
				count++;
			}
		} catch(IOException ie) {}
		return count;
	}

	int getRandom(int count) {
		this.count = count;

		double d = Math.random();
		int j = (int)(d * count);
		j = j+1;

		return j;
	}

	//���� ����, ���� ���� �ʿ�.
	int getNumber() {
		String winner = "";
		int nWinner = 0;

		System.out.print("��÷ �ο� : ");
		try {
			winner = brKey.readLine();
			nWinner = Integer.parseInt(winner);
			if(nWinner<=0 || nWinner>count) {
				pln("�ο��� 1~" + count + " ���̷� �Է����ּ���");
				getNumber();
			}
		} catch(IOException ie) {
			pln("�Է� ����. �ٽ� �Է����ּ���");
			getNumber();
		} catch(NumberFormatException ne) {
			pln("�ο��� �Է����ּ���");
			getNumber();
		}

		return nWinner;
	}

	void getList() {
		try{
			int size = checkTotal();
			brFile = new BufferedReader(new FileReader(path));
			String line = "";

			pln("--------------------����Ʈ ���");
			for(int i=0; i<count; i++){
                line = brFile.readLine();
				list.add(line);

				//���� ���� �Ѹ� ���
				/*
				int rnum = getRandom(count);
				while(nList.contains(rnum)){
					rnum = getRandom(count);
				}
				nList.add(rnum);
				pln(line + "     "+ rnum);
				*/

				pln((i+1) + " : " + line);
			}

			pln("�� : " + count + "��");
		} catch (IOException ie) { }
	}

	//���� ���� �Ѹ� ���
	/*
	void findWinner1() {
		String winner = "";
		//int index = 0;
		int rnum = getRandom(count);

		if(nList.contains(rnum)) {
			int index = nList.indexOf(num);
			winner = list[index].toString();
		}
		pln("��÷�� : " + winner);
	}
	*/

	void findWinner2() {
		pln("-------------------------------");
		pln("���ϴ� ��÷ �ο��� �Է����ּ���");
		int nWinner = getNumber();
		countDown();

		for(int i=0; i<nWinner; i++) {
			int rnum = getRandom(count);
			if(!wList.contains(rnum)){
				wList.add(rnum);
			} else i--;
		}
		
		pln("      ¦¦¦! �����մϴ�!      ");
		pln("-------------------------------");

		/*
		for (Integer number : wList) {
			int index = number.intValue();
			pln("Number = " + list.indexOf(index));
		}*/

		
		for(int i=0; i<nWinner; i++) {
			//Integer temp = new Integer();
			//temp = wList.get(i);
			int index = wList.get(i).intValue();
			//index -= 1;
            pln("��÷�� " + (i+1) + " : " + list.get(index-1));
        }
		pln("-------------------------------");
	}

	void countDown() {
		pln("-------------------------------");
		pln("     ��÷ COUNT DOWN START     ");
		pln("-------------------------------");
		pln("               3");
		try {
			Thread.sleep(1000);
			pln("               2");
			Thread.sleep(1000);
			pln("               1");
			Thread.sleep(1000);
		} catch (InterruptedException e) { }
		pln("-------------------------------");
	}

	public static void main(String[] args) {
		new LottoM();
	}
}
