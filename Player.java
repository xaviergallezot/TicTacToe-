import java.util.* ; 


public class Player {
	


	public static void main(String[] args) {
		final char PION1 = 'X' ; 
		final char PION2 = 'O' ; 
		Scanner in = new Scanner(System.in);
        Grille grille = new Grille();
        IA robot = new IA(new Grille());
        boolean jouer = true ;
        int nbtour ; 
        while (!grille.asGagner())
        { 
        	Coordonnee coordonnee ;
        	char pion ; 
        	System.out.println("on rentre") ;
        	grille.afficherGrille(); 
        	
            //grille.afficher();
           if(jouer)
           {
        	   System.out.print("Joueur 1 : Entrez x et y : ");
        	   
        	   int x = in.nextInt();
               int y = in.nextInt();
               coordonnee = new Coordonnee(y, x) ;
               pion = PION1 ; 
               
               
               
           }else
           {
        	   
        	   
        	   coordonnee = robot.cerveau(); 
        	   pion =PION2 ; 
               
              
           }
           grille.jouerUneCase(coordonnee, pion)  ;
           robot.add(coordonnee , pion);
        	jouer =! jouer ;
            
            
        }
        grille.afficherGrille();
        System.out.println(!jouer ? "win j1" : "win j2") ; //if ternaire si c'est true je retourne win J1 else win j2 
        
        

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



class IA
{	
	
	private Grille grille ; 
	
	
	public IA(Grille grille) 
	{
		this.grille = grille ; 
	}
	public void add(Coordonnee coordonnee, char pion ) 
	{
		grille.jouerUneCase(coordonnee, pion);
	}
	public void annuler(Coordonnee coordonne) 
	{
		grille.jouerUneCase(coordonne, grille.getPionNull());
	}
	
	public Coordonnee cerveau() 
	{
		int max_val = -1000 ; 
		Coordonnee meilleur_coup = null ; 
		int profondeur = 5 ; 
		for (int y = 0 ; y< grille.getTaille() ; y++) 
		{
			for(int x = 0 ; x < grille.getTaille(); x++) 
			{
				Coordonnee coordonne = new Coordonnee(x, y); 
				if(grille.getCase(coordonne).getSigne() == grille.getPionNull()) 
				{
					grille.jouerUneCase(coordonne, 'O') ;
					int val = minimum(profondeur);
					if (val > max_val)
					{
						max_val = val ; 
						meilleur_coup = coordonne ; 
					}
					annuler(coordonne) ; 
				} 
			}
		}
		return meilleur_coup ; 
		
	} 
	public int maximum(int profondeur) 
	{
		//TODO fonction maximum
	}
	
	public int minimum(int profondeur) 
	{
		//TODO fonction minimum
	}
	public void rechercher() 
	{
		//parcourir cases de ma grille 
		// ligne par ligne 
		//colonne par colone
		//diagonale 
		//score 
		//methode pour parcourir 
		private int eval()
	    {
	        int score = 0;

	        for (int i = 0; i < grille.getTaille(); i++)
	        {
	            if ( grille.verifLigne(i, 2) == 'O' || grille.verifCol(i, 2) == 'O' ) //faire les methode verif ligne et verif  col 
	                score += 2;
	            else if ( grille.verifLigne(i, 2) == 'X'||  grille.verifCol(i, 2) == 'X' )
	                score -= 2;
	            else
	                score -= 1;

	            if ( grille.verifLigne(i, 3) == 'O'||  grille.verifCol(i, 3) == 'O' )
	                return 1000;
	            else if ( grille.verifLigne(i, 3) == 'X' || grille.verifCol(i, 3) == 'X' )
	                return -1000;
	        }

	        if ( grille.verifDiago1(2) == 'O' ||  grille.verifDiago2(2) == 'O' )
	            score += 2;
	        else if ( grille.verifDiago1(2) == 'X' | grille.verifDiago2(2) == 'X' )
	            score -= 2;
	        else
	            score -= 1;

	        if ( grille.verifDiago1(3) == 'O' || grille.verifDiago2(3) == 'O' )
	            return 1000;
	        else if ( grille.verifDiago1(3) == 'X' || grille.verifDiago2(3) == 'X' )
	            return -1000;

	        return score;
	    }
	}
	public char verifLigne(int x) 
	{
		//TODO reutiliser la condition de victoire pour parcourir et verifier les lignes 
	}
	public char verifCol(int y)
	{
		//TODO reutiliser la condition de victoire pour parcourir et verifier les lignes 
	}
	 
}



class Grille 
{
	private final int TAILLE = 3 ;
	private HashMap<Coordonnee, Case> maGrille = new HashMap<Coordonnee, Case>()  ; 
	private final char VIDE = '-' ; 
	
	
	public Grille() 
	{
		remplir(); 
	} 
	
	public int getTaille() 
	{
		return TAILLE ; 
	}
	public boolean asGagner() 
	{
	
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
	public char getPionNull() 
	{
		return VIDE ; //changer tout les VIDE en get pion null pareil pour taille en gettaille. 
	}
	
	public void jouerUneCase(Coordonnee coordonnee, char pion)
	{
		getCase(coordonnee).setSigne(pion) ;	
	}
	
	
	//cree une grille de la taille de TAILLE et stocke les cases dans la HMAP
	public void remplir() 
	{
		for(int y=0 ; y<TAILLE ; y++) 
		{
			for(int x=0 ; x<TAILLE ; x++ ) 
			{ 
				maGrille.put(new Coordonnee(x, y), new Case(getPionNull()) ) ; //creer un element dans ma hashmap qui a une Coordonne et qui contient une case
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