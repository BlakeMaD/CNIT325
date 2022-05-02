import com.google.gson.*;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class myList {
    public listitem[] list;

    public myList(){
        this.list = new listitem[50];
        populateListFromFile();
    }
    private void populateListFromFile(){
        listitem[] tempList = new listitem[50];
        Gson gson = new Gson();
        try{
            Reader reader = Files.newBufferedReader(Paths.get("myList.json"));
            tempList = gson.fromJson(reader, listitem[].class);
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        this.list = tempList;
        System.out.println("myList read from file");

    }

    public void writeToFile(){

        try (Writer writer = new FileWriter("myList.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(this.list, writer);
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void addToList(listitem item){

        int x = 0;
        while(x < 50){
            if(this.list[x] != null && item != null){
                System.out.println(this.list[x].id);
                if(this.list[x].id.equals(item.id)){
                    System.out.println("Already in list");
                    return;
                }
            }
            x++;
        }

        x = 0;
        while(list[x] != null && x < 50){
            System.out.println(list[x].title);
            x++;
        }
        list[x] = item;
        System.out.println("item added: " + list[x].title);
    }
}
