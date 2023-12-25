package database;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.*;
import java.util.Vector;

import authentication.AuthenticationService;

public class Data implements Serializable {
	public static Vector<Course> courses = new Vector<Course>();
	public static Vector<Student> students = new Vector<Student>();
	public static Vector<Teacher> teachers = new Vector<Teacher>();
	public static Vector<Manager> managers = new Vector<Manager>();
	public static Vector<User> users = new Vector<User>();
	public static Data INSTANCE;
	static {
		if(new File("Dataa").exists()) {
			try {
				INSTANCE = read();
			}
			catch(Exception e) {
				
				e.getStackTrace();
			}
		}
		else {INSTANCE = new Data();}
	}

	private Data() {

	}

	public static Data read() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("Dataa");
		ObjectInputStream oin = new ObjectInputStream(fis);
		return (Data) oin.readObject();
	}

	public static void write() throws IOException {
		FileOutputStream fos = new FileOutputStream("Dataa");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(INSTANCE);
		oos.close();
	}
}
