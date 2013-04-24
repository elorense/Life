import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Life {
	public static void main(String[] args){
		MySparseArray currentGen = new MySparseArray(0);
		MySparseArray neighborCount = new MySparseArray(0);
		MySparseArray nextGen = new MySparseArray(0);
		BufferedReader bf = null;
		int generations = Integer.parseInt(args[2]);
		
		try {
			bf = new BufferedReader(new FileReader(args[0]));
			try {
				String sourceLine = null;
				while((sourceLine = bf.readLine()) != null){
					String[] lines = sourceLine.split("\\W");
					currentGen.setValue(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]), 1);
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < generations; i++){
			neighborCount = new MySparseArray(0);
			nextGen = new MySparseArray(0);
			RowIterator r = currentGen.iterateRows();
			while (r.hasNext())
			{
			   ElemIterator elmItr = r.next();
			   while (elmItr.hasNext())
			   {
			      MatrixElem me = elmItr.next();
			      
			      if(!currentGen.elementAt(me.rowIndex(), me.columnIndex() - 1).equals(0)){
			    	  neighborCount.setValue(me.rowIndex(), me.columnIndex(), (Integer) neighborCount.elementAt(me.rowIndex(), me.columnIndex()) + 1);
			      }else{
			    	  neighborCount.setValue(me.rowIndex(), me.columnIndex() - 1, (Integer) neighborCount.elementAt(me.rowIndex(), me.columnIndex() - 1) + 1);
			      }
			      if(!currentGen.elementAt(me.rowIndex(), me.columnIndex() + 1).equals(0)){
			    	  neighborCount.setValue(me.rowIndex(), me.columnIndex(), (Integer) neighborCount.elementAt(me.rowIndex(), me.columnIndex()) + 1);
			      }else{
			    	  neighborCount.setValue(me.rowIndex(), me.columnIndex() + 1, (Integer) neighborCount.elementAt(me.rowIndex(), me.columnIndex() + 1) + 1);
			      }
			      if(!currentGen.elementAt(me.rowIndex() + 1, me.columnIndex() + 1).equals(0)){
			    	  neighborCount.setValue(me.rowIndex(), me.columnIndex(), (Integer) neighborCount.elementAt(me.rowIndex(), me.columnIndex()) + 1);
			      }else{
			    	  neighborCount.setValue(me.rowIndex() + 1, me.columnIndex() + 1, (Integer) neighborCount.elementAt(me.rowIndex() + 1, me.columnIndex() + 1) + 1);
			      }
			      if(!currentGen.elementAt(me.rowIndex() - 1, me.columnIndex() + 1).equals(0)){
			    	  neighborCount.setValue(me.rowIndex(), me.columnIndex(), (Integer) neighborCount.elementAt(me.rowIndex(), me.columnIndex()) + 1);
			      }else{
			    	  neighborCount.setValue(me.rowIndex() - 1, me.columnIndex() + 1, (Integer) neighborCount.elementAt(me.rowIndex() - 1, me.columnIndex() + 1) + 1);
			      }
			      if(!currentGen.elementAt(me.rowIndex() - 1, me.columnIndex()).equals(0)){
			    	  neighborCount.setValue(me.rowIndex(), me.columnIndex(), (Integer) neighborCount.elementAt(me.rowIndex(), me.columnIndex()) + 1);
			      }else{
			    	  neighborCount.setValue(me.rowIndex() - 1, me.columnIndex(), (Integer) neighborCount.elementAt(me.rowIndex() - 1, me.columnIndex()) + 1);
			      }
			      if(!currentGen.elementAt(me.rowIndex() + 1, me.columnIndex()).equals(0)){
			    	  neighborCount.setValue(me.rowIndex(), me.columnIndex(), (Integer) neighborCount.elementAt(me.rowIndex(), me.columnIndex()) + 1);
			      }else{
			    	  neighborCount.setValue(me.rowIndex() + 1, me.columnIndex(), (Integer) neighborCount.elementAt(me.rowIndex() + 1, me.columnIndex()) + 1);
			      }
			      if(!currentGen.elementAt(me.rowIndex() + 1, me.columnIndex() - 1).equals(0)){
			    	  neighborCount.setValue(me.rowIndex(), me.columnIndex(), (Integer) neighborCount.elementAt(me.rowIndex(), me.columnIndex()) + 1);
			      }else{
			    	  neighborCount.setValue(me.rowIndex() + 1, me.columnIndex() - 1, (Integer) neighborCount.elementAt(me.rowIndex() + 1, me.columnIndex() - 1) + 1);
			      }
			      if(!currentGen.elementAt(me.rowIndex() - 1, me.columnIndex() - 1).equals(0)){
			    	  neighborCount.setValue(me.rowIndex(), me.columnIndex(), (Integer) neighborCount.elementAt(me.rowIndex(), me.columnIndex()) + 1);
			      }else{
			    	  neighborCount.setValue(me.rowIndex() - 1, me.columnIndex() - 1, (Integer) neighborCount.elementAt(me.rowIndex() - 1, me.columnIndex() - 1) + 1);
			      }
			   }		
			}
					
			r = neighborCount.iterateRows();
			while (r.hasNext())
			{
			   ElemIterator elmItr = r.next();
			   while (elmItr.hasNext())
			   {
			      MatrixElem me = elmItr.next();
			      if(currentGen.elementAt(me.rowIndex(), me.columnIndex()).equals(0)){
			    	  if(me.value().equals(3)){
			    		  nextGen.setValue(me.rowIndex(), me.columnIndex(), 1);
			    	  }
			      }else{
			    	  if(me.value().equals(2) || me.value().equals(3)){
			    		  nextGen.setValue(me.rowIndex(), me.columnIndex(), 1);
			    	  }
			      }
			   }
			}
			currentGen.row.head = nextGen.row.head;
			currentGen.col.head = nextGen.col.head;
		}
		
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(
					args[1])));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		RowIterator r = currentGen.iterateRows();
		while (r.hasNext())
		{
		   ElemIterator elmItr = r.next();
		   while (elmItr.hasNext())
		   {
		      MatrixElem me = elmItr.next();
		      writer.println(me.rowIndex() + ", " +
		                       me.columnIndex());
		   }
		}
		writer.flush();
		writer.close();
	}
}
