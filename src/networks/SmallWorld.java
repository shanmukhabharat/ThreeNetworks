package networks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;


public class SmallWorld extends Graph{
	
	ArrayList<Integer> nodes;
	int edges;
	

	public SmallWorld(ArrayList<Integer> nodes) {
		super(nodes);
	}
	
	public SmallWorld(ArrayList<Integer> temp_nodes, int e){
		super(temp_nodes);
		nodes = temp_nodes;
		edges = e;
	}
	
	public Map<Integer, LinkedList<Integer>> getGraph(){
		
		LinkedList<Integer> neighbors = new LinkedList<Integer>();
		LinkedList<Integer> neighbors_of_neighbors = new LinkedList<Integer>();
		int current = 0,prev=0,next=0,first=0,second=0,s = 0,count = 0;
		
		Graph temp_smallworld = new SmallWorld(nodes);
		for(int i = 0 ; i < nodes.size(); i++){
			
			current = nodes.get(i);
			if(current-1==0)
				prev = nodes.get(nodes.size()-1);
			else
				prev = nodes.get(i-1);
			if(current+1 > nodes.size())
				next = nodes.get(0);
			else
				next = nodes.get(i+1);
			//System.out.println("curr" + current + "prev" + prev + "next" + next);
			if(!temp_smallworld.getNeighbors(current).contains(prev)){
				temp_smallworld.addNeighbor(current, prev);
				temp_smallworld.addNeighbor(prev, current);
			}
			if(!temp_smallworld.getNeighbors(current).contains(next)){
				temp_smallworld.addNeighbor(current, next);
				temp_smallworld.addNeighbor(next, current);
			}
			
		}
		//System.out.println("edges" + temp_smallworld.adj);
		Graph smallworld = new SmallWorld(nodes);
		
		for(int i = 0 ; i<nodes.size() ; i++){
			current = nodes.get(i);
			if(current-1==0)
				prev = nodes.get(nodes.size()-1);
			else
				prev = nodes.get(i-1);
			if(current+1 > nodes.size())
				next = nodes.get(0);
			else
				next = nodes.get(i+1);
		
			neighbors = temp_smallworld.getNeighbors(current);
			//System.out.println("current" + current + "neighbors" + neighbors);
			for(int j = 0 ; j < neighbors.size(); j++){
				int temp = neighbors.get(j);
				//System.out.println("twmp" + temp);
				if(!smallworld.getNeighbors(current).contains(temp)){
					smallworld.addNeighbor(current, temp);
					smallworld.addNeighbor(temp, current);
				}
				neighbors_of_neighbors = temp_smallworld.getNeighbors(temp);
				//System.out.println("non" + neighbors_of_neighbors);
				for(int each : neighbors_of_neighbors ){
					if(each!= current){
						if(!smallworld.getNeighbors(current).contains(each)){
							smallworld.addNeighbor(current, each);
							smallworld.addNeighbor(each, current);
						}
					}
						
				}
				//System.out.println("neighbors" + smallworld.getNeighbors(current));
			}
			
			
		}
		//System.out.println("smallworld"+smallworld.adj);
		while(count<(edges-2*nodes.size())){
			Random var1 = new Random();
			first = var1.nextInt(nodes.size());
			Random var2 = new Random();
			second = var2.nextInt(nodes.size());
			if(first != second){
				if(!smallworld.getNeighbors(nodes.get(first)).contains(nodes.get(second))){
					smallworld.addNeighbor(nodes.get(first), nodes.get(second));
					smallworld.addNeighbor(nodes.get(second), nodes.get(first));
					count++;
				}
			}
		}
		//System.out.println(smallworld.adj);
		this.adj = smallworld.adj;
		return smallworld.adj;	
	}
	
	
	public float getClusteringCoefficient(){
		
		ArrayList<Float> coefficients = new ArrayList<Float>();
		float d = 0,sum = 0;
		for(int i = 0 ; i< nodes.size() ; i++){
			int pairs = 0;
			LinkedList<Integer> neighbors = this.getNeighbors(nodes.get(i));
			for(Integer first : this.getNeighbors(nodes.get(i))){
				for(Integer second : neighbors){
					if(first != second){
						if(this.getNeighbors(second).contains(first)){
							pairs++;
						}
					}
				}
			}
			
			d=(float)neighbors.size()*(neighbors.size()-1)/2;
			if(d!=0.0)
				coefficients.add(pairs/(2*d));
			else
				coefficients.add((float) 0);
		}
		for(float each : coefficients){
			sum=sum+each;
		}
		
		return sum/coefficients.size();
		
	}
}
