import java.util.* ; 


public class Player {

	public static void main(String[] args) {
		final char PION1 = 'X' ; 
		final char PION2 = 'O' ; 
		Scanner in = new Scanner(System.in);
        Grille grille = new Grille();
        IA robot = new IA();
        while (true) 
        { //TODO CONDITION DE FIN DE PARTIE
        	grille.afficherGrille(); 
        	
            //grille.afficher();
            System.out.print("Joueur 1 : Entrez x et y : ");
            int x = in.nextInt();
            int y = in.nextInt();
            grille.jouerUneCase(new Coordonnee(x, y), PION1);
        	grille.afficherGrille();
            System.out.print("Joueur 2 : Entrez x et y : ");
            
             x = in.nextInt();
             y = in.nextInt();
            grille.jouerUneCase(new Coordonnee(x,y), PION2)  ;
        }

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
	
	private static int TAILLE = 3 ;
	private HashMap<Coordonnee, Case> maGrille = new HashMap<Coordonnee, Case>()  ; 
	private static char VIDE = '-' ; 
	
	
	public Grille() 
	{
		remplir() ; 
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