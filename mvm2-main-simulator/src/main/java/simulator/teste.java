package simulator;

import java.lang.reflect.Array;
import java.text.DecimalFormat;

import mvm2services.services.NumberConverter;

public class teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Teste");
		
		String[] i = new String[5];
		
		double vlDecimal = 0.00;
		vlDecimal = 1.292938323;
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("0.00000");

		vlDecimal = NumberConverter.convertToDouble(df.format(vlDecimal));
		System.out.println(vlDecimal);
		
		
		i[0] = "S";
		i[1] = "S";
		i[2] = "S";
		i[3] = "N";
		i[4] = "S";
		
		System.out.println(Array.getLength(i));
		
	}

}
