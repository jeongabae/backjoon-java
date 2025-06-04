import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] cur = br.readLine().split(":");
        String[] target = br.readLine().split(":");

        int curH = Integer.parseInt(cur[0]);
        int curM = Integer.parseInt(cur[1]);
        int curS = Integer.parseInt(cur[2]);

        int targetH = Integer.parseInt(target[0]);
        int targetM = Integer.parseInt(target[1]);
        int targetS = Integer.parseInt(target[2]);

        int curSecondAmount = curH * 3600 + curM * 60 + curS;
        int targetSecondAmount = targetH * 3600 + targetM * 60 + targetS;

        int NeedSecondAmount = targetSecondAmount - curSecondAmount;
        if (NeedSecondAmount <= 0) NeedSecondAmount += 24 * 3600;

        int needH = NeedSecondAmount / 3600;
        int needM = (NeedSecondAmount % 3600) / 60;
        int needS = NeedSecondAmount % 60;

        System.out.println(String.format("%02d:%02d:%02d", needH, needM, needS));
	}
	
}