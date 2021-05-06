package main;

public class Ticket {
    public String id;
    public int time;
    public int duration;
    public int exitTime;

    public Ticket(String id, int time) {
        this.id = id;
        this.time = time;
        
     }   
    
     @Override
    public int compareTo(Ticket o) {
        return exitTime - o.exitTime;
    }

    @Override
    public String toString() {
        return this.id + "-" + this.time + "-" + this.exitTime;
    }
}
