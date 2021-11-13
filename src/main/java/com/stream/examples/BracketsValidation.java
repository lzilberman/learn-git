package com.stream.examples;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class BracketsValidation {

	public static void main(String[] args) {

//        String input = "[,]},{";
//        String input = "a[bcd)def{12}fh]]";
//        String input = "a[bc(d)def{12}fh]]";
//        String input = "[a[bc(d)def{12}fh]]";
        String input = "[a[bc(d)def{12}fh]";
        
        int errIndex = validate(input);

        if(errIndex < 0) {
            System.out.println("Brackets OK");
        } else {
            System.out.println("Wrong bracket at " + (errIndex+1) + " position");
        }

	}

    public static int validate(String input){

        HashMap<Character, Integer> open = new HashMap<Character, Integer>() {{
            put('{', 0);
            put('[', 1);
            put('(', 2);
        }};
        HashMap<Character, Integer> close = new HashMap<Character, Integer>() {{
            put('}', 0);
            put(']', 1);
            put(')', 2);
        }};
        
        Deque<Character> stack = new ArrayDeque<>();
        char[] chars = input.toCharArray();
        int errIndex = -1;
        
        for (int i = 0; i < chars.length; i++) {
        	
            char character = chars[i];
            if (open.containsKey(character)) {
                stack.push(character);
            } 
            else if (close.containsKey(character)) {
                if (stack.isEmpty()) {
                	errIndex = i;
                	break;
                }
                
                Integer closeBracketNumber = close.get(character);
                Character firstInStack = stack.pop();
                if (!open.containsKey(firstInStack)) {
                	errIndex = i;
                	break;
                }
                Integer firstInStackNumber = open.get(firstInStack);
                
                if(closeBracketNumber != firstInStackNumber) {
                	errIndex = i;
                	break;
                }
            }
            
        }

        return errIndex < 0? stack.isEmpty()? -1 : chars.length : errIndex;
    }

}
