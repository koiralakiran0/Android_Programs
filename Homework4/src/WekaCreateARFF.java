import java.io.File;
import java.io.FileNotFoundException;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;

import java.util.*;
import weka.core.Instances;
import weka.core.converters.ArffSaver;


public class WekaCreateARFF {
	public static void main(String[] args) throws Exception {
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		File file = new File("C:/Users/koira/Desktop/data.txt");
		
		attributes.add(new Attribute("id"));      
		attributes.add(new Attribute("name", (FastVector)null));
		/*
		FastVector attVals = new FastVector();
		attVals.addElement(" democrat");
		attVals.addElement(" republican");
		//attributes.add(new Attribute("political_party", attVals));
		 * 
		 */
		//String[] values = {"democrat", "republican"};
		//attributes.add(new Attribute("political_party", Arrays.asList(values)));
		attributes.add(new Attribute("political_party", (FastVector)null));
		
		/*
		attVals = new FastVector();
		attVals.addElement("CA");
		attVals.addElement("TX");
		attVals.addElement("NY");
		attVals.addElement("NC");
		attVals.addElement("SC");
		//attributes.add(new Attribute("state", attVals));
		 * 
		 */
		//String[] values = {"CA", "TX", "NY", "NC", "SC"};
		//attributes.add(new Attribute("state", Arrays.asList(values)));
		attributes.add(new Attribute("state", (FastVector)null));
		
		attributes.add(new Attribute("birth_date", "yyyy-MM-dd"));
		
		Instances dataSet =  new Instances("SimpleARFF", attributes, 0); 
		
		Scanner read = new Scanner(file);
		while(read.hasNextLine()) {
			String dataGet = read.nextLine().replaceAll("\'", "");
			String[] data = dataGet.split(",");
			double[] vals = new double[dataSet.numAttributes()];
			vals[0] = Integer.parseInt(data[0]);
			vals[1] = dataSet.attribute(1).addStringValue(data[1]);
			vals[2] = dataSet.attribute(2).addStringValue(data[2]);
			vals[3] = dataSet.attribute(3).addStringValue(data[3]);
			vals[4] = dataSet.attribute(4).parseDate(data[4]);
			
			dataSet.add(new DenseInstance(1.0, vals));
		}
		
		System.out.println(dataSet);
		
		//Creating arff file     
		ArffSaver arffSaverInstance =  new ArffSaver();     
		arffSaverInstance.setInstances(dataSet);     
		arffSaverInstance.setFile(new File("SimpleARFF.arff"));     
		arffSaverInstance.writeBatch();
	}
}