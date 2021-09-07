package test01;

public class lock {
	int [][] key;
	int [][] lock;
	int [][] newlock;
	
	public lock(int[][] key, int[][]lock){
		this.key=key;
		this.lock=lock;
	}
	
	//key90회전 후 리턴
	public int[][] rotateKey(int[][] key){
		int[][] resultArr=new int[key.length][key.length];
		int keysize = key.length-1;
		
		for(int i=0; i<=keysize; i++) {
			for(int j=0; j<=keysize; j++) {
				resultArr[i][j]=key[keysize-j][i];
			}
		}
		return resultArr;
	}
	
	//lock 3배크기로 조정 후, 센터에 lock 저장 후 리턴
	public void makeNewLock(int[][] lock){
		int locksize= lock.length;
		int newsize = locksize*3;
		newlock= new int[newsize][newsize];
		
		int posi=0;
		for(int i=locksize; i<locksize*2;i++) {
			int posj = 0;
			for(int j=locksize; j<locksize*2; j++) {
				newlock[i][j]=lock[posi][posj];
				posj++;
			}
			posi++;
		}
	}
	
	public boolean checkLock(int[][]nlock) {
		boolean ret= true;
		
		for(int i=3; i<key.length*2; i++) {
			for(int j=3; j<key.length*2; j++) {
				if(nlock[i][j]==0 || nlock[i][j]==2) {
					return false;
				}
			}
		}
		return ret;
	}
	
	public boolean solution() {
		boolean ret =false;	
		int cnt=0;
		for(int n=cnt; n<4; n++) {
			for(int r=1; r<newlock.length-key.length; r++) {
				for(int c=1; c<newlock.length-key.length; c++) {
					for(int i=0; i<key.length; i++) {
						for(int j=0; j<key.length; j++) {
							newlock[r+i][c+j]+=key[i][j];
						}
					}
					 for(int i=0; i<newlock.length; i++) { 
						  	for(int j=0; j<newlock.length; j++) {
						  		System.out.print(newlock[i][j]+" "); 
						  	} 
						  	System.out.println(); 
						  }
					 System.out.println(); 
					if(checkLock(newlock)) { return true; };
					makeNewLock(lock);
					//key복제 끝 검사 진행
					// 결과에 따라 key값 빼기 진행 or true return
				}
			}
			key=rotateKey(key);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] key = {{0,1,0},{0,0,0},{0,1,1}};
		int[][] lock = {{1,1,0},{0,1,0},{1,1,1}};
		lock lock1 = new lock(key,lock);
		lock1.makeNewLock(lock);
		System.out.println(lock1.solution());
	}
}
