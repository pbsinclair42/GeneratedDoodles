package main;

import java.util.ArrayList;

public class SquareArtMain  {
	public static final int width = 50; // width of the grid to be filled, within which (width/2)^2 squares are placed (130 max for Eclipse text display)
	private static final int density = 70; // a rating of how many large blocks of black feature in the image (0-100)
	private static double probability1; // probability a new pixel placed is adjacent to only one filled pixel
	private static double probability2; // probability a new pixel placed is adjacent to only two filled pixels
	private static double probability3; // probability a new pixel placed is adjacent to three or four filled pixels
	
	
	
	private static int[][] art = new int[width][width]; // the array of pixels
	@SuppressWarnings("unchecked")
	private static ArrayList<Pair>[] groups = new ArrayList[6]; // the list storing which group each pixel is in
	
	// Initialise variables and place the first square in the middle of the grid
	private static void initialize(){
		// Calculate the probability of placing a pixel adjacent to each number of pixels {
		probability1 = (-22.5)*(Math.pow((Math.E),((Math.log(7)-Math.log(3))/50*density)))+122.5;
		probability2 = -0.008*(Math.pow(density,2))+0.8*density;
		probability3 = 100-probability1-probability2;
		if (probability3<0){
			probability2+=probability3;
			probability3=0;
		}
		// Calculate the probability of placing a pixel adjacent to each number of pixels }
		
		groups[0]=new ArrayList<Pair>();
		groups[1]=new ArrayList<Pair>();
		groups[2]=new ArrayList<Pair>();
		groups[3]=new ArrayList<Pair>();
		groups[4]=new ArrayList<Pair>();
		groups[5]=new ArrayList<Pair>();
		
		for (int i = 0; i<width; i++){
			for (int j = 0; j<width; j++){
				groups[0].add(new Pair(i,j));
			}
		}
		placeSquare(width/2,width/2);
	}
	
	private static void placeSquare(int x, int y){
		int oldValue = art[x][y];
		setValue(x,y,5);
		if (isValidSquare(x,y)){
			increment(x-1, y);
			increment(x+1, y);
			increment(x, y-1);
			increment(x, y+1);
		}else{
			setValue(x,y,oldValue);
			chooseSquare();
		}
	}
	
	private static void chooseSquare(){
		int type = chooseSquareType(); 
		int choice = (int)(Math.random() * (groups[type].size()));
		Pair chosen = groups[type].get(choice);
		placeSquare(chosen.getx(),chosen.gety());
	}
	
	private static int chooseSquareType(){
		int type;
		int generated = (int)(Math.random() * 100);
		if (generated<probability1){
			type=1;
		}else if(generated<probability1+probability2){
			type=2;
		}else{
			type=3;
		}
		while (groups[type].size()==0){
			type--;
		}
		return type;
	}
	
	private static void setValue(int x, int y, int value){
		groups[art[x][y]].remove(new Pair(x,y));
		art[x][y]=value;
		groups[value].add(new Pair(x,y));
	}
	
	private static void increment(int x, int y){
		if (x>=0&&y>=0&&x<width&&y<width){
			if (art[x][y]<4){
				setValue(x,y,art[x][y]+1);
			}
		}
	}
	
	private static boolean isValidSquare(int x, int y){
		return isValidNeighbour(x-1,y)&&isValidNeighbour(x+1,y)&&isValidNeighbour(x,y-1)&&isValidNeighbour(x,y+1);
	}
	
	private static boolean isValidNeighbour(int x, int y){
		return (x<=0||y<=0||x>=width||y>=width||art[x][y]==5||isEdgeConnected(x,y));
	}
	
	private static boolean isEdgeConnected(int x,int y){
		ArrayList<Pair> checked =new ArrayList<Pair>();
		ArrayList<Pair> queued = new ArrayList<Pair>();
		queued.add(new Pair(x,y));
		
		do {
			int a = queued.get(0).getx();
			int b = queued.get(0).gety();
			queued.remove(0);
			if (isEdge(a,b)){
				return true;
			}else if (art[a][b]==5){
				checked.add(new Pair(a,b));
			}else{
			
				checked.add(new Pair(a,b));
				if (!queued.contains(new Pair(a-1,b))&&!checked.contains(new Pair(a-1,b))){
					queued.add(new Pair(a-1,b));
				}
				if (!queued.contains(new Pair(a+1,b))&&!checked.contains(new Pair(a+1,b))){
					queued.add(new Pair(a+1,b));
				}
				if (!queued.contains(new Pair(a,b-1))&&!checked.contains(new Pair(a,b-1))){
					queued.add(new Pair(a,b-1));
				}
				if (!queued.contains(new Pair(a,b+1))&&!checked.contains(new Pair(a,b+1))){
					queued.add(new Pair(a,b+1));
				}
			}
		} while(queued.size()>0);
		
		return false;
	}
	
	
	private static boolean isEdge(int x, int y){
		return x==0||y==0||x==width||y==width;
	}
	
	private static void displayValues(){
		for (int i = 0; i<width; i++){
			for (int j = 0; j<width; j++){
				System.out.print(art[i][j]==5?" O ":"   ");
			};
			System.out.println();
		};
	}	
	
	
	
	public static int[][] mainFunction() {
		initialize();
		System.out.println(density);
		System.out.println(probability1+","+probability2+","+probability3);
		for (int i = 0; i<Math.floor(width*width/4) ;i++){
			chooseSquare();
		}
		//displayValues();
		return art;
	}
};

