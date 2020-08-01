package nsgl.string;

import nsgl.generic.array.Vector;
import nsgl.generic.collection.Indexed;

public class Template {
	/**
	 * Obtains a String from a template by replacing the set of tags with their associated values. A tag is limited both sides by a character <i>c</i>. 
	 * For example, if <i>str='lorem ·X· dolor ·haha· amet'</i>, <i>c='·'</i> and <i>dictionary={'X':'ipsum', 'haha':'sit' }
	 * then this method will return the string <i>lorem ipsum dolor sit amet'</i>
	 * @param str Template used for generating the String
	 * @param dictionary Set of pairs <i>(TAG,value)</i> used for replacing each <i>TAG</> by its corresponding <i>value</i>
	 * @param c Enclosing tag character
	 * @return A String from a template by replacing the set of tags with their associated values. 
	 */
	public static String get(String str, Indexed<String,String> dictionary, char c){
		String[] x = str.split(""+c);
		int state = 0;
		String res = "";
	  	String tag = "";
		for( int i = 0; i<x.length; i++ ){
			switch( state ){
				case  0:
					res += x[i];
					state = 1;
				break;    
				case 1:
					if( x[i].length() > 0 ){
						tag = x[i];
						state = 2;
					}else{
						res += c;
						state = 0;
					}
				break;
			    	case 2:
				    	if( x[i].length() > 0 || i==x.length-1 ){
						res += ((dictionary.get(tag)!=null)?dictionary.get(tag):tag) + x[i];
						state = 1;
					}else{
			    			tag += c;
						state = 3;
					}                
				break;
				case 3:
					if( x[i].length() > 0 ) tag += x[i];
						state = 2;
			}
		}
		return res;
	}
	
	/**
	 * Obtains the set of tags defined in a String template. A tag is limited both sides by a character <i>c</i>. For example, if <i>str='lorem·X·ipsum·haha· quia'</i>
	 * and <i>c='·'</i> then this method will return the array of tags <i>['X', 'haha']</i>
	 * @param str Template used for generating the String
	 * @param c Enclosing tag character
	 * @return A dictionary, set of pairs <i>(TAG,value)</i>, containing each <i>TAG</> in the template
	 */
	String[] tags(String str, char c){
		Vector<String> array = new Vector<String>();
		String[] x = str.split(""+c);
		int state = 0;
	  	String tag = "";
		for( int i = 0; i<x.length; i++ ){
			switch( state ){
				case  0:
					state = 1;
				break;    
				case 1:
					if( x[i].length() > 0 ){
						tag = x[i];
						state = 2;
					}else{ state = 0; }
				break;
			    	case 2:
				    	if( x[i].length() > 0 || i==x.length-1 ){
						array.add(tag);
						state = 1;
					}else{
			    			tag += c;
						state = 3;
					}                
				break;
				case 3:
					if( x[i].length() > 0 ) tag += x[i];
					state = 2;
			}
		}
		String[] a = new String[array.size()];
		for( int i=0; i<a.length; i++) a[i] = array.get(i);
		return a;
	}	
}