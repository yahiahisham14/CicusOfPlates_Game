package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import controller.Controller;
public class SaveGame implements Serializable{


private String path="C:\\Users\\techno city\\Desktop\\saved\\";
private String fileName="";
Controller control = Controller.createController();
		
public void saveFile(String name) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
			File f = new File(  path+ "text.txt"); 
			fileName=name;
			FileOutputStream os = new FileOutputStream( f );
			 ObjectOutputStream out = new ObjectOutputStream(os);
			 out.writeObject( control ); 
			 out.flush();
			 out.close();
			 os.close();
		 }//end method.
		
		 public Controller load(String name) throws FileNotFoundException, IOException, ClassNotFoundException{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream( path + "text.txt"));
			
		 	Controller s = (Controller) in.readObject();
		 	in.close();
			return s;
		 }
		
		 public String getFileName(){
			 return this.fileName;
		 }

}
