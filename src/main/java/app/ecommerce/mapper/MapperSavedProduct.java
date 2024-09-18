package app.ecommerce.mapper;
import org.springframework.stereotype.Component;

import app.ecommerce.controller.DTO.SavedProductDto;
import app.ecommerce.model.SavedProduct;
@Component
public class MapperSavedProduct {



	
	public SavedProduct mapper(SavedProductDto dto) {
		SavedProduct resultat = new SavedProduct();
		
		resultat.setIdProduct(dto.getIdProduct());
		resultat.setIdCompte(dto.getIdCompte());
		resultat.setQuantity(dto.getQuantity());
		return resultat;
		
	}

}