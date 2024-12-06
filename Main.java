import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

//Class my entry
class MyEntry {
    
    //att
    private Integer key;
    private String value;


    //costr
    public MyEntry(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    //metodi
    public Integer getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return key + " " + value;
    }
}





//Class SkipListPQ
class SkipListPQ {

    private double alpha; //variabile arbitraria tra 0 e 1 
    private Random rand;

    private Vector<LinkedList <MyEntry> > skipList; 
    private int level; //numero di righe effettive
    private LinkedList<MyEntry> list;


    public SkipListPQ(double alpha) {
        this.alpha = alpha;

        //creo la prima lista vuota 
        list = new LinkedList<MyEntry> ();

        list.add(null);
        list.add(null);
        
        skipList.add(list);
        
        level = 1; //aumento il livello 
    }

    public int size() { 
        return level;
    }

    public MyEntry min() {
        
    return null;
    }

    public void insert(int key, String value) {

        MyEntry e = new MyEntry(key,value);

        //cerco dove inserire il nuovo elemento
        int column = search(key);

        /*trovato il posto giusto lancio una moneta fino a quando non esce testa, 
        per ogni croce aggiungo un livell ed inserisco la mia entry*/
        int level = generateEll(alpha, key);

        for(int i=level; i!= 0;i--){
            LinkedList <MyEntry> ls = skipList.get(i);
            ls.add(column,e); //inserco la entry nella colonna  trovata 
        }


    }

    private int  search(int key){

        int i = level;
        int j = 0; 

        for(i=level; i!= 0;i--){ 
            LinkedList<MyEntry> miaLista = skipList.get(i); 

            int miaK = miaLista.get(j).getKey(); 

            //scorro la lista fino a quando non trovo il più piccolo nodo maggiore, successivo a k 
            while(miaK < key || j == miaLista.size()-1){
                j++;
            }
            
            
            j--; //torno al nodo < di k

        }
        int col = j; //colonna precedente o uguale alla key, dove inserire la nuova entry
        return col;
    }

    private int generateEll(double alpha_ , int key) {
        int level = 0;
        if (alpha_ >= 0. && alpha_< 1) {
          while (rand.nextDouble() < alpha_) {
              level += 1;
          }
        }
        else{
          while (key != 0 && key % 2 == 0){
            key = key / 2;
            level += 1;
          }
        }
        return level;
    }

    public MyEntry removeMin() {
	// TO BE COMPLETED 
    return null;
    }

    public void print() {

        //TO BE COMPLETED
    }
}

//main
public class Main {
    public  static void main(String[] args){
    
    }
}
