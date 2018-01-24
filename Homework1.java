
package Homework1;

import java.util.Stack;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;
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

    public class Homework1 extends JPanel
      implements TreeSelectionListener {
            private JEditorPane htmlPane;
            private JTree tree;
            private static Node root;
            private static String get;
           private static boolean useleaf = false;

        private static boolean useSystemLookAndFeel = false;

            public Homework1() {
                super(new GridLayout(1,0));
                            //Create the nodes.
                DefaultMutableTreeNode top = new DefaultMutableTreeNode(root);
                    CreateNodes(top,root);

                    //Create a tree that allows one selection at a time.
                     tree = new JTree(top);
                    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

                            //Listen for when the selection changes.
                tree.addTreeSelectionListener(this);

                           if (playWithLineStyle) {
                            System.out.println("line style = " + lineStyle);
                           tree.putClientProperty("JTree.lineStyle", lineStyle);
                        }

                            //Create the scroll pane and add the tree to it.
                                    JScrollPane treeView = new JScrollPane(tree);

                            //Create the HTML viewing pane.
                                    htmlPane = new JEditorPane();
                    htmlPane.setEditable(false);
                    JScrollPane htmlView = new JScrollPane(htmlPane);

                            //Add the scroll panes to a split pane.
                                   JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
                    splitPane.setTopComponent(treeView);
                    splitPane.setBottomComponent(htmlView);

                            Dimension minimumSize = new Dimension(100, 50);
                    htmlView.setMinimumSize(minimumSize);
                    treeView.setMinimumSize(minimumSize);
                    splitPane.setDividerLocation(100);
                    splitPane.setPreferredSize(new Dimension(500, 300));

                           //Add the split pane to this panel.
                add(splitPane);

                           ImageIcon leafIcon = createImageIcon("middle.gif");
                    if (leafIcon != null) {
                            DefaultTreeCellRenderer renderer =
                                           new DefaultTreeCellRenderer();
                           renderer.setClosedIcon(leafIcon);
                            renderer.setOpenIcon(leafIcon);
                            tree.setCellRenderer(renderer);
                        }
               }
    protected static ImageIcon createImageIcon(String path) {
                   java.net.URL imgURL = Homework1.class.getResource(path);
                    if (imgURL != null) {
                            return new ImageIcon(imgURL);
                        } else {
                            System.err.println("Couldn't find file: " + path);
                            return null;
                        }
               }
   /** Required by TreeSelectionListener interface. */
            public void valueChanged(TreeSelectionEvent e) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                                   tree.getLastSelectedPathComponent();

                           if (node == null) return;
                    useleaf=node.isLeaf();
                    Object nodeInfo = node.getUserObject();
                   DisplayNode((Node)nodeInfo);

                        }


            public static String number;

            public static void main(String[] args) {


                                    number = args[0];
            //        number = "251-*32*+";
                            char[] cha = number.toCharArray();

                             root = Stack(cha);
                     get = infix(root);
                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               createAndShowGUI(); }
             });

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

    public static boolean is_number(char letter) {

                if (letter == '1' || letter == '2' || letter == '3' || letter == '4' || letter == '5' || letter == '6' || letter == '7' || letter == '8' || letter == '9' || letter == '0') {

                    return true;
                   }
                return false;
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

	private static void createAndShowGUI() {
		        if (useSystemLookAndFeel) {
			           try {
				               UIManager.setLookAndFeel(
		                       UIManager.getSystemLookAndFeelClassName());
				            } catch (Exception e) {
				                System.err.println("Couldn't use system look and feel.");
				           }
			       }

				       //Create and set up the window.
						       JFrame frame = new JFrame("Jtree");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				        //Add content to the window.
						        frame.add(new Homework1());

				        //Display the window.
						        frame.pack();
		        frame.setVisible(true);
		    }




   public static Node Stack(char cha[]) {

                Stack<Node> data = new Stack();
               Node node, num1, num2;

                for (int i = 0; i < cha.length; i++) {

                    if (!is_operater(cha[i])) {

                        node = new Node(cha[i]);
                        data.push(node);

                    } else {

                        node = new Node(cha[i]);
                        num2 = data.pop();
                        num1 = data.pop();
                        node.left = num1;
                        node.right = num2;

                        data.push(node);
                    }
                }
                node = data.peek();
                return node;
            }

