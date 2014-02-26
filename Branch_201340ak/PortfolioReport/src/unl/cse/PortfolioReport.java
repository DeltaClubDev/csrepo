package unl.cse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class PortfolioReport {
	private static final PortfolioHub ph = new PortfolioHub();
	
	public PortfolioReport(boolean printXMLPersons, boolean printXMLAssets) {
		new DataConverter(printXMLPersons, printXMLAssets);
		parsePortfolio();
	}
	
	private void parsePortfolio() {
		Scanner s = null;
		PrintWriter pw = null;
    	try {
			s = new Scanner(new File("data/Portfolios.dat"));
			pw = new PrintWriter("data/output.txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
    	pw.println("Portfolios\n"+
		"**********");
    	s.nextLine();
    	while(s.hasNextLine()) {
    		pw.println("\n#~#~#~#~#~#~#~#~#~#\n");
    		String line = s.nextLine();
			String tokens[] = line.split(";");
			String code = tokens[0];
			String owner = tokens[1];
			String manager = tokens[2];
			String derp = "N/A";
			String[] slerp = {"N/A"};
			String benefit = (tokens.length > 3) ? tokens[3] : derp;
			String[] tmp = (tokens.length > 4) ? tokens[4].split(",") : slerp;
			String[][] assets = new String[tmp.length][];
			for (int i = 0; i < tmp.length; i++) {
				assets[i] = tmp[i].split(":");	
			}
			
			Portfolio pf = new Portfolio(code, owner, manager, benefit, assets);
			ph.addPortfolio(pf);
			pw.println(" +--------------++--------------++------------++------------+");
			pw.println(" | Portfolio ID || --Owner ID-- || Manager ID || Benefit ID |");
			pw.println(" +--------------++--------------++------------++------------+");
			String ids = String.format("%-3s %-10s %-4s %-10s %-4s %-8s %-4s %-8s %-2s"," | ",code," || ",owner," || ",manager," || ",benefit," |");
			pw.println(ids);
			pw.println(" +--------------++--------------++------------++------------+");
			pw.println("\nAssets: [ ID ], [ VALUE ]");
			for (String[] row : assets) {
		        pw.println(Arrays.toString(row));
			}
    		pw.println("\n#~#~#~#~#~#~#~#~#~#\n");
    	}
		pw.close();
	}
	
	public static void main(String[] args) {
		boolean printXMLPersons = false;
		boolean printXMLAssets = false;
		new PortfolioReport(printXMLPersons, printXMLAssets);
	}
}