/* 
* 
* Autor: Renan Campos Silverio de Oliveira  
* Data de Criação: 16/07/2018 
* Objetivo: Resolução do problema de identificação de Mutantes pelo Magneto 
*  
* Premissas:  
* - Entradas  -> Um array de String N x N 
* - Saídas    -> true caso seja um mutante e false caso contrário 
* - Premissas: 
*  - A matriz de entrada só pode contar as letras A, C, G e T 
*  - Um mutante é dentificado quando encontra-se uma sequência de 4 letras iguais em mais de uma direção 
*  - A verificação na forma oblíqua é apenas na diagonal principal, de cima para baixo, da esquerda para direita           
* 
*/

import java.util.ArrayList;

public class Mutante {

	private String[] dnaHorizontal; 
	private String[] dnaVertical; 
	private String[] dnaObliquo;  
	
	public boolean isMutant(String[] dna) {  
		boolean achouHorizontal = false; 
		boolean achouVertical = false; 
		boolean achouObliquo = false;  
		
		gerarDnas(dna);  
		
		achouHorizontal = hasSequenceLetters(this.dnaHorizontal); 
		achouVertical = hasSequenceLetters(this.dnaVertical); 
		achouObliquo = hasSequenceLetters(this.dnaObliquo);  
		
		if (achouHorizontal) { 
			if (achouVertical || achouObliquo) { 
				return true; 
			} 
		} 
		else { 
			if (achouVertical && achouObliquo) { 
				return true; 
			} 
		}  
		
		return false; 
	}  
	
	private void gerarDnas(String[] dnaOriginal) {

		ArrayList<String> arrDnaVertical = new ArrayList<>(); 
		ArrayList<String> arrDnaObliquo = new ArrayList<>();  
		int contador = 0;  
		
		for (String fila : dnaOriginal) {  
			for (int i = 0; i < fila.length(); i++) { 
				// Vertical 
				if(contador == 0) { 
					arrDnaVertical.add(Character.toString(fila.charAt(i))); 
				} 
				else { 
					arrDnaVertical.set(i, arrDnaVertical.get(i).concat(Character.toString(fila.charAt(i)))); 
				}  
				
				// Obliquo 
				if (contador == i) { 
					if (contador == 0) { 
						arrDnaObliquo.add(Character.toString(fila.charAt(i))); 
					} 
					else { 
						arrDnaObliquo.set(0, arrDnaObliquo.get(0).concat(Character.toString(fila.charAt(i)))); 
					} 
				} 
			}  
			
			contador++; 
		}  
		
		this.dnaHorizontal = dnaOriginal; 
		this.dnaVertical = arrDnaVertical.stream().toArray(String[]::new); 
		this.dnaObliquo = arrDnaObliquo.stream().toArray(String[]::new); 
	}  
	
	private boolean hasSequenceLetters(String[] dna) {  
		for (String fila : dna) {  
			if (fila.contains("AAAA")) 
				return true;  
			if (fila.contains("CCCC")) 
				return true;  
			if (fila.contains("GGGG")) 
				return true;  
			if (fila.contains("TTTT")) 
				return true; 
		} 
		
		return false; 
	}
}