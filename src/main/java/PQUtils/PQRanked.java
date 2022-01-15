package PQUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PQRanked<T extends Comparable<T>>{
    private List<T> pq;
    private HashMap<T, Integer> rankById;
    private int size = 0;

    public PQRanked(Class c, int capacity) {
        this.pq = new ArrayList<>();
        this.pq.add(null);
        this.rankById = new HashMap<>();
    }

    private int parent(int index){
        return index / 2;
    }

    private int left(int index){
        return 2 * index;
    }

    private int right(int index){
        return 2 * index + 1;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    // https://www.baeldung.com/java-custom-class-map-key
    public boolean contains(T element){
        return this.rankById.containsKey(element);
    }

    public void push(T x){
        int index = size + 1;
        this.pq.add(x);
        this.rankById.put(x, index);
        size++;
        up(index);
    }

    public T pop(){
        T root = this.pq.get(1);
        this.rankById.remove(root);
        T x = this.pq.get(size);

        size--;
        this.pq.remove(this.pq.size() - 1);

        if(!this.isEmpty()){
            //System.out.println("PUTTING " + x + " AT THE BEGINNING");
            this.pq.set(1, x);
            this.rankById.put(x, 1);
            down(1);
        }

        return root;
    }

    public void update(T old, T new_element){
        int index = this.rankById.get(old);

        // System.out.println("[i] PQRanked Index : " + index);

        this.rankById.remove(old);
        this.pq.set(index, new_element);
        this.rankById.put(new_element, index);

        if(less(old,new_element))
            down(index);
        else
            up(index);
    }

    private void up(int index){
        T x = this.pq.get(index);

        while(index > 1 && less(x, this.pq.get(parent(index)))){
            this.pq.set(index, this.pq.get(parent(index)));
            this.rankById.put(this.pq.get(parent(index)), index);
            index /= 2;
        };

        this.pq.set(index, x);
        this.rankById.put(x, index);
    }

    private void down(int index){
        T x = this.pq.get(index);
        int n = size;

        while(true){
            if (right(index) <= n &&
                    less(this.pq.get(right(index)), x) &&
                    less(this.pq.get(right(index)), this.pq.get(left(index)))){
                this.pq.set(index, this.pq.get(right(index)));
                this.rankById.put(this.pq.get(right(index)), index);
                index = right(index);
            }
            else if (left(index) <= n && less(this.pq.get(left(index)), x)){
                this.pq.set(index, this.pq.get(left(index)));
                this.rankById.put(this.pq.get(left(index)), index);
                index = left(index);
            }
            else{
                this.pq.set(index, x);  // insertion index found
                this.rankById.put(x, index);
                return;
            }
        }
    }

    private boolean less(T x, T y){
        return x.compareTo(y) < 0;
    }

    public List<T> getPq() {
        return pq;
    }

    public int getSize() {
        return size;
    }
}