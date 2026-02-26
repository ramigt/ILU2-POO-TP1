package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	
	
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
	    			
	    		}else {
	    			return -1;
	    		}
	    		
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
	    			return null;
	    			
	    				}
	    		
	        String afficherMarche(){
	            int nbEtalVide = 0;
	            String chaine="";

	            for (int i = 0; i < etals.length; i++) {
	                if (!etals[i].isEtalOccupe()) {
	                    chaine= etals[i].afficherEtal();
	                } else {
	                    nbEtalVide++;
	                }
	            }

	            if (nbEtalVide > 0) {
	                chaine = chaine + "Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n";
	            }

	            return chaine;
	        }
	        	
	        	
	        	
	        }
	    		
	    	
	    	
	    	
	}
	    	
	   
}

	    
	    
	    
	    	
	 
	
	

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
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
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}