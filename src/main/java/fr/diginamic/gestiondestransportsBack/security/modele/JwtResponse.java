package fr.diginamic.gestiondestransportsBack.security.modele;

import lombok.Data;

@Data
public class JwtResponse {

    private String jwtToken;

	public JwtResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
    
    
}
