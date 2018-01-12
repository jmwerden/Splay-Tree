package SPLT_A4;

public class SPLT implements SPLT_Interface{
  private BST_Node root;
  private int size;
  
  public SPLT() {
    this.size = 0;
  } 
  
  public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
    return root;
  }

@Override
public void insert(String s) {
//	If node is found, splay it to root
//	if(root.containsNode(s) == true){
//		return;
//	}
	if (empty()) {
		root = new BST_Node(s);
		size += 1;
		return;
	} else {
		
		BST_Node temp = root.containsNode(s);
		if(temp.data.compareTo(s)==0){
			root.splay(temp);
			root = temp;
			return;
		}
		
		size += 1;
//		System.out.println(size);
		root = root.insertNode(s);
		return;
	}
}

@Override
public void remove(String s) {
//	remove: We do this in several steps.
//	do a "contains" on the node to be removed; this will splay it to the root if it is in the tree; if it is not in the tree, the "contains" splays the last accessed node to the root, and we are done... no node is deleted.
	this.contains(s);
	//	unhook the root node, leaving you with two subtrees: the left child L, and the right child R
//	BST_Node leftTemp;
//	leftTemp = root.left;
//	BST_Node rightTemp;
//	rightTemp = root.right;
//	
	
	
	root = root.containsNode(s);
	
	
	BST_Node left = root.left;
	BST_Node right = root.right;
	if(root.left != null && root.right != null){
		
		left = root.left;
		right = root.right;
		
		root.left.par = null;
		root.left = null;
		root.right.par = null;
		root.right = null;
		left = left.findMax();
		left.right = right;
		right.par = left;
		root = left;
		
	}else if(root.left == null && root.right == null){
		root = null;
		
	}else if(root.left != null && root.right == null){
		left = root.left;
		root.left.par = null;
		root.left = null;
		left = left.findMax();
		root = left;
	} else if(root.left == null && root.right!= null){
		 right = root.right;
		root.right.par = null;
		root.right = null;
		right = right.findMin();
		root = right;
	}
//	root.left.par = null;
//	root.right.par = null;
//	root.left = null;
//	root.right = null;
//	root=null;
	
	//	do a "findMax" on L; this will splay the max node to the root, and will also leave that new root of L with no right child subtree (since the value there is larger than all the others in L).
//	leftTemp.findMax();

	//	make R the right child subtree of L.
//	leftTemp.right = rightTemp.par;
//	pro tip: If you choose to use the delegated example of BST we provide, note that remove is much easier than last time and doesn't really get much out of delegating. You need not delegate on this method (or at all), to get the desired results.
		
		
//	remove (alternate)
//	Splay node to be removed to root
//	Remove the root, leaving two disconnected subtrees
//	“join” these 2 trees
//size--;
//root.removeNode(s);
//
//	{  //DIO
//		// TODO Auto-generated method stub
//		if (!contains(s)) {
//			System.out.println("does not contain");
//			return;
//		} else {
//			if (root.data.equals(s) && size == 1) {
//				root = null;
//				size -= 1;
//				return;
//			} else {
//				size -= 1;
//				root.removeNode(s);
//			}
//		}
//	}
//
//	
}

@Override
public String findMin() {
	// TODO Auto-generated method stub
//	Node will be found, splay it to root
// when left child is null; splay that node;
	return root.findMin().data;
	
}

@Override
public String findMax() {
	// TODO Auto-generated method stub
	return root.findMax().data;
}

@Override
public boolean empty() {
	// TODO Auto-generated method stub
	if(root==null){
		return true;
	}
	else { return false;
	
	}
}

@Override
public boolean contains(String s) {
	// TODO Auto-generated method stub
	// if node is found splay it to root
	if(root == null){
		return false;
	}
	else{ 
		BST_Node temp = root.containsNode(s);
		if( temp.data.compareTo(s)==0){
			root.splay(temp);
			root = temp;
			return true;
		}
		else{
			root.splay(temp);
			root = temp;
			return false;
		}
	}
}

@Override
public int size() {
	// TODO Auto-generated method stub
	return size;
}

@Override
public int height() {
	// TODO Auto-generated method stub
	if(this.root == null){
		return -1;
	}

	else { return this.root.getHeight();
	}	
		}
			}
