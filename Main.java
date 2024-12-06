package lib;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
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
    private int size; //numero di liste non vuote
    private LinkedList<MyEntry> list;


    public SkipListPQ(double alpha) {
        this.alpha = alpha;

        //creo la prima lista vuota 
        list = new LinkedList<MyEntry> ();
        list.addLast(null);
        list.addFirst(null);

        skipList= new Vector <LinkedList <MyEntry> > ();
        
        skipList.add(list);
    
        rand = new Random();
    }

    public int size() { 
        //numero di liste attualmente usate 
        return skipList.size() -1;
    }

    public MyEntry min() {

        return this.skipList.get(0).get(1);
    }

    public void insert(int key, String value) {

        MyEntry e = new MyEntry(key,value);

        //cerco dove inserire il nuovo elemento
        int column = search(key);

        /*trovato il posto giusto lancio una moneta fino a quando non esce testa, 
        per ogni croce aggiungo un livell ed inserisco la mia entry*/
         
        int level = generateEll(alpha, key);


        //creo tante liste vuote, quante il numero di livelli che dovrò aggiungere
        for(int i=size;i<=level;i++){
            LinkedList <MyEntry> ls = new LinkedList<MyEntry> ();
            ls.add(null);
            ls.add(null);
            skipList.add(ls);
            size++;
        }


        for(int i=0; i<=level; i++){
            LinkedList <MyEntry> ls = skipList.remove(i); //prendo la lista associata al livello

            //modifico la lista

            //tolgo le sentinelle
            ls.removeFirst();
            ls.removeLast();

            if(column > ls.size()){
                //inserisci in coda 
                ls.addLast(e);
            }else{
                ls.add(column,e); //inserco la entry nella colonna  trovata 
            }

            //aggiungo le sentinelle
            ls.addLast(null);
            ls.addFirst(null);
            skipList.add(i, ls); //inserisco la lista nel livello i della skiplist
        }

    }

    private int  search(int key){

        int col = 0;  //prima colonna diversa dalla sentinella

        for(int i=size; i>=0;i--){ 

            LinkedList<MyEntry> miaLista = new LinkedList<MyEntry>(skipList.get(i));
            //ogni iterazione del ciclo scendo di un livello
            miaLista.removeFirst();


            //scorro la lista finchè non trovo un valore > o uguale alla mia key, o fino a quando non trovo la sentinella di fine 
            while( miaLista.get(col) != null &&  miaLista.get(col).getKey() < key ){
                col++;
            }
            miaLista.addFirst(null);
            

        }
         //colonna precedente o uguale alla key, dove inserire la nuova entry
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
        MyEntry min = min();
        for(int i=0;i< size;i++){
            if(skipList.get(i).get(1) == min){
                skipList.get(i).remove(1);
            }

        }
    return min;
    }

    public void print() {

        for(int i=  size()-1 ;i >= 0; i--){
            System.out.print("lv "+i+ ": ");
            LinkedList <MyEntry> ls = this.skipList.get(i);
        
            for(int j=1;j<=ls.size()-2;j++){
                try{
                    System.out.print(ls.get(j)+" ");
                }catch(Exception e){
                    System.out.println(e);
                }
            }
            System.out.println();
        }
    }

   
}

//main
public class Main {
    public  static void main(String[] args){
        /* 
        args[0] = "test.txt";
        if(args.length < 1){
                System.out.println("numero parametri insufficienti");
        }
        */

        
        String path = "/home/riccardo/Documenti/dati e algoritmi/PriorityQueue/bin/lib/test.txt";
        int n;
        double alpha;
        int in;
        SkipListPQ sk;

        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);

            n = Integer.parseInt(sc.nextLine());
            alpha = Double.parseDouble(sc.nextLine());
            sk = new SkipListPQ(alpha);

            for(int i=0;i<n-1;i++){
                in = Integer.parseInt(sc.nextLine());
                if(in == 0){
                    System.out.println("l'entry con chiave minima è : "+sk.min());
                }else if(in == 1){
                        System.out.println("tolgo la  chiave minima è : "+sk.removeMin());
                }else if(in == 2){
                    int k = Integer.parseInt(sc.nextLine());
                    String e = sc.nextLine();
                    sk.insert(k, e+"");
                }else if(in == 3){
                    sk.print();
                }
            }

            sc.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    

    }
}
