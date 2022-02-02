package fr.diginamic.gestiondestransportsBack.modeles.enums;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RolePersonConverter implements AttributeConverter<RolePerson, Integer> {

	@Override
	public Integer convertToDatabaseColumn(RolePerson role) {
		if (role == null) {
			return null;
		}
		return role.getRole();
	}

	@Override
	public RolePerson convertToEntityAttribute(Integer code) {
		if (code == null) {
			return null;
		}

		return Stream.of(RolePerson.values()).filter(c -> c.getRole().equals(code)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
