package networks;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;


public class Main {
	
	public static boolean isNumeric(String s) {
	    return s.matches("\\d+");
	}
	
	//Requires: Number of nodes, Number of edges and type of graph
	//Output: Edge list of the graph produced, Clustering coefficient of the graph  and average path length
	public static void main(String[] args) throws NumberFormatException, IOException {
		String n = null;
		String edges = null;
		String type = null;
		int sum_of_lengths = 0;
		Map<Integer, LinkedList<Integer>> result = null;
		ArrayList<Integer> lengths = new ArrayList<Integer>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter number of nodes :");
        try {
			n = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<Integer> nodes = new ArrayList<Integer>(Collections.nCopies(Integer.parseInt(n), 0));
		for(int i = 0 ; i <nodes.size(); i++)
			nodes.set(i,i+1);
		
		System.out.print("Enter number of edges :");
		try {
			edges = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.print("Enter the type of graph :");
		try {
			type = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(Integer.parseInt(type) == 1){
			Graph randomgraph = new RandomGraph(nodes,Integer.parseInt(edges));
			result = randomgraph.getGraph();
			System.out.println("Edgelist random graph :" + result);
			System.out.println("Random graph clustering coefficient :" + randomgraph.getClusteringCoefficient());
		}else if(Integer.parseInt(type) == 2){
			Graph smallworld = new SmallWorld(nodes,Integer.parseInt(edges));
			result = smallworld.getGraph();
			System.out.println("Edgelist smallworld graph :" +result);
			System.out.println("Smallworld clustering coefficient :"+smallworld.getClusteringCoefficient());
		}else if(Integer.parseInt(type) == 3){
			Graph preferential = new Preferential(nodes,Integer.parseInt(edges));
			result = preferential.getGraph();
			System.out.println("Edgelist preferential graph :" + result);
			System.out.println("Preferential clustering coefficient :"+ preferential.getClusteringCoefficient());
		}else{
			System.out.println("Enter a valid option");
		}
		
		//***************** Finding Average Path length********************************************
		int iterations = 0;
		while(iterations<1000){
			ArrayList<Integer> visited= new ArrayList<Integer>(Collections.nCopies(nodes.size()+1,0));
			ArrayList<Integer> local_lengths = new ArrayList<Integer>(Collections.nCopies(Integer.parseInt(n+1),0));
			for(int node: nodes){
				local_lengths.set(node, 0);
			}
			
			int from = 0; int to = 0;
			
			Queue<Integer> q = new Queue<>();
			while(from == to){
				Random var1 = new Random();
				from = var1.nextInt(nodes.size()) + 1;
				Random var2 = new Random();
				to = var2.nextInt(nodes.size()) + 1;
			}	
			if(from!=to){
				local_lengths.set(from, 0);
				visited.set(from,1);
				q.enqueue(from);
				while(!(q.size()==0)){
					if(local_lengths.get(to) == 0){
						int current = q.dequeue();
						LinkedList<Integer> edges_current = result.get(current);
						for(int each :edges_current){
							//System.out.println(visited.get());
							if(local_lengths.get(each) == 0 && visited.get(each) == 0){
								q.enqueue(each);
								local_lengths.set(each, local_lengths.get(current)+1);
								visited.set(each, 0);
							}
						}
					}else{
						lengths.add(local_lengths.get(to));
						iterations++;
						break;
					}
				}
			}
		}
		System.out.println(lengths.size());
		
		for(int i = 0 ; i < lengths.size(); i++){
			sum_of_lengths = sum_of_lengths + lengths.get(i);
		}
		System.out.println("average path length :" + (float)sum_of_lengths/1000);
		
			
		//*********************degree calculation******************
		/*
		int degree = 0;
		try {
				File file = new File("/Users/Bharath/Desktop/result.txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				for(int i = 1; i<=result.size(); i++){
					//System.out.println(result.get(i));
					degree = result.get(i).size();
					bw.write(degree + "\n");
				}
				bw.close();
		}catch (IOException e) {
		 	e.printStackTrace();
		}
		////////***********Frequency calculation**********
		
		FileReader fr=new FileReader("/Users/Bharath/Desktop/result.txt");
	 	BufferedReader br1=new BufferedReader(fr);  
	 	ArrayList<Integer> frequency = new ArrayList<>(Collections.nCopies(nodes.size(),0));  
	 	String line;
	 	while ((line=br1.readLine())!= null){ 	
	 		   String words[] = line.split("	");
	 		   String current = words[0];
	 		  if(isNumeric(current)){
	 			  frequency.set(Integer.parseInt(current),frequency.get(Integer.parseInt(current))+1);  
	 		  }
	 	}	  
		try {
			File file = new File("/Users/Bharath/Desktop/result_frequency.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i = 0; i<frequency.size(); i++){
				bw.write(frequency.get(i) + "\n");
			}
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		try {
			File file = new File("/Users/Bharath/Desktop/nodes.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw1 = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw1 = new BufferedWriter(fw1);
			for(int i = 0; i<5242; i++){
				bw1.write(i + "\n");
			}
			bw1.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}*/
	}
}
