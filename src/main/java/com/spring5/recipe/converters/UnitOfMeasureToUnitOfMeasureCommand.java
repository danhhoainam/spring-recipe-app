package com.spring5.recipe.converters;

import com.spring5.recipe.commands.UnitOfMeasureCommand;
import com.spring5.recipe.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure source) {

		if (source == null) {
			return null;
		}

		final UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
		uom.setId(source.getId());
		uom.setDescription(source.getDescription());

		return uom;
	}
}
