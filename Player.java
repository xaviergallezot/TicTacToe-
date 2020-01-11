import java.util.* ; 


public class Player {

	public static void main(String[] args) {
		final char PION1 = 'X' ; 
		final char PION2 = 'O' ; 
		Scanner in = new Scanner(System.in);
        Grille grille = new Grille();
        IA robot = new IA();
        boolean jouer = true ;  
        while (!grille.asGagner())
        { 
        	System.out.println("on rentre") ;
        	grille.afficherGrille(); 
        	
            //grille.afficher();
           if(jouer)
           {
        	   System.out.print("Joueur 1 : Entrez x et y : ");
               int x = in.nextInt();
               int y = in.nextInt();
               grille.jouerUneCase(new Coordonnee(y, x), PION1);
               
           }else
           {
        	   System.out.print("Joueur 2 : Entrez x et y : ");
               int x = in.nextInt();
               int y = in.nextInt();
               grille.jouerUneCase(new Coordonnee(y, x), PION2)  ;
              
           }
        	jouer =! jouer ;
            
            
        }
        System.out.println("on rentre") ;

	}

}
class Coordonnee
{
	
	private int  abs ; 
	private int  ord ;
	
	public Coordonnee(int x, int y){
		abs = x ;
		ord = y ; 
	}
	public int getAbs() {
		return abs ; 
	}
	
	public int getOrd() {
		return ord ; 
	}
	
	@Override 
	public String toString(){
		return "("+ abs +";"+ ord +")" ;
	}

	@Override
    public boolean equals(Object o) {
        if(o == this)
        {
            return true;
        }
        if (!(o instanceof Coordonnee))
        {
            return false;
        }
        Coordonnee coordonnee = (Coordonnee) o;
        return coordonnee.toString().equals(toString());
    }
	
	@Override
	 public int hashCode() {
        return Objects.hash(toString());
    }
	
}
class Case 
{

	private char signe ;
	
	public Case(char signe) 
	{
		this.signe = signe ; 
	}
	
	public void setSigne(char signe) 
	{
		this.signe = signe ; 
	}
	
	public char getSigne()
	{
		return signe ; 
	}
}


class IA{
	
}

class Grille 
{
	
	private final int TAILLE = 3 ;
	private HashMap<Coordonnee, Case> maGrille = new HashMap<Coordonnee, Case>()  ; 
	private final char VIDE = '-' ; 
	
	
	public Grille() 
	{
		remplir() ; 
	} 
	public boolean asGagner() {
	
	        return ligneWin(TAILLE-1)
	        	|| colWin(TAILLE - 1) 
	        	|| diag1Win()
	        	|| diag2Win() ; 
	             
	    }

	
public boolean ligneWin(int y) // prends une ordonnee
{
	
	if (y <0)
		return false ; 
	int compteur = 1 ; 
	char pion = getCase(new Coordonnee(0, y)).getSigne() ;

	for(int x=1 ; x<TAILLE ; x++) //diagonale 1 coordonnee (0,0 1,1  2,2) donc x=y donc i = x = y 
	{
		char contenu = getCase(new Coordonnee(x, y)).getSigne() ;
		if(pion == contenu && contenu != VIDE) //Pour ne pas que la condition de victoire soit validée quand la grille est vide 
		{
			compteur++ ; 
		}
		pion = contenu ; 
	}
	if(compteur == TAILLE) 
		return true ; 
	return ligneWin(y-1) ;
	
	
}
public boolean colWin(int x) //prends une abscisse 
{
	if(x < 0)
		return false ; 
	int compteur = 1 ; 
	char pion = getCase(new Coordonnee(x, 0)).getSigne() ;

	for(int y=1 ; y<TAILLE ; y++) //diagonale 1 coordonnee (0,0 1,1  2,2) donc x=y donc i = x = y 
	{
		char contenu = getCase(new Coordonnee(x, y)).getSigne() ;
		if(pion == contenu && contenu != VIDE) //Pour ne pas que la condition de victoire soit validée quand la grille est vide 
		{
			compteur++ ; 
		}
		pion = contenu ; 
	}
	if(compteur == TAILLE)
		return true ; 
	return colWin(x - 1) ;	
	
}
public boolean diag1Win() 
{
	int compteur = 1 ; 
	char pion = getCase(new Coordonnee(0, 0)).getSigne() ;

	for(int i=1 ; i<TAILLE ; i++) //diagonale 1 coordonnee (0,0 1,1  2,2) donc x=y donc i = x = y 
	{
		char contenu = getCase(new Coordonnee(i, i)).getSigne() ;
		if(pion == contenu && contenu != VIDE) //Pour ne pas que la condition de victoire soit validée quand la grille est vide 
		{
			compteur++ ; 
		}
		pion = contenu ; 
	}
	if(compteur == TAILLE)
		return true ; 
	return false ;	
	
}

public boolean diag2Win()
{
	int compteur = 1 ; 
	char pion = getCase(new Coordonnee(0, TAILLE-1)).getSigne() ;

	for(int i=1 ; i<TAILLE ; i++) //diagonale 1 coordonnee (0,2 1,1  2,0) donc x=0, 1, 2 donc y = 2, 1, 0 
	{
		char contenu = getCase(new Coordonnee(i, TAILLE-i-1)).getSigne() ;
		if(pion == contenu && contenu != VIDE) //Pour ne pas que la condition de victoire soit validée quand la grille est vide 
		{
			compteur++ ; 
		}
		pion = contenu ; 
	}
	if(compteur == TAILLE)
		return true ; 
	return false ;	
	
}

	
	public Case getCase(Coordonnee coordonnee) 
	{
		return maGrille.get(coordonnee) ; 	
	}
	
	public void jouerUneCase(Coordonnee coordonnee, char pion)
	{
		getCase(coordonnee).setSigne(pion) ;	
	}
	
	
	//cree une grille de la taille de TAILLE et stocke les cases dans la HMAP
	public void remplir() 
	{
		for(int y=0 ; y<TAILLE ; y++) {
			for(int x=0 ; x<TAILLE ; x++ ) { 
				maGrille.put(new Coordonnee(x, y), new Case(VIDE) ) ; //creer un element dans ma hashmap qui a une Coordonne et qui contient une case
			}
		}
	}
	
	public void afficherGrille() //permet d'afficher le contenu de la HashMap
	{
	    {
	        System.out.print("  ");
	        for (int i = 0; i < TAILLE ; i++)
	        {
	            System.out.print(i + " ");
	        }
	        System.out.println();
	        for (int y = 0; y < TAILLE ; y++)
	        {
	            for (int x = 0; x < TAILLE ; x++)
	            {
	                if(x == 0)
	                {
	                    System.out.print(y + " ");
	                }
	                System.out.print(getCase(new Coordonnee(x, y)).getSigne() + " ");
	            }
	            System.out.println();
	        }
	    }
	}
	
}