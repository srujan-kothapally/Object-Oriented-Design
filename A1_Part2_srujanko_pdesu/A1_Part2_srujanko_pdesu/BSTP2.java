
public class BSTP2 {
	//CSE 522: Assignment 1, Part 2
		  public static void main(String[] args) {
			AbsTree tr = new DupTree(100);//Creating node for DupTree
		    AbsTree tre = new Tree(100);//Creating node for Tree
		    //inserting values DupTree
			tr.insert(50);
			tr.insert(125);
			tr.insert(150);
			tr.insert(20);
			tr.insert(75);
			tr.insert(20);
			tr.insert(90);
			tr.insert(50);
			tr.insert(125);
			tr.insert(150);
			tr.insert(75);
			tr.insert(90);
			//inserting values for Tree
			tre.insert(50);
			tre.insert(125);
			tre.insert(150);
			tre.insert(20);
			tre.insert(75);
			tre.insert(20);
			tre.insert(90);
			tre.insert(50);
			tre.insert(125);
			tre.insert(150);
			tre.insert(75);
			tre.insert(90);
			//Deleting values from DupTree
			tr.delete(20);
			tr.delete(20);
			tr.delete(20);
			tr.delete(150);
			tr.delete(100);
			tr.delete(150);
			tr.delete(125);
			tr.delete(125);
			tr.delete(50);
			tr.delete(50);
			tr.delete(50);
			tr.delete(75);
			tr.delete(90);
			tr.delete(75);
			tr.delete(90);
			//Deleting values from Tree
			tre.delete(20);
			tre.delete(20);
			tre.delete(20);
			tre.delete(150);
			tre.delete(100);
			tre.delete(150);
			tre.delete(125);
			tre.delete(125);
			tre.delete(50);
			tre.delete(50);
			tre.delete(50);
			tre.delete(75);
			tre.delete(90);
			tre.delete(75);
			tre.delete(90);
			}
	  }
	  
	  abstract class AbsTree {

		public AbsTree(int n) {
			value = n;
			left = null;
			right = null;
			parent = null;
		}

		public void insert(int n) {
			if (value == n)
				count_duplicates();
			else if (value < n)
				if (right == null) {
					right = add_node(n);
			        right.parent = this;
				}else
					right.insert(n);
			else if (left == null) {
				left = add_node(n);
			    left.parent = this;
			}else
				left.insert(n);
		}
		
		public void delete(int n) { 
			//
			// *** do not modify this method ***
			//
			AbsTree t = find(n);
			if (t == null) { // n is not in the tree
				System.out.println("Unable to delete " + n + " -- not in the tree!");
				return;
			} else if (t.left == null && t.right == null) { // n is at a leaf position
				if (t != this) // if t is not the root of the tree
					case1(t);
				else
					System.out.println("Unable to delete " + n + " -- tree will become empty!");
				return;
			} else if (t.left == null || t.right == null) {
				// t has one subtree only
				if (t != this) { // if t is not the root of the tree
					case2(t);
					return;
				} else { // t is the root of the tree with one subtree
					if (t.left != null)
						case3L(t);
					else
						case3R(t);
					return;
				}
			} else 
				// t has two subtrees; replace n with the smallest value in t's right subtree
				case3R(t);
		}
		
		
		
		protected void case1(AbsTree t) {
			if (t instanceof DupTree) {
				DupTree d;
				d = (DupTree)t;
				if(t!= null && d.count>1) {
					d.count--;
					return;
					}
			}
		
			 AbsTree p = t.parent;
			 if(p.value>t.value) {
			 p.left =null;
			 t.parent =null;
			 }else if(p.value<t.value){
				 p.right = null;
				 t.parent=null;
			 }
			// remove leaf node t;
		}
		
		
		protected void case2(AbsTree t) { 
			if (t instanceof DupTree) {
				DupTree d;
				d = (DupTree)t;
				if(t!= null && d.count>1) {
					d.count--;
					return;
					}
			} 
			AbsTree p = t.parent;  
			if (t.left == null) { 
			    t.right.parent = t.parent;
			    if(p.value>t.value) 
			   	 p.left =t.right;
		
			    else if(p.value<t.value) 
			    	p.right = t.right;
			    t.parent = null;
			    t.right = null;
			    return;
			}else if(t.right == null) {
				 t.left.parent = t.parent;
				    if(p.value>t.value) 
				   	 p.left =t.left;
				    else if(p.value<t.value) 
				    	p.right = t.left;    
				    t.parent = null;
				    t.left = null;
				    return;
			}     
		// remove internal node t;
		}
		
		
		protected void case3L(AbsTree t) {
			if (t instanceof DupTree) {
				DupTree d;
				d = (DupTree)t;
				if(t!= null && d.count>1) {
					d.count--;
					return;}
					}
			AbsTree m = max(t.left);
			int f = m.value;
			if (t instanceof DupTree) {
				DupTree u;
				DupTree s;
				s = (DupTree)m;
				u = (DupTree)t;
				u.count = s.count;
				s.count = 1;
				delete(m.value);
				t.value = f;
				return;
			}else {
				delete(m.value);
				t.value = f;
				return;

				}
				
			// replace t.value with the smallest value, f, in
			// t's right subtree; then delete value f from tree;
			}
		

		protected void case3R(AbsTree t) { 
			if (t instanceof DupTree) {
				DupTree d;
				d = (DupTree)t;
				if(t!= null && d.count>1) {
					d.count--;
					return;
					}
			}
			AbsTree m = min(t.right);
			int f = m.value;
		if (t instanceof DupTree) {
			DupTree u;
			DupTree s;
			s = (DupTree)m;
			u = (DupTree)t;
			u.count = s.count;
			s.count = 1;
			delete(m.value);
			t.value = f;
			return;
		}else {
			delete(m.value);
			t.value = f;
			return;

			}
			
		// replace t.value with the smallest value, f, in
		// t's right subtree; then delete value f from tree;
		}

		private AbsTree find(int n) {
			if(value == n)
			    return this;
			if(n>this.value && this.right!=null)
			    return right.find(n);
			if(n<this.value && this.left!=null)
				return left.find(n);
			return null;
			
		
			
		
			// returns the Tree node with value n;
			// returns null if n is not present in the tree;
		}
		
		public AbsTree min(AbsTree tr) {
			if(tr.left==null) {
				return tr;
			}
			else
			  return min(tr.left);
				
			
			// returns the Tree node with the minimum value;
		}

		public AbsTree max(AbsTree tr) {
			if(tr.right==null) {
				return tr;
			}
			else
				return max(tr.right);
			// returns the Tree node with the maximum value;
		}

		protected int value;
		protected AbsTree left;
		protected AbsTree right;
		protected AbsTree parent;

		// Protected Abstract Methods
		
		protected abstract AbsTree add_node(int n);
		protected abstract void count_duplicates();

		// Additional protected abstract methods, as needed
	}


	  class Tree extends AbsTree {

		public Tree(int n) {
			super(n);
		}

		protected AbsTree add_node(int n) {
			return new Tree(n);
		}

		protected void count_duplicates() {
			
		}
		
		// define additional protected methods here, as needed
	    }

	class DupTree extends AbsTree {

		public DupTree(int n) {
			super(n);
			count = 1;
		};

		protected AbsTree add_node(int n) {
			return new DupTree(n);
		}

		protected void count_duplicates() {
			count++;
		}

		// define additional protected methods here, as needed

		protected int count;
	}



