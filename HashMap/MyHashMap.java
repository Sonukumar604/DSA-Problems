import java.util.*;
public class MyHashMap<K, V>{
    private class Node{
        K key;
        V value;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    private int n;
    private int N;
    private LinkedList<Node>[] buckets;
    @SuppressWarnings("unchecked")
    public MyHashMap(){
        this.N = 4;
        this.buckets = new LinkedList[N];
        for(int i = 0; i < N; i++){
            this.buckets[i] = new LinkedList<>();
        }
    }
    private int hashFunction(K key){
        int bi = key.hashCode();
        return Math.abs(bi) % N;
    }
    private int searchInLL(K key, int bi){
        LinkedList<Node> ll = buckets[bi];
        for(int i = 0 ; i < ll.size(); i++){
            if(Objects.equals(ll.get(i).key, key)){
                return i;
            }
        }
        return -1;
    }
    @SuppressWarnings("unchecked")
    private void rehash(){
        LinkedList<Node>[] oldBuckets = buckets;
        N = N * 2; // Double the number of buckets
        buckets = new LinkedList[N];
        for(int i = 0; i < N; i++){
            buckets[i] = new LinkedList<>();
        }
        n = 0;
        for(LinkedList<Node> ll : oldBuckets){
            for(Node node : ll){
                put(node.key, node.value);
            }
        }
    }
    public void put(K key, V value){
        int bi = hashFunction(key);
        int di = searchInLL(key, bi);
        if(di == -1){
            buckets[bi].add(new Node(key, value));
            n++;
        }else{
            Node node = buckets[bi].get(di);
            node.value = value;
        }
        double lambda = (double) n / N;
        if(lambda > 2.0){
            rehash();
        }
    }
    public V get(K key){
        int bi = hashFunction(key);
        int di = searchInLL(key, bi);
        if(di == -1){
            return null;
        }else{
            Node node = buckets[bi].get(di);
            return node.value;
        }
    }
    public boolean containsKey(K key){
        int bi = hashFunction(key);
        int di = searchInLL(key, bi);
        return di != -1;
    }
    public V remove(K key){
        int bi = hashFunction(key);
        int di = searchInLL(key, bi);
        if(di == -1){
            return null;
        }else{
            Node node = buckets[bi].remove(di);
            n--;
            return node.value;
        }
    }
    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();
        for(LinkedList<Node> ll : buckets){
            for(Node node : ll){
                keys.add(node.key);
            }
        }
        return keys;
    }
    public boolean isEmpty(){
        return n == 0;
    }
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("India", 190);
        map.put("China", 200);
        map.put("US", 50);

        // Add more puts to trigger a rehash!
        map.put("Russia", 180);
        map.put("UK", 40);
        map.put("Canada", 60);
        map.put("Germany", 80);
        map.put("France", 70);
        map.put("Japan", 120); // This should trigger rehash as n/N = 9/4 > 2.0

        ArrayList<String> keys = map.keySet();
        System.out.println("--- Initial Map ---");
        for (String key : keys) {
            System.out.println(key + ": " + map.get(key));
        }

        System.out.println("\n--- Removing India ---");
        map.remove("India");
        System.out.println("Value for 'India' after removal: " + map.get("India"));

        System.out.println("\n--- Final Map ---");
        keys = map.keySet();
        for (String key : keys) {
            System.out.println(key + ": " + map.get(key));
        }
    }
}