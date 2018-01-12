package SPLT_A4;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node par; //parent...not necessarily required, but can be useful in splay tree
  boolean justMade; //could be helpful if you change some of the return types on your BST_Node insert.
            //I personally use it to indicate to my SPLT insert whether or not we increment size.
  
  BST_Node(String data){ 
    this.data=data;
    this.justMade=true;
  }
  
  BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
    this.data=data;
    this.left=left;
    this.right=right;
    this.par=par;
    this.justMade=true;
  }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- Some example methods that could be helpful ------------------------------------------
  //
  // add the meat of correct implementation logic to them if you wish

  // you MAY change the signatures if you wish...names too (we will not grade on delegation for this assignment)
  // make them take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  /*
  public BST_Node containsNode(String s){ return false; } //note: I personally find it easiest to make this return a Node,(that being the node splayed to root), you are however free to do what you wish.
  public BST_Node insertNode(String s){ return false; } //Really same logic as above note
  public boolean removeNode(String s){ return false; } //I personal do not use the removeNode internal method in my impl since it is rather easily done in SPLT, feel free to try to delegate this out, however we do not "remove" like we do in BST
  public BST_Node findMin(){ return left; } 
  public BST_Node findMax(){ return right; }
  public int getHeight(){ return 0; }
  


  
  //you could have this return or take in whatever you want..so long as it will do the job internally. As a caller of SPLT functions, I should really have no idea if you are "splaying or not"
                        //I of course, will be checking with tests and by eye to make sure you are indeed splaying
                        //Pro tip: Making individual methods for rotateLeft and rotateRight might be a good idea!
  */
//  private BST_Node getPar(){
//	if (this.par == null){
//		return null;
//	}
//	else {
//		return this.par;
//	}  
//  }
  public BST_Node splay(BST_Node toSplay) {
  //test to see if it's a particular type of transformation
	//run the method associated with that condition
	 
	  if(toSplay.par == null){
			return toSplay;
		}
	  
	  BST_Node parent = toSplay.par;
	  if(parent.par == null){ //check grandparent
	  if(isRotateLeft(toSplay, parent) == true){ //check conditions for rotate left
		  rotateLeft(toSplay);
		  parent.par = toSplay;
		  toSplay.par = null;
		  return toSplay;
	  }
	  if(isRotateRight(toSplay, parent) == true){ //check conditions for rotate right
		  rotateRight(toSplay);
		  parent.par = toSplay;
		  toSplay.par = null;
		  return toSplay;
	  }
	  }
	  BST_Node g = parent.par;
	 
	  if(g.left == parent && parent.left == toSplay){
		  System.out.println("zigzigright");
//		  BST_Node b = toSplay.right;
//		  BST_Node c = parent.right;
//		  toSplay.right = parent;
//		  parent.par = toSplay;
//		  parent.right = g;
//		  parent.left = b;
//		  
//		  if(b.data!= null){
//			  b.par = parent;
//		  }
//		  
//		  g.par = parent;
//		  g.left = c;
//		  if(c.data!=null){
//			  c.par = g;
//		  }
//		  return toSplay;		  
		  zigigRight(g, parent, toSplay); //zigzigright
	  }
	  else if(g.right == parent && parent.right == toSplay){ //zigzigleft
		  System.out.println("zigzigleft");
		  zigigLeft(g, parent, toSplay);
	  }
	  else if(parent.right == toSplay && g.left == parent){//rightleftzigzag
		  System.out.println("rightleftzigzag");
		  zigZagLeft(g, parent, toSplay);	 
	  }
	  else if(parent.left ==toSplay && g.right == parent){//leftrightzigzag
		  System.out.println("leftrightzigzag");
		  zigZagRight(g, parent, toSplay);
	  }
	 
	return toSplay;
  }
  
  
  
private BST_Node zigZagRight(BST_Node g, BST_Node parent, BST_Node toSplay) {

	BST_Node greatG = g.par;
	rotateRight(toSplay);
	toSplay.par = g;
	g.right = toSplay;
	parent.par = toSplay;
	rotateLeft(toSplay);
	g.par = toSplay;
	
	if(greatG != null){
		if(greatG.left == g){
			greatG.left = toSplay;
			toSplay.par = greatG;
		} else if(greatG.right == g){
			greatG.right = toSplay;
			toSplay.par = greatG;
			}
		}
	else if(greatG == null){
		toSplay.par = null;
	}
	return splay(toSplay);
}

private BST_Node zigZagLeft(BST_Node g, BST_Node parent, BST_Node toSplay) {
	BST_Node greatG = g.par;
	rotateLeft(toSplay);
	toSplay.par = g;
	g.left = toSplay;
	parent.par = toSplay;
	rotateRight(toSplay);
	g.par = toSplay;
		 if (greatG != null && greatG.right == g){
			greatG.right = toSplay;
			toSplay.par = greatG;
		} else if(greatG != null&& greatG.left ==g){
			greatG.left = toSplay;
			toSplay.par = greatG;
		} else if(greatG == null){
			toSplay.par = null;
		}
		return splay(toSplay);
}

private BST_Node zigigLeft(BST_Node g, BST_Node parent, BST_Node toSplay) {
	
	BST_Node greatG = g.par;
	  rotateLeft(parent);
	rotateLeft(toSplay);
	  g.par = parent;
	  parent.par = toSplay;
	  if(greatG != null && greatG.left == g){
		  greatG.left = toSplay;
		  toSplay.par = greatG;
	  }
	  
	  else if(greatG != null&& greatG.right == g){
		  greatG.right = toSplay;
		  toSplay.par  = greatG;
	  } else if (greatG == null){
		  toSplay.par = null;
	  }
	  return splay(toSplay);
}

private BST_Node zigigRight(BST_Node g, BST_Node parent, BST_Node toSplay) {

	 BST_Node greatG = g.par;
	  rotateRight(parent);
	  rotateRight(toSplay);
	  g.par = parent;
	  parent.par = toSplay;
	  
	  
	  if(greatG != null && greatG.left == g){
		  greatG.left = toSplay;
		  toSplay.par = greatG;
	  }else if(greatG != null&& greatG.right == g){
		  greatG.right = toSplay;
		  toSplay.par  = greatG;
	  } else if (greatG == null){
		  toSplay.par = null;
	  }
	  return splay(toSplay);
}

private Boolean isRotateLeft(BST_Node toSplay, BST_Node parent) {

		if(parent.par == null){
			if(parent.right == toSplay){
				return true;
			}
			return false;
		}
		return false; // shouldn't reach
		}
private boolean isRotateRight(BST_Node toSplay, BST_Node parent) {
		if(parent.par == null){
			if(parent.left == toSplay){
				return true;
			}
			return false;
		}
	return false;
}


private void rotateLeft(BST_Node toSplay) {
	// TODO Auto-generated method stub
	BST_Node parent = toSplay.par;
	parent.right = toSplay.left;
		if(toSplay.left != null){
			toSplay.left.par = parent;
		}
		toSplay.left = parent;

				parent.par = toSplay;

			return;
}
private void rotateRight(BST_Node toSplay) {
	BST_Node parent = toSplay.par;
	parent.left = toSplay.right;
	if(toSplay.right != null){
		toSplay.right.par = parent;
	}
	toSplay.right = parent;
		parent.par = toSplay;
	return;
}




//  public void splay(BST_Node toSplay) {
//	 //cases: no parents, no grandparents
//	  //grandparent cases: R-r, l-l, r-l, l-r
//	  
////	  x has no grandparent (zig)
////	  If x is left child of root y, then rotate (xy)R.
////	  Else if x is right child of root y, then rotate (yx)L.
//	  
//	  
////	  x is LL or RR grandchild (zig-zig)
////	  If x is left child of y, and y is left child of z, 
////	  then rotate at grandfather (yz)R and then rotate at father (xy)R.
////	  Else if x is right child of y, and y is right child of z, 
////	  then rotate at grandfather (yz)L and then rotate at father (xy)L.
//	  
////	  If x has not become the root, then continue splaying at x.
//
//	  
//	  //	  x is LR or RL grandchild (zig-zag)  
////	  If x is right child of y, and y is left child of z, 
////	  then rotate at father (yx)L and then rotate at grandfather (xz)R.
////	  Else if x is left child of y, and y is right child of z, 
////	  then rotate at father (yx)R and then rotate at grandfather (xz)L.
////	  If x has not become the root, then continue splaying at x.
//	  System.out.println("splaying");
//	 return;
//	  if(toSplay.par == null){ 
//		  System.out.println("no parent");// no parent
//		  return;
//	  }
	  //cases for rotation
	  
	  // no grandparent
//	  if(toSplay.par!=null &&toSplay.par.par==null){
//		  if(toSplay.par.right==toSplay){
//			  //left rotation
//			  leftRotate(toSplay);
//		  }
//		  if(toSplay.par.left==toSplay){
//			  rightRotate(toSplay);
//		  }
//	  }
////	 
//	//LL
//	  if(toSplay.par!=null &&toSplay.par.par!=null){	
//		  if(toSplay.par.left == toSplay && toSplay.par.par.left == toSplay.par){	
//			  //if toSplay has a right child it becomes parents left child
//			  if(toSplay.right!=null){
//				  	toSplay.par.left = toSplay.right;
//				 toSplay.par.left.par = toSplay.par;
//			  	}
//			  //toSplays right becomes it's parent
//			  		toSplay.right = toSplay.par;
//			  		toSplay.par = toSplay.par.par;
//			  		toSplay.par.par.left = toSplay;
//			  		splay(toSplay);
//		  }
//		  //RR
//		else if(toSplay.par.right==toSplay && toSplay.par.par.right == toSplay.par){
//			  if(toSplay.left!=null){
//				  toSplay.par.right = toSplay.left;
//				  toSplay.par.right.par = toSplay.par;
//			  }
//			  toSplay.left = toSplay.par;
//			  toSplay.par = toSplay.par.par;
//			  toSplay.par.par.right = toSplay;
//			  splay(toSplay);
//		}
//		//LR
//		else if(toSplay.par.right == toSplay &&toSplay.par.par.left == toSplay.par){
//			if(toSplay.left!=null){
//				toSplay.par.right = toSplay.left;
//			}
////			if(toSplay.right!=null){
////				toSplay.par.left = toSplay.right;
////			}
//			toSplay.left = toSplay.par;
//			toSplay.par = toSplay.par.par;
//			toSplay.par.par.left = toSplay;
////			toSplay.right = toSplay.par.par;
//			splay(toSplay);
//		}
//		  //RL
//		else if(toSplay.par.left == toSplay && toSplay.par.par.right == toSplay.par){
//			if(toSplay.right!=null){
//				toSplay.par.left = toSplay.right;
//			}
////			if(toSplay.left!=null){
////				toSplay.par.right = toSplay.left;
////			}
//			toSplay.right = toSplay.par;
//			toSplay.par = toSplay.par.par;
//			toSplay.par.par.right = toSplay;
//			splay(toSplay);
//			
//		}
//		  
////	  } 
//  }
	
	
//	private void splyLeft(BST_Node toSplay) {
//	// TODO Auto-generated method stub
//	
//}

//	private void splyRight(BST_Node toSplay) {
//	// TODO Auto-generated method stub
//		System.out.println("splay right");
//	BST_Node parent = toSplay.par;
//	BST_Node tempRightChild = toSplay.left;
//	
//	
//	if(parent.par.right != null){
//		parent.par.right = toSplay;
//		toSplay.left = parent;
//		toSplay.par = parent.par;
//		parent.par = toSplay;
//		parent.right = tempRightChild;
//	}
//	else{
//		toSplay.par = null;
//		parent.par = toSplay;
//		parent.right = tempRightChild;
//		toSplay.left = parent;
//	}
//	
//}

	public BST_Node containsNode(String s){ ////note: I personally find it easiest to make this return a Node,(that being the node splayed to root), you are however free to do what you wish.
		if(data.equals(s)){
			//splay(this);
			return this;
		}
		if(data.compareTo(s)>0){ //s lexiconically less than data
			if(left==null){
				//splay(this);
				return this;
			}
			return left.containsNode(s);
		}
		if(data.compareTo(s)<0){ // s is > than data
			if(right==null){
				//splay(this);
				return this;
			}
			return right.containsNode(s);
		}
		return null; //shouldn't hit
	}
	public BST_Node insertNode(String s){
//		Insert BST style, then splay the new node to root
//		Each insert causes new root
//		if(data.compareTo(s) == 0){
//			splay(this);
//			return false;
//		}
		
		if(s.compareTo(data) < 0){ // s is smaller
			if(left==null){

				left=new BST_Node(s);
				left.par = this;
				BST_Node tempLeft = left;
				splay(left);
				return tempLeft;
			} 
			return left.insertNode(s);
		}
//		if(data.compareTo(s)<0){
		else if(s.compareTo(data)>0){
			if(right==null){  // s is greater
				right=new BST_Node(s);
				right.par = this;
				BST_Node tempRight = right;
				splay(right);
				return tempRight;
			}
			return right.insertNode(s);
		}
		splay(this);
		return this;
		//ie we have a duplicate
	}
	public boolean removeNode(String s){ //DIO
//		We do this in several steps.
//		do a "contains" on the node to be removed; this will splay it to the root if it is in the tree; if it is not in the tree, the "contains" splays the last accessed node to the root, and we are done... no node is deleted.
		//		unhook the root node, leaving you with two subtrees: the left child L, and the right child R
//		do a "findMax" on L; this will splay the max node to the root, and will also leave that new root of L with no right child subtree (since the value there is larger than all the others in L).
//		make R the right child subtree of L.
//		pro tip: If you choose to use the delegated example of BST we provide, note that remove is much easier than last time and doesn't really get much out of delegating. You need not delegate on this method (or at all), to get the desired results.

		if(data==null){
			return false;	
		}
		return true;	
	}
	
		
//		if(data.equals(s)){
//			if(left!=null){
//				data=left.findMax().data;
//				left.removeNode(data);
//				if(left.data==null)left=null;
//			}
//			else if(right!=null){
//				data=right.findMin().data;
//				right.removeNode(data);
//				if(right.data==null)right=null;
//			}
//			else data=null;
//			return true;
//		}
//		else if(data.compareTo(s)>0){
//			if(left==null)return false;
//			if(!left.removeNode(s))return false;
//			if(left.data==null)left=null;
//			return true;
//		}
//		else if(data.compareTo(s)<0){
//			if(right==null)return false;
//			if(!right.removeNode(s))return false;
//			if(right.data==null)right=null;
//			return true;
//		}
//		return false;
//	}
	public BST_Node findMin(){
		if(left!=null){
			return left.findMin();
		}
		else{
			splay(this);
			return this;
		}
	}
	
	public BST_Node findMax(){
		if(right!=null){
			return right.findMax();
		}
		else{
			splay(this);
			return this;
		}
	}
	
	
	public int getHeight(){
		int l=0;
		int r=0;
		if(left!=null){
			l+=left.getHeight()+1;
		}
		if(right!=null){r+=right.getHeight()+1;
		}
		return Integer.max(l, r);
//		

	}
	public String toString(){
		return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")+",Right: "+((this.right!=null)?right.data:"null");
	}

  // --- end example methods --------------------------------------

  
  

  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  
}