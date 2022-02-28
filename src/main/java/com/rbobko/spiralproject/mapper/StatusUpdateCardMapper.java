package com.rbobko.spiralproject.mapper;

import com.rbobko.spiralproject.model.StatusUpdateCard;
import com.rbobko.spiralproject.model.dto.StatusUpdateCardUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusUpdateCardMapper {

    StatusUpdateCard toEntity(StatusUpdateCardUpdateDTO dto);

}
