package main;

public class Pair {
	private int x;
	private int y;//so the pair is (x,y)
	
	//Basic constructor:
	public Pair(int a, int b){
		this.setx(a);
		this.sety(b);
	}

	//Getter and Setter methods:
	public int getx() {
		return x;
	}

	public void setx(int x) {
		this.x = x;
	}

	public int gety() {
		return y;
	}

	public void sety(int y) {
		this.y = y;
	}
	
	public void sets(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public String toString(){
		return "" + x + "," + y;
	}
	
	public boolean equals(Object a){
		return this.getx()==((Pair) a).getx()&&this.gety()==((Pair) a).gety();
	}
}


