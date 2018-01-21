
package Homework1;

import java.util.Stack;



public class Homework1 {

	public static String inPut;

	public static node infix(node tree) {
		Stack a = new Stack();
		for(int i=0;i<inPut.length();i++)
		{
			node x = new node(inPut.charAt(i));
			if(a.empty()||(x.cha!='+'&&x.cha!='-'&&x.cha!='*'&&x.cha!='/'))
			{
				a.push(x);
			}
			else
			{
				node n1 =  (node) a.pop();
				node n2 =  (node) a.pop();
				x.left = n2;
				x.right = n1;
				n1.parent = x;
				n2.parent = x;
				a.push(x);
			}
		}
		tree = (node) a.pop();
		return tree;

	}

	public static void inorder(node n){
		if(n==null){
			return;
		}
		else{
			inorder(n.left);
			if(n.parent == null||n.parent.parent==null)
			{
			}
			else if(n==n.parent.left)
			{
				System.out.print("(");
			}

			System.out.print(n.cha);
			inorder(n.right);

			if(n.parent == null||n.parent.parent==null)
			{
			}
			else if(n==n.parent.right)
			{
				System.out.print(")");
			}
		}
	}
	public static int calculate(node n){
		int summ = 0;
		if((n.right.cha=='+'||n.right.cha=='-'||n.right.cha=='*'||n.right.cha=='/')
				&&(n.left.cha=='+'||n.left.cha=='-'||n.left.cha=='*'||n.left.cha=='/'))
		{
			summ +=calculate(n.right);
			summ +=calculate(n.left);
		}
		else if(n.right.cha=='+'||n.right.cha=='-'||n.right.cha=='*'||n.right.cha=='/')
		{
			summ +=calculate(n.right);
			switch(n.cha){
				case '+':
					summ = Character.getNumericValue(n.left.cha)+summ;
					break;
				case '-':
					summ = Character.getNumericValue(n.left.cha)-summ;
					break;
				case '*':
					summ = Character.getNumericValue(n.left.cha)*summ;
					break;
				case '/':
					summ = Character.getNumericValue(n.left.cha)/summ;
					break;
			}
		}
		else if(n.left.cha=='+'||n.left.cha=='-'||n.left.cha=='*'||n.left.cha=='/')
		{
			summ +=calculate(n.left);
			switch(n.cha){
				case '+':
					summ = summ+Character.getNumericValue(n.right.cha);
					break;
				case '-':
					summ = summ-Character.getNumericValue(n.right.cha);
					break;
				case '*':
					summ = summ*Character.getNumericValue(n.right.cha);
					break;
				case '/':
					summ = summ/Character.getNumericValue(n.right.cha);
					break;
			}
		}
		else
		{
			switch(n.cha){
				case '+':
					summ = Character.getNumericValue(n.left.cha)+Character.getNumericValue(n.right.cha);
					break;
				case '-':
					summ = Character.getNumericValue(n.left.cha)-Character.getNumericValue(n.right.cha);
					break;
				case '*':
					summ = Character.getNumericValue(n.left.cha)*Character.getNumericValue(n.right.cha);
					break;
				case '/':
					summ = Character.getNumericValue(n.left.cha)/Character.getNumericValue(n.right.cha);
					break;
			}
		}
		return summ;
	}

	public static void main(String[] args) {

		String in ="251-*32*+";
		inPut = in;
		//System.out.println(in.length());
		node tree = new node();
		tree = infix(tree);
		inorder(tree);
		int ans = calculate(tree);
		System.out.print("=" + ans);
	}
}
