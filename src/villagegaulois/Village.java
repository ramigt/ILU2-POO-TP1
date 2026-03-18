package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	
	public class Marche {
		private Etal[] etals;
		
		
		
		public Marche(int nb_etals) {
			etals = new Etal[nb_etals];
			for (int i = 0; i < nb_etals; i++) {
	            etals[i] = new Etal();
			}
		}
	        	
	    void utiliserEtal(int indiceEtal, Gaulois vendeur,
				String produit, int nbProduit) {
	    	 etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
	    }
	    
	    int trouverEtalLibre() {
	    	for (int i = 0; i < etals.length ; i++) {
	    		if(!etals[i].isEtalOccupe()) {
	    			return i;
	    			
	    		}
	    	return -1;
	    		
	    	}
	    }
	    	Etal[] trouverEtals(String produit) {
	    	    int compteur = 0;
	    	    for (int i = 0; i < etals.length; i++) {
	    	        if (etals[i].contientProduit(produit)) {
	    	            compteur++;
	    	        }
	    	    }
	    	    Etal[] etalsTrouves = new Etal[compteur];
	    	    int index = 0;
	    	    for (int i = 0; i < etals.length; i++) {
	    	        if (etals[i].contientProduit(produit)) {
	    	            etalsTrouves[index] = etals[i];
	    	            index++;
	    	        }
	    	    }

	    	    return etalsTrouves;
	    	}
	    	
	    	Etal trouverVendeur(Gaulois gaulois) {
	    		for (int i = 0; i < etals.length; i++) {
	    			if(etals[i].getVendeur()==gaulois) {
	    				return etals[i];
	    			}
	    		return null ;
	    			
	    				}
	    		
	        String afficherMarche(){
	            int nbEtalVide = 0;
	            String chaine="";

	            for (int i = 0; i < etals.length; i++) {
	                if (!etals[i].isEtalOccupe()) {
	                    chaine.append(etals[i].afficherEtal());
	                } else {
	                    nbEtalVide++;
	                }
	            }

	            if (nbEtalVide > 0) {
	                chaine = chaine + "Il reste " + nbEtalVide + " √©tals non utilis√©s dans le march√©.\n";
	            }

	            return chaine;
	        
	        	
	        	
	        	
	        }
	    		
	}
	   

	public Village(String nom, int nbVillageoisMaximum,int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		if (chef == null) {
	        throw new VillageSansChefException("Le village n'a pas de chef !");
		}
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les l√©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
	    String chaine = vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + "\n";

	    int indice = marche.trouverEtalLibre();

	    if (indice == -1) {
	        chaine = chaine + "Il n'y a plus d'Ètal libre pour " + vendeur.getNom() + ".\n";
	    } else {
	        marche.utiliserEtal(indice, vendeur, produit, nbProduit);
	        chaine = chaine + "Le vendeur " + vendeur.getNom() + " vend des " + produit + " ‡ l'Ètal n∫" + (indice + 1) + ".\n";
	    }

	    return chaine ;
	}
	    
	    public String rechercherVendeursProduit(String produit) {
	        Etal[] etals = marche.trouverEtals(produit);

	        if (etals.length == 0) {
	            return "Il n'y a pas de vendeur qui propose des " + produit + " au marchÈ.\n";
	        }

	        String chaine = "Les vendeurs qui proposent des " + produit + " sont :\n";

	        for (int i = 0; i < etals.length; i++) {
	            chaine = chaine + "- " + etals[i].getVendeur().getNom() + "\n";
	        }

	        return chaine;
	    }
	    
	    public Etal rechercherEtal(Gaulois vendeur) {
	        return marche.trouverVendeur(vendeur);
	    }
	    public String partirVendeur(Gaulois vendeur) {
	        Etal etal = marche.trouverVendeur(vendeur);

	        if (etal == null) {
	            return vendeur.getNom() + " n'est pas installÈ sur un Ètal.\n";
	        }

	        return etal.libererEtal();
	    }
	    public String afficherMarche() {
	        return marche.afficherMarche();
	    }

	    }

	    
	    
	    


	    
	    
	    
	    
	}
	
	
	
	
	

}