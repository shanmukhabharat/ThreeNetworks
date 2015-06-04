package networks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;


public class RandomGraph extends Graph{

	ArrayList<Integer> nodes;
	int edges;
	
	public RandomGraph(ArrayList<Integer> nodes) {
		super(nodes);
	}
	
	public RandomGraph(ArrayList<Integer> temp_nodes, int e){
		super(temp_nodes);
		nodes = temp_nodes;
		edges = e;
	}
	
	public Map<Integer, LinkedList<Integer>> getGraph(){
		
		int first,second,s=0; 
		int edge_count = 0;
		
		//Graph randomgraph = new RandomGraph(nodes);
		System.out.println("nodes" + nodes + "edges" + edges);
		while(edge_count<edges){
			Random var1 = new Random();
			first = var1.nextInt(nodes.size());
			Random var2 = new Random();
			second = var2.nextInt(nodes.size());
			if(first != second){
				if(!this.getNeighbors(nodes.get(first)).contains(nodes.get(second))){
					this.addNeighbor(nodes.get(first), nodes.get(second));
					this.addNeighbor(nodes.get(second), nodes.get(first));
					edge_count++;
				}
			}
		}
		return this.adj;
		
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
