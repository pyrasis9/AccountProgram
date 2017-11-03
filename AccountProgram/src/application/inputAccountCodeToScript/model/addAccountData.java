package application.inputAccountCodeToScript.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.collections.ObservableList;

public class addAccountData {
	
	public addAccountData() {
		
	}
	
	public ObservableList<Account> addAccountData (ObservableList<Account> masterData) {
		File file = new File("AccountCode.txt");
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String str[]= sc.nextLine().split(",");
				masterData.add(new Account(Integer.valueOf(str[0]), str[1], str[2]));	
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "AccountCode.txt 파일을 찾을 수 없습니다.");
			System.exit(0);
			e.printStackTrace();
		}
		return masterData;		
	}


}
