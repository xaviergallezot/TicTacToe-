import java.util.* ; 


public class Player {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
class Action {
    int col ;
    int row ;
    
    Action(int col, int row) {
        this.col = col ;
        this.row = row ;
    }
    

}
class Coordonnee{
	private int  abs ; 
	private int  ord ;
	
	
}
class Case {

	private char signe ; 
}
class IA{}

class Grille {
	private static int taille = 3 ;
	private HashMap<Coordonnee, Case> maGrille = new HashMap<Coordonnee, Case>()  ; 
}

class Partie {
    
    public int[][] Grille;
    Partie(){
        Grille=new int[3][3];
    }
    public void setGrille(int Row, int col)
    {
        this.Grille[Row][col] = 1; 
    }
    public void jouer(Action a){
        this.Grille[a.row][a.col]=1 ; 
    }
    
    public boolean estpossible(Action action)
    {
        return (this.Grille[action.row][action.col]==0) ;
    }
    
    public List<Action> actionpossible(Action action){
        List<Action> L=new Vector<Action>();
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                int row=i;
                int col=j;
                Action a= new Action(row,col);
                if (estpossible(a)){
                    L.add(a);
                }
            }
        }
        return L;
    }
    
    
}