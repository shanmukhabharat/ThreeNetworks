package networks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;


public class Preferential extends Graph {

	ArrayList<Integer> nodes;
	int edges;

	
	public Preferential(ArrayList<Integer> nodes) {
		super(nodes);	
	}

	public Preferential(ArrayList<Integer> temp_nodes, int e){
		super(temp_nodes);
		nodes = temp_nodes;
		edges = e;
	}
	
	public Map<Integer, LinkedList<Integer>> getGraph(){

		int num_of_edges = 0, edge_node = 0, current =0;
		ArrayList<Integer> preferences = new ArrayList<Integer>();
		
		
		Graph preferential = new Preferential(nodes);
		//initialising for m0 = 3
		preferential.addNeighbor(nodes.get(0), nodes.get(1));
		preferential.addNeighbor(nodes.get(1), nodes.get(0));
		preferential.addNeighbor(nodes.get(0), nodes.get(2));
		preferential.addNeighbor(nodes.get(2), nodes.get(0));
		preferential.addNeighbor(nodes.get(1), nodes.get(2));
		preferential.addNeighbor(nodes.get(2), nodes.get(1));
		preferences.add(nodes.get(0));
		preferences.add(nodes.get(0));
		preferences.add(nodes.get(1));
		preferences.add(nodes.get(1));
		preferences.add(nodes.get(2));
		preferences.add(nodes.get(2));
		
		//System.out.println("first initialization" +preferential.adj);
		
		for(int i = 3 ; i<nodes.size() ; i++){
		
			ArrayList<Integer> ite_preferences = new ArrayList<Integer>();
			current = nodes.get(i);
			Random var1 = new Random();
			num_of_edges = var1.nextInt(3)+1;
			
			while(num_of_edges > 0){
				Random var2 = new Random();
				edge_node = var2.nextInt(Collections.max(preferences))+1;
				//System.out.println("current" + current + "num of edges" + num_of_edges + "edge node" + edge_node);
				if(!preferential.getNeighbors(current).contains(edge_node)){
					ite_preferences.add(edge_node);
					ite_preferences.add(current);
					preferential.addNeighbor(current, edge_node);
					preferential.addNeighbor(edge_node, current);
					num_of_edges--;
				}
			}
			for(int each : ite_preferences)
				preferences.add(each);
			//System.out.println("i" + i);
		}
		//System.out.println("final" + preferential.adj);
		this.adj = preferential.adj;
		return preferential.adj;

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
