package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.datastruct.One;

public class LineManager {

	public static void save(Line line){
		try {
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(new File(Config.LINE_FILENAME)));
			objectOutputStream.writeObject(line);
			objectOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Line get(){
		Line line=null;
		try {
			File file=new File(Config.LINE_FILENAME);
			if(!file.exists()){
				System.out.println("file no exists");
				line=new Line();
				return line;
			}
			ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
			line=(Line) objectInputStream.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return line;
		
	}
}
